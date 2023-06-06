package it.unibo.donkeykong.common;

/**
 * This class represents a rectangle.
 */
public class Rectangle {
   
    private float x, y, width, height;

    public Rectangle(final float x, final float y, final float width, final float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height; 
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getMaxX() {
        return this.x + this.width;
    }

    public float getMaxY() {
        return this.y + this.height;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(final float y) {
        this.y = y;
    }

    public Rectangle getCopy() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }

    public boolean intersects(final Rectangle r) {
        return !this.isRectangleZero() && !r.isRectangleZero()
               && r.getX() < this.x + this.width && r.getMaxX() > this.x
               && r.getY() < this.y + this.height && r.getMaxY() > this.y;
    }

    public boolean intersectsLine(final Line l) {
        if (this.isRectangleZero()) {
            return false;
        }
    
        if (l.getX1() >= this.x && l.getX1() <= this.getMaxX() && l.getY1() >= this.y && l.getY1() <= this.getMaxY()) {
            return true;
        }
        if (l.getX2() >= this.x && l.getX2() <= this.getMaxX() && l.getY2() >= this.y && l.getY2() <= this.getMaxY()) {
            return true;
        }
        
        float x3 = this.x + this.width;
        float y3 = this.y + this.height;
        
        return (l.intersects(new Line(this.x, this.y, this.x, y3))
                || l.intersects(new Line(this.x, y3, x3, y3))
                || l.intersects(new Line(x3, y3, x3, this.y))
                || l.intersects(new Line(x3, this.y, this.x, this.y)));
    }

    private boolean isRectangleZero() {
        return this.width <= 0 && this.height <= 0;
    }
}
