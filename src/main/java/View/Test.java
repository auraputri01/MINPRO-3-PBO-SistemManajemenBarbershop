package View;

import controller.BarbershopController;
import model.Barber;
import model.Diskonable;
import model.Layanan;
import model.MemilikiKapasitas;
import model.Orang;
import model.Pelanggan;
import model.Pelayanan;
import util.Format;

public class Test {
    private static int lulus = 0;
    private static int gagal = 0;

    private static void cek(String nama, boolean hasil) {
        System.out.println((hasil ? "[LULUS] " : "[GAGAL] ") + nama);
        if (hasil) lulus++; else gagal++;
    }

    public static void main(String[] args) {
        BarbershopController c = new BarbershopController();

        System.out.println("=== 1. OVERLOADING (ID otomatis) ===");
        Pelanggan reguler = c.tambahPelanggan("Rina", "081111111111");
        Pelanggan member = c.tambahPelanggan("Budi", "082222222222", true);
        Barber biasa = c.tambahBarber("Joko", 3);
        Barber senior = c.tambahBarber("Andi", 10, true);
        cek("ID pelanggan otomatis PLG001/PLG002", reguler.getIdPelanggan().equals("PLG001")
                && member.getIdPelanggan().equals("PLG002"));
        cek("ID barber otomatis BRB001/BRB002", biasa.getIdBarber().equals("BRB001")
                && senior.getIdBarber().equals("BRB002"));
        cek("Format.rupiah(int) dan rupiah(long) sama hasilnya",
                Format.rupiah(1500000).equals(Format.rupiah(1500000L)));

        System.out.println();
        System.out.println("=== 2. OVERRIDING & POLYMORPHISM ===");
        Orang[] semua = {reguler, member, biasa, senior};
        for (Orang o : semua) {
            System.out.println("Peran (getPeran) : " + o.getPeran());
        }
        Diskonable d1 = reguler;
        Diskonable d2 = member;
        cek("Diskon Reguler = 0", d1.hitungDiskon(40000) == 0);
        cek("Diskon Member = 10%", d2.hitungDiskon(40000) == 4000);
        MemilikiKapasitas k1 = biasa;
        MemilikiKapasitas k2 = senior;
        cek("Kapasitas Barber 5 / Senior 7", k1.getKapasitas() == 5 && k2.getKapasitas() == 7);
        cek("Biaya tambahan Barber 0 / Senior 10.000", k1.getBiayaTambahan() == 0 && k2.getBiayaTambahan() == 10000);

        System.out.println();
        System.out.println("=== 3. tampilkanData() (overriding) ===");
        member.tampilkanData();
        System.out.println("---");
        senior.tampilkanData();

        System.out.println();
        System.out.println("=== 4. ALUR PELAYANAN ===");
        Layanan potong = c.getDaftarLayanan().get(0);
        Pelayanan p = c.buatPelayanan(member, senior, potong);
        cek("Total member + senior = Rp36.000", p.getTotalBayar() == 36000);
        cek("Status barber jadi Melayani", senior.getStatusBarber().equals(Barber.MELAYANI));
        c.mulaiPelayanan(p);
        c.selesaikanPelayanan(p);
        cek("Barber kembali Tersedia setelah selesai", senior.getStatusBarber().equals(Barber.TERSEDIA));
        c.bayarPelayanan(p, Pelayanan.QRIS);
        cek("Pendapatan QRIS Rp36.000", c.getPendapatanBerdasarkanMetode(Pelayanan.QRIS) == 36000);

        System.out.println();
        System.out.println("=== 5. ATURAN & VALIDASI ===");
        Pelayanan p2 = c.buatPelayanan(reguler, biasa, potong);
        cek("Reguler + barber biasa = Rp30.000", p2.getTotalBayar() == 30000);
        try {
            c.selesaikanPelayanan(p2);
            cek("Selesai sebelum diproses ditolak", false);
        } catch (IllegalStateException e) {
            cek("Selesai sebelum diproses ditolak", true);
        }
        try {
            c.hapusBarber(biasa);
            cek("Hapus barber yang punya antrean ditolak", false);
        } catch (IllegalStateException e) {
            cek("Hapus barber yang punya antrean ditolak", true);
        }
        try {
            c.tambahPelanggan("R1", "123");
            cek("Nama/HP tidak valid ditolak", false);
        } catch (IllegalArgumentException e) {
            cek("Nama/HP tidak valid ditolak", true);
        }
        for (int i = 0; i < 4; i++) c.buatPelayanan(reguler, biasa, potong);
        cek("Barber biasa penuh di 5 antrean", biasa.getStatusBarber().equals(Barber.PENUH));
        try {
            c.buatPelayanan(reguler, biasa, potong);
            cek("Antrean ke-6 ditolak", false);
        } catch (IllegalStateException e) {
            cek("Antrean ke-6 ditolak", true);
        }

        System.out.println();
        System.out.println("HASIL: " + lulus + " lulus, " + gagal + " gagal.");
    }
}