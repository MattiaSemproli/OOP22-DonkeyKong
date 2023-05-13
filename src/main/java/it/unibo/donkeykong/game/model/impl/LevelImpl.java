package it.unibo.donkeykong.game.model.impl;

import it.unibo.donkeykong.game.model.api.Level;

public class LevelImpl implements Level{

    private int[][] levelData;

    public LevelImpl(int[][] levelData) {
        this.levelData = levelData;
    }

    @Override
    public int getSpriteIndex(int x, int y) {
        return levelData[y][x];
    }
    
}
