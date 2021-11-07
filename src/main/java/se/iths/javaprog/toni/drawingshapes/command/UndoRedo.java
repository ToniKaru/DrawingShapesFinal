package se.iths.javaprog.toni.drawingshapes.command;

import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.command.commands.*;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

public class UndoRedo {

    private Deque<Command> undoStack = new ArrayDeque<>();
    private Deque<Command> redoStack = new ArrayDeque<>();


    public void undo(){
       if(!undoStack.isEmpty()){
            Command command = undoStack.pop();
            command.unexecute();
            redoStack.push(command);
        }
    }

    public void redo(){
        if(!redoStack.isEmpty()){
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }


    public void insertInUndoRedo(List<Shape> shapes, Shape shape){
        Command cmd = new Insert(shapes, shape);
        undoStack.push(cmd);
        redoStack.clear();
    }

    public void insertInUndoRedo(Shape shape, double newSize){
        Command cmd = new Resize(shape, newSize);
        undoStack.push(cmd);
        redoStack.clear();
    }

    public void insertInUndoRedo(Shape shape, Color oldColor, Color newColor){
        Command cmd = new ReColor(shape, oldColor, newColor);
        undoStack.push(cmd);
        redoStack.clear();
    }



    public Deque<Command> getUndoStack() {
        return undoStack;
    }

    public Deque<Command> getRedoStack() {
        return redoStack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UndoRedo undoRedo = (UndoRedo) o;
        return Objects.equals(undoStack, undoRedo.undoStack) && Objects.equals(redoStack, undoRedo.redoStack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(undoStack, redoStack);
    }

    @Override
    public String toString() {
        return "UndoRedo{" +
                "undoStack=" + undoStack +
                ", redoStack=" + redoStack +
                '}';
    }
}
