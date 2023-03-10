package com.example.osago;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

// Класс формулы

public class FormulaView {

    @FXML
    Button backButton;

    public void goBack() throws Exception{
        Stage stageToClose  = (Stage) backButton.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("administrator-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 500);
        stage.setTitle("Administration View");
        stage.setScene(scene);
        stage.show();
    }

    public void goExit() {
        System.exit(1);
    }
}
