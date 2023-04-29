package it.unibo.donkeykong.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;

public class MainMenuView implements GameEngine{

    public MainMenuView() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(final Graphics g) {
        final int menuX = Window.GAME_WIDTH / 2 - MenuAssets.menuTextureBox / 2;
        final int menuY = Window.GAME_HEIGHT / 2 - MenuAssets.menuTextureBox / 2;

        this.drawBackgroundAssets(menuX, menuY, g);
        this.drawFuncButtons(menuX, menuY, g);
        this.drawLegend(menuX, menuY, g);
        this.drawUtilityButtons(menuX, menuY, g);
        
    }

    private void drawBackgroundAssets(final int menuX, final int menuY, final Graphics g){
        g.drawImage(new ImageIcon("src/main/res/menu_background.png").getImage(), 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT, null);
        g.drawImage(new ImageIcon("src/main/res/menu_layout.png").getImage(), 
                    menuX, menuY, 
                    MenuAssets.menuTextureBox, MenuAssets.menuTextureBox, null);
        g.drawImage(new ImageIcon("src/main/res/title.png").getImage(), 
                    menuX + 115, menuY - 65, 
                    250, 125, null);
    }

    private void drawFuncButtons(final int menuX, final int menuY, final Graphics g){
        g.drawImage(new ImageIcon("src/main/res/play_button.png").getImage(), 
                    menuX + (MenuAssets.menuTextureBox - MenuAssets.buttonWidth) / 2, 
                    menuY + MenuAssets.menuTextureBox/10, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);
        g.drawImage(new ImageIcon("src/main/res/button_layout.png").getImage(), 
                    menuX + (MenuAssets.menuTextureBox - MenuAssets.buttonWidth) / 2, 
                    menuY + MenuAssets.menuTextureBox/10 + MenuAssets.buttonHeight, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);
        g.setColor(new Color(181,78,47,255));
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("LEVELS", 
                    menuX + (MenuAssets.menuTextureBox - MenuAssets.buttonWidth) / 2 + 30, 
                    menuY + MenuAssets.menuTextureBox/10 + MenuAssets.buttonHeight + 45);
    }

    private void drawLegend(final int menuX, final int menuY, final Graphics g){
        g.setColor(new Color(238,163,46,255));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Space to jump", menuX + MenuAssets.menuTextureBox/10 + 10, menuY + MenuAssets.menuTextureBox/2);
        g.setColor(new Color(238,163,46,255));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("R to move right", menuX + MenuAssets.menuTextureBox/10 + 10, menuY + MenuAssets.menuTextureBox/2+20);
        g.setColor(new Color(238,163,46,255));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("L to move left", menuX + MenuAssets.menuTextureBox/10 + 10, menuY + MenuAssets.menuTextureBox/2+40);
        g.setColor(new Color(238,163,46,255));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("W to move up on a ladder", menuX + MenuAssets.menuTextureBox/10 + 10, menuY + MenuAssets.menuTextureBox/2+60);
        g.setColor(new Color(238,163,46,255));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("S to move down on a ladder", menuX + MenuAssets.menuTextureBox/10 + 10, menuY + MenuAssets.menuTextureBox/2+80);
    }

    private void drawUtilityButtons(final int menuX, final int menuY, final Graphics g){
        g.drawImage(new ImageIcon("src/main/res/settings_button.png").getImage(),
                    menuX + MenuAssets.menuTextureBox/10, 
                    menuY + MenuAssets.menuTextureBox - MenuAssets.buttonHeight - MenuAssets.menuTextureBox/8, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);
        g.drawImage(new ImageIcon("src/main/res/quit_button.png").getImage(),
                    menuX + MenuAssets.menuTextureBox - MenuAssets.buttonWidth - MenuAssets.menuTextureBox/10, 
                    menuY + MenuAssets.menuTextureBox - MenuAssets.buttonHeight - MenuAssets.menuTextureBox/8, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);     
    }
}