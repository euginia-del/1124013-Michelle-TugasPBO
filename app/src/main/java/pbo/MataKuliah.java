package pbo;

public class MataKuliah {

    private String kode;
    private String nama;
    private String status;

    public MataKuliah(String kode, String nama, String status) {
        this.kode = kode;
        this.nama = nama;
        this.status = status;
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
}
