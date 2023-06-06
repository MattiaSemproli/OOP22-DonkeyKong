package it.unibo.donkeykong.view.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.rightMenuBorder;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.utilityButtonRightBorderDistanceX;

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

        final Button[] funcButtons = new Button[MenuAssets.numFunctionButtons];
        final Button[] utilityButtons = new Button[MenuAssets.numUtilityButtons];

        funcButtons[MenuAssets.playB] = new ButtonImpl(MenuAssets.funcButtonX,
                                                       menuY + MenuAssets.funcButtonsDistance,
                                                       MenuAssets.buttonWidth, 
                                                       MenuAssets.buttonHeight, Gamestate.PLAYING);
        funcButtons[MenuAssets.levelsB] = new ButtonImpl(MenuAssets.funcButtonX,
                                                         menuY + MenuAssets.funcButtonsDistance + MenuAssets.buttonHeight,
                                                         MenuAssets.buttonWidth, 
                                                         MenuAssets.buttonHeight, Gamestate.CHOSING_LEVELS);
        utilityButtons[MenuAssets.settingsB] = new ButtonImpl(menuX + MenuAssets.utilityButtonLeftBorderDistanceX,
                                                              MenuAssets.utilityButtonY,
                                                              MenuAssets.buttonWidth, 
                                                              MenuAssets.buttonHeight, Gamestate.SETTINGS);
        utilityButtons[MenuAssets.quitB] = new ButtonImpl(rightMenuBorder - utilityButtonRightBorderDistanceX,
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
