package com.example.osago;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

// Класс отображения стоимости страхования

public class ResultView {
    @FXML
    Label result; // Элемент, используемый для отображения стоимости ОСАГО
    @FXML
    Button backButtonUser; // Кнопка возврата на предыдущее окно приложения

    // Метод кнопки, для закрытия текущего окна и возврата на окно пользователя

    @FXML
    void backButton() throws Exception{
        Stage stageToClose  = (Stage) backButtonUser.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 500);
        stage.setTitle("User View");
        stage.setScene(scene);
        stage.show();
    }

    // Метод кнопки для завершения работы программы

    @FXML
    void ExitButton() throws Exception{
        System.exit(1);
    }

    // Метод отображения полученного результат подсчета из класса пользователя

    @FXML
    void initialize() {
        result.setText(String.format("%.2f", UserView.T) + " рублей");
    }

}
