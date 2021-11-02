package se.iths.javaprog.toni.drawingshapes.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

abstract public class Shape {

    private Color color;
    private double x;
    private double y;

    public Shape(Color color, double x, double y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    abstract public void draw(GraphicsContext context);

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return Double.compare(shape.x, x) == 0 && Double.compare(shape.y, y) == 0 && Objects.equals(color, shape.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, x, y);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "color=" + color +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
