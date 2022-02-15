package com.haoming.module1one;

public class Triangle extends TwoDShape {
    double side1;
    double side2;
    double side3;

    public Triangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;

        this.height = heronsHeight();
    }

    private double heronsHeight() {
        return (this.side1 + this.side2 + this.side3) / 2;
    }

    public double getArea() {
        heronsHeight();
        return width * height;
    }
}
