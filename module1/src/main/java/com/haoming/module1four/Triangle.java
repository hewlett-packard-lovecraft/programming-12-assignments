package com.haoming.module1four;

public class Triangle extends TwoDShape
    implements Rotate {

    /**
     *Rotate the shape 90 degrees.
     *
     */

    public void rotate90() {
        this.angle += 90;
    }

    /**
     *Rotate the shape 190 degrees.
     *
     */

    public void rotate180() {
        this.angle += 180;
    }

    /**
     *Rotate the shape a specified degree.
     * @param degree the degree to rotate the shape
     */

    public void rotate(double degree) {
        this.angle = degree;
    }

    double side1;
    double side2;
    double side3;
    double angle;

    public Triangle(double width, double height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public Triangle(double side1, double side2, double side3, Color color) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.color = color;

        this.height = heronsHeight();
    }

    /**
     * Compute the height of the triangle using the Heron's forumula.
     * See https://en.wikipedia.org/wiki/Heron%27s_formula
     * @return the height of the triangle as a double
     */
    private double heronsHeight() {
        return (this.side1 + this.side2 + this.side3) / 2;
    }

    /**
     * Compute the area of the triangle.
     * @return The height of the triangle as a double
     */

    public double getArea() {
        heronsHeight();
        return (width * height)/2;
    }

    public static double getArea(int width, int height) {
        return (width * height)/2;
    }

    /**
     * Print the triangle's properties
     * @return A String with the angle and length of each side of the triangle
     */

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
