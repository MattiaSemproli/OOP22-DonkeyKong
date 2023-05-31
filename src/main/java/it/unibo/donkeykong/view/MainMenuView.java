package it.unibo.donkeykong.view;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.MainMenuController;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;

/**
 * Main menu view, manages main menu graphics.
 */
public class MainMenuView implements GameEngine {

    private final MainMenuController menuController;

    /**
     * Constructor.
     * 
     * @param menuController the linked controller.
     */
    public MainMenuView(final MainMenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public final void update() {
    }

    @Override
    public final void draw(final Graphics g) {
        this.drawBackgroundAssets(g);
        this.drawLegend(g);
        this.menuController.getButtonsFromModel()
                           .forEach((b, i) -> g.drawImage(i, 
                                                      b.getButtonPos().getX(), 
                                                      b.getButtonPos().getY(),
                                                      b.getButtonDim().getX(),
                                                      b.getButtonDim().getY(), null));
    }

    private void drawBackgroundAssets(final Graphics g) {
        g.drawImage(getMenuSources().get(MenuAssets.menuBackground), 
                    0, 
                    0, 
                    Window.GAME_WIDTH, 
                    Window.GAME_HEIGHT, null);
        g.drawImage(getMenuSources().get(MenuAssets.menuTexture), 
                    menuX, 
                    menuY, 
                    MenuAssets.menuTextureBox, 
                    MenuAssets.menuTextureBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.title), 
                    MenuAssets.titleX, 
                    MenuAssets.titleY, 
                    MenuAssets.titleWidth, 
                    MenuAssets.titleHeight, null);
    }

    /**
     * TEMPORARY JAVADOC OTHERWISE THE CHECKSTYLE WILL COMPLAIN EVEN IF IT IS ONLY A MEMO TO REMEMBER IT.
     * TO FIX THIS ONE. 
     * 
     * @param g graphics to draw.
     */
    private void drawLegend(final Graphics g) {
        g.drawImage(getMenuSources().get(MenuAssets.aKey),
                menuX + MenuAssets.menuTextureBox / 10, menuY + MenuAssets.buttonHeight * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.dKey),
                menuX + MenuAssets.menuTextureBox / 10, menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.wKey),
                menuX + MenuAssets.menuTextureBox / 10,
                menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 2,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.sKey),
                menuX + MenuAssets.menuTextureBox / 10,
                menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);

        g.drawImage(getMenuSources().get(MenuAssets.left),
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox, menuY + MenuAssets.buttonHeight * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.right),
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox,
                menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.up),
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox,
                menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 2,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.down),
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox,
                menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);

        final Font font = new Font("Arial", Font.BOLD, 10 * 2);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE LEFT",
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                menuY + MenuAssets.menuTextureBox / 2);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE RIGHT",
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                menuY + MenuAssets.menuTextureBox / 2 + MenuAssets.legendKeyBox);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE UP",
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                menuY + MenuAssets.menuTextureBox / 2 + MenuAssets.legendKeyBox * 2);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE DOWN",
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                menuY + MenuAssets.menuTextureBox / 2 + MenuAssets.legendKeyBox * 3);
    }

}
