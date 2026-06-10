package pbo;

public class DosenTetap extends Employee {

    private double gajiPokok;
    private double honorPerSKS;
    private int totalSKS;

    public DosenTetap(String nama, String email, String password, String status, String nik,
            double gajiPokok, double honorPerSKS, int totalSKS) {
        super(nama, email, password, status, UserType.DOSEN_TETAP, nik);
        this.gajiPokok = gajiPokok;
        this.honorPerSKS = honorPerSKS;
        this.totalSKS = totalSKS;
    }

    public double getGajiPokok() {
        return gajiPokok;
    }

    public void setGajiPokok(double gajiPokok) {
        this.gajiPokok = gajiPokok;
    }

    public double getHonorPerSKS() {
        return honorPerSKS;
    }

    public void setHonorPerSKS(double honorPerSKS) {
        this.honorPerSKS = honorPerSKS;
    }

    public int getTotalSKS() {
        return totalSKS;
    }

    public void setTotalSKS(int totalSKS) {
        this.totalSKS = totalSKS;
    }

    @Override
    public double hitungGaji() {
        return gajiPokok + (honorPerSKS * totalSKS);
    }
}
