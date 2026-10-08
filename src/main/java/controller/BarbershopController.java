package controller;

import java.util.ArrayList;
import model.Barber;
import model.BarberBiasa;
import model.BarberSenior;
import model.Layanan;
import model.Pelanggan;
import model.PelangganMember;
import model.PelangganReguler;
import model.Pelayanan;
import util.Validator;

/**
 * CONTROLLER: menyimpan data (ArrayList) dan seluruh aturan bisnis.
 * Tidak ada Scanner/System.out di sini.
 */
public class BarbershopController {
    private final ArrayList<Pelanggan> daftarPelanggan = new ArrayList<>();
    private final ArrayList<Barber> daftarBarber = new ArrayList<>();
    private final ArrayList<Layanan> daftarLayanan = new ArrayList<>();
    private final ArrayList<Pelayanan> daftarPelayanan = new ArrayList<>();
    private int counterPelanggan = 0;
    private int counterBarber = 0;
    private int counterPelayanan = 0;

    public BarbershopController() {
        daftarLayanan.add(new Layanan("LYN001", "Potong Rambut", 30000, 30));
        daftarLayanan.add(new Layanan("LYN002", "Cukur Jenggot", 15000, 15));
        daftarLayanan.add(new Layanan("LYN003", "Cuci Rambut", 20000, 20));
        daftarLayanan.add(new Layanan("LYN004", "Hair Styling", 25000, 25));
        daftarLayanan.add(new Layanan("LYN005", "Paket Komplit", 60000, 60));
    }

    // ===== PELANGGAN =====
    public ArrayList<Pelanggan> getDaftarPelanggan() { return daftarPelanggan; }

    public boolean idPelangganSudahAda(String id) { return cariPelanggan(id) != null; }

