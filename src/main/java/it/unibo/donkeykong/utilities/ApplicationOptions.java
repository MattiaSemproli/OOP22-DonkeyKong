package it.unibo.donkeykong.utilities;

/**
 * Enum representing states of volume.
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
     * @return the current volume status.
     */
    public static ApplicationOptions getVolumeStatus() {
        return volume;
    }

    /**
     * @param volumeStatus the volume status.
     */
    public static void setVolumeStatus(final ApplicationOptions volumeStatus) {
        volume = volumeStatus;
    }

}
