package se.iths.javaprog.toni.drawingshapes;


import javafx.scene.control.Slider;
import javafx.util.StringConverter;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;

public class Model {

    private final BooleanProperty inColor;
    private final ObjectProperty<Color> color;

    private final DoubleProperty size;


    ObservableList<Shape> shapes =
            FXCollections.observableArrayList();


    Model(){
        this.inColor = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>(Color.BLACK);
        this.size = new SimpleDoubleProperty(15d);

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
