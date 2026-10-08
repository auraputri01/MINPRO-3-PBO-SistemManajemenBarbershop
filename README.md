# **SISTEM MANAJEMEN BARBERSHOP**
# **Mini Project 3 Praktikum Pemrograman Berorientasi Objek (PBO)**

### **Nama: Aura Putri Anandita Syarif NIM: 2509116094 Program Studi: Sistem Informasi (C)**

============================================================================

## Deskripsi Singkat Program

Sistem Manajemen Barbershop adalah program Java berbasis console untuk mengelola operasional barbershop, mulai dari data pelanggan, data barber, daftar layanan, antrean, proses pelayanan, sampai pembayaran. Program ini adalah pengembangan dari Mini Project 2 dengan tambahan polymorphism (overriding dan overloading), abstraction (abstract class dan abstract method), struktur proyek MVC, dan interface sebagai nilai tambah.

Pelanggan terbagi menjadi Reguler dan Member (diskon 10%), sedangkan barber terbagi menjadi Barber biasa (maksimal 5 antrean, tanpa biaya tambahan) dan Barber Senior (maksimal 7 antrean, biaya tambahan Rp10.000). ID dibuat otomatis oleh Controller dengan format PLG001 untuk pelanggan, BRB001 untuk barber, PLY001 untuk pelayanan, dan LYN001 sampai LYN005 untuk layanan. Saat mengubah, menghapus, atau memilih data, pengguna cukup memilih nomor dari daftar tanpa mengetik ID. Program juga memvalidasi input dan menerima perintah "batal" untuk membatalkan pengisian data. Seluruh data disimpan sementara di ArrayList.

============================================================================

## **Penjelasan Struktur Package**

Proyek dibagi menjadi empat package dengan pola MVC.

<img width="500" height="497" alt="image" src="https://github.com/user-attachments/assets/f296f9ec-d62d-42c6-b734-ed2c8cdb5d11" />

Package model berisi class data beserta perilakunya, yaitu pelanggan, barber, layanan, pelayanan, dan dua interface. Package View berisi Main sebagai satu-satunya class yang memakai Scanner dan System.out untuk menampilkan menu, serta Test yang menjalankan pengujian otomatis. Package controller berisi BarbershopController yang menyimpan ArrayList dan seluruh aturan bisnis seperti validasi, perhitungan total, dan perpindahan status, tanpa ada input atau output sama sekali. Package util berisi Format untuk mengubah angka menjadi format rupiah dan Validator untuk aturan validasi input.

Pembagian MVC dibuat supaya setiap bagian hanya mengurus satu tanggung jawab. Model hanya mengurus data dan perilaku objek, sehingga validasi nilai dan perhitungan khas tiap jenis objek ada di sana. View, yaitu Main.java, hanya mengurus cara menampilkan menu dan membaca input pengguna. Main adalah satu-satunya class yang memakai Scanner dan System.out untuk menu. Controller, yaitu BarbershopController.java, hanya mengurus aturan main seperti mencari data, menghitung total, dan mengubah status. Tidak ada satu pun System.out di Controller, sehingga logikanya bisa diuji atau dipakai ulang tanpa terikat pada tampilan konsol. Package util berisi perkakas kecil yang boleh dipakai oleh bagian mana pun.

============================================================================

## **Penjelasan Alur Program**

Saat program dijalankan, Main menampilkan menu utama yang berisi kelola pelanggan, kelola barber, daftar layanan, pelayanan pelanggan, cek status pelanggan, status barber, ringkasan, dan keluar. Ketika pengguna menambah pelanggan atau barber, pengguna mengisi nama, nomor HP atau pengalaman, lalu memilih jenisnya. Main meneruskan data itu ke BarbershopController, yang membuat ID otomatis dan objek sesuai jenis yang dipilih.

