package com.example.osago;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ResultView {
    @FXML
    Label result;
    @FXML
    Button backButtonUser;
    @FXML
    void backButton() throws Exception{
        Stage stageToClose  = (Stage) backButtonUser.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(RoleView.class.getResource("user-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 500);
        stage.setTitle("Authorization");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void ExitButton() throws Exception{
        System.exit(1);
    }

    @FXML
    void initialize() {
        result.setText(String.format("%.2f", UserView.T) + " рублей");
    }

}
