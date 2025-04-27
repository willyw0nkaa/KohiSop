
public class QRIS implements IChannelPayment {
    @Override
    public double kasihDiskon(double jumlah) {
        return jumlah * 0.95;
    }

    @Override
    public double getBiayaAdmin() {
        return 0;
    }

    @Override
    public String getNama() {
        return "QRIS";
    }
}