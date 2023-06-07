package it.unibo.donkeykong.utilities;

import static it.unibo.donkeykong.utilities.ResourceFuncUtilities.loadSources;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Constants class.
 */
public class Constants {

    /**
     * Window general constants.
     */
    public static class Window {
        /**
         * Tile default size in pixels.
         */
        public static final int TILES_DEFAULT_SIZE = 16;
        /**
         * Application scale multiplier.
         */
        public static final float SCALE = 1f;
        /**
         * Number of tiles in width.
         */
        public static final int TILES_IN_WIDTH = 14;
        /**
         * Number of tiles in height.
         */
        public static final int TILES_IN_HEIGHT = 14;
        /**
         * Scaled tile size in pixels.
         */
        public static final int SCALED_TILES_SIZE = Math.round(48 * SCALE);
        /**
         * Game width.
         */
        public static final int GAME_WIDTH = SCALED_TILES_SIZE * TILES_IN_WIDTH;
        /**
         * Game height.
         */
        public static final int GAME_HEIGHT = SCALED_TILES_SIZE * TILES_IN_HEIGHT;
    }

    /**
     * GameLoop constants.
     */
    public static class GameLoop {
        /**
         * FPS capslock;
         */
        public static final int FPS_SET = 120;
        /**
         * Nanoseconds in a second.
         */
        public static final double NANOSECOND = 1_000_000_000.0;
        /**
         * Starting frame value.
         */
        public static final int FRAME_DEFAULT = 0;
        /**
         * Starting delta value.
         */
        public static final int DELTAF_DEFAULT = 0;
    }

