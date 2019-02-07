# OnlineTicketBooking
Online Movie Ticket Booking With Java Spring Boot

 Merupakan sebuah aplikasi yang menggambarkan aktivitas booking tiket bioskop berdasarkan jadwal dan kuota masing-masing tiket. Aplikasi ini dibangun dengan menggunakan bahasa java dengan memanfaatkan Framework Spring boot. Untuk bagian User Interface diimplementasikan dengan Thymeleaf, HTML dan CSS. Terdapat beberapa tabel yang berguna untuk penyimpanan data dari aplikasi ini yang dapat dilihat pada "CreateTable.sql".
 
 Batasan Aplikasi :
 
1. Sistem ini hanya menggunakan 1 bioskop saja
2. Data customer sudah ada sebelumnya
3. Data tiket beserta jumlahnya sudah ada sebelumnya
4. Proses penjualan hanya order saja. Proses pembayaran diluar ruang lingkup pekerjaan
5. Aplikasi ini hanya menggambarkan aktivitas user.

Detail Aplikasi :

1. User melakukan login sesuai dengan email dan password 
2. Jika berhasil login maka sistem akan menampilkan halaman berisi form untuk melakukan booking tiket,
   Jika gagal maka akan terdapat pesan error
3. Saat berhasil login sistem akan secara otomatis menampilkan data user yang melakukan login
4. User dapat melakukan booking tiket dengan diawali memilih jadwal film melalui dropdown yang disediakan
5. Jika sudah dipilih jadwal filmnya, maka sistem akan menampilkan jadwal dari film tersebut yang memang tanggal tayangnya belum lewat.
   masa tayangnya.
6. Jika Sudah berhasil memilih jadwal maka user dimina memasukan jumlah tiket yang akan dibooking.
7. Saat Proses booking berhasil maka sistem akan menampilkan receipt mengenai detil dari booking yang dilakukan.
8. Sistem juga akan melakukan update kuota dari tiket yang dipesan.
9. Jika booking gagal dilakukan karena pengguna melakukan booking pada hari yang sama namun waktu tayangnya sudah terlewat 
   maka sistem akan menampilkan pesan error yang memberi tahu bahwa jadwal yg dipilih sudah lewat waktu tayangnya.
   10 Jika Booking gagal dilakukan karena pengguna melakukan booking melebihi kuota yang ada, maka sistem akan menampilkan        pesan eror yang memberi tahu bahwa jumlah tiket yang dipesan melebihi kuota yang ada
10.Terdapat File Query insert yang berisi contoh insert pada masing-masing table pada aplikasi ini.

Note : Untuk password login yang digunakan adalah "123456".







 

 

