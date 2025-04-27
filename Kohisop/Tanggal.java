import java.text.SimpleDateFormat;
import java.util.Date;

public class Tanggal {
    public static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formatter.format(new Date());
    }
}