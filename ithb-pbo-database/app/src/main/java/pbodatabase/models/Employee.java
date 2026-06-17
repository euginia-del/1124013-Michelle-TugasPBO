package pbodatabase.models;

import pbodatabase.enums.UserStatus;
import pbodatabase.enums.UserType;

public abstract class Employee extends User {

    protected String nik;

    public Employee(String nama, String email, String password, String status, UserType type, String nik) {
        super(nik, nama, email, password, UserStatus.valueOf(status.toUpperCase()), type, null, null);
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
