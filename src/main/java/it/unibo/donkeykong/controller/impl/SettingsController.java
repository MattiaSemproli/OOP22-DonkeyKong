package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.Controller;
import it.unibo.donkeykong.model.impl.Settings;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.api.View;
import it.unibo.donkeykong.view.impl.SettingsView;

/**
 * Settings controller, manages settings view and model and interaction.
 */
public class SettingsController implements Controller {

    private final SettingsView settingsView;
    private final Settings settings;

    /**
     * Constructor.
     */
    public SettingsController() {
        this.settingsView = new SettingsView(this);
        this.settings = new Settings();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyGamestate(final Gamestate gamestate) {
        this.settings.applyGamestate(gamestate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView() {
        return this.settingsView;
    }
}
