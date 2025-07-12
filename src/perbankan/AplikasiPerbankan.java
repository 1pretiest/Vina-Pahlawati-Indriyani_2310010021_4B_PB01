
package perbankan;
import java.util.ArrayList; // Untuk menggunakan ArrayList (Array dinamis)
import java.util.InputMismatchException; // Untuk menangani input yang tidak sesuai
import java.util.Scanner; // Untuk input dari pengguna

// Class utama untuk menjalankan aplikasi perbankan
public class AplikasiPerbankan {

    // ArrayList untuk menyimpan objek AkunBank (Array - dinamis)
    private static ArrayList<AkunBank> daftarAkun = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in); // IO Sederhana

    public static void main(String[] args) {
        int pilihan;
        // Perulangan: Menu utama aplikasi
        do {
            tampilkanMenu();
            try {
                System.out.print("Masukkan pilihan Anda: ");
                pilihan = scanner.nextInt();
                scanner.nextLine(); // Konsumsi newline

                // Seleksi: Memproses pilihan pengguna
                switch (pilihan) {
                    case 1:
                        buatAkunBaru();
                        break;
                    case 2:
                        setorUang();
                        break;
                    case 3:
                        tarikUang();
                        break;
                    case 4:
                        lihatDaftarAkun();
                        break;
                    case 5:
                        System.out.println("Terima kasih telah menggunakan layanan kami!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (InputMismatchException e) { // Error Handling: Menangani input non-angka
                System.out.println("Input tidak valid. Harap masukkan angka.");
                scanner.nextLine(); // Konsumsi input yang salah
                pilihan = 0; // Set pilihan ke 0 agar perulangan berlanjut
            }
            System.out.println(); // Baris kosong untuk kerapian
        } while (pilihan != 5);

        scanner.close(); // Tutup scanner
    }

    // Metode untuk menampilkan menu
    private static void tampilkanMenu() {
        System.out.println("===== Menu Aplikasi Perbankan =====");
        System.out.println("1. Buat Akun Baru");
        System.out.println("2. Setor Uang");
        System.out.println("3. Tarik Uang");
        System.out.println("4. Lihat Daftar Akun");
        System.out.println("5. Keluar");
        System.out.println("===================================");
    }

    // Metode untuk membuat akun baru
    private static void buatAkunBaru() {
        System.out.println("\n--- Buat Akun Baru ---");
        System.out.print("Masukkan nomor akun: ");
        String nomorAkun = scanner.nextLine();

        // Seleksi: Cek apakah nomor akun sudah ada
        if (cariAkun(nomorAkun) != null) {
            System.out.println("Nomor akun sudah ada. Silakan gunakan nomor lain.");
            return;
        }

        System.out.print("Masukkan nama pemilik: ");
        String namaPemilik = scanner.nextLine();

        double saldoAwal = 0;
        boolean inputValid = false;
        while (!inputValid) { // Perulangan untuk validasi input saldo
            try {
                System.out.print("Masukkan saldo awal: ");
                saldoAwal = scanner.nextDouble();
                inputValid = true;
            } catch (InputMismatchException e) { // Error Handling
                System.out.println("Input saldo tidak valid. Harap masukkan angka.");
                scanner.nextLine(); // Konsumsi input yang salah
            }
        }
        scanner.nextLine(); // Konsumsi newline

        System.out.print("Pilih jenis akun (1: Umum, 2: Tabungan): ");
        int jenisAkun = 0;
        inputValid = false;
        while (!inputValid) { // Perulangan untuk validasi input jenis akun
            try {
                jenisAkun = scanner.nextInt();
                inputValid = true;
            } catch (InputMismatchException e) { // Error Handling
                System.out.println("Input tidak valid. Harap masukkan angka 1 atau 2.");
                scanner.nextLine(); // Konsumsi input yang salah
            }
        }
        scanner.nextLine(); // Konsumsi newline

        AkunBank akunBaru;
        if (jenisAkun == 2) {
            double sukuBunga = 0;
            inputValid = false;
            while (!inputValid) { // Perulangan untuk validasi input suku bunga
                try {
                    System.out.print("Masukkan suku bunga (contoh: 0.01 untuk 1%): ");
                    sukuBunga = scanner.nextDouble();
                    inputValid = true;
                } catch (InputMismatchException e) { // Error Handling
                    System.out.println("Input suku bunga tidak valid. Harap masukkan angka desimal.");
                    scanner.nextLine(); // Konsumsi input yang salah
                }
            }
            scanner.nextLine(); // Konsumsi newline
            akunBaru = new TabunganAkun(nomorAkun, namaPemilik, saldoAwal, sukuBunga);
        } else {
            akunBaru = new AkunBank(nomorAkun, namaPemilik, saldoAwal); // Object: Instansiasi class AkunBank
        }

        daftarAkun.add(akunBaru); // Menambahkan objek ke ArrayList
        System.out.println("Akun berhasil dibuat!");
        akunBaru.tampilInfo();
    }

    // Metode untuk setor uang ke akun
    private static void setorUang() {
        System.out.println("\n--- Setor Uang ---");
        System.out.print("Masukkan nomor akun: ");
        String nomorAkun = scanner.nextLine();

        AkunBank akun = cariAkun(nomorAkun); // Cari akun
        if (akun != null) {
            double jumlah = 0;
            boolean inputValid = false;
            while (!inputValid) { // Perulangan untuk validasi input jumlah
                try {
                    System.out.print("Masukkan jumlah yang akan disetor: ");
                    jumlah = scanner.nextDouble();
                    inputValid = true;
                } catch (InputMismatchException e) { // Error Handling
                    System.out.println("Input jumlah tidak valid. Harap masukkan angka.");
                    scanner.nextLine(); // Konsumsi input yang salah
                }
            }
            scanner.nextLine(); // Konsumsi newline
            akun.setor(jumlah);
        } else {
            System.out.println("Akun tidak ditemukan.");
        }
    }

    // Metode untuk tarik uang dari akun
    private static void tarikUang() {
        System.out.println("\n--- Tarik Uang ---");
        System.out.print("Masukkan nomor akun: ");
        String nomorAkun = scanner.nextLine();

        AkunBank akun = cariAkun(nomorAkun); // Cari akun
        if (akun != null) {
            double jumlah = 0;
            boolean inputValid = false;
            while (!inputValid) { // Perulangan untuk validasi input jumlah
                try {
                    System.out.print("Masukkan jumlah yang akan ditarik: ");
                    jumlah = scanner.nextDouble();
                    inputValid = true;
                } catch (InputMismatchException e) { // Error Handling
                    System.out.println("Input jumlah tidak valid. Harap masukkan angka.");
                    scanner.nextLine(); // Konsumsi input yang salah
                }
                }
            scanner.nextLine(); // Konsumsi newline
            akun.tarik(jumlah);
        } else {
            System.out.println("Akun tidak ditemukan.");
        }
    }

    // Metode untuk melihat daftar akun
    private static void lihatDaftarAkun() {
        System.out.println("\n--- Daftar Akun ---");
        if (daftarAkun.isEmpty()) { // Seleksi: Cek apakah daftar akun kosong
            System.out.println("Belum ada akun yang terdaftar.");
        } else {
            // Perulangan: Iterasi melalui ArrayList untuk menampilkan info setiap akun
            for (AkunBank akun : daftarAkun) {
                akun.tampilInfo(); // Polymorphism: Memanggil tampilInfo yang sesuai (AkunBank atau TabunganAkun)
            }
        }
    }

    // Metode bantu untuk mencari akun berdasarkan nomor akun
    private static AkunBank cariAkun(String nomorAkun) {
        // Perulangan: Iterasi melalui ArrayList
        for (AkunBank akun : daftarAkun) {
            if (akun.getNomorAkun().equals(nomorAkun)) { // Seleksi: Membandingkan nomor akun
                return akun;
            }
        }
        return null; // Jika akun tidak ditemukan
    }
}



