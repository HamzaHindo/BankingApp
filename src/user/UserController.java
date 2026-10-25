package user;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public User createUser(String username, String hashedPass, int age) throws SQLException {
        try {
            return userService.createUser(username, hashedPass,age);
        } catch (DuplicateUsernameException e) {
            System.out.println(e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (SQLException e) {
            System.out.println("Database error, Try again: " + e.getMessage());
            return null;
        }
    }

    public User getUserById(UUID id) {
        try {
            return userService.getUserById(id);
        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
            return null;
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
