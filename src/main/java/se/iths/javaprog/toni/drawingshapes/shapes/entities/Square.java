package se.iths.javaprog.toni.drawingshapes.shapes.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

public class Square extends Shape {

    private double side;
    private double scaledSide;
    private double scaledHalfSide;


    public Square(Color color, double x, double y, double side) {
        super(color, x, y);
        this.side = side;
        this.scaledSide = side;
        this.scaledHalfSide = side * 0.5;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.getColor());
        gc.fillRect(getX() - scaledHalfSide, getY() - scaledHalfSide, scaledSide, scaledSide);
    }

    @Override
    public boolean isHit(double x, double y) {
        return x >= this.getX() - scaledHalfSide &&
                x <= this.getX() + scaledHalfSide &&
                y >= this.getY()- scaledHalfSide &&
                y <= this.getY() + scaledHalfSide;
    }

    @Override
    public void reScale(){
        scaledSide = side * this.getScale();
        scaledHalfSide = scaledSide * 0.5;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getScaledSide() {
        return scaledSide;
    }

    public void setScaledSide(double scaledSide) {
        this.scaledSide = scaledSide;
    }

    public double getScaledHalfSide() {
        return scaledHalfSide;
    }

    public void setScaledHalfSide(double scaledHalfSide) {
        this.scaledHalfSide = scaledHalfSide;
    }
}
