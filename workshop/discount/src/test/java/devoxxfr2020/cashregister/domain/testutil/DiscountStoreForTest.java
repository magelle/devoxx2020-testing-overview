package devoxxfr2020.cashregister.domain.testutil;

import devoxxfr2020.cashregister.domain.ApplicableBasketDiscount;
import devoxxfr2020.cashregister.domain.DiscountStore;
import devoxxfr2020.cashregister.domain.FruitDiscount;

import java.util.*;

public class DiscountStoreForTest implements DiscountStore {

    private Map<String, FruitDiscount> FruitDiscounts = new HashMap<>();
    private List<ApplicableBasketDiscount> basketDiscounts = new ArrayList<>();

    @Override
    public Optional<FruitDiscount> getFruitDiscount(String fruit) {
        return Optional.ofNullable(this.FruitDiscounts.get(fruit));
    }

    @Override
    public List<ApplicableBasketDiscount> getBasketDiscount() {
        return basketDiscounts;
    }

    public void storeFruitDiscount(String fruit, FruitDiscount discount) {
        this.FruitDiscounts.put(fruit, discount);
    }
    public void storeBasketDiscount(ApplicableBasketDiscount basketDiscount) {
        this.basketDiscounts.add(basketDiscount);
    }

}
