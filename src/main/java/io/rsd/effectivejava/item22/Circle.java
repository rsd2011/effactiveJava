package main.java.io.rsd.effectivejava.item22;

public class Circle implements Figure {
    final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * (radius * radius);
    }
}
