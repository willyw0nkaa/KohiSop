public class KohiSop {
    private PengelolaMenu pengelolaMenu;
    private PengelolaOrder pengelolaOrder;
    private Tampilan tampilan;
    private UserInput userInput;
    private ProsesorPembayaran prosesPembayaran;

    public static void main(String[] args) {
        KohiSop system = new KohiSop();
        system.run();
    }

    public KohiSop() {
        this.pengelolaMenu = new PengelolaMenu();
        this.pengelolaOrder = new PengelolaOrder();
        this.tampilan = new Tampilan(pengelolaMenu, pengelolaOrder);
        this.userInput = new UserInput();
        this.prosesPembayaran = new ProsesorPembayaran(pengelolaOrder, tampilan, userInput);
    }

    public void run() {
        tampilan.tampilkanWelcomeMessage();
        ambilOrder();
    }

    private void ambilOrder() {
        while (true) {
            String input = userInput.getInputKodeMenu();
            
            if (input.equals("CC")) {
                if (userInput.konfirmasi("Yakin ingin membatalkan pesanan?")) {
                    System.out.println("Pesanan dibatalkan. Terima kasih.");
                    pengelolaOrder.resetPesanan();
                    return;
                }
                continue;
            }
            
            if (input.equals("BAYAR")) {
                if (pengelolaOrder.getJumlahPesanan() == 0) {
                    System.out.println("Anda belum memesan apapun.");
                    continue;
                }
                if (userInput.konfirmasi("Konfirmasi selesai memesan?")) {
                    break;
                }
                continue;
            }
            
            Menu item = pengelolaMenu.cariKodeMenu(input);
            if (item == null) {
                System.out.println("Kode tidak valid. Silakan coba lagi.");
                continue;
            }
            
            boolean isFood = item instanceof Makanan;
            if ((isFood && !pengelolaOrder.bisaTambahMakanan()) || (!isFood && !pengelolaOrder.bisaTambahMinuman())) {
                System.out.println("Anda sudah mencapai batas maksimal pesanan untuk kategori ini.");
                continue;
            }
            
            int maxJumlah = isFood ? 2 : 3;
            int jumlah = userInput.getInputKuantitas(item, maxJumlah);
            
            if (jumlah == 0) {
                System.out.println("Pesanan untuk " + item.getNama() + " dibatalkan.");
                continue;
            }
            
            pengelolaOrder.tambahOrderItem(item, jumlah);
            tampilan.tampilkanPesananSaatIni(); 
            
            if (pengelolaOrder.isOrderFull()) {
                System.out.println("Anda telah mencapai batas maksimal pesanan.");
                break;
            }
        }
        
        if (pengelolaOrder.getJumlahPesanan() > 0) {
            prosesPembayaran.prosesPembayaran();
            pengelolaOrder.resetPesanan(); 
        }
    }
}