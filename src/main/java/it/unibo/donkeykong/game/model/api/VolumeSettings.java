package it.unibo.donkeykong.game.model.api;

import java.awt.event.MouseEvent;
import java.util.Optional;

/**
 * Model for the volume settings.
 */
public interface VolumeSettings {

    /**
     * Method to mute or unmute the game musics.
     * 
     * @param e the mouse event to check if is pressing mute, unmute or no button.
     * @return true if pressed mute game, false if pressed sound game, empty if no button pressed.
     */
    Optional<Boolean> mute(MouseEvent e);

    /**
     * Method to set the theme of the game or the menu.
     * 
     * @param e the mouse event to check if is pressing button 1 or 2, then set its theme.
     */
    void setTheme(MouseEvent e);

}
