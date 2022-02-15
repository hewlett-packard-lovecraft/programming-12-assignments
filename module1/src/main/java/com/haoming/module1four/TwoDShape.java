package com.haoming.module1four;

public class TwoDShape {
    double width;
    double height;
    Color color;

    public TwoDShape(double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public TwoDShape() {
        this.width = 0;
        this.height = 0;
    }

    /**
     * Get the area of the shape
     * @return the width multiplied by the height
     */
    public double getArea() {
        return(width * height);
    }

    /**
     * Set the height of the shape
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }
}
