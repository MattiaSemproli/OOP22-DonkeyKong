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
import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.LevelAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Constants.Monkey;
import it.unibo.donkeykong.utilities.Constants.Player;
import it.unibo.donkeykong.utilities.Constants.PowerupAssets;
import it.unibo.donkeykong.utilities.Constants.Princess;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.view.api.Button;
import it.unibo.donkeykong.view.api.Level;
import it.unibo.donkeykong.view.api.View;

/**
 * Game view, manages game graphics.
 */
public class GameView implements View {

    private final GameController gameController;

    private final Level level;
    private final Map<Button, BufferedImage> buttons = new HashMap<>();
    private BufferedImage[][] playerMovementAni = new BufferedImage[Player.numMovementAni][Player.movementAniSprites];
    private BufferedImage[] monkeyAni = new BufferedImage[Monkey.monkeyAniSprites];
    private BufferedImage[][] barrelAni = new BufferedImage[Barrel.numBarrel][Barrel.barrelAniSprites];
    private BufferedImage[][] princessAni = new BufferedImage[Princess.numPrincessAni][Princess.princessAniSprites];

    /**
     * Constructor.
     * 
     * @param gameController the linked controller.
     */
    public GameView(final GameController gameController) {
        this.gameController = gameController;
        this.level = new LevelImpl();
        this.bufferAnimations();

        final Button settingsPauseButton = new ButtonImpl(Window.GAME_WIDTH - SCALED_TILES_SIZE - Window.TILES_DEFAULT_SIZE,
                                                          Window.TILES_DEFAULT_SIZE, 
                                                          SCALED_TILES_SIZE, 
                                                          SCALED_TILES_SIZE, Gamestate.PAUSE);

        buttons.put(settingsPauseButton, getSettingsSources().get(SettingsAssets.ROUNDED_SETTINGS_BUTTON));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Graphics g) {
        this.level.getLevelData()
                  .forEach((pos, val) -> {
                      g.drawImage(level.getLevelSprite(val), 
                                  SCALED_TILES_SIZE * pos.getX(), 
                                  SCALED_TILES_SIZE * pos.getY(), 
                                  SCALED_TILES_SIZE, 
                                  SCALED_TILES_SIZE, null);
                  });
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
        this.gameController.getPowerupEntitiesFromGameplay()
                           .forEach(e -> g.drawImage(this.getPowerUpSprite(e.getEntityType()),
                                                     Math.round(e.getPosition().getX()), 
                                                     Math.round(e.getPosition().getY()),
                                                     e.getWidth(),
                                                     e.getHeight(), null));
        this.gameController.getInteractableEntitiesFromGameplay()
                           .forEach(entity -> this.drawEntity(g, entity));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final Pair<Integer, Integer> point) {
        this.buttons.keySet().forEach(b -> {
            if (b.getCorners().contains(new Point(point.getX(), point.getY()))) {
                if (b.getButtonGamestate() == Gamestate.PAUSE) {
                    this.gameController.notifyResetKeys();
                    this.gameController.pauseTimer();
                }
                this.gameController.applyGamestate(b.getButtonGamestate());
            }
        });
    }

    /**
     * {@inheritDoc} 
     */
    @Override
    public void keyPressed(final int keyCode) {
        this.gameController.notifyKeyPressed(keyCode);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final int keyCode) {
        this.gameController.notifyKeyReleased(keyCode);
    }

    /**
     * Update animations' indexes.
     */
    public void update() {
        this.gameController.updateAniIndex();
    }

    /**
     * Get the level data map.
     * 
     * @return map of pos and value.
     */
    public Map<Pair<Integer, Integer>, Integer> getLevelMap() {
        return this.level.getLevelData();
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
        return this.getEntityAni(entity.getEntityType(), anim.getX(), anim.getY());
    }

    private BufferedImage getEntityAni(final Type type, final int row, final int col) {
        if (type == Type.PLAYER) {
            return this.playerMovementAni[row][col];
        }
        if (type == Type.MONKEY) {
            return this.monkeyAni[col];
        }
        if (type == Type.BARREL) {
            return this.barrelAni[row][col];
        }
        return this.princessAni[row][col];
    }

