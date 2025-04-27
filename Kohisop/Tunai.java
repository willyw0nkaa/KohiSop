
public class Tunai implements IChannelPayment {
    @Override
    public double kasihDiskon(double amount) {
        return amount;
    }

    @Override
    public double getBiayaAdmin() {
        return 0;
    }

    @Override
    public String getNama() {
        return "Tunai";
    }
}