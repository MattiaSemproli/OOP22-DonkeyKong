package it.unibo.donkeykong.utilities;

/**
 * Enum representing player idles.
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
     * Get the current idle of the player.
     * 
     * @return the current idle of the player.
     */
    public static PlayerIdle getPlayerIdle() {
        return playerIdle;
    }

    /**
     * Set new idle of the player.
     * 
     * @param idle the new player's idle.
     */
    public static void setPlayerIdle(final PlayerIdle idle) {
        playerIdle = idle;
    }

    /**
     * Get the current idle of the princess.
     * 
     * @return the current idle of the princess.
     */
    public static PlayerIdle getPrincessIdle() {
        return princessIdle;
    }

    /**
     * Set new idle of the princess.
     * 
     * @param idle the new princess's idle.
     */
    public static void setPrincessIdle(final PlayerIdle idle) {
        princessIdle = idle;
    }
}
