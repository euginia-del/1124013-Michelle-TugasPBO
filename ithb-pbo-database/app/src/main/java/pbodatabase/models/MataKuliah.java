package pbodatabase.models;

public class MataKuliah {

    private String kode;
    private String nama;
    private String status;
    private int sks;

    public MataKuliah(String kode, String nama, String status, int sks) {
        this.kode = kode;
        this.nama = nama;
        this.status = status;
        this.sks = sks;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
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

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }
}
