package it.unibo.donkeykong.utilities;

import static it.unibo.donkeykong.utilities.ResourceFuncUtilities.loadSources;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static class Window {
        public final static int TILES_DEFAULT_SIZE = 16;
        public final static float SCALE = 3.0f;
        public final static float scala = 1f;
        public final static int TILES_IN_WIDTH = 14;
        public final static int TILES_IN_HEIGHT = 14;
        public final static int SCALED_TILES_SIZE = Math.round(TILES_DEFAULT_SIZE * SCALE * scala);
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
        public static final int menuTextureBox = (int) (500 * Window.scala);
        public static final int buttonHeight = (int)  (75 * Window.scala);
        public static final int buttonWidth = (int)  (200 * Window.scala);
        public static final int legendKeyBox = (int)  (32 * Window.scala);
        public static final int menuX = Window.GAME_WIDTH / 2 - menuTextureBox / 2;
        public static final int menuY = Window.GAME_HEIGHT / 2 - menuTextureBox / 2;
        public static final int numFunctionButtons = 2;
        public static final int numUtilityButtons = 2;
        public static final int playB = 0;
        public static final int levelsB = 1;
        public static final int settingsB = 0;
        public static final int quitB = 1;
        public static final int bottomMenuBorder = menuY + menuTextureBox;
        public static final int rightMenuBorder = menuX + menuTextureBox;
        public static final int funcButtonX = menuX + (menuTextureBox - buttonWidth) / 2;
        public static final int funcButtonsDistance = menuTextureBox / 10;
        public static final int utilityButtonBorderDistanceY = menuTextureBox / 8 + buttonHeight;
        public static final int utilityButtonLeftBorderDistanceX = menuTextureBox / 12;
        public static final int utilityButtonRightBorderDistanceX = menuTextureBox / 12 + buttonWidth;
        public static final int utilityButtonY = bottomMenuBorder - utilityButtonBorderDistanceY;
        public static final int titleHeight = (int) (125 * Window.scala);
        public static final int titleWidth = (int) (250 * Window.scala);
        public static final int titleX = menuX + (int) (115 * Window.scala);
        public static final int titleY = menuY - (int) (65 * Window.scala);

        private static final Map<String, BufferedImage> menuSources = new HashMap<>();

        public static final Map<String, BufferedImage> getMenuSources() {
            return Collections.unmodifiableMap(menuSources);
        }

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
            public static final int squareButtonSize = (int) (75 * Window.scala);
            public static final int homeButtonRightDistance = (int) (125 * Window.scala);
            public static final int homeButtonBottomDistance = (int) (150 * Window.scala);
            public static final int repeatButtonLeftDistance = (int) (125 * Window.scala);
            public static final int repeatButtonBottomDistance = (int) (150 * Window.scala);
            public static final int muteButtonY = menuY + squareButtonSize;
            public static final int musicsButtonY = menuY + squareButtonSize * 3;
            public static final int leftSettingsButtonX = menuTextureBox / 2 - squareButtonSize / 2;
            public static final int rightSettingsButtonX = menuTextureBox / 2 + squareButtonSize * 2;
            public static final int pauseBgOpacity = 128;

            private static final Map<String, BufferedImage> settingsSources = new HashMap<>();

            public static final Map<String, BufferedImage> getSettingsSources() {
                return Collections.unmodifiableMap(settingsSources);
            }

            public static final void loadSettingsSources() {
                settingsSources.put(homeButton, loadSources(homeButton));
                settingsSources.put(roundedVolumeOn, loadSources(roundedVolumeOn));
                settingsSources.put(roundedVolumeOff, loadSources(roundedVolumeOff));
                settingsSources.put(roundedSettingsButton, loadSources(roundedSettingsButton));
                settingsSources.put(themesButton, loadSources(themesButton));
                settingsSources.put(backToPlayButton, loadSources(backToPlayButton));
            }
        }

        public static final class LevelAssets {
            public static final int levelButtonHeight = (int) (135 * Window.scala);
            public static final int levelButtonWidth = (int) (192 * Window.scala);
            public static final int levelButtonDistance = (int) (50 * Window.scala);
            public static final int levelButtonSpacingY = (int) (15 * Window.scala);
            public static final int leftLevelButtonX = menuX + levelButtonDistance;
            public static final int rightLevelButtonX = rightMenuBorder - levelButtonDistance - levelButtonWidth;
            public static final int topLevelbuttonY = menuY + levelButtonDistance;
            public static final int botLevelButtonY = topLevelbuttonY + levelButtonHeight + levelButtonSpacingY;
            public static final String levelOneSource = "level_one";
            public static final String levelTwoSource = "level_two";
            public static final String levelThreeSource = "level_three";
            public static final String levelFourSource = "level_four";

            private static final Map<String, Pair<BufferedImage, Integer>> levelSources = new HashMap<>();

            public static final Map<String, Pair<BufferedImage, Integer>> getLevelSources() {
                return Collections.unmodifiableMap(levelSources);
            }

            public static final void loadLevelSources() {
                levelSources.put(levelOneSource, new Pair<BufferedImage,Integer>(loadSources(levelOneSource), 0));
                levelSources.put(levelTwoSource, new Pair<BufferedImage,Integer>(loadSources("empty_level"), 1));
                levelSources.put(levelThreeSource, new Pair<BufferedImage,Integer>(loadSources("empty_level"), 2));
                levelSources.put(levelFourSource, new Pair<BufferedImage,Integer>(loadSources("empty_level"), 3));
            }        
        }
    }

    public static final class Level {
        public static final String levelOne = "level_one_data";
        public static final String levelSprites = "platform_ladder";
        public static final int blackBlock = 0;
        public static final int platformBlock = 1;
        public static final int coloredLadder = 2;
        public static final int whiteLadder = 3;
        public static final int blockWithUpperLadder = 4;
        public static final int blockWithLowerLadder = 5;
        public static final int blockWithDoubleLadder = 6;
        public static final int levelSpritesLength = 7;
        public static final int numLevelsButtons = 4;
        public static final int platformBlockPadding = Math.round(4 * Window.SCALE * Window.scala);
        public static final int ladderPadding = Math.round(3 * Window.SCALE * Window.scala);
        public static final int princessPadding = 6;
    }

    public static final class Audio {
        public final static String menuMusic0 = "soundtrack.wav";
        public final static String menuMusic1 = "soundtrack1.wav";
        public final static String gameMusic0 = "game.wav";
        public final static String gameMusic1 = "game1.wav";
        public final static int numThemesButtons = 2;
        public final static int numGameThemesButtons = 2;
        public final static float baseVolume = 0.03f;
        public final static int gainMultiplier = 20;

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

    public static final class Physics {
        public static final float jumpSpeed = -2.4f * Window.scala;
        public static final float gravity = 0.04f * Window.scala;
        public static final float speedInAirMultiplierPlayer = 0.6f;
        public static final float speedInAirMultiplierBarrel = 0.4f;
        public static final float fallingSpeed = 1.5f * Window.scala;
        public static final float jumpGravityMultiplier = 1.3f;
    }

    public static final class Barrel {
        public static final int barrelDimension = (int) (48 * Window.scala);
        public static final int barrelWidth = (int) (36 * Window.scala);
        public static final int barrelHeight = (int) (30 * Window.scala);
        public static final Direction firstLevelDirection = Direction.RIGHT;
        public static final float velocity = 2f * Window.scala;
        public static final int spawnDelay = 3;
        public static final int doubleDamageProbability = 3;
        public static final int totalProbability = 10;
    }

    public static final class Player {
        public static final float levelOneStartingPlayerX = Window.SCALED_TILES_SIZE;
        public static final float levelOneStartingPlayerY = Window.SCALED_TILES_SIZE * 12 + Level.platformBlockPadding;
        public static final int playerDimension = (int) (48 * Window.scala);
        public static final float velocity = 2f * Window.scala;
        public static final int movementAni = 0;

        private static final Map<Integer, BufferedImage> playerSources = new HashMap<>();

        public static final Map<Integer, BufferedImage> getPlayerSources() {
            return Collections.unmodifiableMap(playerSources);
        }

        public static final void loadPlayerSources() {
            playerSources.put(0, loadSources("mario_movement"));
        }
    }
    
    public static final class Monkey {
        public static final float levelOneStartingMonkeyX = Window.SCALED_TILES_SIZE / 2f;
        public static final float levelOneStartingMonkeyY = Window.SCALED_TILES_SIZE * 2 + Level.platformBlockPadding;
        public static final int monkeyWidth = (int) (120 * Window.scala);
        public static final int monkeyHeight = (int) (96 * Window.scala);
    }

    public static final class Princess {
        public static final float levelOneStartingPrincessX = Window.SCALED_TILES_SIZE * 5;
        public static final float levelOneStartingPrincessY = Window.SCALED_TILES_SIZE - Level.princessPadding;
        public static final int princessWidth = (int) (48 * Window.scala);
        public static final int princessHeight = (int) (66 * Window.scala);
    }
}
