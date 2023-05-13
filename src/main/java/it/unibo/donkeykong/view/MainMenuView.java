package it.unibo.donkeykong.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.MainMenuController;
import it.unibo.donkeykong.utilities.ResourceFuncUtilities;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

/**
 * Main menu view.
 */
public class MainMenuView implements GameEngine {

    private final MainMenuController menuController;

    /**
     * 
     * @param menuController set the controller to this view.
     */
    public MainMenuView(final MainMenuController menuController) {
        this.menuController = menuController;
    }

    @Override
    public void update() {
        this.menuController.getMainMenu().update();
    }

    @Override
    public final void draw(final Graphics g) {
        this.drawBackgroundAssets(g);
        this.drawLegend(g);
        this.menuController.getMainMenu().draw(g);
    }

    private void drawBackgroundAssets(final Graphics g) {
        g.drawImage(ResourceFuncUtilities.getSources(MenuAssets.menuBackground), 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT,
                null);
        g.drawImage(ResourceFuncUtilities.getSources(MenuAssets.menuTexture),
                menuX, menuY,
                MenuAssets.menuTextureBox, MenuAssets.menuTextureBox, null);
        g.drawImage(ResourceFuncUtilities.getSources("title.png"),
                menuX + 115, menuY - 65,
                250, 125, null);
    }

    private void drawLegend(final Graphics g) {
        g.drawImage(ResourceFuncUtilities.getSources("aKey.png"),
                menuX + MenuAssets.menuTextureBox / 10, menuY + MenuAssets.buttonHeight * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(ResourceFuncUtilities.getSources("dKey.png"),
                menuX + MenuAssets.menuTextureBox / 10, menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(ResourceFuncUtilities.getSources("wKey.png"),
                menuX + MenuAssets.menuTextureBox / 10,
                menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 2,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(ResourceFuncUtilities.getSources("sKey.png"),
                menuX + MenuAssets.menuTextureBox / 10,
                menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);

        g.drawImage(ResourceFuncUtilities.getSources("left_arrow.png"),
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox, menuY + MenuAssets.buttonHeight * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(ResourceFuncUtilities.getSources("right_arrow.png"),
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox,
                menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(ResourceFuncUtilities.getSources("up_arrow.png"),
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox,
                menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 2,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(ResourceFuncUtilities.getSources("down_arrow.png"),
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox,
                menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("TO MOVE LEFT",
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                menuY + MenuAssets.menuTextureBox / 2);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("TO MOVE RIGHT",
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                menuY + MenuAssets.menuTextureBox / 2 + MenuAssets.legendKeyBox);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("TO MOVE UP",
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                menuY + MenuAssets.menuTextureBox / 2 + MenuAssets.legendKeyBox * 2);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("TO MOVE DOWN",
                menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                menuY + MenuAssets.menuTextureBox / 2 + MenuAssets.legendKeyBox * 3);
    }

}
