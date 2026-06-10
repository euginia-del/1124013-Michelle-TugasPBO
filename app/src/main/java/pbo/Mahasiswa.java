package pbo;

public class Mahasiswa {

    private String nim;
    private String nama;
    private String status;

    public Mahasiswa(String nim, String nama, String status) {
        this.nim = nim;
        this.nama = nama;
        this.status = status;
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

    @Override
    public String toString() {
        return "Mahasiswa{"
                + "nim='" + nim + '\''
                + ", nama='" + nama + '\''
                + ", status='" + status + '\''
                + '}';
    }
}
