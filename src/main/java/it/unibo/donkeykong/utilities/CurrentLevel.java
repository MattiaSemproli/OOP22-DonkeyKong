package it.unibo.donkeykong.utilities;

/**
 * Level enum.
 */
public enum CurrentLevel {
    /**
     * Level one.
     */
    ONE,
    /**
     * Level two.
     */
    TWO,
    /**
     * Level three.
     */
    THREE,
    /**
     * Level four.
     */
    FOUR;

    private static CurrentLevel level = ONE;

    /**
     * @return the current level.
     */
    public static CurrentLevel getCurrentLevel() {
        return level;
    }

    /**
     * @param lvl set the current level.
     */
    public static void setCurrentLevel(final CurrentLevel lvl) {
        level = lvl;
    }

}
