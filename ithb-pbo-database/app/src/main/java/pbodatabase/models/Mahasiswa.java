package pbodatabase.models;

import java.util.ArrayList;

import pbodatabase.enums.UserStatus;
import pbodatabase.enums.UserType;

public class Mahasiswa extends User {

    public String nim;
    public String kode_jurusan;
    private ArrayList<Enrollment> enrollments;

    public Mahasiswa(String nim, String nama, String email, String password,
            UserStatus status, UserType type,
            String tanggal_masuk, String tanggal_keluar, String kode_jurusan) {
        super(nim, nama, email, password, status, type, tanggal_masuk, tanggal_keluar);
        this.nim = nim;
        this.kode_jurusan = kode_jurusan;
        this.enrollments = new ArrayList<>();
    }

    public Mahasiswa(String nim, String nama, String statusStr, String email, String password,
            int tahunMasuk, int tahunKelulusan) {
        super(nim, nama, email, password,
                UserStatus.valueOf(statusStr.toUpperCase()),
                UserType.MAHASISWA,
                tahunMasuk + "-01-01",
                tahunKelulusan + "-01-01");
        this.nim = nim;
        this.kode_jurusan = "";
        this.enrollments = new ArrayList<>();
    }

    public String getNim() {
        return nim;
    }

    public int getTahunMasuk() {
        if (tanggal_masuk != null && tanggal_masuk.length() >= 4) {
            return Integer.parseInt(tanggal_masuk.substring(0, 4));
        }
        return 0;
    }

    public String getKodeJurusan() {
        return kode_jurusan;
    }

    public void setKodeJurusan(String kodeJurusan) {
        this.kode_jurusan = kodeJurusan;
    }

    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment e) {
        enrollments.add(e);
    }

    public double hitungIP() {
        if (enrollments.isEmpty()) {
            return 0;
        }

        double totalBobot = 0;
        int totalSKS = 0;

        for (Enrollment e : enrollments) {
            double bobot = nilaiKeBobot(e.getNilai());
            int sks = e.getMataKuliah().getSks();
            totalBobot += bobot * sks;
            totalSKS += sks;
        }

        return totalBobot / totalSKS;
    }

    private double nilaiKeBobot(double nilai) {
        if (nilai >= 85) {
            return 4.0; 
        }else if (nilai >= 80) {
            return 3.5; 
        }else if (nilai >= 75) {
            return 3.0; 
        }else if (nilai >= 70) {
            return 2.5; 
        }else if (nilai >= 65) {
            return 2.0; 
        }else if (nilai >= 60) {
            return 1.5; 
        }else if (nilai >= 55) {
            return 1.0; 
        }else {
            return 0.0;
        }
    }
}
