package auth;

import user.DuplicateUsernameException;
import user.User;
import user.UserController;
import user.UserService;

import java.sql.SQLException;
import java.util.List;

public class AuthService {
    UserService userService;
    SessionManager sessionManager;

    public AuthService(UserService userService, SessionManager sessionManager) {
       this.userService = userService;
       this.sessionManager = sessionManager;
    }

    User login(String username, String password) throws InvalidCredentialsException, SQLException{
        List<User> users = userService.getAllUsers();

        for(User user : users) {
            if(user.username.equals(username)) {
                if(PasswordHasher.verify(password, user.hashedPass)) {
                    sessionManager.setCurrentUser(user.id);
                    return user;
                } else {
                    throw new InvalidCredentialsException(
                            "Invalid username or password"
                    );
                }
            }

        }

        throw new InvalidCredentialsException(
                "Invalid username or password"
        );

    }

    User register(String username, String password, int age) throws DuplicateUsernameException, SQLException{
        List<User> users = userService.getAllUsers();

        for(User user : users) {
            if(user.username.equals(username)) {
                throw new DuplicateUsernameException(
                        "Username already exists"
                );
            }
        }

        String hashedPass = PasswordHasher.hash(password);
        User user = userService.createUser(username, hashedPass, age);

        sessionManager.setCurrentUser(user.id);
        return user;

    }

    void logout() {
        sessionManager.clearSession();
    }
}
