package com.haoming.module1three;

public class Triangle extends TwoDShape
    implements Rotate {

    public void rotate90() {
        this.angle += 90;
    }

    public void rotate180() {
        this.angle += 180;
    }

    public void rotate(double degree) {
        this.angle = degree;
    }

    double side1;
    double side2;
    double side3;
    double angle;

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

    @Override
    public String toString() {
        return "Triangle{" +
                "side1=" + side1 +
                ", side2=" + side2 +
                ", side3=" + side3 +
                ", angle=" + angle +
                '}';
    }
}
