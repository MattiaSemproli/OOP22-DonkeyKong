package it.unibo.donkeykong.utilities;

import static it.unibo.donkeykong.utilities.ResourceFuncUtilities.loadSources;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.utilities.Constants.Monkey;

public class ViewConstants {
    
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
     * Menu graphics constants.
     */
    public static final class MenuAssets {
        /**
         * Application title.
         */
        public static final String TITLE = "title";
        /**
         * Menu background source.
         */
        public static final String MENU_BACKGROUND = "menu_background";
        /**
         * Menu layout source.
         */
        public static final String MENU_TEXTURE = "menu_layout";
        /**
         * Play button source.
         */
        public static final String PLAY_BUTTON = "play_button";
        /**
         * Settings button source.
         */
        public static final String SETTINGS_BUTTON = "settings_button";
        /**
         * Quit button source.
         */
        public static final String QUIT_BUTTON = "quit_button";
        /**
         * Levels button source.
         */
        public static final String LEVELS_BUTTON = "levels_button";
        /**
         * A key source.
         */
        public static final String A = "aKey";
        /**
         * D key source.
         */
        public static final String D = "dKey";
        /**
         * W key source.
         */
        public static final String W = "wKey";
        /**
         * S key source.
         */
        public static final String S = "sKey";
        /**
         * Left arrow source.
         */
        public static final String LEFT = "left_arrow";
        /**
         * Right arrow source.
         */
        public static final String RIGHT = "right_arrow";
        /**
         * Up arrow source.
         */
        public static final String UP = "up_arrow";
        /**
         * Down arrow source.
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
         * Menu sources map of source and image.
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
             * Home button source.
             */
            public static final String HOME_BUTTON = "square_home_button";
            /**
             * Rounded volume on button source.
             */
            public static final String ROUNDED_VOLUME_ON = "square_volume_on_button";
            /**
             * Rounded volume off button source.
             */
            public static final String ROUNDED_VOLUME_OFF = "square_volume_off_button";
            /**
             * Rounded settings button source.
             */
            public static final String ROUNDED_SETTINGS_BUTTON = "square_option_button";
            /**
             * Themes button source.
             */
            public static final String THEMESBUTTON = "button_layout";
            /**
             * Play again (or back to play) button source.
             */
            public static final String AGAIN_BUTTON = "square_playagain_button";
            /**
             * Text scale.
             */
            public static final int TEXTSCALE = 3;
            /**
             * Lose text source.
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
             * Win text source.
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
             * Pause text source.
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
             * Settings text source.
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
             * Settings sources map of source and image.
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
             * Level one source (for levels chose view).
             */
            public static final String LEVEL_ONE_SOURCE = "level_one";
            /**
             * Level two source (for levels chose view).
             */
            public static final String LEVEL_TWO_SOURCE = "level_two";
            /**
             * Level three source (for levels chose view).
             */
            public static final String LEVEL_THREE_SOURCE = "level_three";
            /**
             * Level four source (for levels chose view).
             */
            public static final String LEVEL_FOUR_SOURCE = "level_four";            
            /**
             * Level one data source.
             */
            public static final String LEVEL_ONE = "one";
            /**
             * Level two data source.
             */
            public static final String LEVEL_TWO = "two";
            /**
             * Level three data source.
             */
            public static final String LEVEL_THREE = "three";
            /**
             * Level four data source.
             */
            public static final String LEVEL_FOUR = "four";
            /**
             * Barrel box x position.
             */
            public static final float BARREL_BOX_Y = Monkey.LEVEL_ONE_STARTING_Y + Monkey.MONKEY_HEIGHT;
            /**
             * Barrel box source.
             */
            public static final BufferedImage BARRELBOX = loadSources("barrelbox");
            /**
             * Barrel box width.
             */
            public static final int BARREL_BOX_WIDTH = (int) (56 * Window.SCALE);
            /**
             * Barrel box height.
             */
            public static final int BARREL_BOX_HEIGHT = (int) (96 * Window.SCALE);
            /**
             * Level sprites lenght.
             */
            public static final int LEVEL_SPRITES_LENGHT = 7;

            /**
             * Level sources map of source and pair of image and index.
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
    
    /**
     * Audio constants.
     */
    public static final class AudioAssets {
        /**
         * Menu music 0 source.
         */
        public static final String MENU_MUSIC_0 = "soundtrack.wav";
        /**
         * Menu music 1 source.
         */
        public static final String MENU_MUSIC_1 = "soundtrack1.wav";
        /**
         * Game music 0 source.
         */
        public static final String GAME_MUSIC_0 = "game.wav";
        /**
         * Game music 1 source.
         */
        public static final String GAME_MUSIC_1 = "game1.wav";
        /**
         * Number of themes buttons.
         */
        public static final int NUM_THEMES_BUTTON = 2;
        /**
         * Number of game themes buttons.
         */
        public static final int NUM_GAME_THEMES_BUTTON = 2;
        /**
         * Base volume value.
         */
        public static final float BASE_VOLUME = 0.03f;
        /**
         * Gain multiplier.
         */
        public static final int GAIN_MULTIPLIER = 20;

