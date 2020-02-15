package devoxxfr2020.basket.domain;

public class FruitDiscount {

    private long discount;
    private long threshold;

    public FruitDiscount(long discount, long threshold) {
        this.discount = discount;
        this.threshold = threshold;
    }

    public long getDiscountAmount(long numberOfFruit) {
        long numberOfDiscountApplication = numberOfFruit / threshold;
        return numberOfDiscountApplication * discount;
    }
}
