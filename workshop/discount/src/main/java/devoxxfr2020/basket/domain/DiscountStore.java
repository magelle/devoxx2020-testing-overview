package devoxxfr2020.basket.domain;

import java.util.List;
import java.util.Optional;

public interface DiscountStore {
    Optional<FruitDiscount> getFruitDiscount(String fruit);
    List<BasketDiscount> getBasketDiscount();
}
