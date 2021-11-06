package se.iths.javaprog.toni.drawingshapes;


import se.iths.javaprog.toni.drawingshapes.command.UndoRedo;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.shapes.Shapes;


import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class Model {

    private final BooleanProperty inColor;
    private final ObjectProperty<Color> color;

    private final DoubleProperty size;

    private static String shapeName;

    ObservableList<Shape> shapes =
            FXCollections.observableArrayList();

    private UndoRedo undoRedo;




    Model() {
        this.inColor = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>(Color.BLACK);
        this.size = new SimpleDoubleProperty(100d);
        shapeName = "circle";
        undoRedo = new UndoRedo();
    }

    public void insertInUndoRedo(Shape shape){
        undoRedo.insertInUndoRedo(shape);
    }

    public void insertInUndoRedo(Shape shape, double newScale){
        undoRedo.insertInUndoRedo(shape, newScale);
    }


    public void insertInUndoRedo(Shape shape, Color oldColor, Color newColor){
        undoRedo.insertInUndoRedo(shape, oldColor, newColor);
    }

    private Optional<Shape> getLastShape() {
        return shapes.stream()
                .reduce((first, second) -> second);
    }

    public static Shape makeShape(Color color, double x, double y, Double size) {
        return switch (shapeName) {
            case "circle" -> Shapes.circleOf(color,x,y,size);
            case "square" -> Shapes.squareOf(color,x,y,size);
            default -> throw new IllegalArgumentException();
        };
    }

    public void setShapeName(String shapeName) {
        this.shapeName = shapeName;
    }

    public void saveShapes(Path path){
      //  SvgIO.saveToFile(path, this);
    }

    public List<Shape> getAllShapes(){
        return shapes.stream()
                .collect(Collectors.toUnmodifiableList());
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

    public Double getSize() {
        return size.get();
    }

    public DoubleProperty sizeProperty() {
        return size;
    }

    public void setSize(Double size) {
        this.size.set(size);
    }

    public void undo() {
        undoRedo.undo();
    }

    public void redo(){
        undoRedo.redo();
    }
}
