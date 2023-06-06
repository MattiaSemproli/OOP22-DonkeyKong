package it.unibo.donkeykong.common;

/**
 * This class represents a line.
 */
public class Line {

    private float x1, y1, x2, y2;

    public Line(final float x1, final float y1, final float x2, final float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public float getX1() {
        return this.x1;
    }

    public float getY1() {
        return this.y1;
    }

    public float getX2() {
        return this.x2;
    }

    public float getY2() {
        return this.y2;
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

    public boolean intersects(final Line l) {
        final float x3 = l.getX1();
        final float y3 = l.getY1();
        final float x4 = l.getX2();
        final float y4 = l.getY2(); 
        float a1, a2, a3, a4;
        
        if ((a1 = area2(x1, y1, x2, y2, x3, y3)) == 0) {
            if (between(x1, y1, x2, y2, x3, y3)) {
                return true;
            }
            else 
            {
                if (area2(x1, y1, x2, y2, x4, y4) == 0) 
                {
                    return between(x3, y3, x4, y4, x1, y1) 
                                   || between (x3, y3, x4, y4, x2, y2);
                } else {
                    return false;
                }
            }
        }
        else if ((a2 = area2(x1, y1, x2, y2, x4, y4)) == 0) {
            return between(x1, y1, x2, y2, x4, y4);
        }
     
        if ((a3 = area2(x3, y3, x4, y4, x1, y1)) == 0) {
            if (between(x3, y3, x4, y4, x1, y1)) {
                return true;
            } else {
                if (area2(x3, y3, x4, y4, x2, y2) == 0) {
                    return between(x1, y1, x2, y2, x3, y3) 
                                   || between (x1, y1, x2, y2, x4, y4);
                } else {
                    return false;
                }
            }
        }
        else if ((a4 = area2(x3, y3, x4, y4, x2, y2)) == 0) {
            return between(x3, y3, x4, y4, x2, y2);
        } else {
            return ((a1 > 0) ^ (a2 > 0)) && ((a3 > 0) ^ (a4 > 0));
        } 
    } 

    private boolean between(float x1, float y1, float x2, float y2, float x3, float y3) {
        if (x1 != x2) {
            return (x1 <= x3 && x3 <= x2) || (x1 >= x3 && x3 >= x2);   
        } else {
            return (y1 <= y3 && y3 <= y2) || (y1 >= y3 && y3 >= y2);   
        }
    }

    private float area2(float x1, float y1, float x2, float y2, float x3, float y3) {
        return (x2 - x1) * (y3 - y1) - (x3 - x1) * (y2 - y1);    
    }
}
