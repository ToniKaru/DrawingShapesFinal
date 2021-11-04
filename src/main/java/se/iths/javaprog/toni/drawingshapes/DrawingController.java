package se.iths.javaprog.toni.drawingshapes;

import javafx.beans.property.ObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.command.UndoRedo;
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

    @FXML
    private Button circleButton;
    @FXML
    private Button squareButton;

    @FXML
    private ToggleGroup shapeSelection;

    @FXML
    private Button undoButton;
    @FXML
    private Button redoButton;

    @FXML
    private Button clearButton;








    public DrawingController(){

    }

    public DrawingController(Model model){
        this.model = model;
    }


    public void initialize(){
        model = new Model();
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        slider.valueProperty().bindBidirectional(model.sizeProperty());



    }



    @FXML
    protected void onSave(){
        System.out.println("Implement: save with try catch");
    }

    @FXML
    protected void onExit(){
        Platform.exit();
    }

    public void undoButtonClick(MouseEvent event) {
//        model.undo();
        UndoRedo.undo();;
        drawCanvas();
    }

    public void redoButtonClick(MouseEvent event) {
//        model.redo();
        UndoRedo.redo();
        drawCanvas();
    }

    public void canvasClick(MouseEvent event) {
        if (event.isControlDown()) {
            Optional<Shape> shape = model.shapes.stream()
                    .filter(s -> s.isHit(event.getX(), event.getY()))
                    .reduce((first, second) -> second);
            shape.ifPresent(s -> System.out.println("true"));

//            shape.ifPresent(s -> UndoRedo.insertInUndoRedo(s, s.getSize, model.getSize()));
            shape.ifPresent(s -> model.insertInUndoRedo(s, model.getColor()));
            shape.ifPresent(s -> s.setColor(model.getColor()));
//            shape.ifPresent(s -> s.reSize(model.getSize()));
        }
        else {
            Shape shape = Model.makeShape(model.getColor(), event.getX(), event.getY(), model.getSize());
            model.shapes.add(shape);
//            UndoRedo.insertInUndoRedo(shape, model.getSize(),model.getColor());
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

//    @FXML
//    protected void onShapeSelectionClick(){
//        ReadOnlyObjectProperty<Toggle> choice = shapeSelection.selectedToggleProperty();
//
//    }

    @FXML
    protected void onCircleButtonClick(){
        model.setShapeName("circle");
    }

    @FXML
    protected void onSquareButtonClick() {
        model.setShapeName("square");
    }



    public void onClearButtonClick(MouseEvent event){
        model.shapes.clear();
        drawCanvas();
    }

}
