package pbo.noexception.view;

import java.util.Scanner;

import pbo.noexception.common.Result;
import pbo.noexception.controller.SessionController;
import pbo.noexception.model.User;
import pbo.noexception.service.SessionService;

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
        System.out.println("===== SESSION MENU (No Exception) =====");
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

        Result<User> result = sessionController.login(username, password);
        if (result.isSuccess()) {
            System.out.println("Login berhasil! Selamat datang, " + result.getData().getNama() + ".\n");
        } else {
            System.out.println("Login gagal: " + result.getErrorMessage() + "\n");
        }
    }

    private void getUserInfo() {
        Result<User> result = sessionController.getUserInfo();
        if (result.isSuccess()) {
            User user = result.getData();
            System.out.println("Info user:");
            System.out.println("  Nama     : " + user.getNama());
            System.out.println("  Email    : " + user.getEmail());
            System.out.println("  Username : " + user.getUsername());
            System.out.println();
        } else {
            System.out.println("Gagal: " + result.getErrorMessage() + "\n");
        }
    }

    private void logout() {
        Result<String> result = sessionController.logout();
        if (result.isSuccess()) {
            System.out.println(result.getData() + "\n");
        } else {
            System.out.println("Gagal: " + result.getErrorMessage() + "\n");
        }
    }
}
