package devoxxfr2020.cashregister.domain;

public class AppliedBasketDiscount {

    private String name;
    private long amount;

    public AppliedBasketDiscount(String name, long amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "AppliedBasketDiscount{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
