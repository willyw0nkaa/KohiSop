
public class PengelolaOrder {
    private static final int MAX_ORDER_ITEMS = 10;
    private BarangPesanan[] itemPesanan = new BarangPesanan[MAX_ORDER_ITEMS];
    private int orderCount = 0;
    private int drinkCount = 0;
    private int foodCount = 0;

    public void tambahOrderItem(Menu item, int quantity) {
        if (orderCount < MAX_ORDER_ITEMS) {
            itemPesanan[orderCount++] = new BarangPesanan(item, quantity);
            if (item instanceof Makanan) {
                foodCount++;
            } else {
                drinkCount++;
            }
        }
    }

    public boolean bisaTambahMakanan() {
        return foodCount < 5;
    }

    public boolean bisaTambahMinuman() {
        return drinkCount < 5;
    }

    public boolean isOrderFull() {
        return orderCount >= MAX_ORDER_ITEMS || (foodCount >= 5 && drinkCount >= 5);
    }

    public BarangPesanan[] getOrderItems() {
        return itemPesanan;
    }

    public int getJumlahPesanan() {
        return orderCount;
    }

    public void resetPesanan() {
        orderCount = 0;
        drinkCount = 0;
        foodCount = 0;
    }
}