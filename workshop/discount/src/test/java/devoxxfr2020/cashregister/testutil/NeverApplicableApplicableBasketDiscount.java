package devoxxfr2020.cashregister.testutil;


import devoxxfr2020.cashregister.model.ApplicableBasketDiscount;
import devoxxfr2020.cashregister.model.BasketItem;

import java.util.List;

public class NeverApplicableApplicableBasketDiscount implements ApplicableBasketDiscount {
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
