package Models;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnect {

    //Databaseconfig
    static String url = "localhost";
    static int port = 3306;
    static String database = "gritacademy";
    static String username = "root";
    static String password = "8462";

    //Private variables
    private static DatabaseConnect db;
    private MysqlDataSource dataSource;
    private DatabaseConnect () {}

    private void initializeDataSource() {

        dataSource = new MysqlDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL("jdbc:mysql://" + url + ":" + port + "/" + database + "?serverTimezone=UTC");
    }

    private Connection createConnection() {
        try {
            return dataSource.getConnection();
        } catch(SQLException ex) {
            PrintSQLException(ex);
            return null;
        }
    }

    public static Connection getConnection() {
        if (db == null) {
            db = new DatabaseConnect();
            db.initializeDataSource();
        }
        return db.createConnection();
    }


    public static void PrintSQLException(SQLException sqle) {
        PrintSQLException(sqle, false);
    }
    public static void PrintSQLException(SQLException sqle, Boolean printStackTrace) {
        while(sqle != null) {
            System.out.println("\n---SQLException Caught---\n");
            System.out.println("SQLState: " + sqle.getSQLState());
            System.out.println("ErrorCode: " + sqle.getErrorCode());
            System.out.println("Message: " + sqle.getMessage());
            if(printStackTrace) sqle.printStackTrace();
            sqle = sqle.getNextException();
        }
    }
}