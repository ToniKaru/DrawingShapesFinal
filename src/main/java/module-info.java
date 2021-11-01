module com.example.drawingshapes {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.drawingshapes to javafx.fxml;
    exports com.example.drawingshapes;
}