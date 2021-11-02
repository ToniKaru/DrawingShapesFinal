package se.iths.javaprog.toni.drawingshapes.shapes.entities;

import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Circle extends Shape {

    private double radius;
    private double diameter;

    public Circle(Color color, double x, double y, double radius) {
        super(color, x, y);
        this.radius = radius;
        this.diameter = radius * 2;
    }


    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.getColor());
        gc.fillOval(getX()-radius, getY()-radius, diameter,diameter);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0 && Double.compare(circle.diameter, diameter) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius, diameter);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", diameter=" + diameter +
                '}';
    }
}
