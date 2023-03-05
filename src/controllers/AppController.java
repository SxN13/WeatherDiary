package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import dao.Crud;
import dao.WeatherDAO;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import model.Weather;
import model.WeatherObject;
import model.WeatherType;

public class AppController {

    @FXML
    private AnchorPane apChangeArea;
    @FXML
    private Button bAdd, bCancel, bDelete, bEdit, bSave, bSearch;

    @FXML
    private ChoiceBox<String> cbWeather;

    @FXML
    private DatePicker dpDate;

    @FXML
    private TextArea taNotes;

    @FXML
    private TextField tfDay, tfMorning, tfSearch, tfWind;

    @FXML
    private TableView<Weather> tvData;

    @FXML
    private ImageView ivWeather;

    Crud crud = new Crud();
    DiaryTableController diaryTableController;
    boolean isNew = true;
    Weather selectedWeather;
    ArrayList<Weather> oldList;

    @FXML
    void initialize() {
        getDB();
        setupMainButton();
        setupWeatherCheckBox();
        setupEditButton();
        setupDateConverter(dpDate);
        setNumericRegexCheck(tfWind);
        setNumericRegexCheck(tfMorning);
        setNumericRegexCheck(tfDay);
        setChooseBoxListener();
        setSelectedRowListener(tvData);
        setSearchListener();
        diaryTableController = new DiaryTableController(tvData);
    }

    // Поиск по списку
    private void setSearchListener(){
        tfSearch.textProperty().addListener((observableValue, s, t1) -> {
            ArrayList<Weather> newList = new ArrayList<>(0);
            if(tfSearch.getText().equals("")) {
                tvData.getItems().clear();
                tvData.getItems().addAll(oldList);
            }
            for (Weather weather:
                 tvData.getItems()){
                if(weather.getDate().contains(t1) |
                weather.getNotes().toLowerCase().contains(t1)|
                weather.getDayTemp().contains(t1)|
                weather.getMorningTemp().contains(t1)|
                weather.getDate().contains(t1)|
                weather.getWindSpeed().contains(t1)|
                ((Label) (weather.getHbox().getChildren().get(1))).getText().toLowerCase().contains(t1)){
                    newList.add(weather);
                }
            }
            tvData.getItems().clear();
            tvData.getItems().addAll(newList);
        });
    }

    // Обработка выделения
    private void setSelectedRowListener(TableView<Weather> table) {
        table.getSelectionModel().selectedItemProperty().addListener((observableValue, weather, t1) -> {
            if(!t1.equals(null)){
                bEdit.setVisible(true);
                bDelete.setVisible(true);
            }else {
                bEdit.setVisible(false);
                bDelete.setVisible(false);
            }
        });
        table.setRowFactory( tv -> {
            TableRow<Weather> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                selectedWeather = row.getItem();
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Weather rowData = row.getItem();
                    isNew = false;
                    addData(rowData);
                }
            });
            return row ;
        });
    }

    // Обработка выбора выбора
    private void setChooseBoxListener() {
        cbWeather.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                Image image = new Image("resources/icons/" + WeatherType.imageWeather[(int) t1]);
                ivWeather.setImage(image);
            }
        });
    }

    // Чекбокс с погодой
    private void setupWeatherCheckBox() {
        for (String text : WeatherType.textWeather) {
            cbWeather.getItems().add(text);
        }
    }

    //    Подключение к базе данных
    private void getDB() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    oldList = WeatherDAO.getAll(crud.createConnection(), tvData);
                }catch (Exception ex){
                    System.out.println(ex);
                }
            }
        }).start();
    }

//    Проверка введенных значений
    private void setNumericRegexCheck(TextField textField) {
        textField.textProperty().addListener((arg0, oldValue, newValue) -> {
            if(textField.getText().matches("(^[-]?|^[+]?)[0-9]+([.|,][0-9]+)?")){
                textField.setStyle("-fx-text-fill: black;");
                System.out.println(textField.getText() + " -> OK");
            }
            else {
                System.out.println("!");
                textField.setStyle("-fx-text-fill: red;");
                System.out.println(textField.getText() + " -> BAD");
            }
        });
    }