    /**
     * Menu graphics constants.
     */
    public static final class MenuAssets {
        /**
         * Application title.
         */
        public static final String TITLE = "title";
        /**
         * Menu background string source.
         */
        public static final String MENU_BACKGROUND = "menu_background";
        /**
         * Menu layout string source.
         */
        public static final String MENU_TEXTURE = "menu_layout";
        /**
         * Play button string source.
         */
        public static final String PLAY_BUTTON = "play_button";
        /**
         * Settings button string source.
         */
        public static final String SETTINGS_BUTTON = "settings_button";
        /**
         * Quit button string source.
         */
        public static final String QUIT_BUTTON = "quit_button";
        /**
         * Levels button string source.
         */
        public static final String LEVELS_BUTTON = "levels_button";
        /**
         * A key string source.
         */
        public static final String A = "aKey";
        /**
         * D key string source.
         */
        public static final String D = "dKey";
        /**
         * W key string source.
         */
        public static final String W = "wKey";
        /**
         * S key string source.
         */
        public static final String S = "sKey";
        /**
         * Left arrow string source.
         */
        public static final String LEFT = "left_arrow";
        /**
         * Right arrow string source.
         */
        public static final String RIGHT = "right_arrow";
        /**
         * Up arrow string source.
         */
        public static final String UP = "up_arrow";
        /**
         * Down arrow string source.
         */
        public static final String DOWN = "down_arrow";
        /**
         * Menu texture box size.
         */
        public static final int MENU_TEXTURE_BOX = (int) (500 * Window.SCALE);
        /**
         * Button height.
         */
        public static final int BUTTON_HEIGHT = (int)  (75 * Window.SCALE);
        /**
         * Button width.
         */
        public static final int BUTTON_WIDTH = (int)  (200 * Window.SCALE);
        /**
         * Legend key box size.
         */
        public static final int LEGEND_KEY_BOX = (int)  (32 * Window.SCALE);
        /**
         * Menu box x position.
         */
        public static final int MENU_X = Window.GAME_WIDTH / 2 - MENU_TEXTURE_BOX / 2;
        /**
         * Menu box y position.
         */
        public static final int MENU_Y = Window.GAME_HEIGHT / 2 - MENU_TEXTURE_BOX / 2;
        /**
         * Number of function buttons.
         * Playbutton and levelsbutton.
         */
        public static final int NUM_FUNCTION_BUTTONS = 2;
        /**
         * Number of utility buttons.
         * Settingsbutton and quitbutton.
         */
        public static final int NUM_UTILITY_BUTTONS = 2;
        /**
         * Play button index.
         */
        public static final int PLAY_B = 0;
        /**
         * Levels button index.
         */
        public static final int LEVELS_B = 1;
        /**
         * Settings button index.
         */
        public static final int SETTINGS_B = 0;
        /**
         * Quit button index.
         */
        public static final int QUIT_B = 1;
        /**
         * Font size.
         */
        public static final int FONTSIZE = (int) (60 * Window.SCALE);
        /**
         * Bottom menu border distance.
         */
        public static final int BOTTOM_MENU_BORDER = MENU_Y + MENU_TEXTURE_BOX;
        /**
         * Right menu border distance.
         */
        public static final int RIGHT_MENU_BORDER = MENU_X + MENU_TEXTURE_BOX;
        /**
         * Function buttons x position.
         */
        public static final int FUNC_BUTTON_X = MENU_X + (MENU_TEXTURE_BOX - BUTTON_WIDTH) / 2;
        /**
         * X distance between function buttons.
         */
        public static final int FUNC_BUTTON_DISTANCE = MENU_TEXTURE_BOX / 10;
        /**
         * Utility buttons y border distance.
         */
        public static final int UTILITY_BUTTON_BORDER_DISTANCE_Y = MENU_TEXTURE_BOX / 8 + BUTTON_HEIGHT;
        /**
         * Utility buttons left border distance.
         */
        public static final int UTILITY_BUTTON_LEFT_BORDER_DISTANCE_X = MENU_TEXTURE_BOX / 12;
        /**
         * Utility buttons right border distance.
         */
        public static final int UTILITY_BUTTON_RIGHT_BORDER_DISTANCE_X = MENU_TEXTURE_BOX / 12 + BUTTON_WIDTH;
        /**
         * Utility buttons y position.
         */
        public static final int UTILITY_BUTTON_Y = BOTTOM_MENU_BORDER - UTILITY_BUTTON_BORDER_DISTANCE_Y;
        /**
         * Title height.
         */
        public static final int TITLE_HEIGHT = (int) (125 * Window.SCALE);
        /**
         * Title width.
         */
        public static final int TITLE_WIDTH = (int) (250 * Window.SCALE);
        /**
         * Title x position.
         */
        public static final int TITLE_X = MENU_X + (int) (115 * Window.SCALE);
        /**
         * Title y position.
         */
        public static final int TITLE_Y = MENU_Y - (int) (65 * Window.SCALE);

        /**
         * Menu sources map of string source and image.
         */
        private static final Map<String, BufferedImage> MENUSOURCES = new HashMap<>();

        /**
         * Private constructor.
         */
        private MenuAssets() {}

        /**
         * Get menu sources map.
         * 
         * @return the unmodifiable menu sources map.
         */
        public static Map<String, BufferedImage> getMenuSources() {
            return Collections.unmodifiableMap(MENUSOURCES);
        }

        /**
         * Load all menu sources.
         */
        public static void loadMenuSources() {
            MENUSOURCES.put(MENU_BACKGROUND, loadSources(MENU_BACKGROUND));
            MENUSOURCES.put(MENU_TEXTURE, loadSources(MENU_TEXTURE));
            MENUSOURCES.put(TITLE, loadSources(TITLE));
            MENUSOURCES.put(PLAY_BUTTON, loadSources(PLAY_BUTTON));
            MENUSOURCES.put(SETTINGS_BUTTON, loadSources(SETTINGS_BUTTON));
            MENUSOURCES.put(QUIT_BUTTON, loadSources(QUIT_BUTTON));
            MENUSOURCES.put(LEVELS_BUTTON, loadSources(LEVELS_BUTTON));
            MENUSOURCES.put(A, loadSources(A));
            MENUSOURCES.put(D, loadSources(D));
            MENUSOURCES.put(W, loadSources(W));
            MENUSOURCES.put(S, loadSources(S));
            MENUSOURCES.put(LEFT, loadSources(LEFT));
            MENUSOURCES.put(RIGHT, loadSources(RIGHT));
            MENUSOURCES.put(UP, loadSources(UP));
            MENUSOURCES.put(DOWN, loadSources(DOWN));
        }

