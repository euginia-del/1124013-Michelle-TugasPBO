package pbo;

public class DosenHonorer extends Employee {

    private double honorPerSKS;
    private int totalSKS;

    public DosenHonorer(String nama, String email, String password, String status, String nik,
            double honorPerSKS, int totalSKS) {
        super(nama, email, password, status, UserType.DOSEN_HONORER, nik);
        this.honorPerSKS = honorPerSKS;
        this.totalSKS = totalSKS;
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
        return honorPerSKS * totalSKS;
    }
}
