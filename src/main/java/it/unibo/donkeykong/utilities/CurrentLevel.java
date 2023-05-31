package it.unibo.donkeykong.utilities;

/**
 * Enum representing levels.
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
     * Get the current level.
     * 
     * @return the current level.
     */
    public static CurrentLevel getCurrentLevel() {
        return level;
    }

    /**
     * Set the current level.
     * 
     * @param lvl the new level.
     */
    public static void setCurrentLevel(final CurrentLevel lvl) {
        level = lvl;
    }

}
