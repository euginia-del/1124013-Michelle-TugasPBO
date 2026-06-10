package pbo;

public class Enrollment {

    private Mahasiswa mahasiswa;
    private MataKuliah mataKuliah;
    private double nilai;

    public Enrollment(Mahasiswa mahasiswa, MataKuliah mataKuliah, double nilai) {
        this.mahasiswa = mahasiswa;
        this.mataKuliah = mataKuliah;
        this.nilai = nilai;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public MataKuliah getMataKuliah() {
        return mataKuliah;
    }

    public void setMataKuliah(MataKuliah mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    public double getNilai() {
        return nilai;
    }

    public void setNilai(double nilai) {
        this.nilai = nilai;
    }

    @Override
    public String toString() {
        return "Enrollment{"
                + "mahasiswa=" + mahasiswa.getNama()
                + ", mataKuliah=" + mataKuliah.getNama()
                + ", nilai=" + nilai
                + '}';
    }
}
