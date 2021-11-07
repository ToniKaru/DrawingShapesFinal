package se.iths.javaprog.toni.drawingshapes;

import javafx.collections.ListChangeListener;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import se.iths.javaprog.toni.drawingshapes.shapes.ChosenShape;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static se.iths.javaprog.toni.drawingshapes.drawingio.SvgIO.saveToFile;

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

        model.shapes.addListener((ListChangeListener<? super Shape>) change -> {
            drawCanvas();
        });
    }

    @FXML
    protected void onOpen(){
        System.out.println("Implementing: open");
        FileChooser fileChooser = setFileChooser("Open Dialog");
        File file = fileChooser.showOpenDialog(getWindow());

    }

    @FXML
    protected void onSave(){
        FileChooser chooser = setFileChooser("Save Dialog");
        chooser.setInitialFileName("myShapes");
        File file = chooser.showSaveDialog(getWindow());

        if (file != null){
            saveToFile(model.getAllShapes(), file.toPath(),
                    canvas.getHeight(), canvas.getWidth());
        }
    }

    private FileChooser setFileChooser(String title) {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("svg file", "*.svg");
        chooser.getExtensionFilters().add(extFilter);
        chooser.setInitialDirectory(new File(HOMEPATH));
        chooser.setTitle(title);
        return chooser;
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
        if (event.isControlDown())
            selectMode(event);
        else
            model.makeShape(event.getX(), event.getY());
        drawCanvas();
    }

    private void selectMode(MouseEvent event) {
        Optional<Shape> selectedShape =
                model.getSelectedShape(event.getX(), event.getY());
        if(selectedShape.isPresent())
            updateShape(selectedShape.get());
    }

    private void updateShape(Shape shape) {
        model.updateColor(shape);
        model.updateScale(shape);
    }

    private void drawCanvas() {
        var gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (var shape : model.shapes) {
            shape.draw(gc);
        }
    }

    @FXML
    protected void onConnectButtonClick(){
        model.connect();
    }

    @FXML
    protected void onCircleButtonClick(){
        model.setChosenShape(ChosenShape.CIRCLE);
    }

    @FXML
    protected void onSquareButtonClick() {
        model.setChosenShape(ChosenShape.SQUARE);
    }

    public void onClearButtonClick(MouseEvent event){
        model.shapes.clear();
        drawCanvas();
    }

}
