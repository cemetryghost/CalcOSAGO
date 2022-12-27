package com.example.osago;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

// Класс авторизации

public class AuthorizationView {

    // Объявление FXML элементов

    @FXML
    public Button authAdminButton, backButtonRoleView;

    @FXML
    public TextField loginField;

    @FXML
    private PasswordField passwordField;
    String log = "admin", pass = "12345";

    // Метод проверки логина и пароля

    @FXML
    void adminEnterButton() throws Exception{
        if(!passwordField.getText().equals(pass) || !loginField.getText().equals(log)){
            JOptionPane.showMessageDialog(null, "Неверный логин или пароль");
        }
        else{
            Stage stageToClose  = (Stage) authAdminButton.getScene().getWindow();
            stageToClose.close();

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("administrator-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 850, 500);
            stage.setTitle("Administrator View");
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    void BackButtonRoleView() throws Exception{
        Stage stageToClose  = (Stage) backButtonRoleView.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("role-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 500);
        stage.setTitle("Role");
        stage.setScene(scene);
        stage.show();
    }
}
