package it.unibo.donkeykong.utilities;

import java.awt.event.MouseEvent;
import java.util.Optional;
import java.util.Set;

import it.unibo.donkeykong.game.model.api.Button;

/**
 * Static class ButtonFuncUtilities, manages utilities function for buttons.
 */
public final class ButtonFuncUtilities {
    private ButtonFuncUtilities() {
    }

    /**
     * Get the button pressed.
     * 
     * @param e the mouse event.
     * @param buttons the list of buttons to check.
     * @return a button if the mouse event is in it.
     */
    public static Optional<Button> getButtonPressed(final MouseEvent e, final Set<Button> buttons) {
        for (final Button b : buttons) {
            if (b.getCorners().contains(e.getPoint())) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }
}
