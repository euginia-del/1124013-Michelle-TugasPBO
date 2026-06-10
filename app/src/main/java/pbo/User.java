package pbo;

public abstract class User {

    protected String nama;
    protected String email;
    protected String password;
    protected String status;
    protected UserType type;

    public User(String nama, String email, String password, String status, UserType type) {
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.status = status;
        this.type = type;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserType getType() {
        return type;
    }
}
