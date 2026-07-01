package pbo.noexception.controller;

import pbo.noexception.common.Result;
import pbo.noexception.model.User;
import pbo.noexception.service.SessionService;

public class SessionController {

    private final SessionService sessionService;
    private User session;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
        this.session = null;
    }

    public Result<User> login(String username, String password) {
        if (session != null) {
            return Result.failure("Sudah login, silahkan logout lebih dahulu");
        }

        Result<User> loginResult = sessionService.login(username, password);
        if (!loginResult.isSuccess()) {
            return Result.failure(loginResult.getErrorMessage());
        }

        session = loginResult.getData();
        return Result.success(session);
    }

    public Result<User> getUserInfo() {
        if (session == null) {
            return Result.failure("Silahkan login dulu");
        }
        return Result.success(session);
    }

    public Result<String> logout() {
        if (session == null) {
            return Result.failure("Silahkan login dulu");
        }
        session = null;
        return Result.success("Logout berhasil");
    }
}
