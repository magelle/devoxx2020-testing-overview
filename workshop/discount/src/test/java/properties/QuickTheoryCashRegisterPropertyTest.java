package properties;

import devoxxfr2020.cashregister.domain.*;
import devoxxfr2020.cashregister.domain.discount.LocalizedAppleDiscountApplicable;
import devoxxfr2020.cashregister.domain.discount.MoreThan5FruitsDiscountApplicable;
import devoxxfr2020.cashregister.domain.testutil.DiscountStoreForTest;
import devoxxfr2020.cashregister.domain.testutil.FruitStoreForTest;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.quicktheories.api.Pair;
import org.quicktheories.core.Gen;
import org.quicktheories.core.RandomnessSource;

import java.util.ArrayList;
import java.util.List;

import static org.quicktheories.QuickTheory.qt;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.longs;
import static properties.QuickTheoryCashRegisterPropertyTest.BasketItemsGenerator.basketItems;

public class QuickTheoryCashRegisterPropertyTest {


    public static final String CERISES = "Cerises";
    public static final String BANANES = "Bananes";
    public static final String APPLES = "Apples";
    public static final String MELE = "Mele";
    public static final String POMMES = "Pommes";

    public static final List<String> ALL_FRUITS = List.of(CERISES, POMMES, BANANES, APPLES, MELE);

    @Test
    public void shouldNeverHaveTotalReceiptInferiorTo0() {

        //System.setProperty("QT_EXAMPLES", "5000");
        qt()
                .forAll(StoreGenerator.stores(), basketItems())
                .check((pair, basketItems) -> {
                    PriceWithDiscountCalculator priceWithDiscountCalculator = new PriceWithDiscountCalculator(pair._1, pair._2);
                    CashRegister cashRegister = new CashRegister(priceWithDiscountCalculator, pair._2);
                    Receipt receipt = cashRegister.editReceipt(basketItems);
                    return receipt.getTotal() >= 0;
                });
    }

    static class BasketItemsGenerator implements Gen<List<BasketItem>> {

        @Override
        public List<BasketItem> generate(RandomnessSource in) {
            List<BasketItem> basketItems = new ArrayList<>();
            ALL_FRUITS.forEach(fruit -> {
                int quantity = integers().between(0, 5).generate(in);
                if (quantity > 0) {
                    basketItems.add(new BasketItem(fruit, quantity));
                }
            });
            return basketItems;
        }

        static BasketItemsGenerator basketItems() {
            return new BasketItemsGenerator();
        }
    }

    static class StoreGenerator implements Gen<Pair<FruitStoreForTest, DiscountStoreForTest>> {

        @Override
        public Pair<FruitStoreForTest, DiscountStoreForTest> generate(RandomnessSource in) {
            FruitStoreForTest fruitStore = genFruitStoreForTest(in);
            DiscountStoreForTest discountStore = genDiscountStoreForTest(in, fruitStore);
            return Pair.of(fruitStore, discountStore);
        }

        @NotNull
        private FruitStoreForTest genFruitStoreForTest(RandomnessSource in) {
            FruitStoreForTest fruitStore = new FruitStoreForTest();
            ALL_FRUITS.forEach(fruit -> fruitStore.storeFruit(fruit, longs().between(10, 300).generate(in)));
            return fruitStore;
        }

        @NotNull
        private DiscountStoreForTest genDiscountStoreForTest(RandomnessSource in, FruitStoreForTest fruitStoreForTest) {

            DiscountStoreForTest discountStore = new DiscountStoreForTest();
            ALL_FRUITS.forEach(fruit -> {
                integers().between(2, 5).toOptionals(20).generate(in).ifPresent(threshold -> {
                    long discount = longs().between(10, fruitStoreForTest.getPrice(fruit)).generate(in);
                    discountStore.storeFruitDiscount(fruit, new FruitDiscount(discount, threshold));
                });
            });
            discountStore.storeBasketDiscount(new LocalizedAppleDiscountApplicable());
            discountStore.storeBasketDiscount(new MoreThan5FruitsDiscountApplicable());
            return discountStore;
        }

        static StoreGenerator stores() {
            return new StoreGenerator();
        }
    }

}
