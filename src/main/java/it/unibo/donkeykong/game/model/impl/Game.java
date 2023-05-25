package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.levelOne;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.getSettingsSources;
import static it.unibo.donkeykong.utilities.Constants.Window.SCALED_TILES_SIZE;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.game.model.api.Button;
import it.unibo.donkeykong.game.model.api.Level;
import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Constants.Barrel;
import it.unibo.donkeykong.utilities.Constants.Monkey;
import it.unibo.donkeykong.utilities.Constants.Player;
import it.unibo.donkeykong.utilities.Constants.Princess;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.utilities.Type;

/**
 * Game model (model of gameview like buttons etc...).
 */
public class Game implements ViewModel {

    private final Level level;
    private final Map<Rectangle, BufferedImage> dataLevel = new HashMap<>();
    private final Button settingsPauseButton;
    private BufferedImage[][] playerMovementAni = new BufferedImage[Player.movAniRC.getX()][Player.movAniRC.getY()];
    private BufferedImage[] monkeyAni = new BufferedImage[Monkey.monkeyAniSprites];
    private BufferedImage[][] barrelAni = new BufferedImage[Barrel.numBarrel][Barrel.barrelAniSprites];
    private BufferedImage[][] princessAni = new BufferedImage[Princess.numPrincessAni][Princess.princessAniSprites];

    /**
     * Constructor.
     */
    public Game() {
        this.level = new LevelImpl(levelOne);
        this.mapDataLevel();
        this.settingsPauseButton = new ButtonImpl(Window.GAME_WIDTH - SCALED_TILES_SIZE - Window.TILES_DEFAULT_SIZE, 
                                                  Window.TILES_DEFAULT_SIZE, 
                                                  SCALED_TILES_SIZE, 
                                                  SCALED_TILES_SIZE, Gamestate.PAUSE);
        this.bufferAnimations();
    }

    /**
     * Load all the animations.
     * 
     * PlayerMovementAni contains run and jump animation for the player, row 0 and 1 are for run, 2 and 3 for jump.
     * First one of each is the left facing.
     * 
     * MonkeyAni contains the animation for the monkey
     * 
     * BarrelAni contains the animation for the barrel, first row is for normal barrel, second row for double damage barrel.
     *
     * PrincessAni contains the animation for the princess.
     */
    private void bufferAnimations() {
        for (int r = 0; r < Player.movAniRC.getX(); r++) {
            for (int c = 0; c < Player.movAniRC.getY(); c++) {
                playerMovementAni[r][c] = Player.getPlayerSources()
                                                .get(Player.movementAni)
                                                .getSubimage(c * Player.spriteDimension, 
                                                             r * Player.spriteDimension, 
                                                             Player.spriteDimension, 
                                                             Player.spriteDimension);
            }
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

    private void mapDataLevel() {
        final Map<Pair<Integer, Integer>, Integer> lvl = this.level.getLevelData();
        for (int r = 0; r < Window.TILES_IN_HEIGHT; r++) {
            for (int c = 0; c < Window.TILES_IN_WIDTH; c++) {
                this.dataLevel.put(new Rectangle(SCALED_TILES_SIZE * r, 
                                                 SCALED_TILES_SIZE * c, 
                                                 SCALED_TILES_SIZE, 
                                                 SCALED_TILES_SIZE),
                                   this.level.getLevelSprite(lvl.get(new Pair<>(r, c))));
            }
        }
    }

    /**
     * This method is used by the GameView to get the data of the level to be drawn.
     * 
     * @return a map containing the data of the level to be drawn.
     */
    public Map<Rectangle, BufferedImage> getDataLevel() {
        return new HashMap<>(this.dataLevel);
    }

    @Override
    public final Map<Button, BufferedImage> getButtons() {
        return new HashMap<>() {{
            put(settingsPauseButton, getSettingsSources().get(SettingsAssets.roundedSettingsButton));
        }};
    }

    @Override
    public final Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>();
    }

    public BufferedImage getEntityAni(final Type type, final int row, final int col) {
        if(type == Type.PLAYER) {
            return this.playerMovementAni[row][col];
        }
        if(type == Type.MONKEY) {
            return this.monkeyAni[col];
        }
        if(type == Type.BARREL) {
            return this.barrelAni[row][col];
        }
        return this.princessAni[row][col];
    }
}
