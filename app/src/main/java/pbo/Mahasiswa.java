package pbo;

import java.util.ArrayList;

public class Mahasiswa extends User {

    private String nim;
    private int tahunMasuk;
    private int tahunKelulusan;
    private ArrayList<Enrollment> enrollments;

    public Mahasiswa(String nim, String nama, String status, String email, String password,
            int tahunMasuk, int tahunKelulusan) {
        super(nama, email, password, status, UserType.MAHASISWA);
        this.nim = nim;
        this.tahunMasuk = tahunMasuk;
        this.tahunKelulusan = tahunKelulusan;
        this.enrollments = new ArrayList<>();
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public int getTahunMasuk() {
        return tahunMasuk;
    }

    public void setTahunMasuk(int tahunMasuk) {
        this.tahunMasuk = tahunMasuk;
    }

    public int getTahunKelulusan() {
        return tahunKelulusan;
    }

    public void setTahunKelulusan(int tahunKelulusan) {
        this.tahunKelulusan = tahunKelulusan;
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
