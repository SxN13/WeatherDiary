package dao;

import controllers.DiaryTableController;
import javafx.scene.control.TableView;
import model.Weather;

import java.sql.*;
import java.util.ArrayList;

public class WeatherDAO {
    //    "id"	INTEGER NOT NULL UNIQUE,
//            	"time"	NUMERIC,
//            "morningTemp"	INTEGER,
//            "dayTemp"	INTEGER,
//            "windSpeed"	INTEGER,
//            "weatherType"	INTEGER,
//            "notes"	TEXT,
//    PRIMARY KEY("id" AUTOINCREMENT)
    public static ArrayList<Weather> getAll(Connection connection, TableView<Weather> table) {
        ArrayList<Weather> weatherArrayList = new ArrayList<>(0);

        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(QWeather.getAll);

            // loop through the result set
            while (rs.next()) {
                Weather weather = new Weather();

                weather.setId(rs.getInt("id"));
                weather.setTime(rs.getLong("time"));
                weather.setMorningTemp(rs.getString("morningTemp"));
                weather.setDayTemp(rs.getString("dayTemp"));
                weather.setWindSpeed(rs.getString("windSpeed"));
                weather.setWeatherType(rs.getInt("weatherType"));
                weather.setNotes(rs.getString("notes"));
                weatherArrayList.add(weather);
                // Test async
                DiaryTableController.addItem(table, weather);
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return weatherArrayList;
    }
    public static ArrayList<Weather> get(Connection connection, long time){
        ArrayList<Weather> weatherArrayList = new ArrayList<>(0);
        try{
            PreparedStatement pstmt  = connection.prepareStatement(QWeather.get);
            pstmt.setDouble(1, time);
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            while (rs.next()) {
                Weather weather = new Weather();

                weather.setId(rs.getInt("id"));
                weather.setTime(rs.getLong("name"));
                weather.setMorningTemp(rs.getString("netId"));
                weather.setDayTemp(rs.getString("notes"));
                weather.setWindSpeed(rs.getString("administratorKey"));
                weather.setWeatherType(rs.getInt("allNetKey"));
                weather.setNotes(rs.getString("technicalSupportId"));
                weatherArrayList.add(weather);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return weatherArrayList;
    }
    public static void add(Connection connection, Weather weather){
        try {
            PreparedStatement pstmt = connection.prepareStatement(QWeather.add);
            pstmt.setLong(1, weather.getTime());
            pstmt.setString(2, weather.getMorningTemp());
            pstmt.setString(3, weather.getDayTemp());
            pstmt.setString(4, weather.getWindSpeed());
            pstmt.setInt(5, weather.getWeatherType());
            pstmt.setString(6, weather.getNotes());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void update( Connection connection, Weather weather){
        try {
            PreparedStatement pstmt = connection.prepareStatement(QWeather.update);
            pstmt.setLong(1, weather.getTime());
            pstmt.setString(2, weather.getMorningTemp());
            pstmt.setString(3, weather.getDayTemp());
            pstmt.setString(4, weather.getWindSpeed());
            pstmt.setInt(5, weather.getWeatherType());
            pstmt.setString(6, weather.getNotes());
            pstmt.setInt(7, weather.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delete( Connection connection, Weather weather){
        try {
            PreparedStatement pstmt = connection.prepareStatement(QWeather.delete);
            pstmt.setInt(1, weather.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}