package millionaire.Model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Millad, Jesse
 */
class DBConnection {
    private Connection connection;
    //Jesse

    /**
     * This method will create a connection to database.
     *
     * @return an object that contain the information to connect to the database.
     */
    Connection getConnection() {

        String DB_CONNECTION = "jdbc:mysql://mohammad-ahmad.se/";
        String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_NAME = "rmvigsfl_millionaire";
        String DB_USER = "rmvigsfl_molndal";
        String DB_PASSWORD = "molndalmolndal";

        // The following commented code can be used for locally hosted database instead of the above one.
        /*
        String DB_CONNECTION = "jdbc:mysql://localhost/";
        String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_NAME = "millionaire";
        String DB_USER = "root";
        String DB_PASSWORD = "";*/

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Couldn't load mysql-connector library.");
        }
        try {
            connection = DriverManager.getConnection(DB_CONNECTION + DB_NAME, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            System.err.println("!!Couldn't create a connection to db!!");
        }
        return connection;
    }
}
