package dao;

import java.io.File;
import java.sql.*;

public class Crud {
    Connection connection;
    //    Создание соединения к базе данных
    public Connection createConnection()
    {
        connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            File file = new File("./weather.db");

            if(!file.exists()){
                createNewDatabase();
            }
            connection = DriverManager.getConnection("jdbc:sqlite:./weather.db");

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            createNewDatabase();
        }
        System.out.println("Opened database successfully");
        return connection;
    }
    //    Создание файла базы данных
    private void createNewDatabase() {
        String url = "jdbc:sqlite:./weather.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
            createConnection();
            createTables();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTables() throws SQLException {
        if(connection == null) createConnection();

        Statement statement = connection.createStatement();

        statement.execute(QWeather.create);
    }
}