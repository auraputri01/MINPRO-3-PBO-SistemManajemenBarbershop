package model;

public class BarberBiasa extends Barber {
    public BarberBiasa(String id, String nama, int pengalaman) {
        super(id, nama, pengalaman);
    }

    @Override
    public String getPeran() {
        return "Barber";
    }

    @Override
    public int getKapasitas() {
        return 5;
    }

    @Override
    public int getBiayaTambahan() {
        return 0;
    }
}