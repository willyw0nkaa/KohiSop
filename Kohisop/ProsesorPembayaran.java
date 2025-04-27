public class ProsesorPembayaran {
    private PengelolaOrder pengelolaOrder;
    private Tampilan tampilan;
    private UserInput userInput;

    public ProsesorPembayaran(PengelolaOrder orderManager, Tampilan tampilan, UserInput inputHandler) {
        this.pengelolaOrder = orderManager;
        this.tampilan = tampilan;
        this.userInput = inputHandler;
    }

    public void prosesPembayaran() {
        if (pengelolaOrder.getJumlahPesanan() == 0) {
            System.out.println("Tidak ada pesanan untuk diproses!");
            return;
        }

        double subtotal = hitungSubtotal();
        double totalPajak = hitungPajak();
        double totalSebelumPembayaran = subtotal + totalPajak;

        tampilan.tampilkanDetailPembayaran(subtotal, totalPajak, totalSebelumPembayaran);

        IChannelPayment channelPayment = userInput.pilihMetodePembayaran();
        double totalSetelahDiskon = channelPayment.kasihDiskon(totalSebelumPembayaran);
        double biayaAdmin = channelPayment.getBiayaAdmin();
        double totalIDR = totalSetelahDiskon + biayaAdmin;

        String mataUang = userInput.pilihMataUang();
        tampilan.tampilkanPembayaranAkhir(channelPayment, totalSebelumPembayaran, totalIDR, mataUang);
        tampilan.cetakStruk(channelPayment, totalIDR, mataUang);
    }

    private double hitungSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < pengelolaOrder.getJumlahPesanan(); i++) {
            subtotal += pengelolaOrder.getOrderItems()[i].getSubtotal();
        }
        return subtotal;
    }

    private double hitungPajak() {
        double totalPajak = 0;
        for (int i = 0; i < pengelolaOrder.getJumlahPesanan(); i++) {
            totalPajak += pengelolaOrder.getOrderItems()[i].getPajak();
        }
        return totalPajak;
    }
}