public class BarangPesanan {
    private Menu barang;
    private int jumlah;

    public BarangPesanan(Menu barang, int jumlah) {
        this.barang = barang;
        this.jumlah = jumlah;
    }

    public Menu getBarang() {
        return barang;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double getSubtotal() {
        return barang.getHarga() * jumlah;
    }

    public double getPajak() {
        return barang.hitungPajak() * jumlah;
    }

    public double getTotal() {
        return getSubtotal() + getPajak();
    }
} 
