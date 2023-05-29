package it.unibo.donkeykong.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.game.ecs.api.Entity;
import it.unibo.donkeykong.game.ecs.impl.HealthComponent;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Constants.Monkey;
import it.unibo.donkeykong.utilities.Constants.Player;
import it.unibo.donkeykong.utilities.Constants.PowerupAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets;

/**
 * Game view.
 */
public class GameView implements GameEngine {

    private final GameController gameController;

    /**
     * 
     * @param gameController set the controller to this view.
     */
    public GameView(final GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public final void update() {
        this.gameController.updateAniIndex();
    }

    @Override
    public final void draw(final Graphics g) {
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
        this.gameController.getButtonsFromModel()
                           .forEach((b, i) -> g.drawImage(i, 
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

    private void drawEntity(final Graphics g, final Entity entity) {
        g.drawImage(this.getSprite(entity), 
                    Math.round(entity.getPosition().getX()), 
                    Math.round(entity.getPosition().getY()), 
                    entity.getWidth(), 
                    entity.getHeight(), null);
    }

    private BufferedImage getSprite(final Entity entity) {
        Pair<Integer, Integer> anim = this.gameController.getIdleFromModel(entity);
        return this.gameController.getAnimationFromModel(entity.getEntityType(), anim.getX(), anim.getY());
    }

    private void drawLives(final Graphics g) {
        this.gameController
            .getInteractableEntitiesFromGameplay()
            .stream()
            .filter(e -> e.getEntityType() == Type.PLAYER)
            .findFirst()
            .ifPresent(e -> {
                int lives = e.getComponent(HealthComponent.class).get().getLifes();
                IntStream.range(0, lives).forEach(i -> {
                 g.drawImage(PowerupAssets.getPowerupSources().get(PowerupAssets.life),
                             Window.GAME_WIDTH - Window.SCALED_TILES_SIZE * (Player.numLifes + i) + PowerupAssets.lifePadding,
                             PowerupAssets.lifePadding,
                             PowerupAssets.lifeDimension, 
                             PowerupAssets.lifeDimension, null);
                });
                IntStream.range(lives, Player.numLifes).forEach(i -> {
                g.drawImage(PowerupAssets.getPowerupSources().get(PowerupAssets.noLife),
                             Window.GAME_WIDTH - Window.SCALED_TILES_SIZE * (Player.numLifes + i) + PowerupAssets.lifePadding,
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
}
