package devoxxfr2020.cashregister.application.provider;

import devoxxfr2020.cashregister.domain.ApplicableBasketDiscount;
import devoxxfr2020.cashregister.domain.DiscountStore;
import devoxxfr2020.cashregister.domain.FruitDiscount;
import devoxxfr2020.cashregister.domain.discount.LocalizedAppleDiscountApplicable;
import devoxxfr2020.cashregister.domain.discount.MoreThan5FruitsDiscountApplicable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class StaticDiscountStore implements DiscountStore {

    private Map<String, FruitDiscount> discounts;

    public StaticDiscountStore() {
        this.discounts = new TreeMap<>();
        this.discounts.put("Cerises", new FruitDiscount(20, 2));
        this.discounts.put("Bananes", new FruitDiscount(150, 2));
        this.discounts.put("Apples", new FruitDiscount(100, 3));
        this.discounts.put("Mele", new FruitDiscount(100, 2));
    }

    @Override
    public Optional<FruitDiscount> getFruitDiscount(String fruit) {
        return Optional.ofNullable(discounts.get(fruit));
    }

    @Override
    public List<ApplicableBasketDiscount> getBasketDiscount() {
        return List.of(
                new LocalizedAppleDiscountApplicable(),
                new MoreThan5FruitsDiscountApplicable()
        );
    }
}
