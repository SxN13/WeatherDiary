package dao;

public class QWeather {
    public static String create = """
            CREATE TABLE "weather" (
            	"id"	INTEGER NOT NULL UNIQUE,
            	"time"	NUMERIC,
            	"morningTemp"	TEXT,
            	"dayTemp"	TEXT,
            	"windSpeed"	TEXT,
            	"weatherType"	INTEGER,
            	"notes"	TEXT,
            	PRIMARY KEY("id" AUTOINCREMENT)
            );
            """;
    public static String add = """
            insert into weather (time, morningTemp, dayTemp, windSpeed, weatherType, notes)
            values (?,?,?,?,?,?);
            """;
    public static String get = """
            select * from weather where time=?;
            """;
    public static String getAll = """
            select * from "weather";
            """;
    public static String delete = """
            delete
            from weather
            where id=?;
            """;
    public static String update = """       
            update weather
            set
            time=?,
            morningTemp=?,
            dayTemp=?,
            windSpeed=?,
            weatherType=?,
            notes=?
            where id=?;
            """;
}
