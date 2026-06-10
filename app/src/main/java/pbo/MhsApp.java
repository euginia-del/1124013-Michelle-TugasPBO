package pbo;

import java.util.ArrayList;
import java.util.Scanner;

public class MhsApp {

    private ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();
    private ArrayList<MataKuliah> matkulList = new ArrayList<>();
    private ArrayList<Enrollment> enrollments = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new MhsApp().run();
    }

    private void run() {
        isiDataDummy();

        boolean running = true;
        while (running) {
            System.out.println("\n========== MENU UTAMA ==========");
            System.out.println("1. Mahasiswa");
            System.out.println("2. Mata Kuliah");
            System.out.println("3. Enrollment");
            System.out.println("4. List per Mata Kuliah");
            System.out.println("5. List per Mahasiswa");
            System.out.println("6. Keluar");
            System.out.println("================================");
            System.out.print("Pilih: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    menuMahasiswa();
                    break;
                case "2":
                    menuMataKuliah();
                    break;
                case "3":
                    menuEnrollment();
                    break;
                case "4":
                    listPerMataKuliah();
                    break;
                case "5":
                    listPerMahasiswa();
                    break;
                case "6":
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }

        System.out.println("Program selesai.");
        scanner.close();
    }

    // =====================
    // DATA DUMMY
    // =====================
    private void isiDataDummy() {
        // Mata Kuliah
        MataKuliah pbo = new MataKuliah("IF101", "Pemrograman Berorientasi Objek", "Aktif");
        MataKuliah algo = new MataKuliah("IF102", "Algoritma", "Aktif");
        MataKuliah sd = new MataKuliah("IF103", "Struktur Data", "Aktif");
        MataKuliah bd = new MataKuliah("IF104", "Basis Data", "Aktif");
        MataKuliah ai = new MataKuliah("IF105", "Kecerdasan Buatan", "Aktif");
        matkulList.add(pbo);
        matkulList.add(algo);
        matkulList.add(sd);
        matkulList.add(bd);
        matkulList.add(ai);

        // Mahasiswa 
        Mahasiswa denji = new Mahasiswa("22001", "Denji", "Aktif");
        Mahasiswa power = new Mahasiswa("22002", "Power", "Aktif");
        Mahasiswa aki = new Mahasiswa("22003", "Aki Hayakawa", "Aktif");
        Mahasiswa beam = new Mahasiswa("23001", "Beam", "Aktif");
        mahasiswas.add(denji);
        mahasiswas.add(power);
        mahasiswas.add(aki);
        mahasiswas.add(beam);

        // Enrollment
        tambahEnrollmentDummy(denji, pbo);
        tambahEnrollmentDummy(denji, algo);
        tambahEnrollmentDummy(denji, sd);
        tambahEnrollmentDummy(power, pbo);
        tambahEnrollmentDummy(power, bd);
        tambahEnrollmentDummy(aki, pbo);
        tambahEnrollmentDummy(aki, algo);
        tambahEnrollmentDummy(aki, ai);
        tambahEnrollmentDummy(beam, sd);
        tambahEnrollmentDummy(beam, bd);
        tambahEnrollmentDummy(beam, ai);
    }

    private void tambahEnrollmentDummy(Mahasiswa mhs, MataKuliah mk) {
        Enrollment e = new Enrollment(mhs, mk);
        enrollments.add(e);
        mhs.addEnrollment(e);
    }

    // =====================
    // MENU MAHASISWA
    // =====================
    private void menuMahasiswa() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU MAHASISWA =====");
            System.out.println("1. Tampilkan Semua");
            System.out.println("2. Tambah");
            System.out.println("3. Edit");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    tampilkanMahasiswa();
                    break;
                case "2":
                    tambahMahasiswa();
                    break;
                case "3":
                    editMahasiswa();
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tampilkanMahasiswa() {
        if (mahasiswas.isEmpty()) {
            System.out.println("Belum ada mahasiswa.");
            return;
        }
        System.out.println("\n--- Daftar Mahasiswa ---");
        for (int i = 0; i < mahasiswas.size(); i++) {
            Mahasiswa m = mahasiswas.get(i);
            System.out.println((i + 1) + ". " + m.getNama() + " | NIM: " + m.getNim() + " | " + m.getStatus());
        }
    }

    private void tambahMahasiswa() {
        System.out.print("NIM: ");
        String nim = scanner.nextLine();

        for (Mahasiswa m : mahasiswas) {
            if (m.getNim().equals(nim)) {
                System.out.println("NIM sudah terdaftar!");
                return;
            }
        }

        System.out.print("Nama: ");
        String nama = scanner.nextLine();

        System.out.print("Status: ");
        String status = scanner.nextLine();

        mahasiswas.add(new Mahasiswa(nim, nama, status));
        System.out.println("Mahasiswa berhasil ditambahkan!");
    }

    private void editMahasiswa() {
        tampilkanMahasiswa();
        if (mahasiswas.isEmpty()) {
            return;
        }

        System.out.print("Pilih nomor mahasiswa: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= mahasiswas.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        Mahasiswa m = mahasiswas.get(index);

        System.out.print("Nama baru (" + m.getNama() + "): ");
        String nama = scanner.nextLine();
        if (!nama.isEmpty()) {
            m.setNama(nama);
        }

        System.out.print("Status baru (" + m.getStatus() + "): ");
        String status = scanner.nextLine();
        if (!status.isEmpty()) {
            m.setStatus(status);
        }

        System.out.println("Mahasiswa berhasil diperbarui!");
    }

    // =====================
    // MENU MATA KULIAH
    // =====================
    private void menuMataKuliah() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU MATA KULIAH =====");
            System.out.println("1. Tampilkan Semua");
            System.out.println("2. Tambah");
            System.out.println("3. Edit");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    tampilkanMataKuliah();
                    break;
                case "2":
                    tambahMataKuliah();
                    break;
                case "3":
                    editMataKuliah();
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tampilkanMataKuliah() {
        if (matkulList.isEmpty()) {
            System.out.println("Belum ada mata kuliah.");
            return;
        }
        System.out.println("\n--- Daftar Mata Kuliah ---");
        for (int i = 0; i < matkulList.size(); i++) {
            MataKuliah mk = matkulList.get(i);
            System.out.println((i + 1) + ". " + mk.getNama() + " | Kode: " + mk.getKode() + " | " + mk.getStatus());
        }
    }

    private void tambahMataKuliah() {
        System.out.print("Kode: ");
        String kode = scanner.nextLine();

        for (MataKuliah mk : matkulList) {
            if (mk.getKode().equals(kode)) {
                System.out.println("Kode sudah digunakan!");
                return;
            }
        }

        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Status: ");
        String status = scanner.nextLine();

        matkulList.add(new MataKuliah(kode, nama, status));
        System.out.println("Mata kuliah berhasil ditambahkan!");
    }

    private void editMataKuliah() {
        tampilkanMataKuliah();
        if (matkulList.isEmpty()) {
            return;
        }

        System.out.print("Pilih nomor mata kuliah: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= matkulList.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        MataKuliah mk = matkulList.get(index);

        System.out.print("Nama baru (" + mk.getNama() + "): ");
        String nama = scanner.nextLine();
        if (!nama.isEmpty()) {
            mk.setNama(nama);
        }

        System.out.print("Status baru (" + mk.getStatus() + "): ");
        String status = scanner.nextLine();
        if (!status.isEmpty()) {
            mk.setStatus(status);
        }

        System.out.println("Mata kuliah berhasil diperbarui!");
    }

    // =====================
    // MENU ENROLLMENT
    // =====================
    private void menuEnrollment() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU ENROLLMENT =====");
            System.out.println("1. Tampilkan Semua");
            System.out.println("2. Tambah");
            System.out.println("3. Kembali");
            System.out.print("Pilih: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1":
                    tampilkanEnrollment();
                    break;
                case "2":
                    tambahEnrollment();
                    break;
                case "3":
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tampilkanEnrollment() {
        if (enrollments.isEmpty()) {
            System.out.println("Belum ada enrollment.");
            return;
        }
        System.out.println("\n--- Daftar Enrollment ---");
        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment e = enrollments.get(i);
            System.out.println((i + 1) + ". " + e.getMahasiswa().getNama() + " - " + e.getMataKuliah().getNama());
        }
    }

    private void tambahEnrollment() {
        tampilkanMahasiswa();
        if (mahasiswas.isEmpty()) {
            return;
        }

        System.out.print("Pilih nomor mahasiswa: ");
        int mhsIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (mhsIndex < 0 || mhsIndex >= mahasiswas.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }
        Mahasiswa mhs = mahasiswas.get(mhsIndex);

        tampilkanMataKuliah();
        if (matkulList.isEmpty()) {
            return;
        }

        System.out.print("Pilih nomor mata kuliah: ");
        int mkIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (mkIndex < 0 || mkIndex >= matkulList.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }
        MataKuliah mk = matkulList.get(mkIndex);

        for (Enrollment e : enrollments) {
            if (e.getMahasiswa().getNim().equals(mhs.getNim()) && e.getMataKuliah().getKode().equals(mk.getKode())) {
                System.out.println("Mahasiswa sudah mengambil mata kuliah ini!");
                return;
            }
        }

        Enrollment e = new Enrollment(mhs, mk);
        enrollments.add(e);
        mhs.addEnrollment(e);
        System.out.println("Enrollment berhasil ditambahkan!");
    }

    // =====================
    // LIST PER MATA KULIAH / MAHASISWA
    // =====================
    private void listPerMataKuliah() {
        tampilkanMataKuliah();
        if (matkulList.isEmpty()) {
            return;
        }

        System.out.print("Pilih nomor mata kuliah: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= matkulList.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        MataKuliah mk = matkulList.get(index);
        System.out.println("\n--- Mahasiswa yang ambil: " + mk.getNama() + " ---");

        boolean ada = false;
        for (Enrollment e : enrollments) {
            if (e.getMataKuliah().getKode().equals(mk.getKode())) {
                System.out.println("  - " + e.getMahasiswa().getNama() + " (" + e.getMahasiswa().getNim() + ")");
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("Belum ada mahasiswa.");
        }
    }

    private void listPerMahasiswa() {
        tampilkanMahasiswa();
        if (mahasiswas.isEmpty()) {
            return;
        }

        System.out.print("Pilih nomor mahasiswa: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= mahasiswas.size()) {
            System.out.println("Nomor tidak valid!");
            return;
        }

        Mahasiswa mhs = mahasiswas.get(index);
        System.out.println("\n--- Mata kuliah " + mhs.getNama() + " ---");

        boolean ada = false;
        for (Enrollment e : enrollments) {
            if (e.getMahasiswa().getNim().equals(mhs.getNim())) {
                System.out.println("  - " + e.getMataKuliah().getNama() + " (" + e.getMataKuliah().getKode() + ")");
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("Belum ada mata kuliah.");
        }
    }
}
