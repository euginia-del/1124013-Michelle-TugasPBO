package pbodatabase;

import java.util.ArrayList;
import java.util.Scanner;

import pbodatabase.controller.EnrollmentController;
import pbodatabase.controller.KaryawanController;
import pbodatabase.controller.MahasiswaController;
import pbodatabase.controller.MataKuliahController;
import pbodatabase.models.DosenHonorer;
import pbodatabase.models.DosenTetap;
import pbodatabase.models.Employee;
import pbodatabase.models.Enrollment;
import pbodatabase.models.Mahasiswa;
import pbodatabase.models.MataKuliah;
import pbodatabase.models.Staff;

public class MhsApp {

    private ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();
    private ArrayList<MataKuliah> matkulList = new ArrayList<>();
    private ArrayList<Enrollment> enrollments = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();

    private MahasiswaController mahasiswaCtrl;
    private KaryawanController karyawanCtrl;
    private MataKuliahController matkulCtrl;
    private EnrollmentController enrollmentCtrl;

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new MhsApp().run();
    }

    private void run() {
        mahasiswaCtrl = new MahasiswaController(mahasiswas);
        karyawanCtrl = new KaryawanController(employees);
        matkulCtrl = new MataKuliahController(matkulList);
        enrollmentCtrl = new EnrollmentController(enrollments);

        isiDataDummy();

        boolean running = true;
        while (running) {
            System.out.println("\n========== MENU UTAMA ==========");
            System.out.println("1. Karyawan");
            System.out.println("2. Mahasiswa");
            System.out.println("3. Mata Kuliah");
            System.out.println("4. Enrollment");
            System.out.println("5. List per Mata Kuliah");
            System.out.println("6. List per Mahasiswa");
            System.out.println("7. Keluar");
            System.out.println("================================");
            System.out.print("Pilih: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1": menuKaryawan(); break;
                case "2": menuMahasiswa(); break;
                case "3": menuMataKuliah(); break;
                case "4": menuEnrollment(); break;
                case "5": listPerMataKuliah(); break;
                case "6": listPerMahasiswa(); break;
                case "7": running = false; break;
                default: System.out.println("Pilihan tidak valid!");
            }
        }

        System.out.println("Program selesai.");
        scanner.close();
    }

    // =====================
    // DATA DUMMY
    // =====================
    private void isiDataDummy() {
        MataKuliah pbo  = new MataKuliah("IF101", "Pemrograman Berorientasi Objek", "Aktif", 3);
        MataKuliah algo = new MataKuliah("IF102", "Algoritma", "Aktif", 3);
        MataKuliah sd   = new MataKuliah("IF103", "Struktur Data", "Aktif", 4);
        MataKuliah bd   = new MataKuliah("IF104", "Basis Data", "Aktif", 3);
        MataKuliah ai   = new MataKuliah("IF105", "Kecerdasan Buatan", "Aktif", 4);
        matkulList.add(pbo); matkulList.add(algo); matkulList.add(sd);
        matkulList.add(bd);  matkulList.add(ai);

        employees.add(new DosenTetap("Kishibe", "kishibe@univ.ac.id", "pass123", "Aktif", "DT001", 5000000, 50000, 12));
        employees.add(new DosenTetap("Quanxi",  "quanxi@univ.ac.id",  "pass456", "Aktif", "DT002", 6000000, 60000, 15));
        employees.add(new DosenHonorer("Himeno", "himeno@univ.ac.id", "pass789", "Aktif", "DH001", 40000, 6));
        employees.add(new Staff("Makima", "makima@univ.ac.id", "staff123", "Aktif", "STF001", 3000000));
        employees.add(new Staff("Arai",   "arai@univ.ac.id",   "staff456", "Aktif", "STF002", 2500000));

        Mahasiswa denji = new Mahasiswa("22001", "Denji", "Aktif", "denji@student.ac.id", "pass1", 2022, 2026);
        Mahasiswa power = new Mahasiswa("22002", "Power", "Aktif", "power@student.ac.id", "pass2", 2022, 2026);
        Mahasiswa aki   = new Mahasiswa("22003", "Aki Hayakawa", "Aktif", "aki@student.ac.id", "pass3", 2022, 2026);
        Mahasiswa beam  = new Mahasiswa("23001", "Beam", "Aktif", "beam@student.ac.id", "pass4", 2023, 2027);
        mahasiswas.add(denji); mahasiswas.add(power);
        mahasiswas.add(aki);   mahasiswas.add(beam);

        enrollmentCtrl.tambah(denji, pbo,  90);
        enrollmentCtrl.tambah(denji, algo, 88);
        enrollmentCtrl.tambah(denji, sd,   92);
        enrollmentCtrl.tambah(power, pbo,  78);
        enrollmentCtrl.tambah(power, bd,   75);
        enrollmentCtrl.tambah(aki,   pbo,  85);
        enrollmentCtrl.tambah(aki,   algo, 80);
        enrollmentCtrl.tambah(aki,   ai,   88);
        enrollmentCtrl.tambah(beam,  sd,   55);
        enrollmentCtrl.tambah(beam,  bd,   60);
        enrollmentCtrl.tambah(beam,  ai,   50);
    }

    // =====================
    // MENU KARYAWAN
    // =====================
    private void menuKaryawan() {
        boolean back = false;
        while (!back) {
            System.out.println("\n===== MENU KARYAWAN =====");
            System.out.println("1. Tampilkan Semua");
            System.out.println("2. Tambah");
            System.out.println("3. Edit");
            System.out.println("4. Hapus");
            System.out.println("5. Hitung Gaji");
            System.out.println("6. Kembali");
            System.out.print("Pilih: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1": tampilkanKaryawan(); break;
                case "2": tambahKaryawan(); break;
                case "3": editKaryawan(); break;
                case "4": hapusKaryawan(); break;
                case "5": hitungGajiKaryawan(); break;
                case "6": back = true; break;
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tampilkanKaryawan() {
        ArrayList<Employee> list = karyawanCtrl.getAll();
        if (list.isEmpty()) { System.out.println("Belum ada karyawan."); return; }
        System.out.println("\n--- Daftar Karyawan ---");
        for (int i = 0; i < list.size(); i++) {
            Employee e = list.get(i);
            System.out.println((i + 1) + ". [" + e.getType() + "] " + e.getNama()
                    + " | NIK: " + e.getNik() + " | " + e.getStatus());
        }
    }

    private void tambahKaryawan() {
        System.out.println("\nTipe Karyawan:");
        System.out.println("1. Dosen Tetap");
        System.out.println("2. Dosen Honorer");
        System.out.println("3. Staff");
        System.out.print("Pilih tipe: ");
        String tipe = scanner.nextLine();

        System.out.print("Nama: ");    String nama     = scanner.nextLine();
        System.out.print("Email: ");   String email    = scanner.nextLine();
        System.out.print("Password: ");String password = scanner.nextLine();
        System.out.print("Status: "); String status   = scanner.nextLine();
        System.out.print("NIK: ");     String nik      = scanner.nextLine();

        double gajiPokok = 0, honorPerSKS = 0;
        int totalSKS = 0;

        if (tipe.equals("1")) {
            System.out.print("Gaji Pokok: ");   gajiPokok   = Double.parseDouble(scanner.nextLine());
            System.out.print("Honor per SKS: "); honorPerSKS = Double.parseDouble(scanner.nextLine());
            System.out.print("Total SKS: ");     totalSKS    = Integer.parseInt(scanner.nextLine());
        } else if (tipe.equals("2")) {
            System.out.print("Honor per SKS: "); honorPerSKS = Double.parseDouble(scanner.nextLine());
            System.out.print("Total SKS: ");     totalSKS    = Integer.parseInt(scanner.nextLine());
        } else if (tipe.equals("3")) {
            System.out.print("Gaji Pokok: ");    gajiPokok   = Double.parseDouble(scanner.nextLine());
        } else {
            System.out.println("Tipe tidak valid!"); return;
        }

        karyawanCtrl.tambah(tipe, nama, email, password, status, nik, gajiPokok, honorPerSKS, totalSKS);
        System.out.println("Karyawan berhasil ditambahkan!");
    }

    private void editKaryawan() {
        tampilkanKaryawan();
        ArrayList<Employee> list = karyawanCtrl.getAll();
        if (list.isEmpty()) return;

        System.out.print("Pilih nomor karyawan: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Employee e = karyawanCtrl.get(index);
        if (e == null) { System.out.println("Nomor tidak valid!"); return; }

        System.out.print("Nama baru (" + e.getNama() + "): ");
        String nama = scanner.nextLine();
        if (!nama.isEmpty()) e.setNama(nama);

        System.out.print("Email baru (" + e.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) e.setEmail(email);

        System.out.print("Status baru (" + e.getStatus() + "): ");
        String status = scanner.nextLine();
        if (!status.isEmpty()) e.setStatus(status);

        System.out.println("Karyawan berhasil diperbarui!");
    }

    private void hapusKaryawan() {
        tampilkanKaryawan();
        if (karyawanCtrl.getAll().isEmpty()) return;

        System.out.print("Pilih nomor karyawan yang ingin dihapus: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Employee e = karyawanCtrl.get(index);
        if (e == null) { System.out.println("Nomor tidak valid!"); return; }

        karyawanCtrl.hapus(index);
        System.out.println("Karyawan " + e.getNama() + " berhasil dihapus!");
    }

    private void hitungGajiKaryawan() {
        tampilkanKaryawan();
        if (karyawanCtrl.getAll().isEmpty()) return;

        System.out.print("Pilih nomor karyawan: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Employee e = karyawanCtrl.get(index);
        if (e == null) { System.out.println("Nomor tidak valid!"); return; }

        System.out.println("\n--- Hitung Gaji ---");
        System.out.println("Nama  : " + e.getNama());
        System.out.println("NIK   : " + e.getNik());
        System.out.println("Tipe  : " + e.getType());
        System.out.println("Gaji  : Rp " + String.format("%.0f", e.hitungGaji()));
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
            System.out.println("4. Hitung IP");
            System.out.println("5. Kembali");
            System.out.print("Pilih: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1": tampilkanMahasiswa(); break;
                case "2": tambahMahasiswa(); break;
                case "3": editMahasiswa(); break;
                case "4": hitungIPMahasiswa(); break;
                case "5": back = true; break;
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tampilkanMahasiswa() {
        ArrayList<Mahasiswa> list = mahasiswaCtrl.getAll();
        if (list.isEmpty()) { System.out.println("Belum ada mahasiswa."); return; }
        System.out.println("\n--- Daftar Mahasiswa ---");
        for (int i = 0; i < list.size(); i++) {
            Mahasiswa m = list.get(i);
            System.out.println((i + 1) + ". " + m.getNama()
                    + " | NIM: " + m.getNim()
                    + " | " + m.getStatus()
                    + " | Masuk: " + m.getTahunMasuk());
        }
    }

    private void tambahMahasiswa() {
        System.out.print("NIM: "); String nim = scanner.nextLine();
        System.out.print("Nama: "); String nama = scanner.nextLine();
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Password: "); String password = scanner.nextLine();
        System.out.print("Status: "); String status = scanner.nextLine();
        System.out.print("Tahun Masuk: "); int tahunMasuk = Integer.parseInt(scanner.nextLine());
        System.out.print("Tahun Kelulusan: "); int tahunKelulusan = Integer.parseInt(scanner.nextLine());

        boolean berhasil = mahasiswaCtrl.tambah(nim, nama, status, email, password, tahunMasuk, tahunKelulusan);
        if (berhasil) System.out.println("Mahasiswa berhasil ditambahkan!");
        else System.out.println("NIM sudah terdaftar!");
    }

    private void editMahasiswa() {
        tampilkanMahasiswa();
        if (mahasiswaCtrl.getAll().isEmpty()) return;

        System.out.print("Pilih nomor mahasiswa: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Mahasiswa m = mahasiswaCtrl.get(index);
        if (m == null) { System.out.println("Nomor tidak valid!"); return; }

        System.out.print("Nama baru (" + m.getNama() + "): ");
        String nama = scanner.nextLine();
        if (!nama.isEmpty()) m.setNama(nama);

        System.out.print("Email baru (" + m.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) m.setEmail(email);

        System.out.print("Status baru (" + m.getStatus() + "): ");
        String status = scanner.nextLine();
        if (!status.isEmpty()) m.setStatus(status);

        System.out.println("Mahasiswa berhasil diperbarui!");
    }

    private void hitungIPMahasiswa() {
        tampilkanMahasiswa();
        if (mahasiswaCtrl.getAll().isEmpty()) return;

        System.out.print("Pilih nomor mahasiswa: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Mahasiswa m = mahasiswaCtrl.get(index);
        if (m == null) { System.out.println("Nomor tidak valid!"); return; }

        System.out.println("\n--- Hitung IP ---");
        System.out.println("Nama  : " + m.getNama());
        System.out.println("NIM   : " + m.getNim());
        System.out.println("\nMata Kuliah:");
        for (Enrollment e : m.getEnrollments()) {
            System.out.println("  - " + e.getMataKuliah().getNama()
                    + " | SKS: " + e.getMataKuliah().getSks()
                    + " | Nilai: " + e.getNilai());
        }
        System.out.println("\nIP : " + String.format("%.2f", m.hitungIP()));
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
                case "1": tampilkanMataKuliah(); break;
                case "2": tambahMataKuliah(); break;
                case "3": editMataKuliah(); break;
                case "4": back = true; break;
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tampilkanMataKuliah() {
        ArrayList<MataKuliah> list = matkulCtrl.getAll();
        if (list.isEmpty()) { System.out.println("Belum ada mata kuliah."); return; }
        System.out.println("\n--- Daftar Mata Kuliah ---");
        for (int i = 0; i < list.size(); i++) {
            MataKuliah mk = list.get(i);
            System.out.println((i + 1) + ". " + mk.getNama()
                    + " | Kode: " + mk.getKode()
                    + " | SKS: " + mk.getSks()
                    + " | " + mk.getStatus());
        }
    }

    private void tambahMataKuliah() {
        System.out.print("Kode: "); String kode = scanner.nextLine();
        System.out.print("Nama: "); String nama = scanner.nextLine();
        System.out.print("Status: "); String status = scanner.nextLine();
        System.out.print("SKS: "); int sks = Integer.parseInt(scanner.nextLine());

        boolean berhasil = matkulCtrl.tambah(kode, nama, status, sks);
        if (berhasil) System.out.println("Mata kuliah berhasil ditambahkan!");
        else System.out.println("Kode sudah digunakan!");
    }

    private void editMataKuliah() {
        tampilkanMataKuliah();
        if (matkulCtrl.getAll().isEmpty()) return;

        System.out.print("Pilih nomor mata kuliah: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        MataKuliah mk = matkulCtrl.get(index);
        if (mk == null) { System.out.println("Nomor tidak valid!"); return; }

        System.out.print("Nama baru (" + mk.getNama() + "): ");
        String nama = scanner.nextLine();
        if (!nama.isEmpty()) mk.setNama(nama);

        System.out.print("Status baru (" + mk.getStatus() + "): ");
        String status = scanner.nextLine();
        if (!status.isEmpty()) mk.setStatus(status);

        System.out.print("SKS baru (" + mk.getSks() + "): ");
        String sksStr = scanner.nextLine();
        if (!sksStr.isEmpty()) mk.setSks(Integer.parseInt(sksStr));

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
            System.out.println("3. Edit Nilai");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");
            String pilihan = scanner.nextLine();

            switch (pilihan) {
                case "1": tampilkanEnrollment(); break;
                case "2": tambahEnrollment(); break;
                case "3": editNilaiEnrollment(); break;
                case "4": back = true; break;
                default: System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tampilkanEnrollment() {
        ArrayList<Enrollment> list = enrollmentCtrl.getAll();
        if (list.isEmpty()) { System.out.println("Belum ada enrollment."); return; }
        System.out.println("\n--- Daftar Enrollment ---");
        for (int i = 0; i < list.size(); i++) {
            Enrollment e = list.get(i);
            System.out.println((i + 1) + ". " + e.getMahasiswa().getNama()
                    + " - " + e.getMataKuliah().getNama()
                    + " | Nilai: " + e.getNilai());
        }
    }

    private void tambahEnrollment() {
        tampilkanMahasiswa();
        if (mahasiswaCtrl.getAll().isEmpty()) return;

        System.out.print("Pilih nomor mahasiswa: ");
        int mhsIndex = Integer.parseInt(scanner.nextLine()) - 1;
        Mahasiswa mhs = mahasiswaCtrl.get(mhsIndex);
        if (mhs == null) { System.out.println("Nomor tidak valid!"); return; }

        tampilkanMataKuliah();
        if (matkulCtrl.getAll().isEmpty()) return;

        System.out.print("Pilih nomor mata kuliah: ");
        int mkIndex = Integer.parseInt(scanner.nextLine()) - 1;
        MataKuliah mk = matkulCtrl.get(mkIndex);
        if (mk == null) { System.out.println("Nomor tidak valid!"); return; }

        System.out.print("Nilai (0-100): ");
        double nilai = Double.parseDouble(scanner.nextLine());

        boolean berhasil = enrollmentCtrl.tambah(mhs, mk, nilai);
        if (berhasil) System.out.println("Enrollment berhasil ditambahkan!");
        else System.out.println("Mahasiswa sudah mengambil mata kuliah ini!");
    }

    private void editNilaiEnrollment() {
        tampilkanEnrollment();
        if (enrollmentCtrl.getAll().isEmpty()) return;

        System.out.print("Pilih nomor enrollment: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Enrollment e = enrollmentCtrl.get(index);
        if (e == null) { System.out.println("Nomor tidak valid!"); return; }

        System.out.print("Nilai baru (" + e.getNilai() + "): ");
        String nilaiStr = scanner.nextLine();
        if (!nilaiStr.isEmpty()) {
            e.setNilai(Double.parseDouble(nilaiStr));
            System.out.println("Nilai berhasil diperbarui!");
        }
    }

    // =====================
    // LIST PER MATA KULIAH / MAHASISWA
    // =====================
    private void listPerMataKuliah() {
        tampilkanMataKuliah();
        if (matkulCtrl.getAll().isEmpty()) return;

        System.out.print("Pilih nomor mata kuliah: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        MataKuliah mk = matkulCtrl.get(index);
        if (mk == null) { System.out.println("Nomor tidak valid!"); return; }

        System.out.println("\n--- Mahasiswa yang ambil: " + mk.getNama() + " ---");
        ArrayList<Enrollment> hasil = enrollmentCtrl.getByMataKuliah(mk.getKode());
        if (hasil.isEmpty()) { System.out.println("Belum ada mahasiswa."); return; }
        for (Enrollment e : hasil) {
            System.out.println("  - " + e.getMahasiswa().getNama()
                    + " (" + e.getMahasiswa().getNim() + ") | Nilai: " + e.getNilai());
        }
    }

    private void listPerMahasiswa() {
        tampilkanMahasiswa();
        if (mahasiswaCtrl.getAll().isEmpty()) return;

        System.out.print("Pilih nomor mahasiswa: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        Mahasiswa mhs = mahasiswaCtrl.get(index);
        if (mhs == null) { System.out.println("Nomor tidak valid!"); return; }

        System.out.println("\n--- Mata kuliah " + mhs.getNama() + " ---");
        ArrayList<Enrollment> hasil = enrollmentCtrl.getByMahasiswa(mhs.getNim());
        if (hasil.isEmpty()) { System.out.println("Belum ada mata kuliah."); return; }
        for (Enrollment e : hasil) {
            System.out.println("  - " + e.getMataKuliah().getNama()
                    + " (" + e.getMataKuliah().getKode() + ") | Nilai: " + e.getNilai());
        }
    }
}
