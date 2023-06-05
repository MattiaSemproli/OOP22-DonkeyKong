package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.model.impl.Settings;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.impl.SettingsView;

/**
 * Settings controller.
 */
public class SettingsController {

    private final SettingsView settingsView;
    private final Settings settings;

    /**
     * Constructor.
     */
    public SettingsController() {
        this.settingsView = new SettingsView(this);
        this.settings = new Settings();
    }

    public final void applyGamestate(final Gamestate gamestate) {
        this.settings.applyGamestate(gamestate);
    }

    public final SettingsView getView() {
        return this.settingsView;
    }
}
