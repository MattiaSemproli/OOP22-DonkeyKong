package it.unibo.donkeykong.controller.impl;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.view.MainMenuView;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainMenuController implements MouseListener, GameEngine{

    private final MainMenuView menuView;

    public MainMenuController() {
        this.menuView = new MainMenuView(this);
    }

    @Override
    public void update() {
        this.menuView.update();
    }

    @Override
    public void draw(Graphics g) {
        this.menuView.draw(g);
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
