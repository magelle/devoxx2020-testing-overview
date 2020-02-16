package devoxxfr2020.cashregister.domain.testutil;

import devoxxfr2020.cashregister.domain.BasketDiscount;
import devoxxfr2020.cashregister.domain.BasketItem;

import java.util.List;

public class NeverApplicableBasketDiscount implements BasketDiscount {
    @Override
    public String getName() {
        return "Simple discount for test";
    }

    public boolean isApplicable(List<BasketItem> fruits) {
        return false;
    }

    public long getAmount(List<BasketItem> fruits) {
        return Integer.MAX_VALUE;
    }
}
