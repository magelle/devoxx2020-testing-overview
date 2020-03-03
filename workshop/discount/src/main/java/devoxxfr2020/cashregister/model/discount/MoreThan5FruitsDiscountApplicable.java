package devoxxfr2020.cashregister.model.discount;

import devoxxfr2020.cashregister.model.ApplicableBasketDiscount;
import devoxxfr2020.cashregister.model.BasketItem;

import java.util.List;

public class MoreThan5FruitsDiscountApplicable implements ApplicableBasketDiscount {


    @Override
    public String getName() {
        return "More than 5 fruits";
    }

    @Override
    public long getAmount(List<BasketItem> fruits) {
        return (fruits.stream()
                .mapToLong(item -> item.getQuantity())
                .sum() / 5) * 200;
    }

    @Override
    public boolean isApplicable(List<BasketItem> fruits) {
        return fruits.stream()
                .mapToLong(item -> item.getQuantity())
                .sum() >= 5;
    }
}
