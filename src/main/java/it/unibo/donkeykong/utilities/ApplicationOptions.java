package it.unibo.donkeykong.utilities;

/**
 * Enum representing the state of the game.
 */
public enum ApplicationOptions {

    /**
     * Currently volume on.
     */
    VOLUME_ON,
    /**
     * Currently volume off.
     */
    VOLUME_OFF;

    private static ApplicationOptions volume = VOLUME_ON;

    /**
     * @return the currently status of volume.
     */
    public static ApplicationOptions getVolumeStatus() {
        return volume;
    }

    /**
     * @param volumeStatus set the status of volume.
     */
    public static void setVolumeStatus(final ApplicationOptions volumeStatus) {
        volume = volumeStatus;
    }

}
