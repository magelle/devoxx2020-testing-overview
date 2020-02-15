package devoxxfr2020.basket.domain.util;

import devoxxfr2020.basket.domain.BasketDiscount;
import devoxxfr2020.basket.domain.DiscountStore;
import devoxxfr2020.basket.domain.FruitDiscount;

import java.util.*;

public class DiscountStoreForTest implements DiscountStore {

    private Map<String, FruitDiscount> FruitDiscounts = new HashMap<>();
    private List<BasketDiscount> basketDiscounts = new ArrayList<>();

    @Override
    public Optional<FruitDiscount> getFruitDiscount(String fruit) {
        return Optional.ofNullable(this.FruitDiscounts.get(fruit));
    }

    @Override
    public List<BasketDiscount> getBasketDiscount() {
        return basketDiscounts;
    }

    public void storeFruitDiscount(String fruit, FruitDiscount discount) {
        this.FruitDiscounts.put(fruit, discount);
    }
    public void storeBasketDiscount(BasketDiscount basketDiscount) {
        this.basketDiscounts.add(basketDiscount);
    }

}
