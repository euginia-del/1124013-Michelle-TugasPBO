package pbodatabase.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import pbodatabase.AppConfig;

public class Database {
    public static final String URL = "jdbc:postgresql://"
            + AppConfig.HOST + ":" + AppConfig.PORT + "/" + AppConfig.DATABASE;

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, AppConfig.USER, AppConfig.PASSWORD);
            System.out.println("Connected to PostgreSQL successfully!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }
}