        /**
         * Theme sources map of music and index.
         */
        private static final Map<String, Integer> THEMESOURCES = new HashMap<>();
        /**
         * Game theme sources map of music and index.
         */
        private static final Map<String, Integer> GAMESOURCES = new HashMap<>();

        /**
         * Private constructor.
         */
        private AudioAssets() {}

        /**
         * Get theme sources map.
         * 
         * @return the unmodifiable theme sources map.
         */
        public static final Map<String, Integer> getThemeSources() {
            return Collections.unmodifiableMap(THEMESOURCES);
        }

        /**
         * Get game theme sources map.
         * 
         * @return the unmodifiable game theme sources map.
         */
        public static final Map<String, Integer> getGameSources() {
            return Collections.unmodifiableMap(GAMESOURCES);
        }

        /**
         * Load all menu and game sources.
         */
        public static final void loadThemes() {
            THEMESOURCES.put(MENU_MUSIC_0, 0);
            THEMESOURCES.put(MENU_MUSIC_1, 1);
            
            GAMESOURCES.put(GAME_MUSIC_0, 0);
            GAMESOURCES.put(GAME_MUSIC_1, 1);
        }
    }

    /**
     * Powerup constants.
     */
    public static final class PowerupAssets {
        /**
         * Life powerup dimension.
         */
        public static final int LIFE_DIMENSION = (int) (28 * Window.SCALE);
        /**
         * Life powerup padding.
         */
        public static final int LIFE_PADDING = (int) (10 * Window.SCALE);
        /**
         * Power up border dimension.
         */
        public static final int PUPS_BORDER_DIMENSION = (int) ((Window.SCALED_TILES_SIZE - 8) * Window.SCALE);
        /**
         * Power up border padding.
         */
        public static final int PUPS_BODER_PADDING = (int) (4 * Window.SCALE);
        /**
         * Power up active dimension.
         */
        public static final int PUPS_ACTIVE_DIMENSION = (int) ((Window.SCALED_TILES_SIZE - 20) * Window.SCALE);
        /**
         * Power up active padding.
         */
        public static final int PUPS_ACTIVE_PADDING = (int) (((float) (PUPS_BORDER_DIMENSION - PUPS_ACTIVE_DIMENSION) / 2) * Window.SCALE);
        /**
         * Power up heart source.
         */
        public static final String HEART = "heart";
        /**
         * Power up shield source.
         */
        public static final String SHIELD = "shield";
        /**
         * Power up freeze source.
         */
        public static final String FREEZE = "snowflake";
        /**
         * Power up star source.
         */
        public static final String STAR = "star";
        /**
         * Life source.
         */
        public static final String LIFE = "playerlife";
        /**
         * No life source.
         */
        public static final String NOLIFE = "nolife";
        /**
         * Empty border source.
         */
        public static final String EMPTYBORDER = "empty_border";

        /**
         * Powerup sources map of source and image.
         */
        private static final Map<String, BufferedImage> POWERUPSOURCES = new HashMap<>();

        /**
         * Private constructor.
         */
        private PowerupAssets() {}

        /**
         * Get powerup sources map.
         * 
         * @return the unmodifiable powerup sources map.
         */
        public static final Map<String, BufferedImage> getPowerupSources() {
            return Collections.unmodifiableMap(POWERUPSOURCES);
        }

        /**
         * Load powerup sources.
         */
        public static final void loadPowerupSources() {
            POWERUPSOURCES.put(HEART, loadSources(HEART));
            POWERUPSOURCES.put(SHIELD, loadSources(SHIELD));
            POWERUPSOURCES.put(FREEZE, loadSources(FREEZE));
            POWERUPSOURCES.put(STAR, loadSources(STAR));
            POWERUPSOURCES.put(LIFE, loadSources(LIFE));
            POWERUPSOURCES.put(NOLIFE, loadSources(NOLIFE));
            POWERUPSOURCES.put(EMPTYBORDER, loadSources(EMPTYBORDER));
        }
    }

    /**
     * Barrel constants.
     */
    public static final class BarrelAssets {
        /**
         * Number of barrel existing (normal and double damage).
         */
        public static final int NUM_BARREL = 2;
        /**
         * Barrel sprite width.
         */
        public static final int SPRITE_WIDTH = 12;
        /**
         * Barrel sprite height.
         */
        public static final int SPRITE_HEIGHT = 10;
        /**
         * Barrel animation index.
         */
        public static final int BARREL_ANI = 0;
        /**
         * Double damage barrel animation index.
         */
        public static final int DD_BARREL_ANI = 1;
        /**
         * Number of barrel animation sprites.
         */
        public static final int BARREL_ANI_SPRITES = 4;

