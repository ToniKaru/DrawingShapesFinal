package se.iths.javaprog.toni.drawingshapes.command.commands;

import javafx.collections.ObservableList;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

public class Delete implements Command {

    private ObservableList<Shape> shapes;
    private Shape shape;

    public Delete(Shape shape) {
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
