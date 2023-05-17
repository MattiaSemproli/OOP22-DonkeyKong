package it.unibo.donkeykong.utilities;

import static it.unibo.donkeykong.utilities.ResourceFuncUtilities.loadSources;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static class Window {
        public final static int TILES_DEFAULT_SIZE = 16;
        public final static float SCALE = 3.0f;
        public final static int TILES_IN_WIDTH = 14;
        public final static int TILES_IN_HEIGHT = 14;
        public final static int SCALED_TILES_SIZE = Math.round(TILES_DEFAULT_SIZE * SCALE);
        public final static int GAME_WIDTH = SCALED_TILES_SIZE * TILES_IN_WIDTH;
        public final static int GAME_HEIGHT = SCALED_TILES_SIZE * TILES_IN_HEIGHT;
    }

    public static class GameLoop {
        public final static int FPS_SET = 120;
        public final static int UPS_SET = 120;
        public final static double NANOSECOND = 1000000000.0;
        public final static int FRAME_DEFAULT = 0;
        public final static int UPDATES_DEFAULT = 0;
        public final static int DELTAF_DEFAULT = 0;
        public final static int DELTAU_DEFAULT = 0;
    }

    public static final class MenuAssets {
        public static final String title = "title";
        public static final String menuBackground = "menu_background";
        public static final String menuTexture = "menu_layout";
        public static final String gameLegend = "";
        public static final String playButton = "play_button";
        public static final String settingsButton = "settings_button";
        public static final String quitButton = "quit_button";
        public static final String levelsButton = "levels_button";
        public static final String aKey = "aKey";
        public static final String dKey = "dKey";
        public static final String wKey = "wKey";
        public static final String sKey = "sKey";
        public static final String left = "left_arrow";
        public static final String right = "right_arrow";
        public static final String up = "up_arrow";
        public static final String down = "down_arrow";
        public static final int menuTextureBox = 500;
        public static final int buttonHeight = 75;
        public static final int buttonWidth = 200;
        public static final int legendKeyBox = 32;
        public static final int menuX = Window.GAME_WIDTH / 2 - menuTextureBox / 2;
        public static final int menuY = Window.GAME_HEIGHT / 2 - menuTextureBox / 2;
        public static final int numFunctionButtons = 2;
        public static final int numUtilityButtons = 2;
        public static final int playB = 0;
        public static final int levelsB = 1;
        public static final int settingsB = 0;
        public static final int quitB = 1;

        public static final Map<String, BufferedImage> menuSources = new HashMap<>();

        public static final void loadMenuSources() {
            menuSources.put(menuBackground, loadSources(menuBackground));
            menuSources.put(menuTexture, loadSources(menuTexture));
            menuSources.put(title, loadSources(title));
            menuSources.put(playButton, loadSources(playButton));
            menuSources.put(settingsButton, loadSources(settingsButton));
            menuSources.put(quitButton, loadSources(quitButton));
            menuSources.put(levelsButton, loadSources(levelsButton));
            menuSources.put(aKey, loadSources(aKey));
            menuSources.put(dKey, loadSources(dKey));
            menuSources.put(wKey, loadSources(wKey));
            menuSources.put(sKey, loadSources(sKey));
            menuSources.put(left, loadSources(left));
            menuSources.put(right, loadSources(right));
            menuSources.put(up, loadSources(up));
            menuSources.put(down, loadSources(down));
        }

        public static final class SettingsAssets {
            public static final String homeButton = "square_home_button";
            public static final String roundedVolumeOn = "square_volume_on_button";
            public static final String roundedVolumeOff = "square_volume_off_button";
            public static final String roundedSettingsButton = "square_option_button";
            public static final String themesButton = "button_layout";
            public static final String backToPlayButton = "square_playagain_button";
            public static final int numVolumeButtons = 2;
            public static final int volOnB = 0;
            public static final int volOffB = 1;
            public static final int squareButtonSize = 75;
            public static final int homeButtonRightDistance = 125;
            public static final int homeButtonBottomDistance = 150;
            public static final int repeatButtonLeftDistance = 125;
            public static final int repeatButtonBottomDistance = 150;

            public static final Map<String, BufferedImage> settingsSources = new HashMap<>();

            public static final void loadSettingsSources() {
                settingsSources.put(homeButton, loadSources(homeButton));
                settingsSources.put(roundedVolumeOn, loadSources(roundedVolumeOn));
                settingsSources.put(roundedVolumeOff, loadSources(roundedVolumeOff));
                settingsSources.put(roundedSettingsButton, loadSources(roundedSettingsButton));
                settingsSources.put(themesButton, loadSources(themesButton));
                settingsSources.put(backToPlayButton, loadSources(backToPlayButton));
            }
        }
    }

    public static final class Barrel {
        public static final int velocity = 2;
        public static final int startingBounces = 0;
        public static final int maxBounces = 3;
        public static final float initialPositionXLevel1 = 0.f;
        public static final float initialPositionYLevel1 = 0.f;
        public static final Direction firstLevelDirection = Direction.RIGHT;
    }

    public static final class Level {
        public static final String levelOne = "level_one_data";
        public static final String levelSprites = "platform_ladder";
        public static final String levelOneSource = "level_one";
        public static final String levelTwoSource = "level_two";
        public static final String levelThreeSource = "level_three";
        public static final String levelFourSource = "level_four";
        public static final int blackBlock = 0;
        public static final int platformBlock = 1;
        public static final int coloredLadder = 2;
        public static final int whiteLadder = 3;
        public static final int blockWithUpperLadder = 4;
        public static final int blockWithLowerLadder = 5;
        public static final int blockWithDoubleLadder = 6;
        public static final int levelSpritesLength = 7;
        public static final int numLevelsButtons = 4;

        public static final Map<String, Pair<BufferedImage, Integer>> levelSources = new HashMap<>();

        public static final void loadLevelSources() {
            levelSources.put(levelOneSource, new Pair<BufferedImage,Integer>(loadSources(levelOneSource), 0));
            levelSources.put(levelTwoSource, new Pair<BufferedImage,Integer>(loadSources("empty_level"), 1));
            levelSources.put(levelThreeSource, new Pair<BufferedImage,Integer>(loadSources("empty_level"), 2));
            levelSources.put(levelFourSource, new Pair<BufferedImage,Integer>(loadSources("empty_level"), 3));
        }
    }

    public static final class Audio {
        public final static String menuMusic0 = "soundtrack.wav";
        public final static String menuMusic1 = "soundtrack1.wav";
        public final static String gameMusic0 = "game.wav";
        public final static String gameMusic1 = "game1.wav";
        public final static int numThemesButtons = 2;
        public final static int numGameThemesButtons = 2;
        public final static float baseVolume = 0.5f;
    
        public static final Map<String, Integer> themeSources = new HashMap<>();
        public static final Map<String, Integer> gameSources = new HashMap<>();

        public static final void loadThemes() {
            themeSources.put(menuMusic0, 0);
            themeSources.put(menuMusic1, 1);
            
            gameSources.put(gameMusic0, 0);
            gameSources.put(gameMusic1, 1);
        }

    }
}
