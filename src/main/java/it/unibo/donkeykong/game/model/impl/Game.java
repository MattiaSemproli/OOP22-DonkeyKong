package it.unibo.donkeykong.game.model.impl;

import static it.unibo.donkeykong.utilities.Constants.Level.levelOne;
import static it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets.settingsSources;
import static it.unibo.donkeykong.utilities.Constants.Window.SCALED_TILES_SIZE;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map;

import it.unibo.donkeykong.controller.api.GameEngine;
import it.unibo.donkeykong.game.model.api.Level;
import it.unibo.donkeykong.game.model.api.ViewModel;
import it.unibo.donkeykong.utilities.Constants.MenuAssets.SettingsAssets;
import it.unibo.donkeykong.utilities.Constants.Window;
import it.unibo.donkeykong.utilities.Pair;

public class Game implements GameEngine, ViewModel {

    private final Level level;

    public Game() {
        this.level = new LevelImpl(levelOne);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        this.drawLevel(g);
        g.drawImage(settingsSources.get(SettingsAssets.roundedSettingsButton), 
                    Window.GAME_WIDTH - SCALED_TILES_SIZE - Window.TILES_DEFAULT_SIZE, Window.TILES_DEFAULT_SIZE,
                    SCALED_TILES_SIZE, SCALED_TILES_SIZE, null);
    }

    private void drawLevel(final Graphics g) {
        Map<Pair<Integer, Integer>, Integer> lvl = this.level.getLevelData();
        for (int r = 0; r < Window.TILES_IN_HEIGHT; r++) {
            for (int c = 0; c < Window.TILES_IN_WIDTH; c++) {
                g.drawImage(this.level.getLevelSprite(lvl.get(new Pair<>(r, c))),
                        SCALED_TILES_SIZE * r, SCALED_TILES_SIZE * c,
                        SCALED_TILES_SIZE, SCALED_TILES_SIZE, null);
            }
        }

    }

    @Override
    public ArrayList<ButtonImpl> getButtons() {
        throw new UnsupportedOperationException("Unimplemented method 'getButtons'");
    }
}
