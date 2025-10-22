module com.sio.tpdatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.sio.tpdatabase to javafx.fxml;
    exports com.sio.tpdatabase;
}