package user;

import auth.PasswordHasher;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(String username, String hashedPass, int age) throws DuplicateUsernameException, SQLException {
        try {
            return userRepository.save(username, hashedPass, age);
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                throw new DuplicateUsernameException("Username already exists.");
            }

            throw e;
        }

    }

    public List<User> getAllUsers() throws SQLException {
        return userRepository.getAll();
    }

    public User getUserById(UUID id) throws UserNotFoundException, SQLException  {
        User user = userRepository.getUserById(id);

        if(user == null) {
            throw new UserNotFoundException("User Not Found");
        }

        return user;
    }

}
