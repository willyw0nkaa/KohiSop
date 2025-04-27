public class EMoney implements IChannelPayment {
    @Override
    public double kasihDiskon(double jumlah) {
        return jumlah * 0.93;
    }

    @Override
    public double getBiayaAdmin() {
        return 20;
    }

    @Override
    public String getNama() {
        return "e-Money";
    }
}