package it.unibo.donkeykong.utilities;

public enum Direction {
    
    LEFT(-1,0),

    RIGHT(1,0);

    private int x,y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Direction getOppositeDirection() {
        return this == LEFT ? RIGHT : LEFT;
    }
}
