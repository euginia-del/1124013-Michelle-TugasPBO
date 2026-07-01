package pbo.useexception.view;

import java.util.Scanner;

import pbo.useexception.controller.SessionController;
import pbo.useexception.exception.AlreadyLoggedInException;
import pbo.useexception.exception.NotLoggedInException;
import pbo.useexception.exception.UserNotFoundException;
import pbo.useexception.model.User;
import pbo.useexception.service.SessionService;

public class SessionView {
    private final SessionController sessionController;
    private final Scanner scanner;

    public SessionView() {
        this(new Scanner(System.in));
    }

    public SessionView(Scanner scanner) {
        this.sessionController = new SessionController(new SessionService());
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    login();
                    break;
                case "2":
                    getUserInfo();
                    break;
                case "3":
                    logout();
                    break;
                case "0":
                    running = false;
                    System.out.println("Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("===== SESSION MENU (Use Exception) =====");
        System.out.println("1. Login");
        System.out.println("2. Get User Info");
        System.out.println("3. Logout");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    private void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        try {
            User user = sessionController.login(username, password);
            System.out.println("Login berhasil! Selamat datang, " + user.getNama() + ".\n");
        } catch (AlreadyLoggedInException | UserNotFoundException e) {
            System.out.println("Login gagal: " + e.getMessage() + "\n");
        }
    }

    private void getUserInfo() {
        try {
            User user = sessionController.getUserInfo();
            System.out.println("Info user:");
            System.out.println("  Nama     : " + user.getNama());
            System.out.println("  Email    : " + user.getEmail());
            System.out.println("  Username : " + user.getUsername());
            System.out.println();
        } catch (NotLoggedInException e) {
            System.out.println("Gagal: " + e.getMessage() + "\n");
        }
    }

    private void logout() {
        try {
            String message = sessionController.logout();
            System.out.println(message + "\n");
        } catch (NotLoggedInException e) {
            System.out.println("Gagal: " + e.getMessage() + "\n");
        }
    }
}
