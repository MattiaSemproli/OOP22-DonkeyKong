package it.unibo.donkeykong.common;

/**
 * This class represents a line.
 */
public class Line {

    private float x1, y1, x2, y2;

    /**
     * Constructor.
     * 
     * @param x1 the x of first point.
     * @param y1 the y of first point.
     * @param x2 the x of second point.
     * @param y2 the y of second point.
     */
    public Line(final float x1, final float y1, final float x2, final float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Get the x of first point.
     * 
     * @return the x of first point.
     */
    public double getX1() {
        return (double) this.x1;
    }

    /**
     * Get the y of first point.
     * 
     * @return the y of first point.
     */
    public double getY1() {
        return (double) this.y1;
    }

    /**
     * Get the x of second point.
     * 
     * @return the x of second point.
     */
    public double getX2() {
        return (double) this.x2;
    }

    /**
     * Get the y of second point.
     * 
     * @return the y of second point.
     */
    public double getY2() {
        return (double) this.y2;
    }

    /**
     * Set the x of first point.
     * 
     * @param x1 the new x of first point.
     */
    public void setX1(final float x1) {
        this.x1 = x1;
    }

    /**
     * Set the y of first point.
     * 
     * @param y1 the new y of first point.
     */
    public void setY1(final float y1) {
        this.y1 = y1;
    }

    /**
     * Set the x of second point.
     * 
     * @param x2 the new x of second point.
     */
    public void setX2(final float x2) {
        this.x2 = x2;
    }

    /**
     * Set the y of second point.
     * 
     * @param y2 the new y of second point.
     */
    public void setY2(final float y2) {
        this.y2 = y2;
    }

    /**
     * This method return a defensive copy of the line.
     * 
     * @return the copied line.
     */
    public Line getCopy() {
        return new Line(this.x1, this.y1, this.x2, this.y2);
    }
}
