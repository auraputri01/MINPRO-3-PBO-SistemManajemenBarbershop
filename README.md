# **SISTEM MANAJEMEN BARBERSHOP**

### **Nama: Aura Putri Anandita Syarif NIM: 2509116094 Program Studi: Sistem Informasi (C)**

============================================================================

## Deskripsi Singkat Program

Sistem Manajemen Barbershop adalah program Java berbasis console untuk mengelola operasional barbershop, mulai dari data pelanggan, data barber, daftar layanan, antrean, proses pelayanan, sampai pembayaran. Program ini adalah pengembangan dari Mini Project 2 dengan tambahan polymorphism (overriding dan overloading), abstraction (abstract class dan abstract method), struktur proyek MVC, dan interface sebagai nilai tambah.

Pelanggan terbagi menjadi Reguler dan Member (diskon 10%), sedangkan barber terbagi menjadi Barber biasa (maksimal 5 antrean, tanpa biaya tambahan) dan Barber Senior (maksimal 7 antrean, biaya tambahan Rp10.000). ID dibuat otomatis oleh Controller dengan format PLG001 untuk pelanggan, BRB001 untuk barber, PLY001 untuk pelayanan, dan LYN001 sampai LYN005 untuk layanan. Saat mengubah, menghapus, atau memilih data, pengguna cukup memilih nomor dari daftar tanpa mengetik ID. Program juga memvalidasi input dan menerima perintah "batal" untuk membatalkan pengisian data. Seluruh data disimpan sementara di ArrayList.

============================================================================

# **Penjelasan Struktur Package**

Proyek dibagi menjadi empat package dengan pola MVC.

<img width="500" height="497" alt="image" src="https://github.com/user-attachments/assets/f296f9ec-d62d-42c6-b734-ed2c8cdb5d11" />

Package model berisi class data beserta perilakunya, yaitu pelanggan, barber, layanan, pelayanan, dan dua interface. Package View berisi Main sebagai satu-satunya class yang memakai Scanner dan System.out untuk menampilkan menu, serta Test yang menjalankan pengujian otomatis. Package controller berisi BarbershopController yang menyimpan ArrayList dan seluruh aturan bisnis seperti validasi, perhitungan total, dan perpindahan status, tanpa ada input atau output sama sekali. Package util berisi Format untuk mengubah angka menjadi format rupiah dan Validator untuk aturan validasi input.

============================================================================

# **Penjelasan Alur Program**

Saat program dijalankan, Main menampilkan menu utama yang berisi kelola pelanggan, kelola barber, daftar layanan, pelayanan pelanggan, cek status pelanggan, status barber, ringkasan, dan keluar. Ketika pengguna menambah pelanggan atau barber, pengguna mengisi nama, nomor HP atau pengalaman, lalu memilih jenisnya. Main meneruskan data itu ke BarbershopController, yang membuat ID otomatis dan objek sesuai jenis yang dipilih.

Untuk mendaftarkan pelayanan, pengguna memilih pelanggan, barber, dan layanan dari daftar bernomor. Controller menghitung total dengan rumus harga layanan ditambah biaya tambahan barber, lalu dikurangi diskon pelanggan. Pelayanan yang baru dibuat berstatus Menunggu dan mendapat nomor antrean sesuai barber yang dipilih. Status kemudian berjalan dari Menunggu ke Diproses, lalu Selesai, dan terakhir dibayar dengan metode Tunai atau QRIS. Pelayanan yang masih Menunggu atau Diproses bisa dibatalkan. Di setiap langkah, daftar yang tampil hanya berisi pelayanan yang memang bisa diproses pada langkah itu.

Status barber dihitung otomatis dari jumlah antrean dan kapasitasnya, yaitu Tersedia, Melayani, atau Penuh, dan menjadi Tidak Tersedia jika kehadirannya diubah manual. Menu ringkasan menampilkan jumlah data, status barber, status pelayanan, dan pendapatan berdasarkan metode pembayaran.

