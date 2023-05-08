package it.unibo.donkeykong.utilities;

/**
 * Direction enum.
 */
public enum Direction {
    /**
     * left direction.
     */
    LEFT(-1, 0), 
    /**
     * right direction.
     */
    RIGHT(1, 0);

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
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Method that return the opposite direction.
     * @return left is the actual direction is right and vice versa 
    */
    public Direction getOppositeDirection() {
        return this == LEFT ? RIGHT : LEFT;
    }
}
