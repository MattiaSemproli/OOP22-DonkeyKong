package it.unibo.donkeykong.view;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.PauseController;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;

/**
 * Pause view.
 */
public final class PauseView implements GameEngine {

    private final PauseController pauseController;

    public PauseView(final PauseController pauseController) {
        this.pauseController = pauseController;
    }

    @Override
    public void update() {
        this.pauseController.getPause().update();
    }

    @Override
    public void draw(Graphics g) {
        final Graphics2D pause;
        if (g instanceof Graphics2D) {
            pause = (Graphics2D) g;
            pause.setColor(new Color(0, 0, 0, 128));
            pause.fillRect(0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT);
            pause.drawImage(menuSources.get(MenuAssets.menuTexture), menuX, menuY, MenuAssets.menuTextureBox, MenuAssets.menuTextureBox, null);
            this.pauseController.getPause().draw(g);
        }
    }
}