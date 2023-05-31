package it.unibo.donkeykong.utilities;

/**
 * Player idle enum.
 */
public enum PlayerIdle {
    /**
     * When the player isn't moving or falling.
     */
    STOP,
    /**
     * When the player is moving.
     */
    RUN,
    /**
     * When the player is jumping.
     */
    JUMP,
    /**
     * When the player is falling.
     */
    FALLING,
    /**
     * When the player is stopped when climbing a ladder.
     */
    STOPCLIMBING,
    /**
     * When the player is climbing.
     */
    CLIMBING;

    private static PlayerIdle playerIdle = STOP;
    private static PlayerIdle princessIdle = STOP;

    /**
     * @return the currently state of the player.
     */
    public static PlayerIdle getPlayerIdle() {
        return playerIdle;
    }

    /**
     * @param idle set the current idle of the player.
     */
    public static void setPlayerIdle(final PlayerIdle idle) {
        playerIdle = idle;
    }

    /**
     * @return the currently state of the princess.
     */
    public static PlayerIdle getPrincessIdle() {
        return princessIdle;
    }

    /**
     * @param idle set the current idle of the princess.
     */
    public static void setPrincessIdle(final PlayerIdle idle) {
        princessIdle = idle;
    }
}
