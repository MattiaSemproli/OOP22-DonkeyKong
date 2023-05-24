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
    FALLING;

    private static PlayerIdle playerIdle = STOP;

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
}