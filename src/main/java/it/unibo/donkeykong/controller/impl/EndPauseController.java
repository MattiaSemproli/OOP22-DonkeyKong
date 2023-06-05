package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.controller.api.Controller;
import it.unibo.donkeykong.model.impl.EndPause;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.api.View;
import it.unibo.donkeykong.view.impl.EndPauseView;

/**
 * End pause controller, manages end pause view and model and interaction.
 */
public class EndPauseController implements Controller {

    private final Application application;
    private final EndPauseView endPauseView;
    private final EndPause endPause;

    /**
     * Constructor.
     * 
     * @param application the application.
     */
    public EndPauseController(final Application application) {
        this.application = application;
        this.endPauseView = new EndPauseView(this);
        this.endPause = new EndPause();
    }

    /**
     * Start game controller.
     */
    public void startGameController() {
        this.application.startGameController();
    }

    /**
     * Start the timer.
     */
    public final void startTimer() {
        this.application.getGameController().startTimer();
    }

    /**
     * Stop the timer.
     */
    public void stopTimer() {
        this.application.getGameController().stopTimer();
    }

    /**
     * Get the seconds passed from the game controller.
     * 
     * @return the seconds from the game controller in float.
     */
    public final float getSecondsFromGameController() {
        return this.application.getGameController().getSeconds();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyGamestate(final Gamestate gamestate) {
        this.endPause.applyGamestate(gamestate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView() {
        return this.endPauseView;
    }
}
