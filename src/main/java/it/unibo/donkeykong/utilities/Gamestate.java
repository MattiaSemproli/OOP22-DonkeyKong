package it.unibo.donkeykong.utilities;

/**
 * Enum representing states of game.
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
     * Currently in death (death = lose) view.
     */
    DEATH, 
    /**
     * Currently exiting the game.
     */
    EXIT;

    private static Gamestate gamestate = MENU;

    /**
     * Get current Gamestate.
     * 
     * @return the current state of the game.
     */
    public static Gamestate getGamestate() {
        return gamestate;
    }

    /**
     * Set new Gamestate.
     * 
     * @param gs the new Gamestate.
     */
    public static void setGamestate(final Gamestate gs) {
        gamestate = gs;
    }
}
