package com.haoming.module2two;

public class Circle extends TwoDShape {
    private final double Pi = Math.PI;
    private double radius = 0.0;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Pi * Math.pow(this.radius, 2);
    }
}
