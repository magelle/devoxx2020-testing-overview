package devoxxfr2020.cashregister.domain;

public class FruitDiscount {

    private final long discount;
    private final long threshold;

    public FruitDiscount(long discount, long threshold) {
        this.discount = discount;
        this.threshold = threshold;
    }

    public long getDiscountAmount(long numberOfFruit) {
        long numberOfDiscountApplication = numberOfFruit / threshold;
        return numberOfDiscountApplication * discount;
    }
}