Untuk mendaftarkan pelayanan, pengguna memilih pelanggan, barber, dan layanan dari daftar bernomor. Controller menghitung total dengan rumus harga layanan ditambah biaya tambahan barber, lalu dikurangi diskon pelanggan. Pelayanan yang baru dibuat berstatus Menunggu dan mendapat nomor antrean sesuai barber yang dipilih. Status kemudian berjalan dari Menunggu ke Diproses, lalu Selesai, dan terakhir dibayar dengan metode Tunai atau QRIS. Pelayanan yang masih Menunggu atau Diproses bisa dibatalkan. Di setiap langkah, daftar yang tampil hanya berisi pelayanan yang memang bisa diproses pada langkah itu.

Status barber dihitung otomatis dari jumlah antrean dan kapasitasnya, yaitu Tersedia, Melayani, atau Penuh, dan menjadi Tidak Tersedia jika kehadirannya diubah manual. Menu ringkasan menampilkan jumlah data, status barber, status pelayanan, dan pendapatan berdasarkan metode pembayaran.

============================================================================

## **Contoh Dokumentasi Program**

- Kelola Pelanggan

<img width="326" height="337" alt="image" src="https://github.com/user-attachments/assets/bfd2394c-66d8-4ca7-9812-184ff2d1646b" />

Tambah Pelanggan

<img width="647" height="347" alt="image" src="https://github.com/user-attachments/assets/093d4b8d-12b8-4111-8ca0-eda1b7a19acc" />

Tampilkan Pelanggan

<img width="508" height="322" alt="image" src="https://github.com/user-attachments/assets/fba6d365-6e04-4ef2-afad-a56595403b65" />

Ubah Pelanggan

<img width="478" height="330" alt="image" src="https://github.com/user-attachments/assets/03949e06-7a37-4ec1-b1a4-9b7591a0a2d5" />

Hapus Pelanggan

<img width="437" height="297" alt="image" src="https://github.com/user-attachments/assets/72d0dfb9-a827-4f21-a13a-d3f7024267a5" />

- Kelola Barber

<img width="333" height="356" alt="image" src="https://github.com/user-attachments/assets/e48701eb-737c-4262-a9f6-1a2d77c45eec" />

Tambah Barber

<img width="497" height="367" alt="image" src="https://github.com/user-attachments/assets/92c8c19e-731b-449e-933a-c98213c59246" />

Tampilkan Barber

<img width="426" height="592" alt="image" src="https://github.com/user-attachments/assets/1035fcac-ddea-4c0f-9676-90d8d17497c4" />

Ubah Barber

<img width="512" height="341" alt="image" src="https://github.com/user-attachments/assets/77f5d50f-f9db-456c-bc1f-1ed559f8a23b" />

Hapus Barber

<img width="505" height="322" alt="image" src="https://github.com/user-attachments/assets/62cd36e9-5080-40b7-b44b-fc6421e24062" />

Ubah Status Kehadiran

<img width="497" height="342" alt="image" src="https://github.com/user-attachments/assets/dbbfccec-53d2-4491-b7d9-0a060ce6e810" />

- Lihat Daftar Layanan
  
<img width="470" height="352" alt="image" src="https://github.com/user-attachments/assets/e97ab846-2f37-4bf2-8557-b52c05394b00" />

- Pelayanan Pelanggan
Daftarkan Pelayanan

<img width="501" height="747" alt="image" src="https://github.com/user-attachments/assets/265640d0-d9f2-4554-8712-334344eb19f7" />

Tampilkan Semua Pelayanan

<img width="491" height="442" alt="image" src="https://github.com/user-attachments/assets/55fdffd5-fc56-4440-81d8-8e4dab9a09ef" />

Mulai Pelayanan

<img width="525" height="288" alt="image" src="https://github.com/user-attachments/assets/3fa7007f-1194-448d-a5bd-1a4d52ec98e9" />

Selesaikan Pelayanan

<img width="526" height="300" alt="image" src="https://github.com/user-attachments/assets/7b2636a7-278a-463e-a4fc-ac444fc11da8" />

Pembayaran

<img width="542" height="461" alt="image" src="https://github.com/user-attachments/assets/517716b8-1930-40e1-bc1c-d7a3cdbe46c2" />

Batalkan Pelayanan

<img width="503" height="290" alt="image" src="https://github.com/user-attachments/assets/190210f7-889b-447a-b003-6c2b69e85796" />

