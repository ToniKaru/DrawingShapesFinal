package se.iths.javaprog.toni.drawingshapes.shapes.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

public class Square extends Shape {

    private double size;
    private double halfSize;


    public Square(Color color, double x, double y, double size) {
        super(color, x, y);
        this.size = size;
        this.halfSize = size * 0.5;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(this.getColor());
        gc.fillRect(getX() - halfSize, getY() - halfSize, size,size);
    }

    @Override
    public boolean isHit(double x, double y) {
        return x >= this.getX() - halfSize &&
                x <= this.getX() + halfSize &&
                y >= this.getY()- halfSize &&
                y <= this.getY() + halfSize;
    }

    @Override
    public void reSize(double size){
        this.size = size;
        this.halfSize = size * 0.5;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getHalfSize() {
        return halfSize;
    }

    public void setHalfSize(double halfSize) {
        this.halfSize = halfSize;
    }
}
