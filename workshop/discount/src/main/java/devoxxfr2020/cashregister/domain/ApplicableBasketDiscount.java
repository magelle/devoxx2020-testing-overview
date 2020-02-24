package devoxxfr2020.cashregister.domain;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.List;

public interface ApplicableBasketDiscount {

    String getName();
    long getAmount(List<BasketItem> fruits);
    boolean isApplicable(List<BasketItem> fruits);
}