============================================================================

# **Penjelasan Penerapan Encapsulation dan Inheritance**

Encapsulation diterapkan dengan menjadikan semua atribut model bersifat private dan hanya dapat diakses lewat getter dan setter. Setter juga memvalidasi nilainya sebelum disimpan. Misalnya setNama di class Orang memeriksa format nama, setPengalaman di class Barber memastikan nilainya 0 sampai 50, dan setStatusKehadiran hanya menerima status yang valid. Jumlah pelanggan aktif pada Barber hanya berubah lewat method tambahPelanggan dan kurangiPelanggan, dan antrean yang sudah penuh akan ditolak. Status pembayaran pada Pelayanan hanya berubah lewat method bayar. Constructor Orang dibuat protected agar hanya class turunan yang bisa memakainya. Kumpulan data ArrayList juga hanya dikelola oleh Controller, bukan oleh View.

Inheritance diterapkan dengan Orang sebagai class induk. Pelanggan dan Barber adalah turunan Orang sehingga mewarisi id, nama, dan method tampilkanData. PelangganReguler dan PelangganMember adalah turunan Pelanggan, sedangkan BarberBiasa dan BarberSenior adalah turunan Barber. Setiap subclass memanggil super untuk mengisi data induknya lalu menambahkan perilaku sendiri.

============================================================================

# **Penjelasan Penerapan Polymorphism dan Abstraction**

Abstraction diterapkan lewat tiga abstract class. Orang memiliki abstract method getPeran sehingga setiap turunannya wajib menyebutkan perannya, dan class ini tidak bisa dibuat objeknya langsung. Pelanggan adalah abstract class yang membuat subclass wajib mengisi method hitungDiskon. Barber juga abstract class yang membuat subclass wajib mengisi method getKapasitas dan getBiayaTambahan.

Overriding terlihat pada beberapa method. getPeran di-override oleh PelangganReguler, PelangganMember, BarberBiasa, dan BarberSenior sehingga masing-masing mengembalikan teks perannya sendiri. hitungDiskon menghasilkan 0 untuk Reguler dan 10% untuk Member. getKapasitas menghasilkan 5 untuk Barber biasa dan 7 untuk Barber Senior, sedangkan getBiayaTambahan menghasilkan Rp0 dan Rp10.000. Method tampilkanData di-override pada Pelanggan, PelangganMember, dan Barber untuk menambahkan detail masing-masing, dan isMember serta isSenior di-override pada subclass terkait. Polimorfisme dinamis dapat dilihat di BarbershopController pada method buatPelayanan, ketika variabel bertipe Pelanggan dan Barber memanggil hitungDiskon dan getBiayaTambahan, dan hasilnya mengikuti jenis objek yang sebenarnya.

Overloading diterapkan pada method tambahPelanggan dan tambahBarber di BarbershopController. tambahPelanggan memiliki empat versi, yaitu dengan nama dan nomor HP, dengan tambahan parameter member, dan dua versi lain yang menerima ID manual. tambahBarber juga memiliki empat versi dengan pola yang sama, dengan parameter senior sebagai pembeda. Selain itu, Format.rupiah memiliki versi untuk int dan versi untuk long.

============================================================================

# **Penjelasan Letak Penerapan Nilai Tambah (Interface)**

Nilai tambah yang diterapkan adalah interface, dan letaknya ada di package model. Interface Diskonable berisi method hitungDiskon dan diimplementasikan oleh class Pelanggan, lalu method-nya diisi oleh PelangganReguler dan PelangganMember. Interface MemilikiKapasitas berisi method getKapasitas dan getBiayaTambahan dan diimplementasikan oleh class Barber, lalu method-nya diisi oleh BarberBiasa dan BarberSenior. Penggunaannya terlihat di BarbershopController pada method buatPelayanan, dan di View/Test.java yang menyimpan objek ke variabel bertipe interface, misalnya Diskonable d = member dan MemilikiKapasitas k = senior.