        /**
         * Settings graphics constants.
         */
        public static final class SettingsAssets {
            /**
             * Home button string source.
             */
            public static final String HOME_BUTTON = "square_home_button";
            /**
             * Rounded volume on button string source.
             */
            public static final String ROUNDED_VOLUME_ON = "square_volume_on_button";
            /**
             * Rounded volume off button string source.
             */
            public static final String ROUNDED_VOLUME_OFF = "square_volume_off_button";
            /**
             * Rounded settings button string source.
             */
            public static final String ROUNDED_SETTINGS_BUTTON = "square_option_button";
            /**
             * Themes button string source.
             */
            public static final String THEMESBUTTON = "button_layout";
            /**
             * Play again (or back to play) button string source.
             */
            public static final String AGAIN_BUTTON = "square_playagain_button";
            /**
             * Text scale.
             */
            public static final int TEXTSCALE = 3;
            /**
             * Lose text string source.
             */
            public static final String LOSE_TEXT = "lose";
            /**
             * Lose text width.
             */
            public static final int LOSE_TEXT_WIDTH = (int) (62 * TEXTSCALE  * Window.SCALE);
            /**
             * Lose text height.
             */
            public static final int LOSE_TEXT_HEIGHT = (int) (21 * TEXTSCALE  * Window.SCALE);
            /**
             * Win text string source.
             */
            public static final String WIN_TEXT = "win";
            /**
             * Win text width.
             */
            public static final int WIN_TEXT_WIDTH = (int) (61 * TEXTSCALE  * Window.SCALE);
            /**
             * Win text height.
             */
            public static final int WIN_TEXT_HEIGHT = (int) (21 * TEXTSCALE  * Window.SCALE);
            /**
             * Pause text string source.
             */
            public static final String PAUSE_TEXT = "pause";
            /**
             * Pause text width.
             */
            public static final int PAUSE_TEXT_WIDTH = (int) (79 * TEXTSCALE  * Window.SCALE);
            /**
             * Pause text height.
             */
            public static final int PAUSE_TEXT_HEIGHT = (int) (21 * TEXTSCALE  * Window.SCALE);
            /**
             * Settings text string source.
             */
            public static final String SETTINGS_TEXT = "settings";
            /**
             * Settings text width.
             */
            public static final int SETTINGS_TEXT_WIDTH = (int) (119 * TEXTSCALE  * Window.SCALE);
            /**
             * Settings text height.
             */
            public static final int SETTINGS_TEXT_HEIGHT = (int) (21 * TEXTSCALE * Window.SCALE);
            /**
             * Number of volume buttons.
             */
            public static final int NUM_VOLUME_BUTTONS = 2;
            /**
             * Volume on button index.
             */
            public static final int VOL_ON_B = 0;
            /**
             * Volume off button index.
             */
            public static final int VOL_OFF_B = 1;
            /**
             * Square button size.
             */
            public static final int SQUARE_BUTTON_SIZE = (int) (75 * Window.SCALE);
            /**
             * Themes button width.
             */
            public static final int THEMES_BUTTON_WIDTH = (int) (40 * Window.SCALE);
            /**
             * Home button right distance.
             */
            public static final int HOME_BUTTON_RIGHT_DISTANCE = (int) (125 * Window.SCALE);
            /**
             * Home button bottom distance.
             */
            public static final int HOME_BUTTON_BOTTOM_DISTANCE = (int) (150 * Window.SCALE);
            /**
             * Repeat button left distance.
             */
            public static final int REPEAT_BUTTON_LEFT_DISTANCE = (int) (125 * Window.SCALE);
            /**
             * Repeat button bottom distance.
             */
            public static final int REPEAT_BUTTON_BOTTOM_DISTANCE = (int) (150 * Window.SCALE);
            /**
             * Mute button y position.
             */
            public static final int MUTE_BUTTON_Y = MENU_Y + SQUARE_BUTTON_SIZE;
            /**
             * Music buttons y position.
             */
            public static final int MUSIC_BUTTON_Y = MENU_Y + SQUARE_BUTTON_SIZE * 3;
            /**
             * Left settings button x position.
             */
            public static final int LEFT_SETTINGS_BUTTON_X = MENU_TEXTURE_BOX / 2 - SQUARE_BUTTON_SIZE / 2;
            /**
             * Right settings button x position.
             */
            public static final int RIGHT_SETTINGS_BUTTON_X = MENU_TEXTURE_BOX / 2 + SQUARE_BUTTON_SIZE * 2;
            /**
             * Background opacity behind pause view.
             */
            public static final int PAUSE_BG_OPACITY = 128;

