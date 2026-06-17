package pbodatabase.controller;

import java.util.ArrayList;

import pbodatabase.models.Enrollment;
import pbodatabase.models.Mahasiswa;
import pbodatabase.models.MataKuliah;

public class MahasiswaController {

    private ArrayList<Mahasiswa> mahasiswas;

    public MahasiswaController(ArrayList<Mahasiswa> mahasiswas) {
        this.mahasiswas = mahasiswas;
    }

    public ArrayList<Mahasiswa> getAll() {
        return mahasiswas;
    }

    public Mahasiswa get(int index) {
        if (index < 0 || index >= mahasiswas.size()) return null;
        return mahasiswas.get(index);
    }

    public boolean tambah(String nim, String nama, String statusStr, String email, String password,
            int tahunMasuk, int tahunKelulusan) {
        for (Mahasiswa m : mahasiswas) {
            if (m.getNim().equals(nim)) return false;
        }
        mahasiswas.add(new Mahasiswa(nim, nama, statusStr, email, password, tahunMasuk, tahunKelulusan));
        return true;
    }

    public void tambahEnrollment(Mahasiswa mhs, MataKuliah mk, double nilai, ArrayList<Enrollment> enrollments) {
        Enrollment e = new Enrollment(mhs, mk, nilai);
        enrollments.add(e);
        mhs.addEnrollment(e);
    }
}
