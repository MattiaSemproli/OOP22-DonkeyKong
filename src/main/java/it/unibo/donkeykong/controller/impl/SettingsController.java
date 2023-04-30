package it.unibo.donkeykong.controller.impl;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.impl.Settings;
import it.unibo.donkeykong.view.SettingsView;

public class SettingsController implements MouseListener, GameEngine {

    private final SettingsView settingsView;

    public SettingsController() {
        this.settingsView = new SettingsView(this);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        this.settingsView.draw(g);
    }

    public final Settings getSettings() {
        return new Settings();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
