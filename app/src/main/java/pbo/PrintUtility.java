package pbo;

import java.util.ArrayList;

public class PrintUtility {

    // Print dengan format: Nama - Nama Matkul
    public static void printNamaMatkul(Mahasiswa mhs, ArrayList<Enrollment> enrollments) {
        System.out.println("\n=== Format: Nama - Nama Matkul ===");
        System.out.println("Mahasiswa: " + mhs.getNama());
        System.out.println("Mata Kuliah yang Diambil:");

        boolean hasMatkul = false;
        for (Enrollment e : enrollments) {
            if (e.getMahasiswa().getNim().equals(mhs.getNim())) {
                System.out.println("  - " + e.getMataKuliah().getNama());
                hasMatkul = true;
            }
        }

        if (!hasMatkul) {
            System.out.println("  (Belum ada mata kuliah)");
        }
    }

    // Print dengan format: NIM - Kode Matkul
    public static void printNimKodeMatkul(Mahasiswa mhs, ArrayList<Enrollment> enrollments) {
        System.out.println("\n=== Format: NIM - Kode Matkul ===");
        System.out.println("NIM: " + mhs.getNim());
        System.out.println("Kode Mata Kuliah:");

        boolean hasMatkul = false;
        for (Enrollment e : enrollments) {
            if (e.getMahasiswa().getNim().equals(mhs.getNim())) {
                System.out.println("  - " + e.getMataKuliah().getKode());
                hasMatkul = true;
            }
        }

        if (!hasMatkul) {
            System.out.println("  (Belum ada mata kuliah)");
        }
    }

    // Print semua mahasiswa dengan matkul
    public static void printSemuaMahasiswaDanMatkul(ArrayList<Mahasiswa> mahasiswas, ArrayList<Enrollment> enrollments) {
        System.out.println("\n===== DAFTAR MAHASISWA DAN MATA KULIAH =====");
        for (Mahasiswa mhs : mahasiswas) {
            System.out.println("\n" + mhs.getNama() + " (" + mhs.getNim() + ") - Status: " + mhs.getStatus());
            boolean hasMatkul = false;
            for (Enrollment e : enrollments) {
                if (e.getMahasiswa().getNim().equals(mhs.getNim())) {
                    System.out.println("  • " + e.getMataKuliah().getNama() + " [" + e.getMataKuliah().getKode() + "] - Nilai: " + e.getNilai());
                    hasMatkul = true;
                }
            }
            if (!hasMatkul) {
                System.out.println("  (Belum ada mata kuliah)");
            }
        }
    }

    // Print mata kuliah dengan mahasiswa yang mengambilnya
    public static void printMatkulDanMahasiswa(ArrayList<MataKuliah> matkulList, ArrayList<Enrollment> enrollments) {
        System.out.println("\n===== DAFTAR MATA KULIAH DAN MAHASISWA =====");
        for (MataKuliah mk : matkulList) {
            System.out.println("\n" + mk.getNama() + " (" + mk.getKode() + ") - Status: " + mk.getStatus());
            boolean hasMahasiswa = false;
            for (Enrollment e : enrollments) {
                if (e.getMataKuliah().getKode().equals(mk.getKode())) {
                    System.out.println("  • " + e.getMahasiswa().getNama() + " [" + e.getMahasiswa().getNim() + "] - Nilai: " + e.getNilai());
                    hasMahasiswa = true;
                }
            }
            if (!hasMahasiswa) {
                System.out.println("  (Belum ada mahasiswa)");
            }
        }
    }
}
