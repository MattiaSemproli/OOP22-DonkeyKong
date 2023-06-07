package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.view.ViewConstants.Window.GAME_HEIGHT;
import static it.unibo.donkeykong.view.ViewConstants.Window.GAME_WIDTH;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import it.unibo.donkeykong.controller.api.Application;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.inputs.KeyboardInputs;
import it.unibo.donkeykong.view.inputs.MouseInputs;

/**
 * Application panel.
 */
public final class ApplicationPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final transient Application application;

    /**
     * Constructor.
     * 
     * @param application the linked application.
     */
    public ApplicationPanel(final Application application) {
        this.application = application;
        setSize();
        addKeyListener(new KeyboardInputs(this.application));
        addMouseListener(new MouseInputs(this.application));
    }

    private void setSize() {
        setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        switch (Gamestate.getGamestate()) {
            case MENU:
                this.application.getMainMenuController().getView().draw(g);
                break;
            case PLAYING:
                this.application.getGameController().getView().draw(g);
                break;
            case SETTINGS:
                this.application.getSettingsController().getView().draw(g);
                break;
            case CHOSING_LEVELS:
                this.application.getLevelsMenuController().getView().draw(g);
                break;
            case PAUSE:
            case WIN:
            case DEATH:
                this.application.getGameController().getView().draw(g);
                this.application.getEndPauseController().getView().draw(g);
                break;
            case EXIT:
            default:
                break;
        }
    }
}
