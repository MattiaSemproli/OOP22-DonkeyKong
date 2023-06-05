package it.unibo.donkeykong.model.api;

import it.unibo.donkeykong.utilities.Gamestate;

/**
 * viewModel interface, models a model of a view.
 */
public interface ViewModel {
    /**
     * Apply gamestate based on what happened.
     * 
     * @param gamestate the new gamestate to apply.
     */
    void applyGamestate(Gamestate gamestate);
}
