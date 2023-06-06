package it.unibo.donkeykong.common;
/**
 * This class represents a rectangle.
 */
public class Rectangle {

    /**
     * The bitmask that indicates that a point lies to the left of the rectangle.
     */
    public static final int OUT_LEFT = 1;
    /**
     * The bitmask that indicates that a point lies to the top of the rectangle.
     */
    public static final int OUT_TOP = 2;
    /**
     * The bitmask that indicates that a point lies to the right of the rectangle.
     */
    public static final int OUT_RIGHT = 4;
    /**
     * The bitmask that indicates that a point lies to the bottom of the rectangle.
     */
    public static final int OUT_BOTTOM = 8;

    private float x, y;
    private final float width, height;

    /**
     * Constructor.
     * 
     * @param x the initial x.
     * @param y the initial y.
     * @param width the width.
     * @param height the height.
     */
    public Rectangle(final float x, final float y, final float width, final float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Get x pos.
     * 
     * @return the x.
     */
    public float getX() {
        return this.x;
    }

    /**
     * Get y pos.
     * 
     * @return the y.
     */
    public float getY() {
        return this.y;
    }

    /**
     * Get the width.
     * 
     * @return the width.
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Get the height.
     * 
     * @return the height.
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Get the max x.
     * 
     * @return the x + width.
     */
    public float getMaxX() {
        return this.x + this.width;
    }

    /**
     * Get the max y.
     * 
     * @return the y + height.
     */
    public float getMaxY() {
        return this.y + this.height;
    }

    /**
     * Set the x.
     * 
     * @param x the new x.
     */
    public void setX(final float x) {
        this.x = x;
    }

    /**
     * Set the y.
     * 
     * @param y the new y.
     */
    public void setY(final float y) {
        this.y = y;
    }

    /**
     * This method return a defensive copy of the rectangle.
     * 
     * @return the copied rectangle.
     */
    public Rectangle getCopy() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    /**
     * This method check if a rectangle is colliding with another.
     * 
     * @param r the other rectangle.
     * @return true if the rectangles are colliding.
     */
    public boolean intersects(final Rectangle r) {
        return !this.isRectangleZero() && !r.isRectangleZero()
               && r.getX() < this.x + this.width && r.getMaxX() > this.x
               && r.getY() < this.y + this.height && r.getMaxY() > this.y;
    }

    private boolean isRectangleZero() {
        return this.width <= 0 && this.height <= 0;
    }

    /**
     * This method check if rectangle is colliding with a line.
     * 
     * @param l the line.
     * @return true if the rectangle and the line are colliding.
     */
    public boolean intersectsLine(final Line l) {
        return this.intersectsLine(l.getX1(), l.getY1(), l.getX2(), l.getY2());
    }

    private boolean intersectsLine(final double a, final double b, final double x2, final double y2) {
        int out2;
        out2 = outcode(x2, y2);
        if (out2 == 0) {
            return true;
        }
        double x1 = a;
        double y1 = b;
        int out1 = outcode(x1, y1);
        while (out1 != 0) {
            if ((out1 & out2) != 0) {
                return false;
            }
            if ((out1 & (OUT_LEFT | OUT_RIGHT)) != 0) {
                double x = getX();
                if ((out1 & OUT_RIGHT) != 0) {
                    x += getWidth();
                }
                y1 = y1 + (x - x1) * (y2 - y1) / (x2 - x1);
                x1 = x;
            } else {
                double y = getY();
                if ((out1 & OUT_BOTTOM) != 0) {
                    y += getHeight();
                }
                x1 = x1 + (y - y1) * (x2 - x1) / (y2 - y1);
                y1 = y;
            }
            out1 = outcode(x1, y1);
        }
        return true;
    }

    private int outcode(final double x, final double y) {
        int out = 0;
        if (this.width <= 0) {
            out |= OUT_LEFT | OUT_RIGHT;
        } else if (x < this.x) {
            out |= OUT_LEFT;
        } else if (x > this.x + (double) this.width) {
            out |= OUT_RIGHT;
        }
        if (this.height <= 0) {
            out |= OUT_TOP | OUT_BOTTOM;
        } else if (y < this.y) {
            out |= OUT_TOP;
        } else if (y > this.y + (double) this.height) {
            out |= OUT_BOTTOM;
        }
        return out;
    }
}
