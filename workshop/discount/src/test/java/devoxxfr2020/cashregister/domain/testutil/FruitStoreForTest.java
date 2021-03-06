package devoxxfr2020.cashregister.domain.testutil;

import devoxxfr2020.cashregister.domain.FruitStore;

import java.util.HashMap;
import java.util.Map;

public class FruitStoreForTest implements FruitStore {

    private Map<String, Long> prices = new HashMap<>();

    @Override
    public long getPrice(String fruitName) {
        return prices.get(fruitName);
    }

    public void storeFruit(String fruit, Long price) {
        this.prices.put(fruit, price);
    }

    @Override
    public String toString() {
        return "FruitStoreForTest{" +
                "prices=" + prices +
                '}';
    }
}
