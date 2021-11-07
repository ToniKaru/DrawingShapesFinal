package se.iths.javaprog.toni.drawingshapes.command;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import se.iths.javaprog.toni.drawingshapes.Model;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import se.iths.javaprog.toni.drawingshapes.shapes.entities.Circle;

import static org.junit.jupiter.api.Assertions.*;

class UndoRedoTest {

    @Test
    void undoShouldChangeShapeProperty(){

        Shape shape1 = new Circle(Color.RED,15, 15, 10);



    }


}