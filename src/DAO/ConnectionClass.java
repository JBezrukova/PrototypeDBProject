package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Created by bezru on 21.03.2017.
 */
public class ConnectionClass {

    private static volatile Connection instance;

    private ConnectionClass() {
    }

    public static Connection getInstance() {
        if (instance == null) {
            synchronized (Connection.class) {
                if (instance == null) {
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        instance = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
                                "databaseName=Med;integratedSecurity=true");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }
}