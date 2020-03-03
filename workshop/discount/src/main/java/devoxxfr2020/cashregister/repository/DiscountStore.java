package devoxxfr2020.cashregister.repository;

import devoxxfr2020.cashregister.model.ApplicableBasketDiscount;
import devoxxfr2020.cashregister.model.FruitDiscount;
import devoxxfr2020.cashregister.model.discount.LocalizedAppleDiscountApplicable;
import devoxxfr2020.cashregister.model.discount.MoreThan5FruitsDiscountApplicable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class DiscountStore {

    private Map<String, FruitDiscount> discounts;

    public DiscountStore() {
        this.discounts = new TreeMap<>();
        this.discounts.put("Cerises", new FruitDiscount(20, 2));
        this.discounts.put("Bananes", new FruitDiscount(150, 2));
        this.discounts.put("Apples", new FruitDiscount(100, 3));
        this.discounts.put("Mele", new FruitDiscount(100, 2));
    }

    public Optional<FruitDiscount> getFruitDiscount(String fruit) {
        return Optional.ofNullable(discounts.get(fruit));
    }

    public List<ApplicableBasketDiscount> getBasketDiscount() {
        return List.of(
                new LocalizedAppleDiscountApplicable(),
                new MoreThan5FruitsDiscountApplicable()
        );
    }
}
