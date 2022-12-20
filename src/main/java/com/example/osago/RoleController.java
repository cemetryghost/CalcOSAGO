package com.example.osago;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RoleController {

    Stage stage = new Stage();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adminButton;

    @FXML
    private Button userButton;

    @FXML
    void adminEnter() throws IOException {
        try {
            Stage stageToClose  = (Stage) adminButton.getScene().getWindow();
            stageToClose.close();

            FXMLLoader fxmlLoader = new FXMLLoader(RoleView.class.getResource("authorization-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 500);
            stage.setTitle("Authorization Administrator");
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    void userEnter() {
        try {
            Stage stageToClose  = (Stage) userButton.getScene().getWindow();
            stageToClose.close();

            FXMLLoader fxmlLoader = new FXMLLoader(RoleView.class.getResource("user-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 500);
            stage.setTitle("User Window");
            stage.setScene(scene);
            stage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}