package devoxxfr2020.cashregister.domain.discount;

import devoxxfr2020.cashregister.domain.BasketDiscount;
import devoxxfr2020.cashregister.domain.BasketItem;

import java.util.List;

public class LocalizedAppleDiscount implements BasketDiscount {

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
                .filter(item -> LOCALIZED_POMMES.contains(item.getFruit()))
                .mapToLong(BasketItem::getNumber)
                .sum() >= 4;

    }
}
