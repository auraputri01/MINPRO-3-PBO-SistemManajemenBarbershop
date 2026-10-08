# SISTEM MANAJEMEN BARBERSHOP

### Nama: Aura Putri Anandita Syarif NIM: 2509116094 Program Studi: Sistem Informasi (C)

## Deskripsi Singkat Program

Sistem Manajemen Barbershop adalah program Java berbasis console untuk mengelola operasional barbershop, mulai dari data pelanggan, data barber, daftar layanan, antrean, proses pelayanan, sampai pembayaran. Program ini adalah pengembangan dari Mini Project 2 dengan tambahan polymorphism (overriding dan overloading), abstraction (abstract class dan abstract method), struktur proyek MVC, dan interface sebagai nilai tambah.

Pelanggan terbagi menjadi Reguler dan Member (diskon 10%), sedangkan barber terbagi menjadi Barber biasa (maksimal 5 antrean, tanpa biaya tambahan) dan Barber Senior (maksimal 7 antrean, biaya tambahan Rp10.000). ID dibuat otomatis oleh Controller dengan format PLG001 untuk pelanggan, BRB001 untuk barber, PLY001 untuk pelayanan, dan LYN001 sampai LYN005 untuk layanan. Saat mengubah, menghapus, atau memilih data, pengguna cukup memilih nomor dari daftar tanpa mengetik ID. Program juga memvalidasi input dan menerima perintah "batal" untuk membatalkan pengisian data. Seluruh data disimpan sementara di ArrayList.
