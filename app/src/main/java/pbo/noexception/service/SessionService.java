package pbo.noexception.service;

import java.util.ArrayList;
import java.util.List;

import pbo.noexception.common.Result;
import pbo.noexception.model.User;

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

    public Result<User> login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return Result.success(user);
            }
        }
        return Result.failure("User tidak ditemukan");
    }
}
