package it.unibo.donkeykong.utilities;

/**
 * Enum representing directions.
 */
public enum Direction {
    /**
     * Left direction.
     */
    LEFT(-1, 0), 
    /**
     * Right direction.
     */
    RIGHT(1, 0),
    /**
     * Up direction on ladder.
     */
    UP(0, -1),
    /**
     * Down direction on ladder.
     */
    DOWN(0, 1);

    private int x;
    private int y;

    /**
     * @param x
     * @param y
     */
    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x.
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return y.
     */
    public int getY() {
        return this.y;
    }

    /**
     * Method that return the opposite direction.
     * 
     * @return left if the actual direction is right and vice versa.
    */
    public Direction getOppositeDirection() {
        return this == LEFT ? RIGHT : LEFT;
    }
}
