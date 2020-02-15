package devoxxfr2020.basket.domain.discount;

import devoxxfr2020.basket.domain.BasketDiscount;
import devoxxfr2020.basket.domain.BasketItem;

import java.util.List;

public class MoreThan5FruitsDiscount implements BasketDiscount {


    @Override
    public String getName() {
        return "More than 5 fruits";
    }

    @Override
    public long getAmount(List<BasketItem> fruits) {
        return 200;
    }

    @Override
    public boolean isApplicable(List<BasketItem> fruits) {
        return fruits.stream()
                .mapToLong(item -> item.getNumber())
                .sum() >= 5;
    }
}
