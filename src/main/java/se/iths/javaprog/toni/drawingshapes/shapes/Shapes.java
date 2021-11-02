package se.iths.javaprog.toni.drawingshapes.shapes;

import se.iths.javaprog.toni.drawingshapes.shapes.entities.Circle;
import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.shapes.entities.Square;

public class Shapes {

    public static Shape circleOf(Color color, double x, double y, double radius) {
        return new Circle(color, x, y, radius);
    }

    public static Shape squareOf(Color color, double x, double y, double size) {
        return new Square(color, x, y, size);
    }

}
