public class Tampilan {
    private PengelolaMenu pengelolaMenu;
    private PengelolaOrder pengelolaOrder;

    public Tampilan(PengelolaMenu pengelolaMenu, PengelolaOrder pengelolaOrder) {
        this.pengelolaMenu = pengelolaMenu;
        this.pengelolaOrder = pengelolaOrder;
    }

    public void tampilkanWelcomeMessage() {
        System.out.println("====================================");
        System.out.println("      SELAMAT DATANG DI KOHISOP     ");
        System.out.println("====================================");
        tampilkanKategoriMenu();
    }

    public void tampilkanKategoriMenu() {
        System.out.println("\nMenu Minuman:");
        tampilkanKategori(false);
        System.out.println("\nMenu Makanan:");
        tampilkanKategori(true);
        System.out.println("\nPetunjuk:");
        System.out.println("- Masukkan kode menu untuk memesan");
        System.out.println("- Ketik 'SELESAI' untuk lanjut ke pembayaran");
        System.out.println("- Ketik 'CC' untuk membatalkan pesanan");
    }

    private void tampilkanKategori(boolean isFood) {
        Format.printMenuHeader();
        
        String category = isFood ? "makanan" : "minuman";
        boolean hasItems = false;
        
        for (int i = 0; i < pengelolaMenu.getJumlahMenu(); i++) {
            Menu item = pengelolaMenu.getItemMenu()[i];
            boolean itemMakanan = item instanceof Makanan;
            if (itemMakanan == isFood) {
                Format.printMenuRow(item.getKode(), item.getNama(), item.getHarga());
                hasItems = true;
            }
        }
        
        if (!hasItems) {
            Format.printNoItemsMessage(category);
        }
        
        Format.printMenuFooter();
    }

    public void tampilkanPesananSaatIni() {
        System.out.println("\nPesanan Anda Saat Ini:");
        
        Format.printOrderHeader("Minuman");
        boolean hasDrinks = tampilkanItemPesanan(false);
        if (!hasDrinks) {
            Format.printNoItemsMessage("minuman yang dipesan");
        }
        Format.printOrderFooter();
        
        Format.printOrderHeader("Makanan");
        boolean hasFoods = tampilkanItemPesanan(true);
        if (!hasFoods) {
            Format.printNoItemsMessage("makanan yang dipesan");
        }
        Format.printOrderFooter();
    }

    private boolean tampilkanItemPesanan(boolean isFood) {
        boolean hasItems = false;
        for (int i = 0; i < pengelolaOrder.getJumlahPesanan(); i++) {
            BarangPesanan barang = pengelolaOrder.getOrderItems()[i];
            boolean isFoodItem = barang.getBarang() instanceof Makanan;
            if (isFoodItem == isFood) {
                Format.printOrderRow(
                    barang.getBarang().getKode(),
                    barang.getBarang().getNama(),
                    barang.getJumlah()
                );
                hasItems = true;
            }
        }
        return hasItems;
    }

    public void tampilkanDetailPembayaran(double subtotal, double totalPajak, double totalSebelumPembayaran) {
        System.out.println("\n====================================");
        System.out.println("          RINCIAN PEMBAYARAN        ");
        System.out.println("====================================");
        
        tampilkanDetailPesanan();
        
        System.out.printf("\nSubtotal\t\t: %10.2f IDR\n", subtotal);
        System.out.printf("Total Pajak\t\t: %10.2f IDR\n", totalPajak);
        System.out.printf("Total Sebelum Diskon\t: %10.2f IDR\n", totalSebelumPembayaran);
    }

    private void tampilkanDetailPesanan() {
        System.out.println("\nDetail Pesanan:");
        
        Format.printDetailHeader("Minuman");
        boolean hasDrinks = tampilkanDetailItemPesanan(false);
        if (!hasDrinks) {
            Format.printNoItemsDetailMessage("minuman yang dipesan");
        }
        Format.printDetailFooter();
        
        Format.printDetailHeader("Makanan");
        boolean hasFoods = tampilkanDetailItemPesanan(true);
        if (!hasFoods) {
            Format.printNoItemsDetailMessage("makanan yang dipesan");
        }
        Format.printDetailFooter();
    }

    private boolean tampilkanDetailItemPesanan(boolean isFood) {
        boolean hasItems = false;
        for (int i = 0; i < pengelolaOrder.getJumlahPesanan(); i++) {
            BarangPesanan barang = pengelolaOrder.getOrderItems()[i];
            boolean isFoodItem = barang.getBarang() instanceof Makanan;
            if (isFoodItem == isFood) {
                Menu item = barang.getBarang();
                Format.printDetailRow(
                    item.getKode(),
                    item.getNama(),
                    item.getHarga(),
                    barang.getJumlah(),
                    barang.getSubtotal(),
                    barang.getPajak()
                );
                hasItems = true;
            }
        }
        return hasItems;
    }

    public void tampilkanPembayaranAkhir(IChannelPayment channelPayment, double totalSebelumPembayaran, double totalIDR, String mataUang) {
        System.out.printf("\nMetode Pembayaran\t: %10s\n", channelPayment.getNama());
        if (!(channelPayment instanceof Tunai)) {
            System.out.printf("Diskon\t\t\t: %10.2f IDR\n", totalSebelumPembayaran - channelPayment.kasihDiskon(totalSebelumPembayaran));
        }
        if (channelPayment.getBiayaAdmin() > 0) {
            System.out.printf("Biaya Admin\t\t: %10.2f IDR\n", channelPayment.getBiayaAdmin());
        }
        System.out.printf("Total Setelah Diskon\t: %10.2f IDR\n", totalIDR);
        
        if (!mataUang.equals("IDR")) {
            double convertedAmount = TukarCurrency.tukar(totalIDR, mataUang);
            System.out.printf("\nTotal dalam %s\t\t: %10.2f\n", mataUang, convertedAmount);
        }
    }

    public void cetakStruk(IChannelPayment channelPayment, double totalIDR, String mataUang) {
        System.out.println("\n\n====================================");
        System.out.println("          KUITANSI KOHISOP         ");
        System.out.println("====================================");
        System.out.println("Tanggal: " + Tanggal.getCurrentDate());
        
        tampilkanDetailPesanan();
        
        System.out.println("\nRINCIAN PEMBAYARAN");
        System.out.println("-----------------------------------------");
        System.out.printf("Metode Pembayaran\t: %10s\n", channelPayment.getNama());
        
        if (!(channelPayment instanceof Tunai)) {
            System.out.printf("Diskon\t\t\t: %10.2f IDR\n", 
                totalIDR - channelPayment.kasihDiskon(totalIDR));
        }
        
        if (channelPayment.getBiayaAdmin() > 0) {
            System.out.printf("Biaya Admin\t\t: %10.2f IDR\n", channelPayment.getBiayaAdmin());
        }
        
        System.out.printf("TOTAL IDR\t\t: %10.2f IDR\n", totalIDR);
        
        if (!mataUang.equals("IDR")) {
            double convertedAmount = TukarCurrency.tukar(totalIDR, mataUang);
            System.out.printf("TOTAL\t\t\t: %10.2f %s\n", convertedAmount, mataUang);
        }
        
        System.out.println("=========================================");
        System.out.println("Terima kasih dan silahkan datang kembali.");
        System.out.println("=========================================");
    }
}