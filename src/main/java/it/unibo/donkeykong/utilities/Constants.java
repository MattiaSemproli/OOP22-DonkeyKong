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
        public final static int UPS_SET = 200;
        public final static double NANOSECOND = 1000000000.0;
        public final static int FRAME_DEFAULT = 0;
        public final static int UPDATES_DEFAULT = 0;
        public final static int DELTAF_DEFAULT = 0;
        public final static int DELTAU_DEFAULT = 0;
    } 

}
