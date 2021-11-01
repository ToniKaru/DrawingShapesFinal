package com.example.drawingshapes;


import javafx.beans.property.*;
import javafx.scene.paint.Color;

public class Model {

    private final BooleanProperty inColor;
    private final ObjectProperty<Color> color;


    Model(){
        this.inColor = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>(Color.BLACK);
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


}
