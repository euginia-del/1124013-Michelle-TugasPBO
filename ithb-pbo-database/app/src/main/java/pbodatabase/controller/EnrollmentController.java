package pbodatabase.controller;

import java.util.ArrayList;

import pbodatabase.models.Enrollment;
import pbodatabase.models.Mahasiswa;
import pbodatabase.models.MataKuliah;

public class EnrollmentController {

    private ArrayList<Enrollment> enrollments;

    public EnrollmentController(ArrayList<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public ArrayList<Enrollment> getAll() {
        return enrollments;
    }

    public Enrollment get(int index) {
        if (index < 0 || index >= enrollments.size()) return null;
        return enrollments.get(index);
    }

    public boolean tambah(Mahasiswa mhs, MataKuliah mk, double nilai) {
        for (Enrollment e : enrollments) {
            if (e.getMahasiswa().getNim().equals(mhs.getNim())
                    && e.getMataKuliah().getKode().equals(mk.getKode())) {
                return false; // sudah terdaftar
            }
        }
        Enrollment e = new Enrollment(mhs, mk, nilai);
        enrollments.add(e);
        mhs.addEnrollment(e);
        return true;
    }

    public ArrayList<Enrollment> getByMataKuliah(String kode) {
        ArrayList<Enrollment> hasil = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getMataKuliah().getKode().equals(kode)) {
                hasil.add(e);
            }
        }
        return hasil;
    }

    public ArrayList<Enrollment> getByMahasiswa(String nim) {
        ArrayList<Enrollment> hasil = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getMahasiswa().getNim().equals(nim)) {
                hasil.add(e);
            }
        }
        return hasil;
    }
}
