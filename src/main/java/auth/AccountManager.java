package auth;

import database.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    static DatabaseConnection connection;

    public void register(Account account) {
        try {
            PreparedStatement statement = connection.getConnection().
                    prepareStatement("INSERT INTO auth_account(name, password) VALUES(?,?)");
            statement.setString(1, account.name());
            statement.setString(2, account.password());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean authenticate(String name, String password) {
        Account account = getUser(name);
        if (account != null) {
            return account.password().equals(password);
        } else
            return false;
    }

    public Account getUser(String name) {
        try {
            PreparedStatement statement = connection.getConnection().
                    prepareStatement("SELECT password FROM auth_account WHERE name = ?");
            statement.setString(1, name);
            statement.execute();
            ResultSet set = statement.getResultSet();
            List<Account> accounts = new ArrayList<>();

            while (set.next()) {
                String password = set.getString("password");
                Account account = new Account(name, password);
                accounts.add(account);
            }

            return accounts.get(0);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
