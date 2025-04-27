
public class PengelolaMenu {
    private static final int MAX_MENU_ITEMS = 20;
    private Menu[] menu = new Menu[MAX_MENU_ITEMS];
    private int hitungMenu = 0;

    public PengelolaMenu() {
        inisialisasiMenu();
    }

    private void inisialisasiMenu() {
        tambahMenu(new Minuman("A1", "Caffe Latte", 46));
        tambahMenu(new Minuman("A2", "Cappuccino", 46));
        tambahMenu(new Minuman("E1", "Caffe Americano", 37));
        tambahMenu(new Minuman("E2", "Caffe Mocha", 55));
        tambahMenu(new Minuman("E3", "Caramel Macchiato", 59));
        tambahMenu(new Minuman("E4", "Asian Dolce Latte", 55));
        tambahMenu(new Minuman("E5", "Double Shots Iced Shaken Espresso", 50));
        tambahMenu(new Minuman("B1", "Freshly Brewed Coffee", 23));
        tambahMenu(new Minuman("B2", "Vanilla Sweet Cream Cold Brew", 50));
        tambahMenu(new Minuman("B3", "Cold Brew", 44));

        tambahMenu(new Makanan("M1", "Petemania Pizza", 112));
        tambahMenu(new Makanan("M2", "Mie Rebus Super Mario", 35));
        tambahMenu(new Makanan("M3", "Ayam Bakar Goreng Rebus Spesial", 72));
        tambahMenu(new Makanan("M4", "Soto Kambing Iga Guling", 124));
        tambahMenu(new Makanan("S1", "Singkong Bakar A La Carte", 37));
        tambahMenu(new Makanan("S2", "Ubi Cilembu Bakar Arang", 58));
        tambahMenu(new Makanan("S3", "Tempe Mendoan", 18));
        tambahMenu(new Makanan("S4", "Tahu Bakso Extra Telur", 28));
    }

    private void tambahMenu(Menu item) {
        if (hitungMenu < MAX_MENU_ITEMS) {
            menu[hitungMenu++] = item;
        }
    }

    public Menu cariKodeMenu(String code) {  
        for (int i = 0; i < hitungMenu; i++) {
            if (menu[i].getKode().equals(code)) {
                return menu[i];
            }
        }
        return null;
    }

    public Menu[] getItemMenu() {
        return menu;
    }

    public int getJumlahMenu() {
        return hitungMenu;
    }
}