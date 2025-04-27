
public class Format {
    private static final String MENU_HEADER_FORMAT = "+------+---------------------------------------+--------+";
    private static final String MENU_ROW_FORMAT = "| %-4s | %-37s | %6d |\n";
    private static final String ORDER_HEADER_FORMAT = "+------+---------------------------------------+------------+";
    private static final String ORDER_ROW_FORMAT = "| %-4s | %-37s | %10d |\n";
    private static final String DETAIL_HEADER_FORMAT = "+------+---------------------------------------+--------+------------+------------+------------+";
    private static final String DETAIL_ROW_FORMAT = "| %-4s | %-37s | %6d | %10d | %10.2f | %10.2f |\n";

    public static void printMenuHeader() {
        System.out.println(MENU_HEADER_FORMAT);
        System.out.println("| Kode | Nama Menu                             | Harga  |");
        System.out.println(MENU_HEADER_FORMAT);
    }

    public static void printMenuRow(String code, String name, int price) {
        System.out.printf(MENU_ROW_FORMAT, code, name, price);
    }

    public static void printMenuFooter() {
        System.out.println(MENU_HEADER_FORMAT);
    }

    public static void printOrderHeader(String title) {
        System.out.println(ORDER_HEADER_FORMAT);
        System.out.printf("| Kode | %-37s | Kuantitas  |\n", title);
        System.out.println(ORDER_HEADER_FORMAT);
    }

    public static void printOrderRow(String code, String name, int quantity) {
        System.out.printf(ORDER_ROW_FORMAT, code, name, quantity);
    }

    public static void printOrderFooter() {
        System.out.println(ORDER_HEADER_FORMAT);
    }

    public static void printDetailHeader(String title) {
        System.out.println(DETAIL_HEADER_FORMAT);
        System.out.printf("| Kode | %-37s | Harga  | Kuantitas  | Subtotal   | Pajak      |\n", title);
        System.out.println(DETAIL_HEADER_FORMAT);
    }

    public static void printDetailRow(String code, String name, int price, int quantity, double subtotal, double tax) {
        System.out.printf(DETAIL_ROW_FORMAT, code, name, price, quantity, subtotal, tax);
    }

    public static void printDetailFooter() {
        System.out.println(DETAIL_HEADER_FORMAT);
    }

    public static void printNoItemsMessage(String category) {
        System.out.printf("|      | Tidak ada %-27s |            |\n", category);
    }

    public static void printNoItemsDetailMessage(String category) {
        System.out.printf("|      | Tidak ada %-27s |        |            |            |            |\n", category);
    }
}