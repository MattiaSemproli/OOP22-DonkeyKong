package it.unibo.donkeykong.utilities;

import java.util.Optional;

/**
 * Enum representing levels.
 */
public enum InputAction {
    /**
     * Level one.
     */
    LEFT_MOVE(65, Optional.of(37)),
    /**
     * Level two.
     */
    RIGHT_MOVE(68, Optional.of(39)),
    /**
     * Level three.
     */
    UP_MOVE(87, Optional.of(38)),
    /**
     * Level four.
     */
    DOWN_MOVE(83, Optional.of(40)),
    /**
     * 
     */
    JUMP_ACTION(32, Optional.empty());

    private int keyCode;
    private Optional<Integer> secondKeyCode;

    /**
     * @param key.
     * @param secondKey.
     */
    InputAction(final int key, final Optional<Integer> secondKey) {
        this.keyCode = key;
        this.secondKeyCode = secondKey;
    }

    /**
     * @return keyCode.
     */
    public int getKeyCode() {
        return this.keyCode;
    }

    /**
     * @return secondKeyCode.
     */
    public Optional<Integer> getSecondKeyCode() {
        return this.secondKeyCode;
    }
}
