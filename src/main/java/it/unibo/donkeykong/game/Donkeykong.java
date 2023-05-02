package it.unibo.donkeykong.game;

import it.unibo.donkeykong.controller.impl.ApplicationImpl;

/**
 * Main class.
 */
public final class Donkeykong {

    private Donkeykong() {
    }
    /**
     * Main method.
     * @param args
     */
    public static void main(final String[] args) {
        new ApplicationImpl();
    }
}
