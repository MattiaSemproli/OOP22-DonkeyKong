package it.unibo.donkeykong.game.model.impl;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

public class MainMenu implements GameEngine {

    @Override
    public void draw(Graphics g) {
        this.drawFuncButtons(g);
        this.drawUtilityButtons(g);
    }

    private void drawFuncButtons(final Graphics g){
        g.drawImage(new ImageIcon("src/main/res/play_button.png").getImage(), 
                    menuX + (MenuAssets.menuTextureBox - MenuAssets.buttonWidth) / 2, 
                    menuY + MenuAssets.menuTextureBox / 10, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);
        g.drawImage(new ImageIcon("src/main/res/levels_button.png").getImage(), 
                    menuX + (MenuAssets.menuTextureBox - MenuAssets.buttonWidth) / 2, 
                    menuY + MenuAssets.menuTextureBox / 10 + MenuAssets.buttonHeight, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);
    }

    private void drawUtilityButtons(final Graphics g){
        g.drawImage(new ImageIcon("src/main/res/settings_button.png").getImage(),
                    menuX + MenuAssets.menuTextureBox / 12, 
                    menuY + MenuAssets.menuTextureBox - MenuAssets.buttonHeight - MenuAssets.menuTextureBox / 8, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);
        g.drawImage(new ImageIcon("src/main/res/quit_button.png").getImage(),
                    menuX + MenuAssets.menuTextureBox - MenuAssets.buttonWidth - MenuAssets.menuTextureBox / 12, 
                    menuY + MenuAssets.menuTextureBox - MenuAssets.buttonHeight - MenuAssets.menuTextureBox / 8, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);
    }

    @Override
    public void update() {
    }
}
