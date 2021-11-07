package se.iths.javaprog.toni.drawingshapes.command.commands;

import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Delete implements Command {

    private List<Shape> shapes = new ArrayList<>();
    private Shape shape;

    public Delete(List<Shape> shapes, Shape shape) {
        this.shapes = shapes;
        this.shape = shape;
    }

    @Override
    public void execute() {
        shapes.remove(shape);
    }

    @Override
    public void unexecute() {
        shapes.add(shape);
    }
}
