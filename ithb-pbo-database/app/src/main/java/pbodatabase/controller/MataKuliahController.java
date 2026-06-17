package pbodatabase.controller;

import java.util.ArrayList;

import pbodatabase.models.MataKuliah;

public class MataKuliahController {

    private ArrayList<MataKuliah> matkulList;

    public MataKuliahController(ArrayList<MataKuliah> matkulList) {
        this.matkulList = matkulList;
    }

    public ArrayList<MataKuliah> getAll() {
        return matkulList;
    }

    public MataKuliah get(int index) {
        if (index < 0 || index >= matkulList.size()) return null;
        return matkulList.get(index);
    }

    public boolean tambah(String kode, String nama, String status, int sks) {
        for (MataKuliah mk : matkulList) {
            if (mk.getKode().equals(kode)) return false;
        }
        matkulList.add(new MataKuliah(kode, nama, status, sks));
        return true;
    }
}