        /**
         * Barrel sources map of type barrel and image.
         */
        private static final Map<Integer, BufferedImage> BARRELSOURCES = new HashMap<>();

        /**
         * Private constructor.
         */
        private BarrelAssets() {}

        /**
         * Get barrel sources map.
         * 
         * @return the unmodifiable barrel sources map.
         */
        public static final Map<Integer, BufferedImage> getBarrelSources() {
            return Collections.unmodifiableMap(BARRELSOURCES);
        }

        /**
         * Load barrel sources.
         */
        public static final void loadBarrelSources() {
            BARRELSOURCES.put(BARREL_ANI, loadSources("barrel"));
            BARRELSOURCES.put(DD_BARREL_ANI, loadSources("barrel_dd"));
        }
    }

    /**
     * Monkey constants.
     */
    public static final class MonkeyAssets {
        /**
         * Monkey sprite width.
         */
        public static final int SPRITE_WIDTH = 40;
        /**
         * Monkey sprite height.
         */
        public static final int SPRITE_HEIGHT = 32;
        /**
         * Monkey ani index.
         */     
        public static final int MONKEY_ANI = 0;
        /**
         * Number of monkey ani sprites.
         */
        public static final int MONKEY_ANI_SPRITES = 4;

        /**
         * Monkey sources map of index and image
         */
        private static final Map<Integer, BufferedImage> MONKEYSOURCES = new HashMap<>();

        /**
         * Private constructor.
         */
        private MonkeyAssets() {}

        /**
         * Get monkey sources map.
         * 
         * @return the unmodifiable monkey sources map.
         */
        public static final Map<Integer, BufferedImage> getMonkeySources() {
            return Collections.unmodifiableMap(MONKEYSOURCES);
        }

        /**
         * Load monkey sources.
         */
        public static final void loadMonkeySources() {
            MONKEYSOURCES.put(MONKEY_ANI, loadSources("donkeykong"));
        }
    }

    /**
     * Princess constants.
     */
    public final static class PrincessAssets {
        /**
         * Princess sprite width.
         */
        public static final int SPRITE_WIDTH = 16;
        /**
         * Princess sprite height.
         */
        public static final int SPRITE_HEIGHT = 22;
        /**
         * Princess ani index.
         */
        public static final int PRINCESS_ANI = 0;
        /**
         * Number of princess ani sprites.
         */
        public static final int PRINCESS_ANI_SPRITES = 3;
        /**
         * Number of princess animation.
         */
        public static final int NUM_PRINCESS_ANI = 2; 

        /**
         * Princess source map of index and image.
         */
        private static final Map<Integer, BufferedImage> PRINCESSSOURCES = new HashMap<>();

        /**
         * Private constructor.
         */
        private PrincessAssets() {}

        /**
         * Get princess sources map.
         * 
         * @return the unmodifiable princess sources map.
         */
        public static final Map<Integer, BufferedImage> getPrincessSources() {
            return Collections.unmodifiableMap(PRINCESSSOURCES);
        }

        /**
         * Load princess sources.
         */
        public static final void loadPrincessSources() {
            PRINCESSSOURCES.put(PRINCESS_ANI, loadSources("princess"));
        }
    }

    public final static class PlayerAssets {
        /**
         * Climbing animation index.
         */
        public static final int CLIMB_ANI = 4;
        /**
         * Number of climbing ani sprites.
         */
        public static final int CLIMB_ANI_SPRITES = 2;
        /**
         * Movement animation index.
         */
        public static final int MOVEMENT_ANI = 0;
        /**
         * Number of movement ani sprites.
         */
        public static final int MOVEMENT_ANI_SPRITES = 3;
        /**
         * Player climbing animation index.
         */
        public static final int CLIMBING_ANI = 1;
        /**
         * Player sprite dimension.
         */
        public static final int SPRITE_DIMENSION = 16;
        /**
         * Player number of animation.
         */
        public static final int NUM_MOVEMENT_ANI = 5;
        /**
         * Player number of starting lives.
         */
        public static final int NUM_LIVES = 3;
        /**
         * Player sources map of index and image.
         */
        private static final Map<Integer, BufferedImage> playerSources = new HashMap<>();

        /**
         * Private constructor.
         */
        private PlayerAssets() {}

        /**
         * Get player sources map.
         * 
         * @return the unmodifiable player sources map.
         */
        public static final Map<Integer, BufferedImage> getPlayerSources() {
            return Collections.unmodifiableMap(playerSources);
        }

        /**
         * Load player sources.
         */
        public static final void loadPlayerSources() {
            playerSources.put(MOVEMENT_ANI, loadSources("mario_movement"));
            playerSources.put(CLIMBING_ANI, loadSources("climbingplayer"));
        }
    }
}
