package it.unibo.donkeykong.game.model.impl;

import java.awt.Rectangle;

import it.unibo.donkeykong.game.model.api.Button;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.utilities.Pair;

/**
 * Button implementation.
 */
public class ButtonImpl implements Button {

    private final int x, y, width, height;
    private final Gamestate state;

    /**
     * Constructor.
     * 
     * @param x      x pos of the button.
     * @param y      y pos of the button.
     * @param width  width of the button.
     * @param height height of the button.
     * @param state  state of the button.
     */
    public ButtonImpl(final int x, final int y, final int width, final int height, final Gamestate state) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.state = state;
    }

    @Override
    public final Rectangle getCorners() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    @Override
    public final void applyGamestate() {
        Gamestate.setGamestate(state);
    }

    @Override
    public final Pair<Integer, Integer> getButtonPos() {
        return new Pair<Integer, Integer>(this.x, this.y);
    }

    @Override
    public final Pair<Integer, Integer> getButtonDim() {
        return new Pair<Integer, Integer>(this.width, this.height);
    }
}
