package pbo;

import java.util.ArrayList;

public class Mahasiswa {

    private String nim;
    private String nama;
    private String status;
    private ArrayList<Enrollment> enrollments;

    public Mahasiswa(String nim, String nama, String status) {
        this.nim = nim;
        this.nama = nama;
        this.status = status;
        this.enrollments = new ArrayList<>();
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment e) {
        enrollments.add(e);
    }
}
