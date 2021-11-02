package se.iths.javaprog.toni.drawingshapes;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import se.iths.javaprog.toni.drawingshapes.shapes.Shapes;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class DrawingController {

    Model model;

    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Slider slider;





    public DrawingController(){}

    public DrawingController(Model model){
        this.model = model;
    }


    public void initialize(){
        model = new Model();
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        slider.valueProperty().bindBidirectional(model.sizeProperty());

    }


    @FXML
    protected void onCircleButtonClick(){
        System.out.println("Implement: Set shape to circle.");
    }

    @FXML
    protected void onSave(){
        System.out.println("Implement: save with try catch");
    }

    @FXML
    protected void onExit(){
        Platform.exit();
    }

    public void canvasClick(MouseEvent event) {
        if (event.isControlDown()) {
            Optional<Shape> shape = model.shapes.stream()
                    .filter(s -> s.isHit(event.getX(), event.getY()))
                    .findFirst();
            shape.ifPresent(s -> s.setColor(model.getColor()));
            shape.ifPresent(s -> s.reSize(model.getSize()));
        }
        else {
            Shape shape = Shapes.circleOf(model.getColor(), event.getX(), event.getY(), model.getSize());
            model.shapes.add(shape);
        }
        drawCanvas();
    }

    private void drawCanvas() {
        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : model.shapes) {
            shape.draw(gc);
        }
    }

}