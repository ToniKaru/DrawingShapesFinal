package se.iths.javaprog.toni.drawingshapes.command;

import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.command.commands.*;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

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



//    private static void UndoColor(){
//        if(undo.isEmpty())
//            return;
//        redo.push(undo.peekLast());
//        Command command = undo.removeLast();
//        command.execute();
//    }


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

    public void insertInUndoRedoForDelete(List<Shape> shapes, Shape shape){
        Command cmd = new Delete(shapes, shape);
        undoStack.push(cmd);
        redoStack.clear();
    }



}
