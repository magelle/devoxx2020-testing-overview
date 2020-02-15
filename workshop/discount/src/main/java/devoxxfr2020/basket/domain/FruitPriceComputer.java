package devoxxfr2020.basket.domain;

import org.springframework.stereotype.Service;

@Service
public class FruitPriceComputer {

    private FruitStore fruitStore;
    private DiscountStore discountStore;

    public FruitPriceComputer(FruitStore fruitStore, DiscountStore discountStore) {
        this.fruitStore = fruitStore;
        this.discountStore = discountStore;
    }

    public long getPriceWithDiscount(String fruit, int number) {
        long discountAmount = discountStore.getFruitDiscount(fruit)
                .map(discount -> discount.getDiscountAmount(number))
                .orElse(0L);
        long total = number * fruitStore.getPrice(fruit);
        return total - discountAmount;
    }
}
