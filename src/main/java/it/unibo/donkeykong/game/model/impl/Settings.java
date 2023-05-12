package it.unibo.donkeykong.game.model.impl;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Constants.MenuAssets;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuX;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.menuY;

/** 
 * Settings model. 
 */
public class Settings implements GameEngine, ViewModel {

    private ButtonImpl backHome;
    private final ButtonImpl[] volumeButtons = new ButtonImpl[SettingsAssets.numVolumeButtons];

    /**
     * Constructor.
     */
    public Settings() {
        this.createButtons();
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(final Graphics g) {
        g.drawImage(new ImageIcon(SettingsAssets.homeButton).getImage(),
                this.backHome.getButtonPos().getX(),
                this.backHome.getButtonPos().getY(),
                this.backHome.getButtonDim().getX(),
                this.backHome.getButtonDim().getY(), null);
    }
    
    private void createButtons() {
        this.backHome = new ButtonImpl(menuX+MenuAssets.menuTextureBox/2, 
                                       menuY+MenuAssets.menuTextureBox/2, 50, 50, Gamestate.MENU);

        this.volumeButtons[SettingsAssets.volOnB] = new ButtonImpl(0, 0, 0, 0, null);
        this.volumeButtons[SettingsAssets.volOffB] = new ButtonImpl(0, 0, 0, 0, null);
    }

    @Override
    public final ArrayList<ButtonImpl> getButtons() {
        return new ArrayList<ButtonImpl>(){{
            addAll(Arrays.asList());
            addAll(Arrays.asList());
        }};
    }
}
