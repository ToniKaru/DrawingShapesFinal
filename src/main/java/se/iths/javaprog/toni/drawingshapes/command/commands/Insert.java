package se.iths.javaprog.toni.drawingshapes.command.commands;

import javafx.scene.paint.Color;

import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Insert implements Command{

    private List shapes;
    private Shape shape;


    public Insert(List<Shape> shapes, Shape newShape) {
        this.shapes = shapes;
        this.shape = newShape;
    }

    @Override
    public void execute() {
        if(!shapes.contains(shape))
            shapes.add(shape);
    }

    @Override
    public void unexecute() {
        shapes.remove(shape);
    }

}
