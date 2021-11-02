package se.iths.javaprog.toni.drawingshapes.shapes.entities;

import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Circle extends Shape {

    private double diameter;
    private double radius;

    public Circle(Color color, double x, double y, double diameter) {
        super(color, x, y);
        this.diameter = diameter;
        this.radius = diameter * 0.5;
    }



    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.getColor());
        gc.fillOval(getX()-radius, getY()-radius, diameter,diameter);
    }

    @Override
    public boolean isHit(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();
        double distanceFromCenterSquared = dx * dx + dy * dy;

        return distanceFromCenterSquared < radius*radius;
    }

    @Override
    public void reSize(double diameter){
        this.diameter = diameter;
        this.radius = diameter * 0.5;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
