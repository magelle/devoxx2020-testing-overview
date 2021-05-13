package devoxxfr2020.cashregister.domain;

import java.util.List;

public interface ApplicableBasketDiscount {

    // FIXME : name useful ?
    String getName();

    long getAmount(List<BasketItem> fruits);

    boolean isApplicable(List<BasketItem> fruits);
}
