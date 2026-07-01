package pbo.useexception.service;

import java.util.ArrayList;
import java.util.List;

import pbo.useexception.exception.UserNotFoundException;
import pbo.useexception.model.User;

public class SessionService {

    private final List<User> users;

    public SessionService() {
        users = new ArrayList<>();
        users.add(new User("Furina", "furina@example.com", "furina", "furina123"));
        users.add(new User("Neuvillette", "neuvillette@example.com", "neuvillette", "neuvillette123"));
        users.add(new User("Raiden Shogun", "raiden@example.com", "raiden", "raiden123"));
        users.add(new User("Nahida", "nahida@example.com", "nahida", "nahida123"));
        users.add(new User("Zhongli", "zhongli@example.com", "zhongli", "zhongli123"));
    }

    public User login(String username, String password) throws UserNotFoundException {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserNotFoundException("User tidak ditemukan");
    }
}
