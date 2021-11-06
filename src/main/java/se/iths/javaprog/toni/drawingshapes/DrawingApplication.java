package se.iths.javaprog.toni.drawingshapes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class DrawingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DrawingApplication.class.getResource
                ("drawing-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Let's Draw Shapes!");
        stage.setScene(scene);
        DrawingController controller = fxmlLoader.getController();
        controller.setStage(stage);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}