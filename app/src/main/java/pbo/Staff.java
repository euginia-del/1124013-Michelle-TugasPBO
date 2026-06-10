package pbo;

public class Staff extends Employee {

    private double gajiPokok;

    public Staff(String nama, String email, String password, String status, String nik, double gajiPokok) {
        super(nama, email, password, status, UserType.STAFF, nik);
        this.gajiPokok = gajiPokok;
    }

    public double getGajiPokok() { return gajiPokok; }
    public void setGajiPokok(double gajiPokok) { this.gajiPokok = gajiPokok; }

    @Override
    public double hitungGaji() {
        return gajiPokok;
    }
}
