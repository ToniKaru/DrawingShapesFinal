package se.iths.javaprog.toni.drawingshapes.command.commands;

import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

public class ReColor implements Command {

    private Shape shape;
    private Color oldColor;
    private Color newColor;

    public ReColor(Shape shape, Color newColor) {
        this.shape = shape;
        this.oldColor = shape.getColor();
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
}
