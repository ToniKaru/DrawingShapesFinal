package se.iths.javaprog.toni.drawingshapes.command.commands;

import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.util.Objects;

public class ReColor implements Command {

    private Shape shape;
    private Color oldColor;
    private Color newColor;

    public ReColor(Shape shape, Color oldColor, Color newColor) {
        this.shape = shape;
        this.oldColor = oldColor;
        this.newColor = newColor;
    }

    @Override
    public void execute() {
        shape.setColor(newColor);
    }

    @Override
    public void unexecute() {
        shape.setColor(oldColor);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReColor reColor = (ReColor) o;
        return Objects.equals(shape, reColor.shape)
                && Objects.equals(oldColor, reColor.oldColor)
                && Objects.equals(newColor, reColor.newColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shape, oldColor, newColor);
    }

    @Override
    public String toString() {
        return "ReColor{" +
                "shape=" + shape +
                ", oldColor=" + oldColor +
                ", newColor=" + newColor +
                '}';
    }
}
