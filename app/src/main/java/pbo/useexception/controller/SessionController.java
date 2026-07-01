package pbo.useexception.controller;

import pbo.useexception.exception.AlreadyLoggedInException;
import pbo.useexception.exception.NotLoggedInException;
import pbo.useexception.exception.UserNotFoundException;
import pbo.useexception.model.User;
import pbo.useexception.service.SessionService;

public class SessionController {

    private final SessionService sessionService;
    private User session;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
        this.session = null;
    }

    public User login(String username, String password)
            throws AlreadyLoggedInException, UserNotFoundException {
        if (session != null) {
            throw new AlreadyLoggedInException("Sudah login, silahkan logout lebih dahulu");
        }

        User user = sessionService.login(username, password);
        session = user;
        return session;
    }

    public User getUserInfo() throws NotLoggedInException {
        if (session == null) {
            throw new NotLoggedInException("Silahkan login dulu");
        }
        return session;
    }

    public String logout() throws NotLoggedInException {
        if (session == null) {
            throw new NotLoggedInException("Silahkan login dulu");
        }
        session = null;
        return "Logout berhasil";
    }
}
