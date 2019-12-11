package millionaire.Model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private Connection connection;
    public Connection getConnection(){
        String DB_CONNECTION = "jdbc:mysql://localhost/";
        String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_NAME = "millionaire";
        String DB_USER = "root";
        String DB_PASSWORD = "";

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION + DB_NAME, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
