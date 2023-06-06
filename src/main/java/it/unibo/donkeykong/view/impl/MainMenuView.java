package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.MENU_X;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.MENU_Y;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.RIGHT_MENU_BORDER;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.UTILITY_BUTTON_RIGHT_BORDER_DISTANCE_X;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.controller.impl.MainMenuController;
import it.unibo.donkeykong.utilities.Constants.Audio;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;
import it.unibo.donkeykong.view.api.Button;
import it.unibo.donkeykong.view.api.View;

/**
 * Main menu view, manages main menu graphics.
 */
public class MainMenuView implements View {

    private final MainMenuController menuController;

    private final Map<Button, BufferedImage> buttons = new HashMap<>();

    /**
     * Constructor.
     * 
     * @param menuController the linked controller.
     */
    public MainMenuView(final MainMenuController menuController) {
        this.menuController = menuController;

        final Button[] funcButtons = new Button[MenuAssets.NUM_FUNCTION_BUTTONS];
        final Button[] utilityButtons = new Button[MenuAssets.NUM_UTILITY_BUTTONS];

        funcButtons[MenuAssets.PLAY_B] = new ButtonImpl(MenuAssets.funcButtonX,
                                                       MENU_Y + MenuAssets.funcButtonsDistance,
                                                       MenuAssets.buttonWidth, 
                                                       MenuAssets.buttonHeight, Gamestate.PLAYING);
        funcButtons[MenuAssets.LEVELS_B] = new ButtonImpl(MenuAssets.funcButtonX,
                                                         MENU_Y + MenuAssets.funcButtonsDistance + MenuAssets.buttonHeight,
                                                         MenuAssets.buttonWidth, 
                                                         MenuAssets.buttonHeight, Gamestate.CHOSING_LEVELS);
        utilityButtons[MenuAssets.SETTINGS_B] = new ButtonImpl(MENU_X + MenuAssets.utilityButtonLeftBorderDistanceX,
                                                              MenuAssets.utilityButtonY,
                                                              MenuAssets.buttonWidth, 
                                                              MenuAssets.buttonHeight, Gamestate.SETTINGS);
        utilityButtons[MenuAssets.QUIT_B] = new ButtonImpl(RIGHT_MENU_BORDER; - UTILITY_BUTTON_RIGHT_BORDER_DISTANCE_X;,
                                                          MenuAssets.utilityButtonY,
                                                          MenuAssets.buttonWidth, 
                                                          MenuAssets.buttonHeight, Gamestate.EXIT);

        this.buttons.put(funcButtons[MenuAssets.playB], getMenuSources().get(MenuAssets.playButton));
        this.buttons.put(funcButtons[MenuAssets.levelsB], getMenuSources().get(MenuAssets.levelsButton));
        this.buttons.put(utilityButtons[MenuAssets.settingsB], getMenuSources().get(MenuAssets.settingsButton));
        this.buttons.put(utilityButtons[MenuAssets.quitB], getMenuSources().get(MenuAssets.quitButton));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final Graphics g) {
        this.drawBackgroundAssets(g);
        this.drawLegend(g);
        this.buttons.forEach((b, bi) -> g.drawImage(bi, 
                                                    b.getButtonPos().getX(), 
                                                    b.getButtonPos().getY(),
                                                    b.getButtonDim().getX(),
                                                    b.getButtonDim().getY(), null));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(final Pair<Integer, Integer> point) {
        this.buttons.keySet().forEach(b -> {
            if (b.getCorners().contains(new Point(point.getX(), point.getY()))) {
                if (b.getButtonGamestate().equals(Gamestate.PLAYING)) {
                    AudioUtilities.playSoundtrack(Audio.gameMusic0);
                    this.menuController.startLevel();
                    this.menuController.startGameController();
                }
                this.menuController.applyGamestate(b.getButtonGamestate());
            }
        });
    }

    private void drawBackgroundAssets(final Graphics g) {
        g.drawImage(getMenuSources().get(MenuAssets.menuBackground), 
                    0, 
                    0, 
                    Window.GAME_WIDTH, 
                    Window.GAME_HEIGHT, null);
        g.drawImage(getMenuSources().get(MenuAssets.menuTexture), 
                    MENU_X, 
                    MENU_Y, 
                    MenuAssets.menuTextureBox, 
                    MenuAssets.menuTextureBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.title), 
                    MenuAssets.titleX, 
                    MenuAssets.titleY, 
                    MenuAssets.titleWidth, 
                    MenuAssets.titleHeight, null);
    }

    private void drawLegend(final Graphics g) {
        g.drawImage(getMenuSources().get(MenuAssets.aKey),
                MENU_X + MenuAssets.menuTextureBox / 10, MENU_Y + MenuAssets.buttonHeight * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.dKey),
                MENU_X + MenuAssets.menuTextureBox / 10, MENU_Y + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.wKey),
                MENU_X + MenuAssets.menuTextureBox / 10,
                MENU_Y + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 2,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.sKey),
                MENU_X + MenuAssets.menuTextureBox / 10,
                MENU_Y + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);

        g.drawImage(getMenuSources().get(MenuAssets.left),
                MENU_X + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox, MENU_Y + MenuAssets.buttonHeight * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.right),
                MENU_X + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox,
                MENU_Y + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.up),
                MENU_X + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox,
                MENU_Y + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 2,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);
        g.drawImage(getMenuSources().get(MenuAssets.down),
                MENU_X + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox,
                MENU_Y + MenuAssets.buttonHeight * 3 + MenuAssets.legendKeyBox * 3,
                MenuAssets.legendKeyBox, MenuAssets.legendKeyBox, null);

        final Font font = new Font("Arial", Font.BOLD, 10 * 2);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE LEFT",
                MENU_X + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                MENU_Y + MenuAssets.menuTextureBox / 2);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE RIGHT",
                MENU_X + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                MENU_Y + MenuAssets.menuTextureBox / 2 + MenuAssets.legendKeyBox);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE UP",
                MENU_X + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                MENU_Y + MenuAssets.menuTextureBox / 2 + MenuAssets.legendKeyBox * 2);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE DOWN",
                MENU_X + MenuAssets.menuTextureBox / 10 + MenuAssets.legendKeyBox * 2,
                MENU_Y + MenuAssets.menuTextureBox / 2 + MenuAssets.legendKeyBox * 3);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final int keyCode) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final int keyCode) {
    }
}
