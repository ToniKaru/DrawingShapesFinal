package se.iths.javaprog.toni.drawingshapes;


import se.iths.javaprog.toni.drawingshapes.command.UndoRedo;
import se.iths.javaprog.toni.drawingshapes.drawingio.Network;
import se.iths.javaprog.toni.drawingshapes.shapes.ChosenShape;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.shapes.Shapes;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


public class Model {

    private final BooleanProperty inColor;
    private final ObjectProperty<Color> color;
    private final DoubleProperty size;

    ChosenShape chosenShape;

    ObservableList<Shape> shapes =
            FXCollections.observableArrayList();

    private UndoRedo undoRedo;

    private Network network;


    public Model() {
        this.inColor = new SimpleBooleanProperty();
        this.color = new SimpleObjectProperty<>(Color.BLACK);
        this.size = new SimpleDoubleProperty(100d);
        chosenShape = ChosenShape.CIRCLE;
        undoRedo = new UndoRedo();
        network = new Network(shapes);
    }


    public void makeShape(double x, double y) {
        Shape shape = createShape(x, y);
        addShape(shape);
    }

    public void addShape(Shape shape){
        undoRedo.insertInUndoRedo(shapes, shape);
        shapes.add(shape);
        network.sendToServer(shape);
    }

    public static Shape createShape (ChosenShape type, double x, double y, Color color, double size){
        return switch (type) {
            case CIRCLE -> Shapes.circleOf(color, x, y, size);
            case SQUARE -> Shapes.squareOf(color, x, y, size);
        };
    }

    private Shape createShape(double x, double y) {
         return switch (chosenShape) {
             case CIRCLE -> Shapes.circleOf(this.getColor(), x, y,this.getSize());
             case SQUARE -> Shapes.squareOf(this.getColor(), x, y,this.getSize());
        };
    }

    public void setChosenShape(ChosenShape chosenShape) {
        this.chosenShape = chosenShape;
    }

    public Optional<Shape> getSelectedShape(double x, double y) {
         return shapes.stream()
                .filter(s -> s.isHit(x, y))
                .reduce((first, second) -> second);
    }

    public List<Shape> getAllShapes(){
        return shapes.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public void updateColor(Shape shape) {
        if(!getColor().equals(shape.getColor())) {
            undoRedo.insertInUndoRedo(shape, shape.getColor(), getColor());
            shape.setColor(getColor());
            network.sendToServer(shape);
        }
    }

    public void updateScale(Shape shape) {
        var scale = getScale();
        if(scale != shape.getScale()){
            undoRedo.insertInUndoRedo(shape, scale);
            shape.setScale(scale);
            network.sendToServer(shape);
        }
    }


    public void undo() {
        undoRedo.undo();
    }

    public void redo(){
        undoRedo.redo();
    }


    public UndoRedo getUndoRedo() {
        return undoRedo;
    }

    public void connect(){
        network.connect();
    }

    private double getScale() {
        return size.get() * 0.01;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model model = (Model) o;
        return Objects.equals(inColor, model.inColor) && Objects.equals(color, model.color) && Objects.equals(size, model.size) && Objects.equals(shapes, model.shapes) && Objects.equals(undoRedo, model.undoRedo) && Objects.equals(network, model.network);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inColor, color, size, shapes, undoRedo, network);
    }

    @Override
    public String toString() {
        return "Model{" +
                "inColor=" + inColor +
                ", color=" + color +
                ", size=" + size +
                ", shapes=" + shapes +
                ", undoRedo=" + undoRedo +
                ", network=" + network +
                '}';
    }
}

