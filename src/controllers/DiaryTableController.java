package controllers;

import dao.WeatherDAO;
import javafx.application.Platform;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Weather;

import java.sql.Connection;

public class DiaryTableController {
    private TableView<Weather> tableView;
    public DiaryTableController(TableView<Weather> tableView){
        this.tableView = tableView;
        this.tableView.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        this.tableView.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("morningTemp"));
        this.tableView.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("dayTemp"));
        this.tableView.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("windSpeed"));
        this.tableView.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("hbox"));
        this.tableView.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("notes"));
    }

    public static void addItem(TableView<Weather> table, Weather weather) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                table.getItems().add(weather);
            }
        });
    }
    public void updateTable(){

    }
    public void clearTable(){
        tableView.getItems().clear();
    }

    public static void fillTable(TableView<Weather> table, Weather weather){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                table.getItems().add(weather);
            }
        });
    }
}
