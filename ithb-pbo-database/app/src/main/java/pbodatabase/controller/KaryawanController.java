package pbodatabase.controller;

import java.util.ArrayList;

import pbodatabase.models.DosenHonorer;
import pbodatabase.models.DosenTetap;
import pbodatabase.models.Employee;
import pbodatabase.models.Staff;

public class KaryawanController {

    private ArrayList<Employee> employees;

    public KaryawanController(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Employee> getAll() {
        return employees;
    }

    public void tambah(String tipe, String nama, String email, String password, String status, String nik,
            double gajiPokok, double honorPerSKS, int totalSKS) {
        switch (tipe) {
            case "1":
                employees.add(new DosenTetap(nama, email, password, status, nik, gajiPokok, honorPerSKS, totalSKS));
                break;
            case "2":
                employees.add(new DosenHonorer(nama, email, password, status, nik, honorPerSKS, totalSKS));
                break;
            case "3":
                employees.add(new Staff(nama, email, password, status, nik, gajiPokok));
                break;
            default:
                System.out.println("Tipe tidak valid!");
        }
    }

    public Employee get(int index) {
        if (index < 0 || index >= employees.size()) return null;
        return employees.get(index);
    }

    public boolean hapus(int index) {
        if (index < 0 || index >= employees.size()) return false;
        employees.remove(index);
        return true;
    }
}
