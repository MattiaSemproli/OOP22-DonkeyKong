package it.unibo.donkeykong.game.model.impl;

import java.awt.Graphics;
import java.util.ArrayList;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.api.ViewModel;

public class Pause implements GameEngine, ViewModel {

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
    }

    @Override
    public ArrayList<ButtonImpl> getButtons() {
        return new ArrayList<>() {{
            
        }};
    }
    
}
