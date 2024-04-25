import database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("YEMS");

        DatabaseConnection dbConnection = new DatabaseConnection();

        dbConnection.connect();

        try {
            PreparedStatement statement = dbConnection.getConnection().prepareStatement(
                    "INSERT INTO auth_account(name, password)" +
                            " VALUES('test','test')"
            );
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        dbConnection.disconnect();

    }
}
