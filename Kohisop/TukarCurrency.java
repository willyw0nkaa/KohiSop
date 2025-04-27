public class TukarCurrency {
    public static double tukar(double jumlahIDR, String mataUang) {
        double rate;
        
        switch (mataUang) {
            case "USD" -> rate = 15.0;
            case "JPY" -> rate = 0.1;
            case "MYR" -> rate = 4.0;
            case "EUR" -> rate = 14.0;
            default -> throw new IllegalArgumentException("Mata uang tidak didukung: " + mataUang);
        }
        
        return jumlahIDR / rate;
    }
}

    
