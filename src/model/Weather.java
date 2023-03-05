package model;

import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

import javax.swing.text.html.ImageView;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Weather {
//    "id"	INTEGER NOT NULL UNIQUE,
//            	"time"	NUMERIC,
//            "morningTemp"	INTEGER,
//            "dayTemp"	INTEGER,
//            "windSpeed"	INTEGER,
//            "weatherType"	INTEGER,
//            "notes"	TEXT,
//    PRIMARY KEY("id" AUTOINCREMENT)
    private int id;
    private long time;
    private String morningTemp;
    private String dayTemp;
    private String windSpeed;
    private int weatherType;
    private String notes;
    private String weather;
    private Image imageWeather;
    private String date;
    private HBox hbox;

    public Weather(){

    }

    public String getDate(){
        Date d = new Date(time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return Instant.ofEpochMilli(d.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate().format(formatter);
    }
    public LocalDate getLocalDate(){
        Date d = new Date(time);

        return Instant.ofEpochMilli(d.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    public String getWeather() {
        weather = WeatherType.textWeather[weatherType];
        return weather;
    }
    public  Image getImageWeather() {
        imageWeather = new Image("resources/icons/" + WeatherType.imageWeather[weatherType]);
        return imageWeather;
    }
    public HBox getHbox() {
        return WeatherObject.getWeatherBlock(weatherType);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public long getTime() {
        return time;
    }
    public void setTime(long time) {this.time = time;}
    public String getMorningTemp() {
        return morningTemp;
    }
    public void setMorningTemp(String morningTemp) {
        this.morningTemp = morningTemp;
    }
    public String getDayTemp() {
        return dayTemp;
    }
    public void setDayTemp(String dayTemp) {
        this.dayTemp = dayTemp;
    }
    public String getWindSpeed() {
        return windSpeed;
    }
    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
    public int getWeatherType() {
        return weatherType;
    }
    public void setWeatherType(int weatherType) {
        this.weatherType = weatherType;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
}
