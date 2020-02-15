package devoxxfr2020.basket.domain;

import java.util.List;

public interface BasketDiscount {

    String getName();
    long getAmount(List<BasketItem> fruits);
    boolean isApplicable(List<BasketItem> fruits);
}