    public Pelanggan cariPelanggan(String id) {
        for (Pelanggan p : daftarPelanggan) {
            if (p.getIdPelanggan().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    // OVERLOADING (ID otomatis): tanpa parameter member -> Reguler
    public Pelanggan tambahPelanggan(String nama, String noHp) {
        return tambahPelanggan(nama, noHp, false);
    }

    // OVERLOADING (ID otomatis): dengan parameter member
    public Pelanggan tambahPelanggan(String nama, String noHp, boolean member) {
        return tambahPelanggan(buatIdPelanggan(), nama, noHp, member);
    }

    // OVERLOADING: ID diinput manual, tanpa parameter member
    public Pelanggan tambahPelanggan(String id, String nama, String noHp) {
        return tambahPelanggan(id, nama, noHp, false);
    }

    // OVERLOADING: ID diinput manual, dengan parameter member
    public Pelanggan tambahPelanggan(String id, String nama, String noHp, boolean member) {
        if (!Validator.isIdValid(id)) throw new IllegalArgumentException("ID pelanggan tidak valid.");
        if (idPelangganSudahAda(id)) throw new IllegalArgumentException("ID pelanggan sudah digunakan.");
        if (!Validator.isNamaValid(nama)) throw new IllegalArgumentException("Nama tidak valid.");
        if (!Validator.isNoHpValid(noHp)) throw new IllegalArgumentException("No HP tidak valid.");

        // POLIMORFISME: variabel bertipe Pelanggan, objek berbeda jenis
        Pelanggan p = member ? new PelangganMember(id, nama, noHp) : new PelangganReguler(id, nama, noHp);
        daftarPelanggan.add(p);
        return p;
    }

    private String buatIdPelanggan() {
        String id;
        do {
            counterPelanggan++;
            id = String.format("PLG%03d", counterPelanggan);
        } while (idPelangganSudahAda(id));
        return id;
    }

    public void ubahPelanggan(Pelanggan p, String nama, String noHp) {
        if (!Validator.isNamaValid(nama)) throw new IllegalArgumentException("Nama tidak valid.");
        if (!Validator.isNoHpValid(noHp)) throw new IllegalArgumentException("No HP tidak valid.");
        p.setNama(nama);
        p.setNoHp(noHp);
    }

    public void hapusPelanggan(Pelanggan p) {
        for (Pelayanan pl : daftarPelayanan) {
            if (pl.getIdPelanggan().equalsIgnoreCase(p.getIdPelanggan())
                    && (pl.isAktif() || (Pelayanan.SELESAI.equals(pl.getStatusPelayanan()) && !pl.isLunas()))) {
                throw new IllegalStateException("Pelanggan masih memiliki pelayanan yang belum tuntas.");
            }
        }
        daftarPelanggan.remove(p);
    }

    public int getJumlahPelangganMember() {
        int n = 0;
        for (Pelanggan p : daftarPelanggan) if (p.isMember()) n++;
        return n;
    }

    // ===== BARBER =====
    public ArrayList<Barber> getDaftarBarber() { return daftarBarber; }

    public boolean idBarberSudahAda(String id) { return cariBarber(id) != null; }

    public Barber cariBarber(String id) {
        for (Barber b : daftarBarber) {
            if (b.getIdBarber().equalsIgnoreCase(id)) return b;
        }
        return null;
    }

    // OVERLOADING (ID otomatis): tanpa parameter senior -> Barber biasa
    public Barber tambahBarber(String nama, int pengalaman) {
        return tambahBarber(nama, pengalaman, false);
    }

    // OVERLOADING (ID otomatis): dengan parameter senior
    public Barber tambahBarber(String nama, int pengalaman, boolean senior) {
        return tambahBarber(buatIdBarber(), nama, pengalaman, senior);
    }

    // OVERLOADING: ID diinput manual, tanpa parameter senior
    public Barber tambahBarber(String id, String nama, int pengalaman) {
        return tambahBarber(id, nama, pengalaman, false);
    }

    // OVERLOADING: ID diinput manual, dengan parameter senior
    public Barber tambahBarber(String id, String nama, int pengalaman, boolean senior) {
        if (!Validator.isIdValid(id)) throw new IllegalArgumentException("ID barber tidak valid.");
        if (idBarberSudahAda(id)) throw new IllegalArgumentException("ID barber sudah digunakan.");
        if (!Validator.isNamaValid(nama)) throw new IllegalArgumentException("Nama tidak valid.");
        if (!Validator.isPengalamanValid(pengalaman)) throw new IllegalArgumentException("Pengalaman harus 0-50 tahun.");

        Barber b = senior ? new BarberSenior(id, nama, pengalaman) : new BarberBiasa(id, nama, pengalaman);
        daftarBarber.add(b);
        return b;
    }

    private String buatIdBarber() {
        String id;
        do {
            counterBarber++;
            id = String.format("BRB%03d", counterBarber);
        } while (idBarberSudahAda(id));
        return id;
    }

    public void ubahBarber(Barber b, String nama, int pengalaman) {
        if (!Validator.isNamaValid(nama)) throw new IllegalArgumentException("Nama tidak valid.");
        if (!Validator.isPengalamanValid(pengalaman)) throw new IllegalArgumentException("Pengalaman harus 0-50 tahun.");
        b.setNama(nama);
        b.setPengalaman(pengalaman);
    }

    public void hapusBarber(Barber b) {
        if (b.getJumlahPelangganAktif() > 0) {
            throw new IllegalStateException("Barber masih memiliki antrean aktif.");
        }
        daftarBarber.remove(b);
    }

    public void ubahStatusBarber(Barber b, boolean aktif) {
        if (!aktif && b.getJumlahPelangganAktif() > 0) {
            throw new IllegalStateException("Barber masih memiliki antrean aktif, tidak bisa diubah ke tidak tersedia.");
        }
        b.setStatusKehadiran(aktif ? Barber.HADIR_AKTIF : Barber.HADIR_TIDAK_TERSEDIA);
    }

    public int getJumlahBarberSenior() {
        int n = 0;
        for (Barber b : daftarBarber) if (b.isSenior()) n++;
        return n;
    }

    public int getJumlahBarberDenganStatus(String status) {
        int n = 0;
        for (Barber b : daftarBarber) if (b.getStatusBarber().equals(status)) n++;
        return n;
    }

    // ===== LAYANAN =====
    public ArrayList<Layanan> getDaftarLayanan() { return daftarLayanan; }

    public Layanan cariLayanan(String id) {
        for (Layanan l : daftarLayanan) {
            if (l.getIdLayanan().equalsIgnoreCase(id)) return l;
        }
        return null;
    }

    // ===== PELAYANAN =====
    public ArrayList<Pelayanan> getDaftarPelayanan() { return daftarPelayanan; }

    public Pelayanan cariPelayanan(String id) {
        for (Pelayanan p : daftarPelayanan) {
            if (p.getIdPelayanan().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    public Pelayanan buatPelayanan(Pelanggan pelanggan, Barber barber, Layanan layanan) {
        String status = barber.getStatusBarber();
        if (Barber.TIDAK_TERSEDIA.equals(status)) throw new IllegalStateException("Barber sedang tidak tersedia.");
        if (Barber.PENUH.equals(status)) throw new IllegalStateException("Antrean barber sudah penuh.");

        int subtotal = layanan.getHarga() + barber.getBiayaTambahan();     // polimorfisme Barber
        int total = subtotal - pelanggan.hitungDiskon(subtotal);           // polimorfisme Pelanggan

        int nomorAntrean = 1;
        for (Pelayanan p : daftarPelayanan) {
            if (p.getIdBarber().equalsIgnoreCase(barber.getIdBarber())) nomorAntrean++;
        }

        counterPelayanan++;
        String idPelayanan = String.format("PLY%03d", counterPelayanan);
        Pelayanan pelayanan = new Pelayanan(idPelayanan, pelanggan.getIdPelanggan(),
                barber.getIdBarber(), layanan.getIdLayanan(), nomorAntrean, total);
        daftarPelayanan.add(pelayanan);
        barber.tambahPelanggan();
        return pelayanan;
    }

    public void mulaiPelayanan(Pelayanan p) {
        if (!Pelayanan.MENUNGGU.equals(p.getStatusPelayanan())) {
            throw new IllegalStateException("Hanya pelayanan berstatus Menunggu yang dapat dimulai.");
        }
        p.setStatusPelayanan(Pelayanan.DIPROSES);
    }

    public void selesaikanPelayanan(Pelayanan p) {
        if (!Pelayanan.DIPROSES.equals(p.getStatusPelayanan())) {
            throw new IllegalStateException("Hanya pelayanan berstatus Diproses yang dapat diselesaikan.");
        }
        p.setStatusPelayanan(Pelayanan.SELESAI);
        lepasAntrean(p);
    }

    public void bayarPelayanan(Pelayanan p, String metode) {
        if (!Pelayanan.SELESAI.equals(p.getStatusPelayanan())) {
            throw new IllegalStateException("Pelayanan belum selesai.");
        }
        if (p.isLunas()) throw new IllegalStateException("Pelayanan sudah dibayar.");
        p.bayar(metode);
    }

    public void batalkanPelayanan(Pelayanan p) {
        if (!p.isAktif()) {
            throw new IllegalStateException("Pelayanan berstatus " + p.getStatusPelayanan() + " tidak dapat dibatalkan.");
        }
        p.setStatusPelayanan(Pelayanan.DIBATALKAN);
        lepasAntrean(p);
    }

    private void lepasAntrean(Pelayanan p) {
        Barber b = cariBarber(p.getIdBarber());
        if (b != null) b.kurangiPelanggan();
    }

    // ===== RINGKASAN =====
    public int getJumlahPelayananDenganStatus(String status) {
        int n = 0;
        for (Pelayanan p : daftarPelayanan) if (p.getStatusPelayanan().equals(status)) n++;
        return n;
    }

    public int getTotalPendapatan() {
        int total = 0;
        for (Pelayanan p : daftarPelayanan) if (p.isLunas()) total += p.getTotalBayar();
        return total;
    }

    public int getPendapatanBerdasarkanMetode(String metode) {
        int total = 0;
        for (Pelayanan p : daftarPelayanan) {
            if (p.isLunas() && p.getMetodePembayaran().equals(metode)) total += p.getTotalBayar();
        }
        return total;
    }

    public int getTotalBelumDibayar() {
        int total = 0;
        for (Pelayanan p : daftarPelayanan) {
            if (Pelayanan.SELESAI.equals(p.getStatusPelayanan()) && !p.isLunas()) total += p.getTotalBayar();
        }
        return total;
    }
}