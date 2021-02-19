package com.company;

import java.util.Comparator;
import java.util.List;

public abstract class Shape {

    protected int iX, iY, iZ;

    public final void setPosition(int aX, int aY, int aZ) {
        this.iX = aX;
        this.iY = aY;
        this.iZ = aZ;
    }

    public final void matchPositionOf (Shape aShape) {
        this.setPosition(aShape.iX, aShape.iY, aShape.iZ);
    }

    public abstract int getArea();

    public abstract int getVolume();

    public abstract void rotateXY();

    public abstract boolean canContain(Shape aShape);

    public abstract boolean attemptToContain(Shape aShape);

    public abstract List<Shape> breakUp(Shape aOccupied);

    public abstract String toFullString();

    @Override
    public String toString(){
        return "[X=" + iX + ", Y=" + iY + ", Z=" + iZ + "]";
    }

    @Override
    public boolean equals(Object obj) {
        Shape other = (Shape) obj;
        if (iX != other.iX || iY != other.iY || iZ != other.iZ)
            return false;
        return true;
    }

    public static final int getTotalVolume(List<Shape> aShapes) {
        int liTotalVolume = 0;
        for (Shape loShape : aShapes) {
            liTotalVolume += loShape.getVolume();
        }
        return liTotalVolume;
    }

}

class shapeAreaComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return (o1.getArea() - o2.getArea());
    }
}

class shapeVolumeComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape o1, Shape o2) {
        return (o1.getVolume() - o2.getVolume());
    }
}
