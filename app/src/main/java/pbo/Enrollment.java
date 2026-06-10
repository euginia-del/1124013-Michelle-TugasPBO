package pbo;

public class Enrollment {

    private Mahasiswa mahasiswa;
    private MataKuliah mataKuliah;

    public Enrollment(Mahasiswa mahasiswa, MataKuliah mataKuliah) {
        this.mahasiswa = mahasiswa;
        this.mataKuliah = mataKuliah;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }
}
