package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.model.impl.EndPause;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.impl.EndPauseView;

/**
 * Pause controller.
 */
public class EndPauseController {

    private final Application application;
    private final EndPauseView endPauseView;
    private final EndPause endPause;

    /**
     * Constructor.
     * 
     * @param application the application.
     */
    public EndPauseController(final ApplicationImpl application) {
        this.application = application;
        this.endPauseView = new EndPauseView(this);
        this.endPause = new EndPause();
    }

    public final void stopTimer() {
        this.application.getGameController().stopTimer();
    }

    public final void startTimer() {
        this.application.getGameController().startTimer();
    }

    public final void startGameController() {
        this.application.startGameController();
    }

    /**
     * Get the seconds passed from the game controller.
     * 
     * @return the seconds from the game controller in float.
     */
    public final float getSecondsFromGameController() {
        return this.application.getGameController().getSeconds();
    }

    public final void applyGamestate(final Gamestate gamestate) {
        this.endPause.applyGamestate(gamestate);
    }

    public final EndPauseView getView() {
        return this.endPauseView;
    }
}
