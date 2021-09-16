import java.sql.*;

public class DatabaseConnection {
    private static DatabaseConnection instance = new DatabaseConnection();
    public static final String URL = "jdbc:h2:file:~/shop";
    public static final String USER = "haroon";
    public static final String PASSWORD = "123456";
    public static final String DRIVER_CLASS = "org.h2.Driver";

    //private constructor
    private DatabaseConnection() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }

    public static void endConnection(Connection con) {
        instance.closeConnection(con);
    }

    private void closeConnection(Connection con){
       try{
           con.close();
       }catch (SQLException e){
           System.out.println("Error Closing connection...");
       }
    }


}
