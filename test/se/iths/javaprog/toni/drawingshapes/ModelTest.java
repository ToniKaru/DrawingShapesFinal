package se.iths.javaprog.toni.drawingshapes;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import se.iths.javaprog.toni.drawingshapes.shapes.Shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ModelTest {

    Model model = new Model();
    List<Shape> testList = new ArrayList<>();

    @Test
    void getAllShapesShouldReturnBothShapes() {
        Shape shape1 = Shapes.squareOf(Color.RED, 10, 20, 30);
        Shape shape2 = Shapes.circleOf(Color.GREEN, 90, 80, 70);
        testList.add(shape1);
        testList.add(shape2);
        model.shapes.add(shape1);
        model.shapes.add(shape2);
        List<Shape> shapesList = model.getAllShapes();
        
        assertEquals(testList, shapesList);
    }

    @Test
    void getSelectedShapeShouldReturnHitShape(){
        Shape shape1 = Shapes.circleOf(Color.PINK, 30,40,10);
        Shape shape2 = Shapes.squareOf(Color.TAN, 100, 90, 30);
        model.shapes.add(shape1);
        model.shapes.add(shape2);
        Optional<Shape> hitShape = model.getSelectedShape(30,40);
        Shape shape;

        assertTrue(hitShape.isPresent());

        shape = hitShape.get();
        assertEquals(shape1, shape);
    }
}