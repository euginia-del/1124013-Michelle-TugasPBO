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
        initializeDummyData();
        boolean running = true;
        while (running) {
            displayMainMenu();
            System.out.print("Pilih menu (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    menuMataKuliah();
                    break;
                case "2":
                    menuMahasiswa();
                    break;
                case "3":
                    menuEnrollment();
                    break;
                case "4":
                    menuListMataKuliah();
                    break;
                case "5":
                    menuListMahasiswa();
                    break;
                case "6":
                    running = false;
                    System.out.println("Terima kasih! Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }

    private void initializeDummyData() {
        // =========================
        // DATA MATA KULIAH
        // =========================
        MataKuliah pbo = new MataKuliah("IF101", "Pemrograman Berorientasi Objek", "Aktif");
        MataKuliah algo = new MataKuliah("IF102", "Algoritma", "Aktif");
        MataKuliah sd = new MataKuliah("IF103", "Struktur Data", "Aktif");
        MataKuliah bd = new MataKuliah("IF104", "Basis Data", "Aktif");
        MataKuliah ai = new MataKuliah("IF105", "Artificial Intelligence", "Aktif");

        matkulList.add(pbo);
        matkulList.add(algo);
        matkulList.add(sd);
        matkulList.add(bd);
        matkulList.add(ai);

        // =========================
        // DATA MAHASISWA
        // =========================
        Mahasiswa denji = new Mahasiswa("123456789", "Denji", "Aktif");
        Mahasiswa reze = new Mahasiswa("987654321", "Reze", "Aktif");
        Mahasiswa power = new Mahasiswa("111222333", "Power", "Aktif");
        Mahasiswa beam = new Mahasiswa("444555666", "Beam", "Aktif");

        mahasiswas.add(denji);
        mahasiswas.add(reze);
        mahasiswas.add(power);
        mahasiswas.add(beam);

        // =========================
        // DATA ENROLLMENT (RELASI)
        // =========================
        // Denji ambil: PBO, Algoritma, Struktur Data
        Enrollment e1 = new Enrollment(denji, pbo, 90);
        Enrollment e2 = new Enrollment(denji, algo, 88);
        Enrollment e3 = new Enrollment(denji, sd, 92);

        // Reze ambil: PBO, Basis Data
        Enrollment e4 = new Enrollment(reze, pbo, 85);
        Enrollment e5 = new Enrollment(reze, bd, 87);

        // Power ambil: PBO, Algoritma
        Enrollment e6 = new Enrollment(power, pbo, 78);
        Enrollment e7 = new Enrollment(power, algo, 80);

        // Beam ambil: Struktur Data, Basis Data, AI
        Enrollment e8 = new Enrollment(beam, sd, 55);
        Enrollment e9 = new Enrollment(beam, bd, 60);
        Enrollment e10 = new Enrollment(beam, ai, 50);

        enrollments.add(e1);
        enrollments.add(e2);
        enrollments.add(e3);
        enrollments.add(e4);
        enrollments.add(e5);
        enrollments.add(e6);
        enrollments.add(e7);
        enrollments.add(e8);
        enrollments.add(e9);
        enrollments.add(e10);
    }

    private void displayMainMenu() {
        System.out.println("\n========== MENU UTAMA ==========");
        System.out.println("1. Mata Kuliah");
        System.out.println("2. Mahasiswa");
        System.out.println("3. Mahasiswa - Mata Kuliah");
        System.out.println("4. List Berdasarkan Mata Kuliah");
        System.out.println("5. List Berdasarkan Mahasiswa");
        System.out.println("6. Keluar");
        System.out.println("================================");
    }

    // ===== MENU MATA KULIAH =====
    private void menuMataKuliah() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU MATA KULIAH =====");
            System.out.println("1. Tambah Mata Kuliah");
            System.out.println("2. Edit Mata Kuliah");
            System.out.println("3. Lihat Semua Mata Kuliah");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    tambahMataKuliah();
                    break;
                case "2":
                    editMataKuliah();
                    break;
                case "3":
                    lihatSemuaMataKuliah();
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tambahMataKuliah() {
        System.out.print("Masukkan Kode Mata Kuliah: ");
        String kode = scanner.nextLine().trim();

        // Validasi apakah mata kuliah sudah ada
        for (MataKuliah mk : matkulList) {
            if (mk.getKode().equalsIgnoreCase(kode)) {
                System.out.println("Mata Kuliah dengan kode " + kode + " sudah ada!");
                return;
            }
        }

        System.out.print("Masukkan Nama Mata Kuliah: ");
        String nama = scanner.nextLine().trim();

        System.out.print("Masukkan Status (Aktif/Tidak Aktif): ");
        String status = scanner.nextLine().trim();

        MataKuliah mk = new MataKuliah(kode, nama, status);
        matkulList.add(mk);
        System.out.println("Mata Kuliah berhasil ditambahkan!");
    }

    private void editMataKuliah() {
        if (matkulList.isEmpty()) {
            System.out.println("Belum ada mata kuliah!");
            return;
        }

        lihatSemuaMataKuliah();
        System.out.print("Masukkan nomor mata kuliah yang ingin diedit: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (index < 0 || index >= matkulList.size()) {
                System.out.println("Nomor tidak valid!");
                return;
            }

            MataKuliah mk = matkulList.get(index);

            System.out.print("Edit Nama [" + mk.getNama() + "]: ");
            String nama = scanner.nextLine().trim();
            if (!nama.isEmpty()) {
                mk.setNama(nama);
            }

            System.out.print("Edit Status [" + mk.getStatus() + "]: ");
            String status = scanner.nextLine().trim();
            if (!status.isEmpty()) {
                mk.setStatus(status);
            }

            System.out.println("Mata Kuliah berhasil diperbarui!");
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
    }

    private void lihatSemuaMataKuliah() {
        if (matkulList.isEmpty()) {
            System.out.println("\nBelum ada mata kuliah!");
            return;
        }

        System.out.println("\n===== DAFTAR MATA KULIAH =====");
        for (int i = 0; i < matkulList.size(); i++) {
            MataKuliah mk = matkulList.get(i);
            System.out.println((i + 1) + ". " + mk.getNama() + " (" + mk.getKode() + ") - " + mk.getStatus());
        }
    }

    // ===== MENU MAHASISWA =====
    private void menuMahasiswa() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU MAHASISWA =====");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Edit Mahasiswa");
            System.out.println("3. Lihat Semua Mahasiswa");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    tambahMahasiswa();
                    break;
                case "2":
                    editMahasiswa();
                    break;
                case "3":
                    lihatSemuaMahasiswa();
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tambahMahasiswa() {
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scanner.nextLine().trim();

        // Validasi apakah mahasiswa sudah ada
        for (Mahasiswa mhs : mahasiswas) {
            if (mhs.getNim().equalsIgnoreCase(nim)) {
                System.out.println("Mahasiswa dengan NIM " + nim + " sudah ada!");
                return;
            }
        }

        System.out.print("Masukkan Nama Mahasiswa: ");
        String nama = scanner.nextLine().trim();

        System.out.print("Masukkan Status (Aktif/Cuti/Lulus/Dropout): ");
        String status = scanner.nextLine().trim();

        Mahasiswa mhs = new Mahasiswa(nim, nama, status);
        mahasiswas.add(mhs);
        System.out.println("Mahasiswa berhasil ditambahkan!");
    }

    private void editMahasiswa() {
        if (mahasiswas.isEmpty()) {
            System.out.println("Belum ada mahasiswa!");
            return;
        }

        lihatSemuaMahasiswa();
        System.out.print("Masukkan nomor mahasiswa yang ingin diedit: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (index < 0 || index >= mahasiswas.size()) {
                System.out.println("Nomor tidak valid!");
                return;
            }

            Mahasiswa mhs = mahasiswas.get(index);

            System.out.print("Edit Nama [" + mhs.getNama() + "]: ");
            String nama = scanner.nextLine().trim();
            if (!nama.isEmpty()) {
                mhs.setNama(nama);
            }

            System.out.print("Edit Status [" + mhs.getStatus() + "]: ");
            String status = scanner.nextLine().trim();
            if (!status.isEmpty()) {
                mhs.setStatus(status);
            }

            System.out.println("Mahasiswa berhasil diperbarui!");
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
    }

    private void lihatSemuaMahasiswa() {
        if (mahasiswas.isEmpty()) {
            System.out.println("\nBelum ada mahasiswa!");
            return;
        }

        System.out.println("\n===== DAFTAR MAHASISWA =====");
        for (int i = 0; i < mahasiswas.size(); i++) {
            Mahasiswa mhs = mahasiswas.get(i);
            System.out.println((i + 1) + ". " + mhs.getNama() + " (" + mhs.getNim() + ") - " + mhs.getStatus());
        }
    }

    // ===== MENU ENROLLMENT (MAHASISWA - MATA KULIAH) =====
    private void menuEnrollment() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU MAHASISWA - MATA KULIAH =====");
            System.out.println("1. Tambah Enrollment");
            System.out.println("2. Edit Nilai Enrollment");
            System.out.println("3. Lihat Semua Enrollment");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    tambahEnrollment();
                    break;
                case "2":
                    editNilaiEnrollment();
                    break;
                case "3":
                    lihatSemuaEnrollment();
                    break;
                case "4":
                    back = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tambahEnrollment() {
        if (mahasiswas.isEmpty()) {
            System.out.println("Belum ada mahasiswa!");
            return;
        }
        if (matkulList.isEmpty()) {
            System.out.println("Belum ada mata kuliah!");
            return;
        }

        lihatSemuaMahasiswa();
        System.out.print("Pilih nomor mahasiswa: ");
        try {
            int mhsIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (mhsIndex < 0 || mhsIndex >= mahasiswas.size()) {
                System.out.println("Nomor mahasiswa tidak valid!");
                return;
            }

            Mahasiswa mhs = mahasiswas.get(mhsIndex);

            // Validasi status mahasiswa
            if (!mhs.getStatus().equalsIgnoreCase("Aktif")) {
                System.out.println("Mahasiswa dengan status '" + mhs.getStatus() + "' tidak bisa mengambil mata kuliah!");
                return;
            }

            lihatSemuaMataKuliah();
            System.out.print("Pilih nomor mata kuliah: ");
            int mkIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (mkIndex < 0 || mkIndex >= matkulList.size()) {
                System.out.println("Nomor mata kuliah tidak valid!");
                return;
            }

            MataKuliah mk = matkulList.get(mkIndex);

            // Validasi status mata kuliah
            if (!mk.getStatus().equalsIgnoreCase("Aktif")) {
                System.out.println("Mata kuliah dengan status '" + mk.getStatus() + "' tidak bisa diambil!");
                return;
            }

            // Validasi apakah sudah ada enrollment yang sama
            for (Enrollment e : enrollments) {
                if (e.getMahasiswa().getNim().equals(mhs.getNim())
                        && e.getMataKuliah().getKode().equals(mk.getKode())) {
                    System.out.println("Mahasiswa ini sudah mengambil mata kuliah ini!");
                    return;
                }
            }

            System.out.print("Masukkan Nilai (0-100): ");
            double nilai = Double.parseDouble(scanner.nextLine().trim());

            if (nilai < 0 || nilai > 100) {
                System.out.println("Nilai harus antara 0-100!");
                return;
            }

            Enrollment enrollment = new Enrollment(mhs, mk, nilai);
            enrollments.add(enrollment);
            System.out.println("Enrollment berhasil ditambahkan!");

        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
    }

    private void editNilaiEnrollment() {
        if (enrollments.isEmpty()) {
            System.out.println("Belum ada enrollment!");
            return;
        }

        lihatSemuaEnrollment();
        System.out.print("Masukkan nomor enrollment yang ingin diedit: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (index < 0 || index >= enrollments.size()) {
                System.out.println("Nomor tidak valid!");
                return;
            }

            Enrollment enrollment = enrollments.get(index);

            System.out.print("Masukkan Nilai Baru (0-100) [" + enrollment.getNilai() + "]: ");
            String nilaiStr = scanner.nextLine().trim();

            if (!nilaiStr.isEmpty()) {
                double nilai = Double.parseDouble(nilaiStr);

                if (nilai < 0 || nilai > 100) {
                    System.out.println("Nilai harus antara 0-100!");
                    return;
                }

                enrollment.setNilai(nilai);
                System.out.println("Nilai berhasil diperbarui!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
    }

    private void lihatSemuaEnrollment() {
        if (enrollments.isEmpty()) {
            System.out.println("\nBelum ada enrollment!");
            return;
        }

        System.out.println("\n===== DAFTAR ENROLLMENT =====");
        for (int i = 0; i < enrollments.size(); i++) {
            Enrollment e = enrollments.get(i);
            System.out.println((i + 1) + ". " + e.getMahasiswa().getNama() + " - "
                    + e.getMataKuliah().getNama() + " (Nilai: " + e.getNilai() + ")");
        }
    }

    // ===== MENU LIST BERDASARKAN MATA KULIAH =====
    private void menuListMataKuliah() {
        if (matkulList.isEmpty()) {
            System.out.println("\nBelum ada mata kuliah!");
            return;
        }

        lihatSemuaMataKuliah();
        System.out.print("\nPilih nomor mata kuliah untuk melihat mahasiswa yang mengambilnya: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (index < 0 || index >= matkulList.size()) {
                System.out.println("Nomor tidak valid!");
                return;
            }

            MataKuliah mk = matkulList.get(index);

            System.out.println("\n===== MAHASISWA YANG MENGAMBIL: " + mk.getNama() + " =====");
            boolean found = false;
            for (Enrollment e : enrollments) {
                if (e.getMataKuliah().getKode().equals(mk.getKode())) {
                    System.out.println("- " + e.getMahasiswa().getNama() + " (" + e.getMahasiswa().getNim()
                            + ") - Nilai: " + e.getNilai());
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Belum ada mahasiswa yang mengambil mata kuliah ini.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
    }

    // ===== MENU LIST BERDASARKAN MAHASISWA =====
    private void menuListMahasiswa() {
        if (mahasiswas.isEmpty()) {
            System.out.println("\nBelum ada mahasiswa!");
            return;
        }

        lihatSemuaMahasiswa();
        System.out.print("\nPilih nomor mahasiswa untuk melihat mata kuliah yang diambilnya: ");
        try {
            int index = Integer.parseInt(scanner.nextLine().trim()) - 1;

            if (index < 0 || index >= mahasiswas.size()) {
                System.out.println("Nomor tidak valid!");
                return;
            }

            Mahasiswa mhs = mahasiswas.get(index);

            System.out.println("\n===== MATA KULIAH YANG DIAMBIL: " + mhs.getNama() + " =====");
            System.out.println("NIM: " + mhs.getNim() + " | Status: " + mhs.getStatus());

            boolean found = false;
            double totalNilai = 0;
            int jumlahMatkul = 0;

            System.out.println("\nDaftar Mata Kuliah:");
            for (Enrollment e : enrollments) {
                if (e.getMahasiswa().getNim().equals(mhs.getNim())) {
                    System.out.println("- " + e.getMataKuliah().getNama() + " (" + e.getMataKuliah().getKode()
                            + ") - Nilai: " + e.getNilai());
                    totalNilai += e.getNilai();
                    jumlahMatkul++;
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Belum ada mata kuliah yang diambil oleh mahasiswa ini.");
            } else {
                double rataRata = totalNilai / jumlahMatkul;
                System.out.println("\nRata-rata Nilai: " + String.format("%.2f", rataRata));
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!");
        }
    }
}
