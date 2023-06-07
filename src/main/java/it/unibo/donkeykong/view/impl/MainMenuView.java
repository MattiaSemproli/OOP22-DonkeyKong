package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.MENU_X;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.MENU_Y;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.RIGHT_MENU_BORDER;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.UTILITY_BUTTON_RIGHT_BORDER_DISTANCE_X;
import static it.unibo.donkeykong.utilities.ViewConstants.MenuAssets.getMenuSources;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.controller.impl.MainMenuController;
import it.unibo.donkeykong.utilities.Constants.Audio;
import it.unibo.donkeykong.utilities.ViewConstants.MenuAssets;
import it.unibo.donkeykong.utilities.ViewConstants.Window;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.Gamestate;
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

        funcButtons[MenuAssets.PLAY_B] = new ButtonImpl(MenuAssets.FUNC_BUTTON_X,
                                                        MENU_Y + MenuAssets.FUNC_BUTTON_DISTANCE,
                                                        MenuAssets.BUTTON_WIDTH, 
                                                        MenuAssets.BUTTON_HEIGHT, Gamestate.PLAYING);
        funcButtons[MenuAssets.LEVELS_B] = new ButtonImpl(MenuAssets.FUNC_BUTTON_X,
                                                          MENU_Y + MenuAssets.FUNC_BUTTON_DISTANCE + MenuAssets.BUTTON_HEIGHT,
                                                          MenuAssets.BUTTON_WIDTH, 
                                                          MenuAssets.BUTTON_HEIGHT, Gamestate.CHOSING_LEVELS);
        utilityButtons[MenuAssets.SETTINGS_B] = new ButtonImpl(MENU_X + MenuAssets.UTILITY_BUTTON_LEFT_BORDER_DISTANCE_X,
                                                               MenuAssets.UTILITY_BUTTON_Y,
                                                               MenuAssets.BUTTON_WIDTH, 
                                                               MenuAssets.BUTTON_HEIGHT, Gamestate.SETTINGS);
        utilityButtons[MenuAssets.QUIT_B] = new ButtonImpl(RIGHT_MENU_BORDER - UTILITY_BUTTON_RIGHT_BORDER_DISTANCE_X,
                                                           MenuAssets.UTILITY_BUTTON_Y,
                                                           MenuAssets.BUTTON_WIDTH, 
                                                           MenuAssets.BUTTON_HEIGHT, Gamestate.EXIT);

        this.buttons.put(funcButtons[MenuAssets.PLAY_B], getMenuSources().get(MenuAssets.PLAY_BUTTON));
        this.buttons.put(funcButtons[MenuAssets.LEVELS_B], getMenuSources().get(MenuAssets.LEVELS_BUTTON));
        this.buttons.put(utilityButtons[MenuAssets.SETTINGS_B], getMenuSources().get(MenuAssets.SETTINGS_BUTTON));
        this.buttons.put(utilityButtons[MenuAssets.QUIT_B], getMenuSources().get(MenuAssets.QUIT_BUTTON));
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
        g.drawImage(getMenuSources().get(MenuAssets.MENU_BACKGROUND), 
                    0, 
                    0, 
                    Window.GAME_WIDTH, 
                    Window.GAME_HEIGHT, null);
        g.drawImage(getMenuSources().get(MenuAssets.MENU_TEXTURE), 
                    MENU_X, 
                    MENU_Y, 
                    MenuAssets.MENU_TEXTURE_BOX, 
                    MenuAssets.MENU_TEXTURE_BOX, null);
        g.drawImage(getMenuSources().get(MenuAssets.TITLE), 
                    MenuAssets.TITLE_X, 
                    MenuAssets.TITLE_Y, 
                    MenuAssets.TITLE_WIDTH, 
                    MenuAssets.TITLE_HEIGHT, null);
    }

    private void drawLegend(final Graphics g) {
        g.drawImage(getMenuSources().get(MenuAssets.A),
                    MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10, MENU_Y + MenuAssets.BUTTON_HEIGHT * 3,
                    MenuAssets.LEGEND_KEY_BOX, MenuAssets.LEGEND_KEY_BOX, null);
        g.drawImage(getMenuSources().get(MenuAssets.D),
                    MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10, MENU_Y + MenuAssets.BUTTON_HEIGHT * 3 + MenuAssets.LEGEND_KEY_BOX,
                    MenuAssets.LEGEND_KEY_BOX, MenuAssets.LEGEND_KEY_BOX, null);
        g.drawImage(getMenuSources().get(MenuAssets.W),
                    MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10,
                    MENU_Y + MenuAssets.BUTTON_HEIGHT * 3 + MenuAssets.LEGEND_KEY_BOX * 2,
                    MenuAssets.LEGEND_KEY_BOX, MenuAssets.LEGEND_KEY_BOX, null);
        g.drawImage(getMenuSources().get(MenuAssets.S),
                    MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10,
                    MENU_Y + MenuAssets.BUTTON_HEIGHT * 3 + MenuAssets.LEGEND_KEY_BOX * 3,
                    MenuAssets.LEGEND_KEY_BOX, MenuAssets.LEGEND_KEY_BOX, null);

        g.drawImage(getMenuSources().get(MenuAssets.LEFT),
                    MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10 + MenuAssets.LEGEND_KEY_BOX, MENU_Y + MenuAssets.BUTTON_HEIGHT * 3,
                    MenuAssets.LEGEND_KEY_BOX, MenuAssets.LEGEND_KEY_BOX, null);
        g.drawImage(getMenuSources().get(MenuAssets.RIGHT),
                    MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10 + MenuAssets.LEGEND_KEY_BOX,
                    MENU_Y + MenuAssets.BUTTON_HEIGHT * 3 + MenuAssets.LEGEND_KEY_BOX,
                    MenuAssets.LEGEND_KEY_BOX, MenuAssets.LEGEND_KEY_BOX, null);
        g.drawImage(getMenuSources().get(MenuAssets.UP),
                    MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10 + MenuAssets.LEGEND_KEY_BOX,
                    MENU_Y + MenuAssets.BUTTON_HEIGHT * 3 + MenuAssets.LEGEND_KEY_BOX * 2,
                    MenuAssets.LEGEND_KEY_BOX, MenuAssets.LEGEND_KEY_BOX, null);
        g.drawImage(getMenuSources().get(MenuAssets.DOWN),
                    MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10 + MenuAssets.LEGEND_KEY_BOX,
                    MENU_Y + MenuAssets.BUTTON_HEIGHT * 3 + MenuAssets.LEGEND_KEY_BOX * 3,
                    MenuAssets.LEGEND_KEY_BOX, MenuAssets.LEGEND_KEY_BOX, null);

        final Font font = new Font("Arial", Font.BOLD, 10 * 2);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE LEFT",
                MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10 + MenuAssets.LEGEND_KEY_BOX * 2,
                MENU_Y + MenuAssets.MENU_TEXTURE_BOX / 2);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE RIGHT",
                MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10 + MenuAssets.LEGEND_KEY_BOX * 2,
                MENU_Y + MenuAssets.MENU_TEXTURE_BOX / 2 + MenuAssets.LEGEND_KEY_BOX);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE UP",
                MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10 + MenuAssets.LEGEND_KEY_BOX * 2,
                MENU_Y + MenuAssets.MENU_TEXTURE_BOX / 2 + MenuAssets.LEGEND_KEY_BOX * 2);

        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("TO MOVE DOWN",
                MENU_X + MenuAssets.MENU_TEXTURE_BOX / 10 + MenuAssets.LEGEND_KEY_BOX * 2,
                MENU_Y + MenuAssets.MENU_TEXTURE_BOX / 2 + MenuAssets.LEGEND_KEY_BOX * 3);
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
