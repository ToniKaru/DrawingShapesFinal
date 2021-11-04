package se.iths.javaprog.toni.drawingshapes;


import se.iths.javaprog.toni.drawingshapes.command.UndoRedo;
import se.iths.javaprog.toni.drawingshapes.command.commands.*;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.shapes.Shapes;

import java.util.Optional;



public class Model {

    private final BooleanProperty inColor;
    private final ObjectProperty<Color> color;

    private final DoubleProperty size;

    private static String shapeName;

    ObservableList<Shape> shapes =
            FXCollections.observableArrayList();


    Model(){
        this.inColor = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>(Color.BLACK);
        this.size = new SimpleDoubleProperty(55d);
        shapeName = "circle";
    }


    public void insertInUndoRedo(Shape shape, Color newColor){
        UndoRedo.insertInUndoRedo( shape,  newColor);
    }

    private Optional<Shape> getLastShape() {
        return shapes.stream()
                .reduce((first, second) -> first);
    }

    public static Shape makeShape(Color color, double x, double y, Double size) {
        return switch (shapeName) {
            case "circle" -> Shapes.circleOf(color,x,y,size);
            case "square" -> Shapes.squareOf(color,x,y,size);
            default -> throw new IllegalArgumentException();
        };
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public Color getColor(){
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public boolean isInColor() {
        return inColor.get();
    }

    public BooleanProperty inColorProperty() {
        return inColor;
    }

    public void setInColor(boolean inColor) {
        this.inColor.set(inColor);
    }

    public Double getSize() {
        return size.get();
    }

    public DoubleProperty sizeProperty() {
        return size;
    }

    public void setSize(Double size) {
        this.size.set(size);
    }
}
