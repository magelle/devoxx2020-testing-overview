package devoxxfr2020.cashregister.domain.discount;

import devoxxfr2020.cashregister.domain.ApplicableBasketDiscount;
import devoxxfr2020.cashregister.domain.BasketItem;

import java.util.List;

public class MoreThan5FruitsDiscountApplicable implements ApplicableBasketDiscount {


    @Override
    public String getName() {
        return "More than 5 fruits";
    }

    @Override
    public long getAmount(List<BasketItem> fruits) {
        return (fruits.stream()
                .mapToLong(BasketItem::getQuantity)
                .sum() / 5) * 200;
        // faulty value return 200;
    }

    @Override
    public boolean isApplicable(List<BasketItem> fruits) {
        return fruits.stream()
                .mapToLong(BasketItem::getQuantity)
                .sum() >= 5;
    }
}
