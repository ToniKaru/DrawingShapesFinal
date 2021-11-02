module se.iths.javaprog.toni.drawingshapes {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.iths.javaprog.toni.drawingshapes to javafx.fxml;
    exports se.iths.javaprog.toni.drawingshapes;
}