package pbo;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pilih versi program:");
        System.out.println("1. No Exception");
        System.out.println("2. Use Exception");
        System.out.print("Pilihan: ");
        String pilihan = scanner.nextLine().trim();

        if (pilihan.equals("2")) {
            new pbo.useexception.view.SessionView(scanner).start();
        } else {
            new pbo.noexception.view.SessionView(scanner).start();
        }
    }
}
