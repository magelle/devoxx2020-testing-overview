package devoxxfr2020.cashregister.domain.testutil;

import devoxxfr2020.cashregister.domain.ApplicableBasketDiscount;
import devoxxfr2020.cashregister.domain.BasketItem;

import java.util.List;

public class SimpleApplicableBasketDiscount implements ApplicableBasketDiscount {
    private final int amount;

    public SimpleApplicableBasketDiscount(int amount) {
        this.amount = amount;
    }

    @Override
    public String getName() {
        return "Simple discount for test";
    }

    public boolean isApplicable(List<BasketItem> fruits) {
        return true;
    }

    public long getAmount(List<BasketItem> fruits) {
        return amount;
    }
}
