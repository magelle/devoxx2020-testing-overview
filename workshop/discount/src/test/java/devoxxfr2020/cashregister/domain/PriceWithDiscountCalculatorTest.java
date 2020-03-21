package devoxxfr2020.cashregister.domain;

import devoxxfr2020.cashregister.domain.testutil.DiscountStoreForTest;
import devoxxfr2020.cashregister.domain.testutil.FruitStoreForTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PriceWithDiscountCalculatorTest {

    public static final String BANANES = "Bananes";
    private FruitStoreForTest fruitStore  = new FruitStoreForTest();
    private DiscountStoreForTest discountStore = new DiscountStoreForTest();
    private PriceWithDiscountCalculator priceWithDiscountCalculator = new PriceWithDiscountCalculator(fruitStore, discountStore);

    @Test
    public void should_multiply_the_price_by_the_number_of_item() {
        fruitStore.storeFruit(BANANES, 150L);

        long price = priceWithDiscountCalculator.priceWithDiscount(BANANES, 2);

        assertThat(price).isEqualTo(300);
    }

    @Test
    public void should_apply_the_discount_when_there_is_one() {
        fruitStore.storeFruit(BANANES, 150L);
        discountStore.storeFruitDiscount(BANANES, new FruitDiscount(150, 3));

        long price = priceWithDiscountCalculator.priceWithDiscount(BANANES, 3);

        assertThat(price).isEqualTo(300);
    }

}