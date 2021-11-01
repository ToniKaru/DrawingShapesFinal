package com.example.drawingshapes;

import com.example.drawingshapes.shapes.Shapes;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class DrawingController {

    Model model;

    @FXML
    private Button circleButton;
    @FXML
    private Label testText;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;




    public DrawingController(){}

    public DrawingController(Model model){ this.model = model; }


    public void initialize(){
        model = new Model();

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    }



    @FXML
    protected void onTestButtonClick() {
        testText.setText("Welcome to Drawing Shapes!");
    }

    @FXML
    protected void onCircleButtonClick(){  }




    public void onSave(){
        System.out.println("Implement save with try catch");
    }

    public void onExit(){
        Platform.exit();
    }

    public void canvasClicked(MouseEvent event) {
        var context = canvas.getGraphicsContext2D();

        context.fillOval(event.getX(), event.getY(), 25,25);
    }
}