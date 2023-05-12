package it.unibo.donkeykong.game.model.api;

import java.util.ArrayList;

import it.unibo.donkeykong.game.model.impl.ButtonImpl;

/**
 * View model.
 */
public interface ViewModel {
    
    /** 
     * @return all the buttons.
     */
    ArrayList<ButtonImpl> getButtons();

}
