package UselessThings;

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
    }

    public int getSeller() {
        return Seller;
    }

    public void setSeller(int seller) {
        Seller = seller;
    }

    public int getBuyer() {
        return Buyer;
    }

    public void setBuyer(int buyer) {
        Buyer = buyer;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getVolume() {
        return Volume;
    }

    public void setVolume(int volume) {
        Volume = volume;
    }
}
