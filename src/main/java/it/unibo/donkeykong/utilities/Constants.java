package it.unibo.donkeykong.utilities;

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
        public static final String title = "src/main/res/title.png";
        public static final String menuBackground = "src/main/res/menu_background.png";
        public static final String menuTexture = "src/main/res/menu_layout.png";
        public static final String gameLegend = "";
        public static final String playButton = "src/main/res/play_button.png";
        public static final String settingsButton = "src/main/res/settings_button.png";
        public static final String quitButton = "src/main/res/quit_button.png";
        public static final String levelsButton = "src/main/res/levels_button.png";
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

        public static final class SettingsAssets {
            public static final String homeButton = "src/main/res/square_home_button.png";
            public static final String roundedVolumeOn = "src/main/res/square_volume_on_button.png";
            public static final String roundedVolumeOff = "src/main/res/square_volume_off_button.png";
            public static final int numVolumeButtons = 2;
            public static final int volOnB = 0;
            public static final int volOffB = 1;
            public static final int squareButtonSize = 75;
            public static final int homeButtonRightDistance = 125;
            public static final int homeButtonBottomDistance = 150;
        }
    }

    public static final class Barrel {
        public static final float initialPositionX = 0.f;
        public static final float initialPositionY = 0.f;
    }
}
