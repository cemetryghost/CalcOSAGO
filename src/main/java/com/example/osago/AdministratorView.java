package com.example.osago;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/* Класс администратора */

public class AdministratorView implements Initializable {

    /* Объявление и инициализация объектов, с указанием пути к файлам из папки проекта */

    public static String agePath = "C:\\Users\\matve\\Downloads\\Telegram Desktop\\jk\\OSAGO\\age.txt";
    public static String citiesPath = "C:\\Users\\matve\\Downloads\\Telegram Desktop\\jk\\OSAGO\\cities.txt";
    public static String driversPath = "C:\\Users\\matve\\Downloads\\Telegram Desktop\\jk\\OSAGO\\drivers.txt";
    public static String kbmPath = "C:\\Users\\matve\\Downloads\\Telegram Desktop\\jk\\OSAGO\\kbm.txt";
    public static String monthsPath = "C:\\Users\\matve\\Downloads\\Telegram Desktop\\jk\\OSAGO\\months.txt";
    public static String powerPath = "C:\\Users\\matve\\Downloads\\Telegram Desktop\\jk\\OSAGO\\power.txt";
    public static String basePath = "C:\\Users\\matve\\Downloads\\Telegram Desktop\\jk\\OSAGO\\base.txt";

    /* Объявление и инициализация объектов, с методом  */

    public static ObservableList<String> age = pathToList(agePath);
    public static ObservableList<String> cities = pathToList(citiesPath);
    public static ObservableList<String> months = pathToList(monthsPath);
    public static ObservableList<String> kbmArray = pathToList(kbmPath);
    public static ObservableList<String> drivers = pathToList(driversPath);
    public static ObservableList<String> power = pathToList(powerPath);
    public static ObservableList<String> base = pathToList(basePath);

    @FXML
    ComboBox<String> ageCombo, powerCombo, placeCombo, seasonCombo, baseCombo, driversCombo, KBMCombo; /* Объявляем выпадающие списки, с их id */

    @FXML
    TextField ageField, powerField, placeField, seasonField, baseField, driversField, KBMField; /* Объявляем текстовые поля, с их id */

    @FXML
    Button formulaButton, backButtonAuth; /* Объявляем кнопки, с их id */

    // Метод

    public void Calculate() {
        try{
            if(!placeCombo.getSelectionModel().isEmpty() && !placeField.getText().isEmpty()){
                /*
                Получается текст из комбобокса и значение из текстфилда */
                String combo = placeCombo.getValue();
                double field = Double.parseDouble(placeField.getText());

                String number = combo.split("\\s{2,100}")[1]; // Полученный текст из комбобокса делится на массив по пробелам и берётся 1 индекс, т.е коэффициент
                String write = combo.replaceAll(number, String.valueOf(field)); // В переменную записывается текст из комбобокса с изменённым коэффициентом
                int k = placeCombo.getSelectionModel().getSelectedIndex(); // Берётся индекс выбранного из комбобокса текста

                cities.set(k, write); // В листе под выбранным индексом меняется текста на новый с изменённым коэффициентом
                placeCombo.getItems().set(k, write); // Комбобокс обновляется с новыми значениями
                reWrite(citiesPath, cities); // В файл записывается новый изменённый лист
            }

            if(!seasonCombo.getSelectionModel().isEmpty() && !seasonField.getText().isEmpty()){
                String combo = seasonCombo.getValue();
                double field = Double.parseDouble(seasonField.getText());

                String number = combo.split("\\s{2,100}")[1];
                String write = combo.replaceAll(number, String.valueOf(field));
                int k = seasonCombo.getSelectionModel().getSelectedIndex();

                months.set(k, write);
                seasonCombo.getItems().set(k, write);
                reWrite(monthsPath, months);
            }

            if(!KBMCombo.getSelectionModel().isEmpty() && !KBMField.getText().isEmpty()){
                String combo = KBMCombo.getValue();
                Double field = Double.parseDouble(KBMField.getText());

                String number = combo.split("кбм\\s")[1].replaceAll("\\)","");
                String write = combo.replaceAll(number, String.valueOf(field));
                int k = KBMCombo.getSelectionModel().getSelectedIndex();

                kbmArray.set(k, write);
                KBMCombo.getItems().set(k, write);
                reWrite(kbmPath, kbmArray);
            }

            if(!powerCombo.getSelectionModel().isEmpty() && !powerField.getText().isEmpty()){
                String combo = powerCombo.getValue();
                Double field = Double.parseDouble(powerField.getText());

                String number = combo.split("\\s{2,100}")[1];
                String write = combo.replaceAll(number, String.valueOf(field));
                int k = powerCombo.getSelectionModel().getSelectedIndex();

                power.set(k, write);
                powerCombo.getItems().set(k, write);
                reWrite(powerPath, power);
            }

            if(!baseCombo.getSelectionModel().isEmpty() && !baseField.getText().isEmpty()){
                String combo = baseCombo.getValue();
                Double field = Double.parseDouble(baseField.getText());

                String number = combo.split("\\s{2,100}")[1];
                String write = combo.replaceAll(number, String.valueOf(field));
                int k = baseCombo.getSelectionModel().getSelectedIndex();

                base.set(k, write);
                baseCombo.getItems().set(k, write);
                reWrite(basePath, base);
            }

            if(!driversCombo.getSelectionModel().isEmpty() && !driversField.getText().isEmpty()){
                String combo = driversCombo.getValue();
                double field = Double.parseDouble(driversField.getText());

                String number = combo.split("\\s{2,100}")[1];
                String write = combo.replaceAll(number, String.valueOf(field));
                int k = driversCombo.getSelectionModel().getSelectedIndex();

                drivers.set(k, write);
                driversCombo.getItems().set(k, write);
                reWrite(driversPath, drivers);
            }

            if(!ageCombo.getSelectionModel().isEmpty() && !ageField.getText().isEmpty()){
                String combo = ageCombo.getValue();
                double field = Double.parseDouble(ageField.getText());

                String number = combo.split("\\s{2,100}")[1];
                String write = combo.replaceAll(number, String.valueOf(field));
                int k = ageCombo.getSelectionModel().getSelectedIndex();

                age.set(k, write);
                ageCombo.getItems().set(k, write);
                reWrite(agePath, age);
            }
            clearAllField(); // Очищение всех текстфилдов
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(null, "Ошибка");
        }
    }