    private void drawLives(final Graphics g) {
        final int lives = this.gameController.getPlayerLives();
        IntStream.range(0, lives).forEach(i -> {
            g.drawImage(PowerupAssets.getPowerupSources().get(PowerupAssets.life),
                        Window.GAME_WIDTH - SCALED_TILES_SIZE * (Player.numLives + i) + PowerupAssets.lifePadding,
                        PowerupAssets.lifePadding,
                        PowerupAssets.lifeDimension, 
                        PowerupAssets.lifeDimension, null);
        });
        IntStream.range(lives, Player.numLives).forEach(i -> {
        g.drawImage(PowerupAssets.getPowerupSources().get(PowerupAssets.noLife),
                    Window.GAME_WIDTH - SCALED_TILES_SIZE * (Player.numLives + i) + PowerupAssets.lifePadding,
                    PowerupAssets.lifePadding,
                    PowerupAssets.lifeDimension, 
                    PowerupAssets.lifeDimension, null);
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
                        i * SCALED_TILES_SIZE + PowerupAssets.powerupBorderPadding,
                        PowerupAssets.powerupBorderPadding, 
                        PowerupAssets.powerupBorderDimension, 
                        PowerupAssets.powerupBorderDimension, null);
            g.drawImage(getPowerUpSprite(activePowerUps.get(i)),
                        i * SCALED_TILES_SIZE + PowerupAssets.powerupBorderPadding 
                                                        + PowerupAssets.powerupActivePadding,
                        PowerupAssets.powerupBorderPadding + PowerupAssets.powerupActivePadding, 
                        PowerupAssets.powerupActiveDimension, 
                        PowerupAssets.powerupActiveDimension, null);
        });
    }
    private void bufferAnimations() {
        for (int r = 0; r < Player.numMovementAni - 1; r++) {
            for (int c = 0; c < Player.movementAniSprites; c++) {
                playerMovementAni[r][c] = Player.getPlayerSources()
                                                .get(Player.movementAni)
                                                .getSubimage(c * Player.spriteDimension, 
                                                             r * Player.spriteDimension, 
                                                             Player.spriteDimension, 
                                                             Player.spriteDimension);
            }
        }
        for (int c = 0; c < Player.climbingAniSprites; c++) {
            playerMovementAni[Player.climbAni][c] = Player.getPlayerSources()
                                                          .get(Player.climbingAni)
                                                          .getSubimage(c * Player.spriteDimension, 
                                                                       0, 
                                                                       Player.spriteDimension, 
                                                                       Player.spriteDimension);
        }
        for (int c = 0; c < monkeyAni.length; c++) {
            monkeyAni[c] = Monkey.getMonkeySources()
                                 .get(Monkey.monkeyAni)
                                 .getSubimage(c * Monkey.spriteWidth, 
                                              0, 
                                              Monkey.spriteWidth, 
                                              Monkey.spriteHeight);
        }
        for (int c = 0; c < Barrel.barrelAniSprites; c++) {
            barrelAni[Barrel.barrelAni][c] = Barrel.getBarrelSources()
                                                   .get(Barrel.barrelAni)
                                                   .getSubimage(c * Barrel.spriteWidth,
                                                                0, 
                                                                Barrel.spriteWidth, 
                                                                Barrel.spriteHeight);
            barrelAni[Barrel.ddBarrelAni][c] = Barrel.getBarrelSources()
                                                     .get(Barrel.ddBarrelAni)
                                                     .getSubimage(c * Barrel.spriteWidth,
                                                                  0, 
                                                                  Barrel.spriteWidth, 
                                                                  Barrel.spriteHeight);
        }
        for (int r = 0; r < Princess.numPrincessAni; r++) {
            for (int c = 0; c < Princess.princessAniSprites; c++) {
                princessAni[r][c] = Princess.getPrincessSources()
                                         .get(Princess.princessAni)
                                         .getSubimage(c * Princess.spriteWidth,
                                                      r * Princess.spriteHeight, 
                                                      Princess.spriteWidth, 
                                                      Princess.spriteHeight);
            }
        }
    }
}
