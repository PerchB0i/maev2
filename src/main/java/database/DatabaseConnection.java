package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:test.sqlite");
            System.out.println("Connected");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void disconnect() {
        try {
            connection.close();
            System.out.println("Disconnected");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
