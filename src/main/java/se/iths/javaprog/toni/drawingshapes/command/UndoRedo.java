package se.iths.javaprog.toni.drawingshapes.command;

import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.command.commands.*;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.util.ArrayDeque;
import java.util.Deque;

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


//    public static void insertInUndoRedo(Shape shape, double size, Color color){
//        Command cmd = new Create(shape, size, color);
//        undos.push(cmd);
////        redos.clear();
//    }
//
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
//
//
//    public void insertInUndoRedoForDelete(Shape shape){
//        Command cmd = new Delete(shape);
//        //shapes.remove(shape);
//        undos.push(cmd);
//        //       redos.clear();
//    }



}
