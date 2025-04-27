
import java.util.Scanner;

public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);

    public String getInputKodeMenu() {
        System.out.print("\nMasukkan kode menu (BAYAR/CC): ");
        return scanner.nextLine().trim().toUpperCase();
    }


    public boolean konfirmasi(String message) {
        System.out.print(message + " (Y/T): ");
        String input = scanner.nextLine().trim().toUpperCase();
        return input.equals("Y");
    }

    public int getInputKuantitas(Menu item, int maxQuantity) {
        while (true) {
            try {
                System.out.printf("Jumlah %s (1-%d, 0 untuk batal): ",
                        item.getNama(), maxQuantity);
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) return 1;
                
                int quantity = Integer.parseInt(input);
                if (quantity == 0) return 0;
                
                if (quantity < 1 || quantity > maxQuantity) {
                    System.out.printf("Jumlah harus antara 1-%d. Silakan coba lagi.\n", maxQuantity);
                    continue;
                }
                return quantity;
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Silakan masukkan angka.");
            }
        }
    }

    public IChannelPayment pilihMetodePembayaran() {
        while (true) {
            System.out.println("\nPILIH METODE PEMBAYARAN:");
            System.out.println("1. Tunai");
            System.out.println("2. QRIS (Diskon 5%)");
            System.out.println("3. e-Money (Diskon 7% + Biaya Admin 20 IDR)");
            System.out.print("Pilihan (1-3): ");
            
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": return new Tunai();
                case "2": return new QRIS();
                case "3": return new EMoney();
                default: System.out.println("Pilihan tidak valid. Silakan pilih 1-3.");
            }
        }
    }

    public String pilihMataUang() {
        while (true) {
            System.out.println("\nPILIH MATA UANG:");
            System.out.println("1. IDR (Rupiah)");
            System.out.println("2. USD (1 USD = 15 IDR)");
            System.out.println("3. JPY (10 JPY = 1 IDR)");
            System.out.println("4. MYR (1 MYR = 4 IDR)");
            System.out.println("5. EUR (1 EUR = 14 IDR)");
            System.out.print("Pilihan (1-5): ");
            
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": return "IDR";
                case "2": return "USD";
                case "3": return "JPY";
                case "4": return "MYR";
                case "5": return "EUR";
                default: System.out.println("Pilihan tidak valid. Silakan pilih 1-5.");
            }
        }
    }
}