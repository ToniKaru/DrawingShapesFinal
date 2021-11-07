package se.iths.javaprog.toni.drawingshapes.command.commands;

import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.util.List;
import java.util.Objects;

public class Insert implements Command{

    private List<Shape> shapes;
    private Shape shape;


    public Insert(List<Shape> shapes, Shape newShape) {
        this.shapes = shapes;
        this.shape = newShape;
    }

    @Override
    public void execute() {
        if(!shapes.contains(shape)){
            shapes.add(shape);
        }
    }

    @Override
    public void unexecute() {
        shapes.remove(shape);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Insert insert = (Insert) o;
        return Objects.equals(shapes, insert.shapes) && Objects.equals(shape, insert.shape);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shapes, shape);
    }

    @Override
    public String toString() {
        return "Insert{" +
                "shapes=" + shapes +
                ", shape=" + shape +
                '}';
    }
}
