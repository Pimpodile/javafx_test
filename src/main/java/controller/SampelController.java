package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SampelController {

    @FXML
    private VBox vBoxTF;

    @FXML
    private Button btAdd;

    @FXML
    private void onActionAdd() {
        System.out.println("Button clicked");
    }
}
