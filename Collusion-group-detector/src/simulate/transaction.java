package simulate;

public class transaction {
    int Seller;
    int Buyer;
    int Count;
    int Volume;

    public transaction(int seller, int buyer, int count, int volume) {
        Seller = seller;
        Buyer = buyer;
        Count = count;
        Volume = volume;
    }

    @Override
    public String toString() {
        return "transaction{" +
                "Seller=" + Seller +
                ", Buyer=" + Buyer +
                ", Count=" + Count +
                ", Volume=" + Volume +
                '}';
//        return Seller+","+Buyer+","+Count+","+Volume;
    }
}
