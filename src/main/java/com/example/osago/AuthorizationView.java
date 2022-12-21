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

    @FXML
    private Button authAdminButton, backButtonRoleView; // Объявление кнопок, с их id

    @FXML
    private TextField loginField; // Объявление поля для логина, с его id

    @FXML
    private PasswordField passwordField; // Объявление поля для пароля, с его id
    String log = "admin", pass = "12345"; // Объявления и иницализация объектов типа String для логина и пароля

    // Метод, для проверки логина и пароля по нажатию кнопки "Войти"

    @FXML
    void adminEnterButton() throws Exception{
        /* Условная конструкция
        * Если введенный пароль или логин не равны значениям, которые мы присвоили ранее, то выведется ошибка
        * Иначе, закроется окно авторизации и осуществиться переход на окно администрирования
        */
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
    // Метод, по нажатию кнопки "Назад", происходит закрытие текущего окна авторизации и возврата на окно выбора роли

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
