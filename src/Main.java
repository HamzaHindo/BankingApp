import auth.AuthController;
import auth.AuthService;
import auth.SessionManager;
import database.DatabaseConnection;
import user.User;
import user.UserController;
import user.UserRepository;
import user.UserService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main{

    public static void main()  {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        UserController userController = new UserController(userService);

        SessionManager sessionManager = new SessionManager();
        AuthService authService = new AuthService(userService, sessionManager);
        AuthController authController = new AuthController(authService);

        User user = authController.login("MostafaMahmoud", "Mostafa@123");

        System.out.println(user.username);

    }
}