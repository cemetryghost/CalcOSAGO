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

public class UserView {
    @FXML
    private TextField ageField, experienceField, powerField; // Объявление текстовых полей возраста, стажа и мощности двигателя

    @FXML
    private Button calcButton, buttonBackRole; // Объявление кнопок расчета и возврата на предыдущее окно

    @FXML
    private ComboBox<String> kbmMenu, ogranDrivers, registrationMenu, seasonMenu; //Объявление выпадающих списков КБМ, ограничения по водителям, местом регистрации и сезонностью использования ТС

    @FXML
    private CheckBox taxiCheck; // Объявление флаговой метки, для использования автомобиля в такси

    public static double T; // Переменная, которая будет использовать остальные коэффициенты для расчета
    private double TB = 0; // Объект коэффициента базовой ставки
    private double KO, KS, KBM, KVS, KT, KM; // Остальные коэффициенты, необходимые для вычисления страхования

    @FXML
    void Calculate(){
        try{
            int age = Integer.parseInt(ageField.getText());
            int experience = Integer.parseInt(experienceField.getText());
            int power = Integer.parseInt(powerField.getText());

            if(age - experience < 18){
                JOptionPane.showMessageDialog(null, "Ошибка! Проверьте поля возраста и стажа!");
            }
            else{
                if(taxiCheck.isSelected()){
                    TB = Double.parseDouble(AdministratorView.base.get(0).split("\\s{2,100}")[1]);
                }
                else{
                    TB = Double.parseDouble(AdministratorView.base.get(1).split("\\s{2,100}")[1]);
                }

                /*
                Из листа "drivers" берём элемент под тем индексом, который был выбран в ComboBox.
                Этот элемент делим на массив, где разделительным знаком является 2 и больше пробелов.
                Из полученного массива берём элемент с индексом 1.

                Такой метод применяется к переменным KO, KS, KBM, KT.
                 */
                KO = Double.parseDouble(AdministratorView.drivers.get(ogranDrivers.getSelectionModel().getSelectedIndex()).split("\\s{2,100}")[1]);
                KS = Double.parseDouble(AdministratorView.months.get(seasonMenu.getSelectionModel().getSelectedIndex()).split("\\s{2,100}")[1]);
                KBM = Double.parseDouble(AdministratorView.kbmArray.get(kbmMenu.getSelectionModel().getSelectedIndex()).split("кбм")[1].replaceAll("\\)","").trim());
                KT = Double.parseDouble(AdministratorView.cities.get(registrationMenu.getSelectionModel().getSelectedIndex()).split("\\s{2,100}")[1]);

                KM = getPowerKef(power);
                KVS = getKefAge(age, experience);

                T = TB * KT * KBM * KVS * KO * KM * KS; // Главная расчетная формула

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
            JOptionPane.showMessageDialog(null, "Ошибка! Проверьте корректность заполнения всех полей!");
        }
    }

    @FXML
    void initialize() {

        /*
        В ComboBox помещаем значения из листа без индекса.
         */
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

    public static double getKefAge(int age, int experience){
        double number = 0; // Переменная для ретурна.
        for(int i = 0; i < AdministratorView.age.size(); i++){ // Перебираем массив с возрастом и стажем
            // Если возраст меньше или равен 59, опыт меньше 14 и в выбранном элементе нет слова "больше"
            if(age <= 59 && experience < 14 && !AdministratorView.age.get(i).split(" ")[1].contains("больше")){
                String text = AdministratorView.age.get(i).split(" ")[1]; // Делим элемент по пробелам и берём тот элемент, где написан возраст (16-21)
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

            // Если возраст 59 и меньше и опыт больше 14
            else if(age <= 59 && experience > 14){
                String text = AdministratorView.age.get(i).split(" ")[1]; // Делим элемент по пробелам и берём тот элемент, где написан возраст (16-21)
                int start = Integer.parseInt(text.split("-")[0]); // Помещается возраст от (16)
                int end = Integer.parseInt(text.split("-")[1]); // Помещается возраст до (21)

                // Если введённый возраст находится в в диапазоне и после слова "Стаж" есть слово "больше", то берётся коэффициент из этого элемента и возвращается
                if(age >= start && age <= end && AdministratorView.age.get(i).split(" ")[3].equals("больше")){
                    number = Double.parseDouble(AdministratorView.age.get(i).split("\\s{2,100}")[1]);
                    return number;
                }
            }

            // Если возраст больше или равен 60, опыт меньше или равен 14 и в элементе есть фраза "больше 60"
            else if(age >= 60 && experience <= 14 && AdministratorView.age.get(i).contains("больше 60")){
                String text = AdministratorView.age.get(i).split(" ")[4]; // Делится строка по пробелам и берётся 4 элемент, который является стажем
                if(Integer.parseInt(text) == experience){ // Если выбранный стаж равен введённому то берётся коэффициент из этого элемента и возвращается
                    number = Double.parseDouble(AdministratorView.age.get(i).split("\\s{2,100}")[1]);
                    return number;
                }
            }

            // Если возраст болше 60 и опты больше 14, то берётся последний элемент листа, делится по пробелу в 2 и больше символа и берётся 1 индекс, где написан коэффициент
            else if(age > 60 && experience > 14){
                number = Double.parseDouble(AdministratorView.age.get(AdministratorView.age.size() - 1).split("\\s{2,100}")[1]);
                return number;
            }
        }
        return number;
    }

    public static double getPowerKef(int power){
        double result = 0;

        // Если лошадиных сил меньше или равно 50, то берётся первый элемент листа, делится по пробелам и берётся 1 индекс, где коэф
        if(power <= 50){
            result = Double.parseDouble(AdministratorView.power.get(0).split("\\s{2,100}")[1]);
            return result;
        }

        // Если лошадиных сил больше 150, то берётся последний элемент листа, делится по пробелам и берётся 1 индекс, где коэф
        else if(power > 150){
            result = Double.parseDouble(AdministratorView.power.get(AdministratorView.power.size() - 1).split("\\s{2,100}")[1]);
            return result;
        }

        // Если лошадиные силы в диапазоне от 51 до 151
        else if(power <= 150 && power >= 51){
            for(int i = 1; i < AdministratorView.power.size() - 1; i++){ // Перебирается лист
                String space = AdministratorView.power.get(i).split(" ")[0]; // Элемент делится по пробелам, берётся 0 индекс, где диапазон (50-70)
                int start = Integer.parseInt(space.split("-")[0]); // Берётся первое число (50)
                int end = Integer.parseInt(space.split("-")[1]); // Берётся второе число (70)

                // Если силы находятся в этом диапазоне, то элемент делится по большому пробелу и берётся 1 индекс, где коэф
                if(power >= start && power <= end){
                    result = Double.parseDouble(AdministratorView.power.get(i).split("\\s{2,100}")[1]);
                    return result;
                }
            }
        }
        return result;
    }

    // Метод кнопки, для возвращения на окно выбора роли

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
