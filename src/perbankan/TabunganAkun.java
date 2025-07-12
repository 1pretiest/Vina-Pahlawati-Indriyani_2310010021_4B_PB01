
package perbankan;


class TabunganAkun extends AkunBank {
    private double sukuBunga; // Atribut tambahan untuk akun tabungan

    public TabunganAkun(String nomorAkun, String namaPemilik, double saldoAwal, double sukuBunga) {
        super(nomorAkun, namaPemilik, saldoAwal); // Memanggil constructor superclass
        this.sukuBunga = sukuBunga;
    }

    public double getSukuBunga() {
        return sukuBunga;
    }

    public void hitungBunga() {
        double bunga = getSaldo() * sukuBunga;
        setor(bunga); // Menambahkan bunga ke saldo
        System.out.println("Bunga sebesar " + bunga + " telah ditambahkan.");
    }

    // Polymorphism: Override metode tarik untuk menambahkan biaya penarikan (contoh)
    @Override
    public void tarik(double jumlah) {
        double biayaPenarikan = 5000; // Contoh biaya penarikan
        if (getSaldo() >= (jumlah + biayaPenarikan)) {
            super.tarik(jumlah + biayaPenarikan); // Panggil metode tarik dari superclass
            System.out.println("Biaya penarikan sebesar " + biayaPenarikan + " dikenakan.");
        } else {
            System.out.println("Saldo tidak mencukupi untuk penarikan dan biaya.");
        }
    }

    @Override
    public void tampilInfo() {
        super.tampilInfo(); // Panggil metode tampilInfo dari superclass
        System.out.println("Suku Bunga   : " + sukuBunga * 100 + "%");
        System.out.println("------------------------------------");
    }
}