package devoxxfr2020.cashregister.domain;

public class FruitDiscount {

    private long discount;
    private long threshold;

    public FruitDiscount(long discount, long threshold) {
        this.discount = discount;
        this.threshold = threshold;
    }

    public long getDiscount() {
        return discount;
    }

    public long getDiscountAmount(long numberOfFruit) {
        long numberOfDiscountApplication = numberOfFruit / threshold;
        return numberOfDiscountApplication * discount;
    }

    @Override
    public String toString() {
        return "FruitDiscount{" +
                "discount=" + discount +
                ", threshold=" + threshold +
                '}';
    }
}
