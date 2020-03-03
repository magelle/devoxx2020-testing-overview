package devoxxfr2020.cashregister.model;

import java.util.List;

public interface ApplicableBasketDiscount {

    String getName();
    long getAmount(List<BasketItem> fruits);
    boolean isApplicable(List<BasketItem> fruits);
}
