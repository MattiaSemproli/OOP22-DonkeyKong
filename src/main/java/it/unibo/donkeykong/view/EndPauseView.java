package it.unibo.donkeykong.view;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.EndPauseController;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;

/**
 * Pause view.
 */
public final class EndPauseView implements GameEngine {

    private final EndPauseController endPauseController;

    /**
     * Constructor.
     * 
     * @param endPauseController set the controller to this view.
     */
    public EndPauseView(final EndPauseController endPauseController) {
        this.endPauseController = endPauseController;
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(final Graphics g) {
        final Graphics2D pause;
        if (g instanceof Graphics2D) {
            pause = (Graphics2D) g;
            pause.setColor(new Color(0, 0, 0, SettingsAssets.pauseBgOpacity));
            pause.fillRect(0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT);
            pause.drawImage(getMenuSources().get(MenuAssets.menuTexture), 
                            menuX, 
                            menuY, 
                            MenuAssets.menuTextureBox, 
                            MenuAssets.menuTextureBox, null);
            this.endPauseController.getButtonsFromModel()
                                .forEach((b, i) -> g.drawImage(i, 
                                                               b.getButtonPos().getX(), 
                                                               b.getButtonPos().getY(),
                                                               b.getButtonDim().getX(),
                                                               b.getButtonDim().getY(), null));
            this.drawText(g);
            if (Gamestate.getGamestate().equals(Gamestate.PAUSE)) {
                this.endPauseController.getAlternativeButtonsFromModel()
                                    .forEach((rectangle, image) -> g.drawImage(image, 
                                                                               rectangle.x, 
                                                                               rectangle.y, 
                                                                               rectangle.width,
                                                                               rectangle.height, null));
            } else {
                final Font font = new Font("Arial", Font.BOLD, MenuAssets.fontSize);
                g.setColor(Color.WHITE);
                g.setFont(font);
                final String txt = "Time: " + this.endPauseController.getSecondsFromGameController();
                final int textWidth = g.getFontMetrics(font).stringWidth(txt);
                g.drawString(txt,
                             menuX + (MenuAssets.menuTextureBox - textWidth) / 2,
                             menuY + MenuAssets.menuTextureBox / 2);
            }
        }
    }

    private void drawText(final Graphics g) {
        if (Gamestate.getGamestate().equals(Gamestate.PAUSE)) {
            g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                    menuX + (MenuAssets.menuTextureBox - SettingsAssets.pauseTextWidth) / 2,
                    menuY - (SettingsAssets.pauseTextHeight / 2),
                    SettingsAssets.pauseTextWidth,
                    SettingsAssets.pauseTextHeight,
                    null);
        } else if (Gamestate.getGamestate().equals(Gamestate.DEATH)) {
            g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                    menuX + (MenuAssets.menuTextureBox - SettingsAssets.loseTextWidth) / 2,
                    menuY - (SettingsAssets.loseTextHeight / 2),
                    SettingsAssets.loseTextWidth,
                    SettingsAssets.loseTextHeight,
                    null);
        } else if (Gamestate.getGamestate().equals(Gamestate.WIN)) {
            g.drawImage(SettingsAssets.getTextSources().get(Gamestate.getGamestate()), 
                    menuX + (MenuAssets.menuTextureBox - SettingsAssets.winTextWidth) / 2,
                    menuY - (SettingsAssets.winTextHeight / 2),
                    SettingsAssets.winTextWidth,
                    SettingsAssets.winTextHeight,
                    null);
        }
    }
}
