package devoxxfr2020.cashregister.model.discount;

import devoxxfr2020.cashregister.model.ApplicableBasketDiscount;
import devoxxfr2020.cashregister.model.BasketItem;

import java.util.List;

public class LocalizedAppleDiscountApplicable implements ApplicableBasketDiscount {

    public static final List<String> LOCALIZED_POMMES = List.of("Pommes", "Apples", "Mele");

    @Override
    public String getName() {
        return "More than 4 Apples";
    }

    @Override
    public long getAmount(List<BasketItem> fruits) {
        return 100;
    }

    @Override
    public boolean isApplicable(List<BasketItem> fruits) {
        return fruits.stream()
                .filter(item -> LOCALIZED_POMMES.contains(item.getName()))
                .mapToLong(BasketItem::getQuantity)
                .sum() >= 4;

    }
}
