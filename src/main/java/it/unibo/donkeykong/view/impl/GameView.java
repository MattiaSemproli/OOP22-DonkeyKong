package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.SettingsAssets.getSettingsSources;
import static it.unibo.donkeykong.utilities.ViewConstants.Window.SCALED_TILES_SIZE;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.controller.impl.GameController;
import it.unibo.donkeykong.model.ecs.api.Entity;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Type;
import it.unibo.donkeykong.utilities.ViewConstants.BarrelAssets;
import it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.LevelAssets;
import it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.ViewConstants.MonkeyAssets;
import it.unibo.donkeykong.utilities.ViewConstants.PlayerAssets;
import it.unibo.donkeykong.utilities.ViewConstants.PowerupAssets;
import it.unibo.donkeykong.utilities.ViewConstants.PrincessAssets;
import it.unibo.donkeykong.utilities.ViewConstants.Window;
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
    private BufferedImage[][] playerMovementAni, barrelAni, princessAni;
    private BufferedImage[] monkeyAni;

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
        g.drawImage(LevelAssets.BARRELBOX, 
                    0, 
                    Math.round(LevelAssets.BARREL_BOX_Y - LevelAssets.BARREL_BOX_HEIGHT), 
                    LevelAssets.BARREL_BOX_WIDTH, 
                    LevelAssets.BARREL_BOX_HEIGHT, null);
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
            g.drawImage(PowerupAssets.getPowerupSources().get(PowerupAssets.LIFE),
                        Window.GAME_WIDTH - SCALED_TILES_SIZE * (PlayerAssets.NUM_LIVES + i) + PowerupAssets.LIFE_PADDING,
                        PowerupAssets.LIFE_PADDING,
                        PowerupAssets.LIFE_DIMENSION, 
                        PowerupAssets.LIFE_DIMENSION, null);
        });
        IntStream.range(lives, PlayerAssets.NUM_LIVES).forEach(i -> {
        g.drawImage(PowerupAssets.getPowerupSources().get(PowerupAssets.NOLIFE),
                    Window.GAME_WIDTH - SCALED_TILES_SIZE * (PlayerAssets.NUM_LIVES + i) + PowerupAssets.LIFE_PADDING,
                    PowerupAssets.LIFE_PADDING,
                    PowerupAssets.LIFE_DIMENSION, 
                    PowerupAssets.LIFE_DIMENSION, null);
        });
    }

    private BufferedImage getPowerUpSprite(final Type powerup) {
        if (powerup == Type.HEART) {
            return PowerupAssets.getPowerupSources().get(PowerupAssets.HEART);
        } else if (powerup == Type.SHIELD) {
            return PowerupAssets.getPowerupSources().get(PowerupAssets.SHIELD);
        } else if (powerup == Type.SNOWFLAKE) {
            return PowerupAssets.getPowerupSources().get(PowerupAssets.FREEZE);
        }
        return PowerupAssets.getPowerupSources().get(PowerupAssets.STAR);
    }

    private void drawActivePowerUps(final Graphics g) {
        final List<Type> activePowerUps = this.gameController.getListOfActivePowerUps();
        IntStream.range(0, activePowerUps.size()).forEach(i -> {
            g.drawImage(PowerupAssets.getPowerupSources().get(PowerupAssets.EMPTYBORDER), 
                        i * SCALED_TILES_SIZE + PowerupAssets.PUPS_BODER_PADDING,
                        PowerupAssets.PUPS_BODER_PADDING, 
                        PowerupAssets.PUPS_BORDER_DIMENSION, 
                        PowerupAssets.PUPS_BORDER_DIMENSION, null);
            g.drawImage(getPowerUpSprite(activePowerUps.get(i)),
                        i * SCALED_TILES_SIZE + PowerupAssets.PUPS_BODER_PADDING + PowerupAssets.PUPS_ACTIVE_PADDING,
                        PowerupAssets.PUPS_BODER_PADDING + PowerupAssets.PUPS_ACTIVE_PADDING, 
                        PowerupAssets.PUPS_ACTIVE_DIMENSION, 
                        PowerupAssets.PUPS_ACTIVE_DIMENSION, null);
        });
    }
    private void bufferAnimations() {
        playerMovementAni = new BufferedImage[PlayerAssets.NUM_MOVEMENT_ANI][PlayerAssets.MOVEMENT_ANI_SPRITES];
        monkeyAni = new BufferedImage[MonkeyAssets.MONKEY_ANI_SPRITES];
        barrelAni = new BufferedImage[BarrelAssets.NUM_BARREL][BarrelAssets.BARREL_ANI_SPRITES];
        princessAni = new BufferedImage[PrincessAssets.NUM_PRINCESS_ANI][PrincessAssets.PRINCESS_ANI_SPRITES];
        for (int r = 0; r < PlayerAssets.NUM_MOVEMENT_ANI - 1; r++) {
            for (int c = 0; c < PlayerAssets.MOVEMENT_ANI_SPRITES; c++) {
                playerMovementAni[r][c] = PlayerAssets.getPlayerSources()
                                                .get(PlayerAssets.MOVEMENT_ANI)
                                                .getSubimage(c * PlayerAssets.SPRITE_DIMENSION, 
                                                             r * PlayerAssets.SPRITE_DIMENSION, 
                                                             PlayerAssets.SPRITE_DIMENSION, 
                                                             PlayerAssets.SPRITE_DIMENSION);
            }
        }
        for (int c = 0; c < PlayerAssets.CLIMB_ANI_SPRITES; c++) {
            playerMovementAni[PlayerAssets.CLIMB_ANI][c] = PlayerAssets.getPlayerSources()
                                                          .get(PlayerAssets.CLIMBING_ANI)
                                                          .getSubimage(c * PlayerAssets.SPRITE_DIMENSION, 
                                                                       0, 
                                                                       PlayerAssets.SPRITE_DIMENSION, 
                                                                       PlayerAssets.SPRITE_DIMENSION);
        }
        for (int c = 0; c < monkeyAni.length; c++) {
            monkeyAni[c] = MonkeyAssets.getMonkeySources()
                                 .get(MonkeyAssets.MONKEY_ANI)
                                 .getSubimage(c * MonkeyAssets.SPRITE_WIDTH, 
                                              0, 
                                              MonkeyAssets.SPRITE_WIDTH, 
                                              MonkeyAssets.SPRITE_HEIGHT);
        }
        for (int c = 0; c < BarrelAssets.BARREL_ANI_SPRITES; c++) {
            barrelAni[BarrelAssets.BARREL_ANI][c] = BarrelAssets.getBarrelSources()
                                                   .get(BarrelAssets.BARREL_ANI)
                                                   .getSubimage(c * BarrelAssets.SPRITE_WIDTH,
                                                                0, 
                                                                BarrelAssets.SPRITE_WIDTH, 
                                                                BarrelAssets.SPRITE_HEIGHT);
            barrelAni[BarrelAssets.DD_BARREL_ANI][c] = BarrelAssets.getBarrelSources()
                                                     .get(BarrelAssets.DD_BARREL_ANI)
                                                     .getSubimage(c * BarrelAssets.SPRITE_WIDTH,
                                                                  0, 
                                                                  BarrelAssets.SPRITE_WIDTH, 
                                                                  BarrelAssets.SPRITE_HEIGHT);
        }
        for (int r = 0; r < PrincessAssets.NUM_PRINCESS_ANI; r++) {
            for (int c = 0; c < PrincessAssets.PRINCESS_ANI_SPRITES; c++) {
                princessAni[r][c] = PrincessAssets.getPrincessSources()
                                         .get(PrincessAssets.PRINCESS_ANI)
                                         .getSubimage(c * PrincessAssets.SPRITE_WIDTH,
                                                      r * PrincessAssets.SPRITE_HEIGHT, 
                                                      PrincessAssets.SPRITE_WIDTH, 
                                                      PrincessAssets.SPRITE_HEIGHT);
            }
        }
    }
}
