package devoxxfr2020.cashregister.domain.testutil;

import devoxxfr2020.cashregister.domain.BasketDiscount;
import devoxxfr2020.cashregister.domain.BasketItem;

import java.util.List;

public class SimpleBasketDiscount implements BasketDiscount {
    private int amount;

    public SimpleBasketDiscount(int amount) {
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
