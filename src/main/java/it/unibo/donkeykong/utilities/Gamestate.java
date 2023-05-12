package it.unibo.donkeykong.utilities;

/**
 * Enum representing the state of the game.
 */
public enum Gamestate {
    /**
     * Currently in the menu.
     */
    MENU,
    /**
     * Currently in the settings.
     */
    SETTINGS,
    /**
     * Currently choosing the level to play.
     */
    CHOSING_LEVELS,
    /**
     * Currently playing.
     */
    PLAYING,
    /**
     * Currently in game pause.
     */
    PAUSE,
    /**
     * Currently in the win view.
     */
    WIN,
    /**
     * Currently in you died (death = lose) view.
     */
    DEATH, 
    /**
     * Currently exiting the game.
     */
    EXIT;

    private static Gamestate gamestate = MENU;

    /**
     * @return the currently state of the game
     */
    public static Gamestate getGamestate() {
        return gamestate;
    }

    /**
     * @param gs set the state of the game
     */
    public static void setGamestate(final Gamestate gs) {
        gamestate = gs;
    }

}
