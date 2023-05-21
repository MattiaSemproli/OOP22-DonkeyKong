package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.Settings;
import it.unibo.donkeykong.utilities.AudioUtilities;
import it.unibo.donkeykong.utilities.ButtonFuncUtilities;
import it.unibo.donkeykong.view.SettingsView;

/**
 * Settings controller.
 */
public class SettingsController implements MouseListener, GameEngine {

    private final SettingsView settingsView;
    private final Settings settings;

    /**
     * Constructor.
     */
    public SettingsController() {
        this.settingsView = new SettingsView(this);
        this.settings = new Settings();
    }

    @Override
    public final void update() {
        this.settingsView.update();
    }

    @Override
    public final void draw(final Graphics g) {
        this.settingsView.draw(g);
    }

    /**
     * Get the settings model.
     * 
     * @return the settings model.
     */
    public final Settings getSettings() {
        return this.settings;
    }

    @Override
    public final void mousePressed(final MouseEvent e) {
        ButtonFuncUtilities.getButtonPressed(e, this.settings.getButtons().keySet()).ifPresent(b -> b.applyGamestate());
        this.settings.mute(e).ifPresent(mute -> AudioUtilities.setMuted(mute));
        this.settings.setTheme(e);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }
}
