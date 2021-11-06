module se.iths.javaprog.toni.drawingshapes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens se.iths.javaprog.toni.drawingshapes to javafx.fxml;
    exports se.iths.javaprog.toni.drawingshapes;
}