    // Метод для заполнения всех выпадающих списков объектами коллекции, содержащие пути к файлам

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeCombo.getItems().addAll(cities);
        seasonCombo.getItems().addAll(months);
        KBMCombo.getItems().addAll(kbmArray);
        driversCombo.getItems().addAll(drivers);
        powerCombo.getItems().addAll(power);
        ageCombo.getItems().addAll(age);
        baseCombo.getItems().addAll(base);
    }

    // Метод для очистки всех текстовых полей, при изменении администратором коэффициентов

    public void clearAllField(){
        ageField.setText("");
        driversField.setText("");
        baseField.setText("");
        powerField.setText("");
        KBMField.setText("");
        seasonField.setText("");
        placeField.setText("");
    }

    public static ObservableList<String> pathToList(String path){
        ObservableList<String> list = FXCollections.observableArrayList(); // Создаётся новый лист
        String line; // Создаётся строка для будущей записи

        // Чтение файла и запись его в
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))){
            while ((line = br.readLine()) != null) {
                list.add(line); //
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public static void reWrite(String path, ObservableList<String> list){
        String write = "";

        // Полученный данные из листа записываются в переменную, после чего переходит на другую строку
        for(int i = 0; i < list.size(); i++){
            write +=  list.get(i) + "\r\n";
        }

        // Запись в файл
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write(write);
            bw.flush();
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    // Метод кнопки, при нажатии происходит закрытие формы администрирования и переход на окно информации по формуле

    public void toFormula() throws Exception{
        Stage stageToClose  = (Stage) formulaButton.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("formula-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 500);
        stage.setTitle("Formula");
        stage.setScene(scene);
        stage.show();
    }

    public void getSelectedPlace() {
        // Берётся индекс полученного элемента
        int k = placeCombo.getSelectionModel().getSelectedIndex();
        // В TextField помещается текст из листа под номером индекса
        String text = cities.get(k).split("\\s{2,100}")[1];
        placeField.setText(text);
    }

    public void getSelectedAge() {
        int k = ageCombo.getSelectionModel().getSelectedIndex();
        String text = age.get(k).split("\\s{2,100}")[1];
        ageField.setText(text);
    }

    public void getSelectedPower() {
        int k = powerCombo.getSelectionModel().getSelectedIndex();
        String text = power.get(k).split("\\s{2,100}")[1];
        powerField.setText(text);
    }

    public void getSelectedSeason() {
        int k = seasonCombo.getSelectionModel().getSelectedIndex();
        String text = months.get(k).split("\\s{2,100}")[1];
        seasonField.setText(text);
    }

    public void getSelectedBase() {
        int k = baseCombo.getSelectionModel().getSelectedIndex();
        String text = base.get(k).split("\\s{2,100}")[1];
        baseField.setText(text);
    }

    public void getSelectedDrivers() {
        int k = driversCombo.getSelectionModel().getSelectedIndex();
        String text = drivers.get(k).split("\\s{2,100}")[1];
        driversField.setText(text);
    }

    public void getSelectedKBM() {
        int k = KBMCombo.getSelectionModel().getSelectedIndex();
        String text = kbmArray.get(k).split("кбм")[1].replaceAll("\\)","").trim();
        KBMField.setText(text);
    }

    // Метод кнопки, для выхода из приложения

    public void Exit() throws Exception{
        System.exit(1);
    }

    // Метод кнопки, для возврата на окно авторизации

    @FXML
    void BackButtonAuth() throws Exception{
        Stage stageToClose  = (Stage) backButtonAuth.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(AdministratorView.class.getResource("authorization-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 500);
        stage.setTitle("Authorization");
        stage.setScene(scene);
        stage.show();
    }
}
