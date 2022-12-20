package com.example.osago;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class AuthorizationView {

    @FXML
    Button backButtonRoleView;

    @FXML
    private Button authAdminButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;
    String log = "admin", pass = "12345";

    @FXML
    void adminEnterButton() throws Exception{
        if(!passwordField.getText().equals(pass) || !loginField.getText().equals(log)){
            JOptionPane.showMessageDialog(null, "Неверный логин или пароль");
        }
        else{
            Stage stageToClose  = (Stage) authAdminButton.getScene().getWindow();
            stageToClose.close();

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(RoleView.class.getResource("administrator-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 500);
            stage.setTitle("Authorization");
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    void BackButtonRoleView() throws Exception{
        Stage stageToClose  = (Stage) backButtonRoleView.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(RoleView.class.getResource("role-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 500);
        stage.setTitle("Role");
        stage.setScene(scene);
        stage.show();
    }
}
