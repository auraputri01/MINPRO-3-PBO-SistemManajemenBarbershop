package model;

public class PelangganReguler extends Pelanggan {
    public PelangganReguler(String id, String nama, String noHp) {
        super(id, nama, noHp);
    }

    @Override
    public String getPeran() {
        return "Reguler";
    }

    @Override
    public int hitungDiskon(int totalHarga) {
        return 0;
    }
}