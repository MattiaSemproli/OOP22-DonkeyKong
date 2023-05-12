package it.unibo.donkeykong.utilities;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Optional;

import it.unibo.donkeykong.game.model.impl.ButtonImpl;

/**
 * Functional utilities for buttons.
 */
public final class ButtonFuncUtilities {
    private ButtonFuncUtilities() {
    }

    /**
     * Get the button pressed.
     * 
     * @param e       mouse event.
     * @param buttons list of buttons to check.
     * @return        if the mouse event is in a button return it otherwise an optional empty.
     */
    public static Optional<ButtonImpl> getButtonPressed(final MouseEvent e, final ArrayList<ButtonImpl> buttons) {
        for (final ButtonImpl b : buttons) {
            if (b.getCorners().contains(e.getPoint())) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }
}
