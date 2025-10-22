package com.sio.tpdatabase;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TpDbController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
