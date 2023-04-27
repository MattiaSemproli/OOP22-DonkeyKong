package it.unibo.donkeykong.view;

import java.awt.Graphics;
import javax.swing.ImageIcon;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.utilities.Constants.Window;

public class MainMenuView implements GameEngine{

    public MainMenuView() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(final Graphics g) {
        g.drawImage(new ImageIcon("src/main/res/menu_background.png").getImage(), 0, 0, Window.GAME_WIDTH, Window.GAME_HEIGHT, null);
        g.drawImage(new ImageIcon("src/main/res/menu_layout.png").getImage(), 125, 145, 400, 400, null);
        g.drawImage(new ImageIcon("src/main/res/play_button.png").getImage(), 250, 175, 150, 100, null);
        g.drawImage(new ImageIcon("src/main/res/settings_button.png").getImage(), 250, 275, 150, 100, null);
    }
}