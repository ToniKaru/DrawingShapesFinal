package se.iths.javaprog.toni.drawingshapes;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static se.iths.javaprog.toni.drawingshapes.fileIO.SvgIO.saveToFile;

public class DrawingController {


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
    private Button undoButton;
    @FXML
    private Button redoButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button clearButton;

    private Model model;
    private Stage stage;

    private static final String HOMEPATH = System.getProperty("user.home");


    public void initialize(){
        model = new Model();
        colorPicker.valueProperty().bindBidirectional(model.colorProperty());
        slider.valueProperty().bindBidirectional(model.sizeProperty());

    }

    @FXML
    protected void onOpen(){
//        System.out.println("Implementing: open");
//        FileChooser fileChooser = new FileChooser();
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("svg file", ".svg");
//        fileChooser.getExtensionFilters().add(extFilter);
//        fileChooser.setTitle("Open Dialog");
//        File file = fileChooser.showOpenDialog(getWindow());
//
//        if (file != null){
//            readFile(file.toPath(), canvas.getHeight(), canvas.getWidth());
//        }

    }

    @FXML
    protected void onSave(){
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("svg file", ".svg");
        chooser.getExtensionFilters().add(extFilter);
        chooser.setInitialDirectory(new File(HOMEPATH));
        chooser.setTitle("Save Dialog");
        chooser.setInitialFileName("myShapes");

        File file = chooser.showSaveDialog(getWindow());

        if (file != null){
            saveToFile(model.getAllShapes(), file.toPath(), canvas.getHeight(), canvas.getWidth());
        }
    }

    public void setStage(Stage stage) throws IOException{
        this.stage = stage;
    }
    private Window getWindow(){
        return stage.getScene().getWindow();

    }

    @FXML
    protected void onExit(){
        Platform.exit();
    }

    public void undoButtonClick(MouseEvent event) {
        model.undo();
        drawCanvas();
    }

    public void redoButtonClick(MouseEvent event) {
        model.redo();
        drawCanvas();
    }


    public void canvasClick(MouseEvent event) {
        if (event.isControlDown()) {
            Optional<Shape> optionalShape = model.shapes.stream()
                    .filter(s -> s.isHit(event.getX(), event.getY()))
                    .reduce((first, second) -> second);
            if(optionalShape.isEmpty())
                return;

            Shape shape = optionalShape.get();

            if(!model.getColor().equals(shape.getColor())){
                model.insertInUndoRedo(shape, shape.getColor(), model.getColor());
                shape.setColor(model.getColor());
            }

            double scale = getScale(model.getSize());
            if(scale != shape.getScale()){
                model.insertInUndoRedo(shape, scale);
                shape.setScale(scale);
            }
        }

        else {
            Shape shape = Model.makeShape(model.getColor(), event.getX(), event.getY(), model.getSize());
            model.shapes.add(shape);
            model.insertInUndoRedo(model.shapes, shape);
        }
        drawCanvas();
    }

    private double getScale(Double scale) {
        return scale * 0.01;

    }

    private void drawCanvas() {
        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : model.shapes) {
            shape.draw(gc);
        }
    }

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
