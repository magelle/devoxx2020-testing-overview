package properties;

import devoxxfr2020.cashregister.domain.*;
import devoxxfr2020.cashregister.domain.discount.LocalizedAppleDiscountApplicable;
import devoxxfr2020.cashregister.domain.discount.MoreThan5FruitsDiscountApplicable;
import devoxxfr2020.cashregister.domain.testutil.DiscountStoreForTest;
import devoxxfr2020.cashregister.domain.testutil.FruitStoreForTest;
import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JqwikCashRegisterPropertyTest {

    public static final String CERISES = "Cerises";
    public static final String BANANES = "Bananes";
    public static final String POMMES = "Pommes";

    private final FruitStoreForTest fruitStore = new FruitStoreForTest();
    private final DiscountStoreForTest discountStore = new DiscountStoreForTest();
    private final PriceWithDiscountCalculator priceWithDiscountCalculator = new PriceWithDiscountCalculator(fruitStore, discountStore);
    private final CashRegister cashRegister = new CashRegister(priceWithDiscountCalculator, discountStore);

    {
        this.fruitStore.storeFruit(CERISES, 75L);
        this.fruitStore.storeFruit(BANANES, 150L);
        this.fruitStore.storeFruit(POMMES, 50L);

        this.discountStore.storeFruitDiscount(CERISES, new FruitDiscount(20L, 2));
        this.discountStore.storeFruitDiscount(BANANES, new FruitDiscount(150L, 2));
        this.discountStore.storeFruitDiscount(POMMES, new FruitDiscount(50L, 4));

        this.discountStore.storeBasketDiscount(new LocalizedAppleDiscountApplicable());
        this.discountStore.storeBasketDiscount(new MoreThan5FruitsDiscountApplicable());
    }

    @Provide("basket item")
    Arbitrary<BasketItem> randomBasketItem() {
        return Arbitraries.of(CERISES, BANANES, POMMES)
                .flatMap((String fruit) -> Arbitraries.integers().between(1, 10).map(quantity -> Tuple.of(fruit, quantity)))
                .map(tuple -> new BasketItem(tuple.get1(), tuple.get2()));
    }

    @Property
    public void shouldNeverHaveTotalReceiptInferiorTo0(@ForAll  @Size(min = 1, max = 3) @UniqueElements List<@From("basket item") BasketItem> basketItems) {
        Receipt receipt = this.cashRegister.editReceipt(basketItems);
        assertThat(receipt.getTotal()).isGreaterThan(0);

    }
}