            /**
             * Settings sources map of string source and image.
             */
            private static final Map<String, BufferedImage> SETTINGSSOURCES = new HashMap<>();
            /**
             * Text sources map of gamestate and image.
             */
            private static final Map<Gamestate, BufferedImage> TEXTSOURCES = new HashMap<>();

            /**
             * Private constructor.
             */
            private SettingsAssets() {}

            /**
             * Get settings sources map.
             * 
             * @return the unmodifiable settings sources map.
             */
            public static Map<String, BufferedImage> getSettingsSources() {
                return Collections.unmodifiableMap(SETTINGSSOURCES);
            }
            /**
             * Get text sources map.
             * 
             * @return the unmodifiable text sources map.
             */
            public static Map<Gamestate, BufferedImage> getTextSources() {
                return Collections.unmodifiableMap(TEXTSOURCES);
            }

            /**
             * Load settings sources.
             */
            public static void loadSettingsSources() {
                SETTINGSSOURCES.put(HOME_BUTTON, loadSources(HOME_BUTTON));
                SETTINGSSOURCES.put(ROUNDED_VOLUME_ON, loadSources(ROUNDED_VOLUME_ON));
                SETTINGSSOURCES.put(ROUNDED_VOLUME_OFF, loadSources(ROUNDED_VOLUME_OFF));
                SETTINGSSOURCES.put(ROUNDED_SETTINGS_BUTTON, loadSources(ROUNDED_SETTINGS_BUTTON));
                SETTINGSSOURCES.put(THEMESBUTTON, loadSources(THEMESBUTTON));
                SETTINGSSOURCES.put(AGAIN_BUTTON, loadSources(AGAIN_BUTTON));
            }

            /**
             * Load text sources.
             */
            public static void loadTextSources() {
                TEXTSOURCES.put(Gamestate.WIN, loadSources(WIN_TEXT));
                TEXTSOURCES.put(Gamestate.DEATH, loadSources(LOSE_TEXT));
                TEXTSOURCES.put(Gamestate.SETTINGS, loadSources(SETTINGS_TEXT));
                TEXTSOURCES.put(Gamestate.PAUSE, loadSources(PAUSE_TEXT));
            }
        }

