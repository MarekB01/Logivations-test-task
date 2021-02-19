package com.company;

import java.util.Arrays;
import java.util.List;

public class Box extends Shape {


    protected int iWidth, iLength, iHeight;


    public Box(int aWidth, int aLength, int aHeight) {
        super();
        this.iWidth = aWidth;
        this.iLength = aLength;
        this.iHeight = aHeight;
    }


    public Box(int aWidth, int aLength, int aHeight, int aX, int aY,int aZ) {
        super.setPosition(aX, aY, aZ);
        this.iWidth = aWidth;
        this.iLength = aLength;
        this.iHeight = aHeight;
    }


    @Override
    public int getArea() {
        return this.iWidth * this.iLength;
    }


    @Override
    public int getVolume() {
        return this.iWidth * this.iLength * this.iHeight;
    }


    @Override
    public void rotateXY() {

        int loWidth = this.iWidth;
        this.iWidth = this.iLength;
        this.iLength = loWidth;
    }


    @Override
    public boolean canContain(Shape aShape) {
        if (aShape instanceof Box)
            return canContain((Box) aShape);
        return false;
    }


    public boolean canContain(Box aoBox) {
        return (aoBox.iWidth <= this.iWidth
                && aoBox.iLength <= this.iLength && aoBox.iHeight <= this.iHeight);
    }


    @Override
    public boolean attemptToContain(Shape aShape) {
        if (aShape instanceof Box)
            return attemptToContain((Box) aShape);
        return false;
    }


    public boolean attemptToContain(Box aBox) {
        if (canContain(aBox))
            return true;
        rotateXY();
        if (canContain(aBox))
            return true;
        rotateXY();
        return false;
    }


    @Override
    public List<Shape> breakUp(Shape aShape) {
        if (aShape instanceof Box)
            return breakUp((Box) aShape);
        return null;
    }


    public List<Shape> breakUp(Box aoBox) {
        Shape loSubBoxX = new Box(aoBox.iWidth - this.iWidth, aoBox.iLength,
                aoBox.iHeight, this.iX + this.iWidth, aoBox.iY, aoBox.iZ);
        Shape loSubBoxY = new Box(this.iWidth, aoBox.iLength - this.iLength,
                aoBox.iHeight, this.iX, aoBox.iY + this.iLength, aoBox.iZ);
        Shape loSubBoxZ = new Box(this.iWidth, this.iLength, aoBox.iHeight
                - this.iHeight, this.iX, aoBox.iY, aoBox.iZ + this.iHeight);
        return Arrays.asList(loSubBoxX, loSubBoxY, loSubBoxZ);
    }


    @Override
    public String toFullString() {
        return "Box [Width=" + iWidth + ", Length=" + iLength + ", Height="
                + iHeight + ", Position=" + super.toString() + "]";
    }


    @Override
    public String toString() {
        return "Box [Width=" + iWidth + ", Length=" + iLength + ", Height="
                + iHeight + "]";
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (!super.equals(obj))
            return false;
        Box other = (Box) obj;
        if (iWidth != other.iWidth || iLength != other.iLength
                || iHeight != other.iHeight)
            return false;
        return true;
    }
}

