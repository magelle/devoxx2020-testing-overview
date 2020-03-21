package devoxxfr2020.cashregister.domain;

import org.springframework.stereotype.Service;

@Service
public class PriceWithDiscountCalculator {

    private FruitStore fruitStore;
    private DiscountStore discountStore;

    public PriceWithDiscountCalculator(FruitStore fruitStore, DiscountStore discountStore) {
        this.fruitStore = fruitStore;
        this.discountStore = discountStore;
    }

    public long priceWithDiscount(String fruit, int number) {
        long discountAmount = discountStore.getFruitDiscount(fruit)
                .map(discount -> discount.getDiscountAmount(number))
                .orElse(0L);
        long total = number * fruitStore.getPrice(fruit);
        return total - discountAmount;
    }
}
