package user;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {



    User save(String username, String hashed_pass, int age) throws SQLException {
        String sql = """
                INSERT INTO users (username, age, password)
                VALUES (?, ?, ?)
                RETURNING id, username, password, age
                """;

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setInt(2, age);
            statement.setString(3, hashed_pass);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new User(
                            result.getObject("id", UUID.class),
                            result.getString("username"),
                            result.getString("password"),
                            result.getInt("age")
                    );

                }

                throw new SQLException("User insertion failed.");

            } catch (SQLException e) {
                if ("23505".equals(e.getSQLState())) {
                    throw new SQLException("Username already exists.", e);
                }

                throw e;
            }
        }
    }



    List<User> getAll() throws SQLException {
        String sql = """
            SELECT id, username, password, age
            FROM users
            """;

        List<User> users = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                User user = new User(
                        result.getObject("id", UUID.class),
                        result.getString("username"),
                        result.getString("password"),
                        result.getInt("age")
                );

                users.add(user);
            }
        }

        return users;
    }

    public User getUserById(UUID id) throws SQLException {
        String sql = """
            SELECT id, username, password, age
            FROM users
            WHERE id = ?
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setObject(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new User(
                            result.getObject("id", UUID.class),
                            result.getString("username"),
                            result.getString("password"),
                            result.getInt("age")
                    );
                }

                return null;
            }
        }
    }
}
