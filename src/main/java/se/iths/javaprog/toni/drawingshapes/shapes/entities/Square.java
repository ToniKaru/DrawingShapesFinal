package se.iths.javaprog.toni.drawingshapes.shapes.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.util.Objects;

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

    private Square(Square square){
        super(square);
        this.side = square.side;
        this.scaledSide = square.scaledSide;
        this.scaledHalfSide = square.scaledHalfSide;
    }

    @Override
    public Square copyOf(){
        return new Square(this);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.getColor());
        gc.fillRect(getX(), getY(), scaledSide, scaledSide);
    }

    @Override
    public boolean isHit(double x, double y) {
        return x >= this.getX()  &&
                x <= this.getX() + scaledSide &&
                y >= this.getY() &&
                y <= this.getY() + scaledSide;
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

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                ", scaledSide=" + scaledSide +
                ", scaledHalfSide=" + scaledHalfSide +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Double.compare(square.side, side) == 0 && Double.compare(square.scaledSide, scaledSide) == 0 && Double.compare(square.scaledHalfSide, scaledHalfSide) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(side, scaledSide, scaledHalfSide);
    }
}
