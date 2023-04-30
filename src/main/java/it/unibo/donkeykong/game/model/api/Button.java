package it.unibo.donkeykong.game.model.api;

import java.util.ArrayList;

import it.unibo.donkeykong.utilities.Pair;

public interface Button {

    boolean isMouseOver();

    boolean isMousePressed();

    ArrayList<Pair<Integer, Integer>> getCorners();

    void applyGamestate();

    Pair<Integer, Integer> getButtonPos();

    Pair<Integer, Integer> getButtonDim();
}