        /**
         * Level graphics constants.
         */
        public static final class LevelAssets {
            /**
             * Level button height.
             */
            public static final int LEVEL_BUTTON_HEIGHT = (int) (135 * Window.SCALE);
            /**
             * Level button width.
             */
            public static final int LEVEL_BUTTON_WIDTH = (int) (192 * Window.SCALE);
            /**
             * Distance between level buttons.
             */
            public static final int LEVEL_BUTTON_DISTANCE = (int) (50 * Window.SCALE);
            /**
             * Level buttons spacing y axis.
             */
            public static final int LEVEL_BUTTON_SPACING_Y = (int) (15 * Window.SCALE);
            /**
             * Left level buttons x position.
             */
            public static final int LEFT_LEVEL_BUTTON_X = MENU_X + LEVEL_BUTTON_DISTANCE;
            /**
             * Right level buttons x position.
             */
            public static final int RIGHT_LEVEL_BUTTON_X = RIGHT_MENU_BORDER - LEVEL_BUTTON_DISTANCE - LEVEL_BUTTON_WIDTH;
            /**
             * Top level buttons y position.
             */
            public static final int TOP_LEVEL_BUTTON_Y = MENU_Y + LEVEL_BUTTON_DISTANCE;
            /**
             * Bottom level buttons y position.
             */
            public static final int BOT_LEVEL_BUTTON_Y = TOP_LEVEL_BUTTON_Y + LEVEL_BUTTON_HEIGHT + LEVEL_BUTTON_SPACING_Y;
            /**
             * Level one string source.
             */
            public static final String LEVEL_ONE_SOURCE = "level_one";
            /**
             * Level two string source.
             */
            public static final String LEVEL_TWO_SOURCE = "level_two";
            /**
             * Level three string source.
             */
            public static final String LEVEL_THREE_SOURCE = "level_three";
            /**
             * Level four string source.
             */
            public static final String LEVEL_FOUR_SOURCE = "level_four";
            /**
             * Barrel box string source.
             */
            public static final BufferedImage BARRELBOX = loadSources("barrelbox");

            /**
             * Level sources map of string source and pair of image and index.
             */
            private static final Map<String, Pair<BufferedImage, Integer>> LEVELSOURCES = new HashMap<>();

            /**
             * Private constructor.
             */
            private LevelAssets() {}

            /**
             * Get level sources map.
             * 
             * @return the unmodifiable level sources map.
             */
            public static Map<String, Pair<BufferedImage, Integer>> getLevelSources() {
                return Collections.unmodifiableMap(LEVELSOURCES);
            }

            /**
             * Load level sources.
             */
            public static void loadLevelSources() {
                LEVELSOURCES.put(LEVEL_ONE_SOURCE, new Pair<>(loadSources(LEVEL_ONE_SOURCE), 0));
                LEVELSOURCES.put(LEVEL_TWO_SOURCE, new Pair<>(loadSources(LEVEL_TWO_SOURCE), 1));
                LEVELSOURCES.put(LEVEL_THREE_SOURCE, new Pair<>(loadSources(LEVEL_THREE_SOURCE), 2));
                LEVELSOURCES.put(LEVEL_FOUR_SOURCE, new Pair<>(loadSources(LEVEL_FOUR_SOURCE), 3));
            }
        }
    }

