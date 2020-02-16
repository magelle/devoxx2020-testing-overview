package properties;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;
import devoxxfr2020.cashregister.domain.BasketItem;

import java.util.List;

public class BasketItems extends Generator<BasketItem> {
    public BasketItems() {
        super(BasketItem.class);
    }

    @Override public BasketItem generate(
            SourceOfRandomness r,
            GenerationStatus status) {
        String fruit = r.choose(List.of(CashRegisterPropertyTest.CERISES, CashRegisterPropertyTest.POMMES, CashRegisterPropertyTest.BANANES, CashRegisterPropertyTest.APPLES, CashRegisterPropertyTest.MELE));
        return new BasketItem(fruit, r.nextInt(0, Integer.MAX_VALUE));
    }
}
