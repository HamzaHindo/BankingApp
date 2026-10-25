package auth;

import user.DuplicateUsernameException;
import user.User;

import java.sql.SQLException;

public class AuthController {
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public User login(String username, String password) {
        try {
            return authService.login(username, password);

        } catch (InvalidCredentialsException e) {
            System.out.println("Invalid username or password.");
            return null;

        } catch (SQLException e) {
            System.out.println("DB Error: " + e.getMessage());
            return null;

        }
    }
    public User register(String username, String password, int age) {
        try {
            return authService.register(username, password, age);

        } catch (DuplicateUsernameException e) {
            System.out.println(e.getMessage());
            return null;

        } catch (SQLException e) {
            System.err.println("Database error. Please try again.");
            return null;
        }
    }
    public void logout() {
        authService.logout();
    }
}
