package se.iths.javaprog.toni.drawingshapes.command.commands;

import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.util.Objects;

public class Resize implements Command {

    private Shape shape;
    private double oldScale;
    private double newScale;


    public Resize(Shape shape, double newScale) {
        this.shape = shape;
        this.oldScale = shape.getScale();
        this.newScale = newScale;
    }

    @Override
    public void execute() {
        shape.setScale(newScale);
    }

    @Override
    public void unexecute() {
        shape.setScale(oldScale);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resize resize = (Resize) o;
        return Double.compare(resize.oldScale, oldScale) == 0
                && Double.compare(resize.newScale, newScale) == 0
                && Objects.equals(shape, resize.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, oldScale, newScale);
    }

    @Override
    public String toString() {
        return "Resize{" +
                "shape=" + shape +
                ", oldScale=" + oldScale +
                ", newScale=" + newScale +
                '}';
    }
}