    public static final class PowerupAssets {
        public static final int spawnOpPowerUpDelay = 120 * 15;
        public static final int moveOpPowerUpDelay = 180;
        public static final int minSpawn = (int) (Monkey.levelOneStartingMonkeyY / Window.SCALED_TILES_SIZE) + 2;
        public static final int maxSpawn = (int) (Player.levelOneStartingPlayerY / Window.SCALED_TILES_SIZE) - 1;
        public static final int heartWidth = (int) (36 * Window.SCALE);
        public static final int heartHeight = (int) (33 * Window.SCALE);
        public static final int heartYpadding = (int) (8 * Window.SCALE);
        public static final int heartXpadding = (int) (6 * Window.SCALE);
        public static final int shieldWidth = (int) (36 * Window.SCALE);
        public static final int shieldHeight = (int) (40 * Window.SCALE);
        public static final int shieldXpadding = (int) (6 * Window.SCALE);
        public static final int shieldYpadding = (int) (4 * Window.SCALE);
        public static final int freezeDimension = (int) (46 * Window.SCALE);
        public static final int freezePadding = (int) (1 * Window.SCALE);
        public static final int starDimension = (int) (40 * Window.SCALE);
        public static final int starPadding = (int) (4 * Window.SCALE);
        public static final int lifeDimension = (int) (28 * Window.SCALE);
        public static final int lifePadding = (int) (10 * Window.SCALE);
        public static final int powerupBorderDimension = (int) ((Window.SCALED_TILES_SIZE - 8) * Window.SCALE);
        public static final int powerupBorderPadding = (int) (4 * Window.SCALE);
        public static final int powerupActiveDimension = (int) ((Window.SCALED_TILES_SIZE - 20) * Window.SCALE);
        public static final int powerupActivePadding = (int) (((float) (powerupBorderDimension - powerupActiveDimension) / 2) * Window.SCALE);
        public static final String heart = "heart";
        public static final String shield = "shield";
        public static final String freeze = "snowflake";
        public static final String star = "star";
        public static final String life = "playerlife";
        public static final String noLife = "nolife";
        public static final String emptyBorder = "empty_border";

        private static final Map<String, BufferedImage> powerupsources = new HashMap<>();

        private PowerupAssets() {}

        public static final Map<String, BufferedImage> getPowerupSources() {
            return Collections.unmodifiableMap(powerupsources);
        }

        public static final void loadPowerupSources() {
            powerupsources.put(heart, loadSources(heart));
            powerupsources.put(shield, loadSources(shield));
            powerupsources.put(freeze, loadSources(freeze));
            powerupsources.put(star, loadSources(star));
            powerupsources.put(life, loadSources(life));
            powerupsources.put(noLife, loadSources(noLife));
            powerupsources.put(emptyBorder, loadSources(emptyBorder));
        }
    }

    public static final class Level {
        public static final String levelOne = "one";
        public static final String levelTwo = "two";
        public static final String levelThree = "three";
        public static final String levelFour = "four";
        public static final int defaultSpriteSize = 16;
        public static final int blackBlock = 0;
        public static final int platformBlock = 1;
        public static final int coloredLadder = 2;
        public static final int whiteLadder = 3;
        public static final int blockWithUpperLadder = 4;
        public static final int blockWithLowerLadder = 5;
        public static final int blockWithDoubleLadder = 6;
        public static final int levelSpritesLength = 7;
        public static final int numLevelsButtons = 4;
        public static final int platformBlockPadding = Math.round(12 * Window.SCALE);
        public static final int ladderPadding = Math.round(9 * Window.SCALE);
        public static final int princessPadding = 6;
    }

    public static final class Audio {
        public static final String menuMusic0 = "soundtrack.wav";
        public static final String menuMusic1 = "soundtrack1.wav";
        public static final String gameMusic0 = "game.wav";
        public static final String gameMusic1 = "game1.wav";
        public static final int numThemesButtons = 2;
        public static final int numGameThemesButtons = 2;
        public static final float baseVolume = 0.03f;
        public static final int gainMultiplier = 20;

        private static final Map<String, Integer> themeSources = new HashMap<>();
        private static final Map<String, Integer> gameSources = new HashMap<>();

        public static final Map<String, Integer> getThemeSources() {
            return Collections.unmodifiableMap(themeSources);
        }

        public static final Map<String, Integer> getGameSources() {
            return Collections.unmodifiableMap(gameSources);
        }

        public static final void loadThemes() {
            themeSources.put(menuMusic0, 0);
            themeSources.put(menuMusic1, 1);
            
            gameSources.put(gameMusic0, 0);
            gameSources.put(gameMusic1, 1);
        }
    }

