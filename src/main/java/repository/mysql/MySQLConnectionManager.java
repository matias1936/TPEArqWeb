package repository.mysql;

import factory.ConnectionManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionManager implements ConnectionManager {
    private static volatile MySQLConnectionManager instance;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/demodao";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private MySQLConnectionManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MySQLConnectionManager getInstance() {
        if (instance == null) {
            synchronized (MySQLConnectionManager.class) {
                if (instance == null) {
                    instance = new MySQLConnectionManager();
                }
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void shutdown() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection = null;
            synchronized (MySQLConnectionManager.class) {
                instance = null;
            }
        }
    }
}
