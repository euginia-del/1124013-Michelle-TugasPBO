package pbo;

public abstract class Employee extends User {

    protected String nik;

    public Employee(String nama, String email, String password, String status, UserType type, String nik) {
        super(nama, email, password, status, type);
        this.nik = nik;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public abstract double hitungGaji();
}
