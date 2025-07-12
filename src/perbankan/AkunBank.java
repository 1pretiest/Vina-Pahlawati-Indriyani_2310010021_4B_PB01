
package perbankan;

 
class AkunBank {
    // Atribut: Variabel yang menyimpan data objek (Encapsulation: private)
    private String nomorAkun;
    private String namaPemilik;
    private double saldo;

    // Constructor: Metode khusus untuk membuat objek baru
    public AkunBank(String nomorAkun, String namaPemilik, double saldoAwal) {
        this.nomorAkun = nomorAkun;
        this.namaPemilik = namaPemilik;
        // Seleksi: Memastikan saldo awal tidak negatif
        if (saldoAwal >= 0) {
            this.saldo = saldoAwal;
        } else {
            this.saldo = 0; // Saldo awal tidak boleh negatif
            System.out.println("Saldo awal tidak boleh negatif. Saldo diatur ke 0.");
        }
    }

    // Mutator (Setter): Metode untuk mengubah nilai atribut
    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    // Accessor (Getter): Metode untuk mendapatkan nilai atribut
    public String getNomorAkun() {
        return nomorAkun;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public double getSaldo() {
        return saldo;
    }

    // Metode untuk setor uang
    public void setor(double jumlah) {
        // Seleksi: Memastikan jumlah yang disetor positif
        if (jumlah > 0) {
            saldo += jumlah;
            System.out.println("Setor berhasil. Saldo sekarang: " + saldo);
        } else {
            System.out.println("Jumlah setor harus positif.");
        }
    }

    // Metode untuk tarik uang
    public void tarik(double jumlah) {
        // Seleksi: Memastikan jumlah yang ditarik positif dan saldo mencukupi
        if (jumlah > 0) {
            if (saldo >= jumlah) {
                saldo -= jumlah;
                System.out.println("Tarik berhasil. Saldo sekarang: " + saldo);
            } else {
                System.out.println("Saldo tidak mencukupi.");
            }
        } else {
            System.out.println("Jumlah tarik harus positif.");
        }
    }

    // Metode untuk menampilkan informasi akun
    public void tampilInfo() {
        System.out.println("------------------------------------");
        System.out.println("Nomor Akun   : " + nomorAkun);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo        : " + saldo);
        System.out.println("------------------------------------");
    }
}
