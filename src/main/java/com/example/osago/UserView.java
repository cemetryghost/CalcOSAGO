package com.example.osago;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.*;

// Класс пользователя

public class UserView {

    // Объявление элементов FXML

    @FXML
    TextField ageField, experienceField, powerField;
    @FXML
    Button calcButton, buttonBackRole;
    @FXML
    ComboBox<String> kbmMenu, ogranDrivers, registrationMenu, seasonMenu;
    @FXML
    CheckBox taxiCheck;

    public static double T;
    public double TB = 0;
    public double KO, KS, KBM, KVS, KT, KM;

    // Метод расчета, при нажатии кнопки "Расчет"

    @FXML
    void Calculate(){
        try{
            int age = Integer.parseInt(ageField.getText());
            int experience = Integer.parseInt(experienceField.getText());
            int power = Integer.parseInt(powerField.getText());

            /* Условная конструкция, служащая для корректной проверки полей возраста и стажа
               Если возраст минус стаж равен меньше 18, то отобразится диалоговое окно с ошибкой
               Например, если введен возраст 22 и стаж 10 лет - данные не являются возможными
             */

            if(age - experience < 18){
                JOptionPane.showMessageDialog(null, "Ошибка! Проверьте поля возраста и стажа!");
            }
            else{
                if(taxiCheck.isSelected()){ // Если нажата флаговая метка, то берем значение из листа ТБ с использованием такси, иначе без использования
                    TB = Double.parseDouble(AdministratorView.base.get(0).split("\\s{2,100}")[1]);
                }
                else{
                    TB = Double.parseDouble(AdministratorView.base.get(1).split("\\s{2,100}")[1]);
                }

                /*
                Из листа "drivers" берём элемент под тем индексом, который был выбран в ComboBox.
                Этот элемент делим на массив, где разделительным знаком является 2 и больше пробелов.
                Из полученного массива берём элемент с индексом 1, т.е. коэффициент.

                Такой метод применяется к переменным KO, KS, KBM, KT.
                 */
                KO = Double.parseDouble(AdministratorView.drivers.get(ogranDrivers.getSelectionModel().getSelectedIndex()).split("\\s{2,100}")[1]);
                KS = Double.parseDouble(AdministratorView.months.get(seasonMenu.getSelectionModel().getSelectedIndex()).split("\\s{2,100}")[1]);
                KBM = Double.parseDouble(AdministratorView.kbmArray.get(kbmMenu.getSelectionModel().getSelectedIndex()).split("кбм")[1].replaceAll("\\)","").trim());
                KT = Double.parseDouble(AdministratorView.cities.get(registrationMenu.getSelectionModel().getSelectedIndex()).split("\\s{2,100}")[1]);

                KM = getPowerKef(power);
                KVS = getKefAge(age, experience);

                T = TB * KT * KBM * KVS * KO * KM * KS;

                Stage stageToClose = (Stage) calcButton.getScene().getWindow();
                stageToClose.close();

                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("result-view.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 850, 500);
                stage.setTitle("Result View");
                stage.setScene(scene);
                stage.show();
            }
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(null, "Ошибка! Проверьте корректность всех полей!");
        }
    }

    // Метод добавления всех значений в СomboBox

    @FXML
    void initialize() {
        try{
            for(int i = 0; i < AdministratorView.cities.size(); i++){
                registrationMenu.getItems().addAll(AdministratorView.cities.get(i).split("\\s{2,100}")[0]);
            }
            for(int i = 0; i < AdministratorView.months.size(); i++){
                seasonMenu.getItems().addAll(AdministratorView.months.get(i).split("\\s{2,100}")[0]);
            }

            kbmMenu.getItems().addAll(AdministratorView.kbmArray);

            for(int i = 0; i < AdministratorView.drivers.size(); i++){
                ogranDrivers.getItems().addAll(AdministratorView.drivers.get(i).split("\\s{2,100}")[0]);
            }
        }
        catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    // Метод для соответствия введенным занчениям возраста и стажа, с имеющимися в листе значениями для расчета

    public static double getKefAge(int age, int experience){
        double number = 0;
        for(int i = 0; i < AdministratorView.age.size(); i++){
            if(age <= 59 && experience < 14 && !AdministratorView.age.get(i).split(" ")[1].contains("больше")){
                String text = AdministratorView.age.get(i).split(" ")[1]; // Делим элемент по пробелам и берём тот элемент, где написан возраст
                int start = Integer.parseInt(text.split("-")[0]); // Помещается возраст от (16)
                int end = Integer.parseInt(text.split("-")[1]); // Помещается возраст до (21)
                /*
                Если введённый возраст находится в данном диапазоне и данный элемент содержит нужный стаж
                делим элемент по пробелу в количестве от 2 до 100 и берём значение 1 индекса, то есть коэффициент.
                Возвращаем коэффициент
                 */
                if(age >= start && age <= end && AdministratorView.age.get(i).split(" ")[3].contains(String.valueOf(experience))){
                    number = Double.parseDouble(AdministratorView.age.get(i).split("\\s{2,100}")[1]);
                    return number;
                }
            }
            else if(age <= 59 && experience > 14){
                String text = AdministratorView.age.get(i).split(" ")[1];
                int start = Integer.parseInt(text.split("-")[0]);
                int end = Integer.parseInt(text.split("-")[1]);

                if(age >= start && age <= end && AdministratorView.age.get(i).split(" ")[3].equals("больше")){
                    number = Double.parseDouble(AdministratorView.age.get(i).split("\\s{2,100}")[1]);
                    return number;
                }
            }
            else if(age >= 60 && experience <= 14 && AdministratorView.age.get(i).contains("больше 60")){
                String text = AdministratorView.age.get(i).split(" ")[4]; // Делится строка по пробелам и берётся 4 элемент, который является стажем
                if(Integer.parseInt(text) == experience){ // Если выбранный стаж равен введённому, то берётся коэффициент из этого элемента и возвращается
                    number = Double.parseDouble(AdministratorView.age.get(i).split("\\s{2,100}")[1]);
                    return number;
                }
            }
            else if(age > 60 && experience > 14){
                number = Double.parseDouble(AdministratorView.age.get(AdministratorView.age.size() - 1).split("\\s{2,100}")[1]);
                return number;
            }
        }
        return number;
    }

    // Метод соответствия мощности

    public static double getPowerKef(int power){
        double result = 0;

        if(power <= 50){
            result = Double.parseDouble(AdministratorView.power.get(0).split("\\s{2,100}")[1]);
            return result;
        }
        else if(power > 150){
            result = Double.parseDouble(AdministratorView.power.get(AdministratorView.power.size() - 1).split("\\s{2,100}")[1]);
            return result;
        }
        else if(power <= 150 && power >= 51){
            for(int i = 1; i < AdministratorView.power.size() - 1; i++){
                String space = AdministratorView.power.get(i).split(" ")[0];
                int start = Integer.parseInt(space.split("-")[0]); // Берётся первое число (50)
                int end = Integer.parseInt(space.split("-")[1]); // Берётся второе число (70)

                if(power >= start && power <= end){
                    result = Double.parseDouble(AdministratorView.power.get(i).split("\\s{2,100}")[1]);
                    return result;
                }
            }
        }
        return result;
    }
    @FXML
    void ButtonBackRole() throws Exception{
        Stage stageToClose  = (Stage) buttonBackRole.getScene().getWindow();
        stageToClose.close();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("role-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 500);
        stage.setTitle("Role");
        stage.setScene(scene);
        stage.show();
    }
}
