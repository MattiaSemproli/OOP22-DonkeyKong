package it.unibo.donkeykong.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.controller.impl.MainMenuController;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;

public class MainMenuView implements GameEngine{

    private final MainMenuController menuController;

    public MainMenuView(final MainMenuController menuController) {
        this.menuController = menuController;
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
                    menuY + MenuAssets.menuTextureBox / 10, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);
        g.drawImage(new ImageIcon("src/main/res/levels_button.png").getImage(), 
                    menuX + (MenuAssets.menuTextureBox - MenuAssets.buttonWidth) / 2, 
                    menuY + MenuAssets.menuTextureBox / 10 + MenuAssets.buttonHeight, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);
    }

    private void drawLegend(final int menuX, final int menuY, final Graphics g){
        g.drawImage(new ImageIcon("src/main/res/aKey.png").getImage(), 
                    menuX + MenuAssets.menuTextureBox / 10, menuY + MenuAssets.buttonHeight * 3,
                    MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(new ImageIcon("src/main/res/dKey.png").getImage(), 
                    menuX + MenuAssets.menuTextureBox / 10, menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox,
                    MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(new ImageIcon("src/main/res/wKey.png").getImage(), 
                    menuX + MenuAssets.menuTextureBox / 10, menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 2,
                    MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(new ImageIcon("src/main/res/sKey.png").getImage(), 
                    menuX + MenuAssets.menuTextureBox / 10, menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 3,
                    MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        
        g.drawImage(new ImageIcon("src/main/res/left_arrow.png").getImage(), 
                    menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox, menuY + MenuAssets.buttonHeight * 3,
                    MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(new ImageIcon("src/main/res/right_arrow.png").getImage(), 
                    menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox, menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox,
                    MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(new ImageIcon("src/main/res/up_arrow.png").getImage(), 
                    menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox, menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 2,
                    MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(new ImageIcon("src/main/res/down_arrow.png").getImage(), 
                    menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox, menuY + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 3,
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
                    menuY + MenuAssets.menuTextureBox / 2  + MenuAssets.legendKeyBox * 2);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("TO MOVE DOWN", 
                    menuX + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2, 
                    menuY + MenuAssets.menuTextureBox / 2 + MenuAssets.legendKeyBox * 3);   
    }

    private void drawUtilityButtons(final int menuX, final int menuY, final Graphics g){
        g.drawImage(new ImageIcon("src/main/res/settings_button.png").getImage(),
                    menuX + MenuAssets.menuTextureBox / 12, 
                    menuY + MenuAssets.menuTextureBox - MenuAssets.buttonHeight - MenuAssets.menuTextureBox / 8, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);
        g.drawImage(new ImageIcon("src/main/res/quit_button.png").getImage(),
                    menuX + MenuAssets.menuTextureBox - MenuAssets.buttonWidth - MenuAssets.menuTextureBox / 12, 
                    menuY + MenuAssets.menuTextureBox - MenuAssets.buttonHeight - MenuAssets.menuTextureBox / 8, 
                    MenuAssets.buttonWidth, MenuAssets.buttonHeight, null);     
    }
}
