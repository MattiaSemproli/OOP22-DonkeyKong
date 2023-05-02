package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.Settings;
import it.unibo.donkeykong.view.SettingsView;

/**
 * Settings controller.
 */
public class SettingsController implements MouseListener, GameEngine {

    private final SettingsView settingsView;

    /**
     * Constructor.
     */
    public SettingsController() {
        this.settingsView = new SettingsView(this);
    }

    @Override
    public void update() {
    }

    @Override
    public final void draw(final Graphics g) {
        this.settingsView.draw(g);
    }

    /**
     * Get the settings model.
     * @return new settings model.
     */
    public final Settings getSettings() {
        return new Settings();
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
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
