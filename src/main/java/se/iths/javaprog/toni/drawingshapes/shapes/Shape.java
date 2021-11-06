package se.iths.javaprog.toni.drawingshapes.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

abstract public class Shape {

    private Color color;
    private double x;
    private double y;
    private double scale;
    private static final double ORIGINAL_SCALE = 1.0d;

    public Shape(Color color, double x, double y) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.scale = ORIGINAL_SCALE;
    }

    abstract public void draw(GraphicsContext context);

    abstract public boolean isHit(double x, double y);

    abstract protected void reScale();

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
        reScale();
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


}
