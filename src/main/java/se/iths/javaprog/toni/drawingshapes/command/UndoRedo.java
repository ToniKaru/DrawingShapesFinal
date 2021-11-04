package se.iths.javaprog.toni.drawingshapes.command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import se.iths.javaprog.toni.drawingshapes.command.commands.*;
import se.iths.javaprog.toni.drawingshapes.shapes.Shape;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class UndoRedo {

    private static Deque<Command> undos = new ArrayDeque<>();
    private static Deque<Command> redos = new ArrayDeque<>();


    public static void undo (){
       if(!undos.isEmpty()){
            Command command = undos.pop();
            command.unexecute();
            redos.push(command);
        }
    }

    public static void redo(){
        if(!redos.isEmpty()){
            Command command = redos.pop();
            command.execute();
            undos.push(command);
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
    public static void insertInUndoRedo(Shape shape, double oldSize, double newSize){
//        Command cmd = new Resize(shape, oldSize, newSize);
//        undos.push(cmd);
//        redos.clear();
    }


    public static void insertInUndoRedo(Shape shape, Color newColor){
        Command cmd = new ReColor(shape, newColor);
        undos.push(cmd);
        redos.clear();
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