    /**
     * Physics constants.
     */
    public static final class Physics {
        /**
         * Jump speed.
         */
        public static final float JUMP_SPEED = -2.4f * Window.SCALE;
        /**
         * Gravity.
         */
        public static final float GRAVITY = 0.04f * Window.SCALE;
        /**
         * Player's speed in air multiplier.
         */
        public static final float PLAYER_AIR_MULTIPLIER = 0.6f;
        /**
         * Barrel's speed in air multiplier.
         */
        public static final float BARREL_AIR_MULTIPLIER = 0.4f;
        /**
         * Gravity while jumping.
         */
        public static final float JUMP_GRAVITY_MULTIPLIER = 1.3f;
    }

    public static final class Barrel {
        public static final int barrelDimension = (int) (48 * Window.SCALE);
        public static final int barrelWidth = (int) (36 * Window.SCALE);
        public static final int barrelHeight = (int) (30 * Window.SCALE);
        public static final int barrelBoxWidth = (int) (56 * Window.SCALE);
        public static final int barrelBoxHeight = (int) (96 * Window.SCALE);
        public static final int barrelFloorError = 12;
        public static final Direction firstLevelDirection = Direction.RIGHT;
        public static final float velocity = 2f * Window.SCALE;
        public static final int spawnDelay = 130;
        public static final int doubleDamageProbability = 3;
        public static final int totalPUProbability = 10;
        public static final int totalDirProbability = 2;
        public static final int changeDirProbability = 1;
        public static final int barrelAni = 0;
        public static final int ddBarrelAni = 1;
        public static final int barrelAniSprites = 4;
        public static final int numBarrel = 2;
        public static final int spriteWidth = 12;
        public static final int spriteHeight = 10;
        public static final int aniBarrelSpeed = 15;

        private static final Map<Integer, BufferedImage> barrelSources = new HashMap<>();

        public static final Map<Integer, BufferedImage> getBarrelSources() {
            return Collections.unmodifiableMap(barrelSources);
        }

        public static final void loadBarrelSources() {
            barrelSources.put(barrelAni, loadSources("barrel"));
            barrelSources.put(ddBarrelAni, loadSources("barrel_dd"));
        }
    }

    public static final class Player {
        public static final float levelOneStartingPlayerX = Window.SCALED_TILES_SIZE;
        public static final float levelOneStartingPlayerY = Window.SCALED_TILES_SIZE * 12 + Level.platformBlockPadding;
        public static final float levelTwoStartingPlayerX = Window.SCALED_TILES_SIZE;
        public static final float levelTwoStartingPlayerY = Window.SCALED_TILES_SIZE * 12 + Level.platformBlockPadding;
        public static final float levelThreeStartingPlayerX = Window.SCALED_TILES_SIZE * 2;
        public static final float levelThreeStartingPlayerY = Window.SCALED_TILES_SIZE * 12 + Level.platformBlockPadding;
        public static final float levelFourStartingPlayerX = Window.SCALED_TILES_SIZE * (Window.TILES_IN_WIDTH / 2);
        public static final float levelFourStartingPlayerY = Window.SCALED_TILES_SIZE * 12 + Level.platformBlockPadding;
        public static final int playerDimension = (int) (48 * Window.SCALE);
        public static final int ladderErrorPercentage = 40;
        public static final float canLadderError = playerDimension * ladderErrorPercentage / 100;
        public static final float velocity = 2f * Window.SCALE;
        public static final int numLives = 3;
        public static final int damageTaken = -1;
        public static final int doubleDamage = 2 * damageTaken;
        public static final int extraLife = 1;
        public static final int starDuration = 120 * 3;
        public static final int freezeDuration = 120 * 3;
        public static final int movementAni = 0;
        public static final int climbingAni = 1;
        public static final int spriteDimension = 16;
        public static final int movementAniSprites = 3;
        public static final int numMovementAni = 5;
        public static final int runAni = 0;
        public static final int jumpAni = 2;
        public static final int leftAni = 0;
        public static final int rightAni = 1;
        public static final int midAirAni = 1;
        public static final int climbingAniSprites = 2;
        public static final int climbAni = 4;
        public static final int aniPlayerSpeed = 15;
        public static final int aniClimbSpeed = 15;
        
