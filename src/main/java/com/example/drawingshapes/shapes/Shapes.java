package com.example.drawingshapes.shapes;

import com.example.drawingshapes.shapes.entities.Circle;
import javafx.scene.paint.Color;

public class Shapes {

    public static Shape circleOf(Color color, double x, double y, double radius) {
        return new Circle(color, x, y, radius);
    }

}
