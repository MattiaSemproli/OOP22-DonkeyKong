package it.unibo.donkeykong.utilities;

public enum Gamestate {
    MENU,

    SETTINGS,

    CHOSING_LEVELS,

    SHOP,

    PLAYING,

    PAUSE,

    WIN,

    DEATH;

    public static Gamestate gamestate = MENU;

    public static Gamestate getGamestate() {
        return gamestate;
    }

    public static void setGamestate(final Gamestate gs) {
        gamestate = gs;
    }

}
