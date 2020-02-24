package devoxxfr2020.cashregister.domain;

import java.util.List;
import java.util.Optional;

public interface DiscountStore {
    Optional<FruitDiscount> getFruitDiscount(String fruit);
    List<ApplicableBasketDiscount> getBasketDiscount();
}
