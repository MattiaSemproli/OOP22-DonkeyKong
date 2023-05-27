package it.unibo.donkeykong.utilities;

public enum CurrentLevel {
    ONE,
    TWO,
    THREE;

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
