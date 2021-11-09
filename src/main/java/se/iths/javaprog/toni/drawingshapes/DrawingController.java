package se.iths.javaprog.toni.drawingshapes;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import se.iths.javaprog.toni.drawingshapes.shapes.ChosenShape;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.io.File;
import java.util.Optional;

import static se.iths.javaprog.toni.drawingshapes.drawingio.SvgIO.saveToFile;

public class DrawingController {

    private Model model;
    private Stage stage;
    private static final String HOMEPATH = System.getProperty("user.home");

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

        model.shapes.addListener((ListChangeListener<? super Shape>) change -> drawCanvas());
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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private Window getWindow(){
        return stage.getScene().getWindow();
    }

    @FXML
    protected void onExit(){
        Platform.exit();
    }

    @FXML
    protected void undoButtonClick(MouseEvent event) {
        model.undo();
        drawCanvas();
    }

    @FXML
    protected void redoButtonClick(MouseEvent event) {
        model.redo();
        drawCanvas();
    }
    @FXML
    protected void canvasClick(MouseEvent event) {
        if (event.isControlDown())
            selectMode(event);
        else
            model.makeShape(event.getX(), event.getY());
        drawCanvas();
    }

    private void selectMode(MouseEvent event) {
        Optional<Shape> selectedShape =
                model.getSelectedShape(event.getX(), event.getY());
        selectedShape.ifPresent(this::updateShape);
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
    protected void onDisconnectButtonClick(){
        model.disconnect();
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

    public Model getModel() {
        return model;
    }



}
