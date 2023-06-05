package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;
import static it.unibo.donkeykong.utilities.Constants.Window.SCALED_TILES_SIZE;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.model.ecs.impl.HealthComponent;
import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Constants.Monkey;
import it.unibo.donkeykong.utilities.Constants.Player;
import it.unibo.donkeykong.utilities.Constants.PowerupAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.view.api.Button;
import it.unibo.donkeykong.view.api.View;

/**
 * Game view, manages game graphics.
 */
public class GameView implements View {

    private final GameController gameController;

    private final Button settingsPauseButton;
    private Map<Button, BufferedImage> buttons = new HashMap<>();
    
    /**
     * Constructor.
     * 
     * @param gameController the linked controller.
     */
    public GameView(final GameController gameController) {
        this.gameController = gameController;

        this.settingsPauseButton = new ButtonImpl(Window.GAME_WIDTH - SCALED_TILES_SIZE - Window.TILES_DEFAULT_SIZE, 
                                                  Window.TILES_DEFAULT_SIZE, 
                                                  SCALED_TILES_SIZE, 
                                                  SCALED_TILES_SIZE, Gamestate.PAUSE);

        buttons.put(settingsPauseButton, getSettingsSources().get(SettingsAssets.roundedSettingsButton));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Graphics g) {
        this.gameController.getDataLevelFromModel()
                           .forEach((tile, sprite) -> g.drawImage(sprite, 
                                                                  tile.x, 
                                                                  tile.y, 
                                                                  tile.width, 
                                                                  tile.height, null));
        g.drawImage(LevelAssets.barrelBox, 
                    0, 
                    Math.round(Monkey.levelOneStartingMonkeyY + Monkey.monkeyHeight - Barrel.barrelBoxHeight), 
                    Barrel.barrelBoxWidth, 
                    Barrel.barrelBoxHeight, null);
        this.drawLives(g);
        this.drawActivePowerUps(g);
        this.buttons.forEach((b, bi) -> g.drawImage(bi, 
                                                    b.getButtonPos().getX(),
                                                    b.getButtonPos().getY(), 
                                                    b.getButtonDim().getX(), 
                                                    b.getButtonDim().getY(), null));
        this.gameController.getEntitiesFromGameplay()
                           .stream()
                           .filter(e -> e.getEntityType() == Type.STAR
                                        || e.getEntityType() == Type.HEART
                                        || e.getEntityType() == Type.SNOWFLAKE
                                        || e.getEntityType() == Type.SHIELD)
                           .forEach(e -> g.drawImage(this.getPowerUpSprite(e.getEntityType()),
                                                     Math.round(e.getPosition().getX()), 
                                                     Math.round(e.getPosition().getY()),
                                                     e.getWidth(),
                                                     e.getHeight(), null));
        this.gameController.getInteractableEntitiesFromGameplay()
                           .forEach(entity -> this.drawEntity(g, entity));

        /**
         * Draw hitboxes.
         */
        // this.gameController.getEntitiesFromGameplay().forEach(e -> {
        //     final Rectangle2D r = e.getComponent(CollisionComponent.class).get().getHitbox();
        //     g.setColor(java.awt.Color.GREEN);
        //     g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
        // });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final Pair<Integer,Integer> point) {
        this.buttons.keySet().forEach(b -> {
            if (b.getCorners().contains(new Point(point.getX(), point.getY()))) {
                if (b.getButtonGamestate() == Gamestate.PAUSE) {
                    this.gameController.pauseTimer();
                }
                this.gameController.applyGamestate(b.getButtonGamestate());
            }
        });
    }

    /**
     * Update animations' indexes.
     */
    public void update() {
        this.gameController.updateAniIndex();
    }

    private void drawEntity(final Graphics g, final Entity entity) {
        g.drawImage(this.getSprite(entity), 
                    Math.round(entity.getPosition().getX()), 
                    Math.round(entity.getPosition().getY()), 
                    entity.getWidth(), 
                    entity.getHeight(), null);
    }

    private BufferedImage getSprite(final Entity entity) {
        final Pair<Integer, Integer> anim = this.gameController.getIdleFromModel(entity);
        return this.gameController.getAnimationFromModel(entity.getEntityType(), anim.getX(), anim.getY());
    }

    private void drawLives(final Graphics g) {
        this.gameController
            .getInteractableEntitiesFromGameplay()
            .stream()
            .filter(e -> e.getEntityType() == Type.PLAYER)
            .findFirst()
            .ifPresent(e -> {
                final int lives = e.getComponent(HealthComponent.class).get().getLives();
                IntStream.range(0, lives).forEach(i -> {
                 g.drawImage(PowerupAssets.getPowerupSources().get(PowerupAssets.life),
                             Window.GAME_WIDTH - Window.SCALED_TILES_SIZE * (Player.numLives + i) + PowerupAssets.lifePadding,
                             PowerupAssets.lifePadding,
                             PowerupAssets.lifeDimension, 
                             PowerupAssets.lifeDimension, null);
                });
                IntStream.range(lives, Player.numLives).forEach(i -> {
                g.drawImage(PowerupAssets.getPowerupSources().get(PowerupAssets.noLife),
                             Window.GAME_WIDTH - Window.SCALED_TILES_SIZE * (Player.numLives + i) + PowerupAssets.lifePadding,
                             PowerupAssets.lifePadding,
                             PowerupAssets.lifeDimension, 
                             PowerupAssets.lifeDimension, null);
                });
            });
    }

    private BufferedImage getPowerUpSprite(final Type powerup) {
        if (powerup == Type.HEART) {
            return PowerupAssets.getPowerupSources().get(PowerupAssets.heart);
        } else if (powerup == Type.SHIELD) {
            return PowerupAssets.getPowerupSources().get(PowerupAssets.shield);
        } else if (powerup == Type.SNOWFLAKE) {
            return PowerupAssets.getPowerupSources().get(PowerupAssets.freeze);
        }
        return PowerupAssets.getPowerupSources().get(PowerupAssets.star);
    }

    private void drawActivePowerUps(final Graphics g) {
        final List<Type> activePowerUps = this.gameController.getListOfActivePowerUps();
                IntStream.range(0, activePowerUps.size()).forEach(i -> {
                    g.drawImage(PowerupAssets.getPowerupSources().get(PowerupAssets.emptyBorder), 
                                i * Window.SCALED_TILES_SIZE + PowerupAssets.powerupBorderPadding,
                                PowerupAssets.powerupBorderPadding, 
                                PowerupAssets.powerupBorderDimension, 
                                PowerupAssets.powerupBorderDimension, null);
                    g.drawImage(getPowerUpSprite(activePowerUps.get(i)),
                                i * Window.SCALED_TILES_SIZE + PowerupAssets.powerupBorderPadding 
                                                               + PowerupAssets.powerupActivePadding,
                                PowerupAssets.powerupBorderPadding + PowerupAssets.powerupActivePadding, 
                                PowerupAssets.powerupActiveDimension, 
                                PowerupAssets.powerupActiveDimension, null);
                });
    }
}
