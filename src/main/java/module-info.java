module com.sio.tpdatabase {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.sio.tpdatabase to javafx.fxml;
    exports com.sio.tpdatabase;
}