- Cek Status Pelanggan
  
<img width="487" height="726" alt="image" src="https://github.com/user-attachments/assets/b2c25ffb-04f3-451f-9da5-0f3a5274d223" />

- Lihat Status Barber
  
<img width="473" height="438" alt="image" src="https://github.com/user-attachments/assets/b328b8ac-8dc4-44a8-bd8a-3a948b5539db" />

- Ringkasan Barber
  
<img width="470" height="707" alt="image" src="https://github.com/user-attachments/assets/64fb4129-9570-4608-93d0-78e1b55b3186" />

============================================================================

## **Penjelasan Penerapan Encapsulation dan Inheritance**

### ***Encapsulation***
Diterapkan dengan membuat atribut pada tiap class bersifat private, lalu data diakses atau diubah lewat getter, setter, atau method khusus:

- Atribut private dan setter dengan validasi
Atribut seperti nama di model/Orang.java, pengalaman dan statusKehadiran di model/Barber.java, serta noHp di model/Pelanggan.java dibuat private. Setter tidak sekadar menyimpan nilai, tetapi memeriksanya lebih dulu. Orang.setNama() menolak nama yang formatnya tidak sesuai, Barber.setPengalaman() menolak pengalaman di luar 0 sampai 50 tahun, dan Barber.setStatusKehadiran() hanya menerima status Aktif atau Tidak Tersedia. Constructor Orang juga menolak ID yang formatnya tidak valid.

- Data hanya berubah lewat method yang disediakan
Jumlah pelanggan aktif pada Barber bersifat private dan hanya berubah lewat tambahPelanggan() dan kurangiPelanggan(). Method tambahPelanggan() menolak penambahan ketika antrean sudah penuh. Pada model/Pelayanan.java, status pembayaran dan metode pembayaran hanya berubah lewat method bayar(), sehingga pelayanan tidak bisa berstatus lunas tanpa metode pembayaran.

- Constructor dan helper protected
Constructor dan method cetak() di model/Orang.java bersifat protected sehingga hanya bisa dipakai oleh class turunannya.

- Data dikelola oleh Controller
Seluruh ArrayList (pelanggan, barber, layanan, pelayanan) di controller/BarbershopController.java bersifat private final dan hanya dikelola lewat method publik yang disediakan, misalnya tambahPelanggan(), cariBarber(), dan buatPelayanan(). View tidak membuat atau mengisi data sendiri.

### ***Inheritance***
Terdapat satu abstract class induk, Orang, yang diturunkan menjadi dua cabang, dan masing-masing cabang diturunkan sekali lagi:

- Orang
Menyimpan atribut yang dimiliki semua orang di sistem ini, yaitu id dan nama, beserta getter, setter, dan method tampilkanData() (lihat model/Orang.java).

- Pelanggan
Turunan Orang yang menambahkan nomor HP dan menyediakan method isMember(). Dari Pelanggan diturunkan PelangganReguler (tanpa diskon) dan PelangganMember (diskon 10%) pada model/PelangganReguler.java dan model/PelangganMember.java.

- Barber
Turunan Orang yang menambahkan pengalaman, jumlah pelanggan aktif, status kehadiran, dan status barber. Dari Barber diturunkan BarberBiasa (maksimal 5 antrean, tanpa biaya tambahan) dan BarberSenior (maksimal 7 antrean, biaya tambahan Rp10.000) pada model/BarberBiasa.java dan model/BarberSenior.java.

Setiap subclass memanggil super(...) di constructor-nya untuk mengisi data induk, sehingga kode untuk id dan nama tidak ditulis ulang. Method yang diwarisi juga dipakai ulang, misalnya PelangganMember.tampilkanData() memanggil super.tampilkanData() lalu hanya menambahkan informasi diskon.

Perbedaan perilaku antar-subclass ini benar-benar dipakai dalam perhitungan total pembayaran. Total dihitung dari harga layanan ditambah biaya tambahan barber, lalu dikurangi diskon pelanggan, sehingga hasilnya berbeda tergantung kombinasi barber dan pelanggan yang dipilih.

