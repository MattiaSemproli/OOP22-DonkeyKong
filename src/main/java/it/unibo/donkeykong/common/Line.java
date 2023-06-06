package it.unibo.donkeykong.common;

/**
 * This class represents a line.
 */
public class Line {

    public float x1, y1, x2, y2;

    public Line(final float x1, final float y1, final float x2, final float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getX1() {
        return (double) this.x1;
    }

    public double getY1() {
        return (double) this.y1;
    }

    public double getX2() {
        return (double) this.x2;
    }

    public double getY2() {
        return (double) this.y2;
    }

    public void setX1(final float x1) {
        this.x1 = x1;
    }

    public void setY1(final float y1) {
        this.y1 = y1;
    }

    public void setX2(final float x2) {
        this.x2 = x2;
    }

    public void setY2(final float y2) {
        this.y2 = y2;
    }

    public Line getCopy() {
        return new Line(this.x1, this.y1, this.x2, this.y2);
    }
}
