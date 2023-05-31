package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.MenuAssets.getMenuSources;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.rightMenuBorder;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.utilityButtonRightBorderDistanceX;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import it.unibo.donkeykong.game.model.api.Button;
import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Gamestate;

/**
 * Main menu model.
 */
public class MainMenu implements ViewModel {

    private final Button[] funcButtons = new Button[MenuAssets.numFunctionButtons];
    private final Button[] utilityButtons = new Button[MenuAssets.numUtilityButtons];

    /**
     * Constructor.
     */
    public MainMenu() {
        this.createFuncButtons();
        this.createUtilityButtons();
    }

    private void createFuncButtons() {
        this.funcButtons[MenuAssets.playB] = new ButtonImpl(MenuAssets.funcButtonX,
                                                            menuY + MenuAssets.funcButtonsDistance,
                                                            MenuAssets.buttonWidth, 
                                                            MenuAssets.buttonHeight, Gamestate.PLAYING);
        this.funcButtons[MenuAssets.levelsB] = new ButtonImpl(MenuAssets.funcButtonX,
                                                              menuY + MenuAssets.funcButtonsDistance + MenuAssets.buttonHeight,
                                                              MenuAssets.buttonWidth, 
                                                              MenuAssets.buttonHeight, Gamestate.CHOSING_LEVELS);
    }

    private void createUtilityButtons() {
        this.utilityButtons[MenuAssets.settingsB] = new ButtonImpl(menuX + MenuAssets.utilityButtonLeftBorderDistanceX,
                                                                   MenuAssets.utilityButtonY,
                                                                   MenuAssets.buttonWidth, 
                                                                   MenuAssets.buttonHeight, Gamestate.SETTINGS);
        this.utilityButtons[MenuAssets.quitB] = new ButtonImpl(rightMenuBorder - utilityButtonRightBorderDistanceX,
                                                               MenuAssets.utilityButtonY,
                                                               MenuAssets.buttonWidth, 
                                                               MenuAssets.buttonHeight, Gamestate.EXIT);
    }

    @Override
    public final Map<Button, BufferedImage> getButtons() {
        final Map<Button, BufferedImage> buttons = new HashMap<>();
        buttons.put(funcButtons[MenuAssets.playB], getMenuSources().get(MenuAssets.playButton));
        buttons.put(funcButtons[MenuAssets.levelsB], getMenuSources().get(MenuAssets.levelsButton));
        buttons.put(utilityButtons[MenuAssets.settingsB], getMenuSources().get(MenuAssets.settingsButton));
        buttons.put(utilityButtons[MenuAssets.quitB], getMenuSources().get(MenuAssets.quitButton));
        return buttons;
        // return new HashMap<>() {{
        //     put(funcButtons[MenuAssets.playB], getMenuSources().get(MenuAssets.playButton));
        //     put(funcButtons[MenuAssets.levelsB], getMenuSources().get(MenuAssets.levelsButton));
        //     put(utilityButtons[MenuAssets.settingsB], getMenuSources().get(MenuAssets.settingsButton));
        //     put(utilityButtons[MenuAssets.quitB], getMenuSources().get(MenuAssets.quitButton));
        // }};
    }

    @Override
    public final Map<Rectangle, BufferedImage> getAlternativeButtons() {
        return new HashMap<>();
    }
}