============================================================================

## **Penjelasan Penerapan Abstraction dan Polymorphism**

### ***Abstraction***
Diterapkan dengan abstract class dan abstract method:

- Abstract class
Orang, Pelanggan, dan Barber adalah abstract class sehingga tidak bisa dibuat objeknya secara langsung. Objek yang dibuat adalah turunannya, yaitu PelangganReguler, PelangganMember, BarberBiasa, dan BarberSenior (lihat model/Orang.java, model/Pelanggan.java, dan model/Barber.java).
- Abstract method
getPeran() dibuat abstract di model/Orang.java sehingga setiap turunannya wajib menyebutkan perannya sendiri. Method hitungDiskon() (interface Diskonable) wajib diisi oleh PelangganReguler dan PelangganMember, sedangkan getKapasitas() dan getBiayaTambahan() (interface MemilikiKapasitas) wajib diisi oleh BarberBiasa dan BarberSenior.

### ***Polymorphism***
Diterapkan dalam dua bentuk:

- Method overriding
getPeran(), hitungDiskon(), getKapasitas(), dan getBiayaTambahan() didefinisikan ulang di tiap subclass dengan hasil yang berbeda-beda. Pelanggan Reguler tidak mendapat diskon sedangkan Member mendapat 10%, barber biasa maksimal 5 antrean tanpa biaya tambahan sedangkan barber senior maksimal 7 antrean dengan biaya tambahan Rp10.000 (lihat model/PelangganReguler.java, model/PelangganMember.java, model/BarberBiasa.java, dan model/BarberSenior.java). Method tampilkanData() juga di-override di model/Pelanggan.java, model/PelangganMember.java, dan model/Barber.java untuk menambahkan data khusus masing-masing.
- Method overloading
Di controller/BarbershopController.java, method tambahPelanggan() dan tambahBarber() masing-masing punya empat versi: versi singkat yang otomatis membuat data reguler/biasa, versi dengan parameter boolean tambahan untuk memilih jenis member/senior, dan dua versi lain yang menerima ID secara manual. Selain itu, Format.rupiah() di util/Format.java punya versi untuk int dan versi untuk long.

============================================================================

## **Penjelasan Letak Penerapan Nilai Tambah (Interface)**

Nilai tambah yang diterapkan adalah interface, dan letaknya ada di package model. Interface Diskonable berisi method hitungDiskon dan diimplementasikan oleh class Pelanggan, lalu method-nya diisi oleh PelangganReguler dan PelangganMember. Interface MemilikiKapasitas berisi method getKapasitas dan getBiayaTambahan dan diimplementasikan oleh class Barber, lalu method-nya diisi oleh BarberBiasa dan BarberSenior. Penggunaannya terlihat di BarbershopController pada method buatPelayanan, dan di View/Test.java yang menyimpan objek ke variabel bertipe interface, misalnya Diskonable d = member dan MemilikiKapasitas k = senior.

============================================================================

## **Aturan Bisnis Tambahan**

Selain ketentuan wajib, program ini menambahkan beberapa aturan agar data tetap konsisten. Input divalidasi untuk mencegah nama atau nomor HP yang tidak sesuai, pengalaman di luar 0 sampai 50 tahun, dan pilihan menu yang tidak tersedia. Status barber ditentukan otomatis dari jumlah pelanggan aktif. Barber tanpa pelanggan berstatus Tersedia, barber yang sedang menangani pelanggan berstatus Melayani, dan barber yang sudah mencapai kapasitas berstatus Penuh. Barber juga dapat diubah menjadi Tidak Tersedia saat sedang tidak bekerja. Kapasitas dijaga otomatis, yaitu maksimal 5 antrean untuk barber biasa dan 7 untuk barber senior, dan pelayanan baru ditolak jika barber sudah penuh. Barber atau pelanggan yang masih punya pelayanan belum tuntas tidak bisa dihapus. ID pelanggan, barber, dan pelayanan serta nomor antrean dibuat otomatis oleh sistem, sehingga pengguna tidak perlu mengetiknya.









