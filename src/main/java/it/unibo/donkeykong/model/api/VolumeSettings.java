package it.unibo.donkeykong.model.api;

import java.awt.event.MouseEvent;
import java.util.Optional;

/**
 * VolumeSettings interface, models audio functions.
 */
public interface VolumeSettings {

    /**
     * Mute or unmute game volume.
     * 
     * @param e the mouse event to be checked.
     * @return true if pressed mute game, false if pressed sound game, empty if no button pressed.
     */
    Optional<Boolean> mute(MouseEvent e);

    /**
     * Set game or menu theme
     * 
     * @param e the mouse event to check which theme button is pressed.
     */
    void setTheme(MouseEvent e);

}
