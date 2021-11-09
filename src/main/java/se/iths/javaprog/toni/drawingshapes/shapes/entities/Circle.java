package se.iths.javaprog.toni.drawingshapes.shapes.entities;

import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Circle extends Shape {

    private double radius;
    private double scaledDiameter;
    private double scaledRadius;

    public Circle(Color color, double x, double y, double diameter) {
        super(color, x, y);
        this.radius = diameter * 0.5;
        this.scaledRadius = radius;
        this.scaledDiameter = diameter;
    }

    private Circle(Circle circle){
        super(circle);
        this.radius = circle.radius;
        this.scaledRadius = circle.scaledRadius;
        this.scaledDiameter = circle.scaledDiameter;
    }

    @Override
    public Shape copyOf(){
        return new Circle(this);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.getColor());
        gc.fillOval(getX()-scaledRadius, getY()-scaledRadius, scaledDiameter,scaledDiameter);
    }

    @Override
    public boolean isHit(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();
        double distanceFromCenterSquared = dx * dx + dy * dy;

        return distanceFromCenterSquared < scaledRadius*scaledRadius;
    }

    @Override
    public void reScale() {
        scaledRadius = radius * this.getScale();
        scaledDiameter = scaledRadius * 2;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getScaledDiameter() {
        return scaledDiameter;
    }

    public void setScaledDiameter(double scaledDiameter) {
        this.scaledDiameter = scaledDiameter;
    }

    public double getScaledRadius() {
        return scaledRadius;
    }

    public void setScaledRadius(double scaledRadius) {
        this.scaledRadius = scaledRadius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, scaledDiameter, scaledRadius);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                ", scaledDiameter=" + scaledDiameter +
                ", scaledRadius=" + scaledRadius +
                '}';
    }
}