//    Изменение формата даты
    private void setupDateConverter(DatePicker datePicker) {
        datePicker.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                if (localDate != null) {
                    return dateFormatter.format(localDate);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String s) {
                if (s != null && !s.isEmpty()) {
                    return LocalDate.parse(s, dateFormatter);
                } else {
                    return null;
                }
            }

            String pattern = "dd.MM.yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            {
                datePicker.setPromptText("дд.мм.гггг");
            }
        });
    }

//    Очистка формы
    private void formClear(){
        dpDate.setValue(null);
        tfDay.setText("");
        tfMorning.setText("");
        tfWind.setText("");
        cbWeather.getSelectionModel().select(0);
        taNotes.setText("");
    }

//    Инициализация кнопок второстепенного меню
    private void setupEditButton() {
        bSave.setOnAction(actionEvent -> {
            save();
        });
        bCancel.setOnAction(actionEvent -> {
            formClear();
            apChangeArea.setVisible(!apChangeArea.isVisible());
        });
    }

    //    Инициализация кнопок главного окна
    private void setupMainButton() {
        bAdd.setOnAction(actionEvent -> {
            isNew = true;
            addData();
        });
        bEdit.setOnAction(actionEvent -> {
            isNew = false;
            addData(tvData.getSelectionModel().getSelectedItem());
        });
        bDelete.setOnAction(actionEvent -> deleteEntry());
    }

    // Удаление выыделенной записи
    private void deleteEntry() {
        if(selectedWeather != null){
            WeatherDAO.delete(crud.createConnection(), selectedWeather);
            refreshTable();
        }
    }

    // Изменение записи
    private void addData(Weather weather) {
        apChangeArea.setVisible(!apChangeArea.isVisible());
        dpDate.setValue(weather.getLocalDate());
        tfMorning.setText(String.valueOf(weather.getMorningTemp()));
        tfDay.setText(String.valueOf(weather.getDayTemp()));
        tfWind.setText(String.valueOf(weather.getWindSpeed()));
        cbWeather.getSelectionModel().select(weather.getWeatherType());
        taNotes.setText(weather.getNotes());
        selectedWeather = weather;
    }
    // Добавление новой записи
    private void addData() {
        apChangeArea.setVisible(!apChangeArea.isVisible());
        formClear();
    }
    // Сохранение формы в БД
    private void save() {
        if(!getCheck()) return;
        Weather weather = new Weather();
        weather.setTime((java.sql.Date.valueOf(dpDate.getValue())).getTime());
        weather.setDayTemp(tfDay.getText());
        weather.setMorningTemp(tfMorning.getText());
        weather.setWindSpeed(tfWind.getText());
        weather.setWeatherType(cbWeather.getSelectionModel().getSelectedIndex());
        weather.setNotes(taNotes.getText());
        if(isNew){
            WeatherDAO.add(crud.createConnection(), weather);
        }else {
            weather.setId(selectedWeather.getId());
            selectedWeather = null;
            WeatherDAO.update(crud.createConnection(), weather);
        }
        isNew = false;
        addData();
        refreshTable();
    }

    // Обновление таблицы
    private void refreshTable() {
        tvData.getItems().clear();
        oldList = WeatherDAO.getAll(crud.createConnection(), tvData);
    }

    // Проверка правильности данных с формы
    private boolean getCheck() {
        boolean checked = dpDate.getValue()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                .matches("^([123]0|[012][1-9]|31).(0[1-9]|1[012]).(19[0-9]{2}|2[0-9]{3})$");
        if(getCorrectNumber(tfDay) & getCorrectNumber(tfWind) & getCorrectNumber(tfMorning)){
            checked = true;
        }
        return checked;
    }

    // Проверка численных значений
    private boolean getCorrectNumber(TextField textField) {
        if(textField.getText().equals("")){
            return true;
        }else {
            return textField.getText().matches("(^[-]?)[0-9]+([.|,][0-9]+)?");
        }
    }
}