        private static final Map<Integer, BufferedImage> playerSources = new HashMap<>();

        public static final Map<Integer, BufferedImage> getPlayerSources() {
            return Collections.unmodifiableMap(playerSources);
        }

        public static final void loadPlayerSources() {
            playerSources.put(movementAni, loadSources("mario_movement"));
            playerSources.put(climbingAni, loadSources("climbingplayer"));
        }
    }
    
    public static final class Monkey {
        public static final float levelOneStartingMonkeyX = Window.SCALED_TILES_SIZE / 2f;
        public static final float levelOneStartingMonkeyY = Window.SCALED_TILES_SIZE * 2 + Level.platformBlockPadding;
        public static final int monkeyWidth = (int) (120 * Window.SCALE);
        public static final int monkeyHeight = (int) (96 * Window.SCALE);
        public static final int monkeyAni = 0;
        public static final int monkeyAniSprites = 4;
        public static final int spriteWidth = 40;
        public static final int spriteHeight = 32;
        public static final int aniMonkeySpeed = 25;
        public static final int throwAnimationTime = aniMonkeySpeed * (monkeyAniSprites - 1);

        private static final Map<Integer, BufferedImage> monkeySources = new HashMap<>();

        public static final Map<Integer, BufferedImage> getMonkeySources() {
            return Collections.unmodifiableMap(monkeySources);
        }

        public static final void loadMonkeySources() {
            monkeySources.put(monkeyAni, loadSources("donkeykong"));
        }
    }

    public static final class Princess {
        public static final float levelOneStartingPrincessX = Window.SCALED_TILES_SIZE * 5;
        public static final float levelOneStartingPrincessY = Window.SCALED_TILES_SIZE - Level.princessPadding;
        public static final int princessWidth = (int) (48 * Window.SCALE);
        public static final int princessHeight = (int) (66 * Window.SCALE);
        public static final float velocity = 0.5f * Window.SCALE;
        public static final int nextRandomMoveTime = 24;
        public static final int totalProbability = 10;
        public static final int sameDirProb = 4;
        public static final int changeDirProb = 7;
        public static final int noMoveProb = 9;
        public static final int noMoveAddictionalTime = 24;
        public static final int spriteWidth = 16;
        public static final int spriteHeight = 22;
        public static final int princessAni = 0;
        public static final int leftAni = 0;
        public static final int rightAni = 1;
        public static final int princessAniSprites = 3;
        public static final int numPrincessAni = 2; 
        public static final int aniPrincessSpeed = 15;

        private static final Map<Integer, BufferedImage> princessSources = new HashMap<>();

        public static final Map<Integer, BufferedImage> getPrincessSources() {
            return Collections.unmodifiableMap(princessSources);
        }

        public static final void loadPrincessSources() {
            princessSources.put(princessAni, loadSources("princess"));
        }
    }

    public static final class Action {
        public static final int MOVE_LEFT = 65;
        public static final int MOVE_LEFT_ARROW = 37;
        public static final int MOVE_RIGHT = 68;
        public static final int MOVE_RIGHT_ARROW = 39;
        public static final int MOVE_UP = 87;
        public static final int MOVE_UP_ARROW = 38;
        public static final int MOVE_DOWN = 83;
        public static final int MOVE_DOWN_ARROW = 40;
        public static final int JUMP = 32;
        public static final int ESCAPE = 27;

        public static boolean isMovementCode(final int code) {
            return code == MOVE_LEFT || code == MOVE_LEFT_ARROW 
                || code == MOVE_RIGHT || code == MOVE_RIGHT_ARROW
                || code == MOVE_UP || code == MOVE_UP_ARROW 
                || code == MOVE_DOWN || code == MOVE_DOWN_ARROW
                || code == JUMP;
        }
    }
}
