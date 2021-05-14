package properties;

import devoxxfr2020.cashregister.domain.*;
import devoxxfr2020.cashregister.domain.discount.LocalizedAppleDiscountApplicable;
import devoxxfr2020.cashregister.domain.discount.MoreThan5FruitsDiscountApplicable;
import devoxxfr2020.cashregister.domain.testutil.DiscountStoreForTest;
import devoxxfr2020.cashregister.domain.testutil.FruitStoreForTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FailingTest {

    public static final String CERISES = "Cerises";
    public static final String BANANES = "Bananes";
    public static final String POMMES = "Pommes";

    @Test
    @Disabled
    void fail1() {
        FruitStoreForTest fruitStore = new FruitStoreForTest();
        fruitStore.storeFruit(BANANES, 10L);
        fruitStore.storeFruit(CERISES, 10L);
        fruitStore.storeFruit(POMMES, 36L);
        DiscountStoreForTest discountStore = new DiscountStoreForTest();
        discountStore.storeBasketDiscount(new LocalizedAppleDiscountApplicable());
        discountStore.storeBasketDiscount(new MoreThan5FruitsDiscountApplicable());
        List<BasketItem> basketItems = Arrays.asList(
                new BasketItem(CERISES, 5),
                new BasketItem(POMMES, 1),
                new BasketItem(BANANES, 1)
        );

        PriceWithDiscountCalculator priceWithDiscountCalculator = new PriceWithDiscountCalculator(fruitStore, discountStore);
        CashRegister cashRegister = new CashRegister(priceWithDiscountCalculator, discountStore);
        Receipt receipt = cashRegister.editReceipt(basketItems);
        assertThat(receipt.getTotal()).isGreaterThan(0L);
    }

    @Test
    @Disabled
    void fail2() {

        FruitStoreForTest fruitStore = new FruitStoreForTest();
        fruitStore.storeFruit(POMMES, 100L);

        DiscountStoreForTest discountStore = new DiscountStoreForTest();
        discountStore.storeFruitDiscount(POMMES, new FruitDiscount(100, 2));

        discountStore.storeBasketDiscount(new LocalizedAppleDiscountApplicable());
        discountStore.storeBasketDiscount(new MoreThan5FruitsDiscountApplicable());

        List<BasketItem> basketItems = Collections.singletonList(
                new BasketItem(POMMES, 6)
        );

        PriceWithDiscountCalculator priceWithDiscountCalculator = new PriceWithDiscountCalculator(fruitStore, discountStore);
        CashRegister cashRegister = new CashRegister(priceWithDiscountCalculator, discountStore);
        Receipt receipt = cashRegister.editReceipt(basketItems);
        assertThat(receipt.getTotal()).isGreaterThan(0L);
    }
}
