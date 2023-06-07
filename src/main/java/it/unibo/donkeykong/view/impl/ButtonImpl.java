package it.unibo.donkeykong.view.impl;

import java.awt.Rectangle;

import it.unibo.donkeykong.common.Pair;
import it.unibo.donkeykong.utilities.Gamestate;
import it.unibo.donkeykong.view.api.Button;

/**
 * Button class, manages a button.
 */
public class ButtonImpl implements Button {

    private final int x, y, width, height;
    private final Gamestate state;

    /**
     * Constructor.
     * 
     * @param x the x pos of button.
     * @param y the y pos of button.
     * @param width the width of button.
     * @param height the height of button.
     * @param state the game state of button.
     */
    public ButtonImpl(final int x, final int y, final int width, final int height, final Gamestate state) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.state = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getCorners() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getButtonPos() {
        return new Pair<Integer, Integer>(this.x, this.y);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pair<Integer, Integer> getButtonDim() {
        return new Pair<Integer, Integer>(this.width, this.height);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Gamestate getButtonGamestate() {
        return this.state;
    }
}
