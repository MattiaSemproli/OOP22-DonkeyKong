package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.Settings;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.impl.SettingsView;

/**
 * Settings controller.
 */
public class SettingsController implements GameEngine {

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

    public final void applyGamestate(final Gamestate gamestate) {
        this.settings.applyGamestate(gamestate);
    }

    public final SettingsView getView() {
        return this.settingsView;
    }
}
