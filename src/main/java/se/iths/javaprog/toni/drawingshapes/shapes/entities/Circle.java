package se.iths.javaprog.toni.drawingshapes.shapes.entities;

import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public class Circle extends Shape {

    private double radius;
    //private double diameter;

    public Circle(Color color, double x, double y, double radius) {
        super(color, x, y);
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.getColor());
        gc.fillOval(getX()-radius, getY()-radius, radius*2,radius*2);
    }

    @Override
    public boolean isHit(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();
        double distanceFromCenterSquared = dx * dx + dy * dy;

        return distanceFromCenterSquared < radius*radius;
    }

    @Override
    public void reSize(double radius){
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


}
