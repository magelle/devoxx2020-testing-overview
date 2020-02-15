package devoxxfr2020.basket.domain;

import devoxxfr2020.basket.domain.util.DiscountStoreForTest;
import devoxxfr2020.basket.domain.util.FruitStoreForTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FruitPriceComputerTest {

    public static final String BANANES = "Bananes";
    private FruitStoreForTest fruitStore  = new FruitStoreForTest();
    private DiscountStoreForTest discountStore = new DiscountStoreForTest();
    private FruitPriceComputer fruitPriceComputer = new FruitPriceComputer(fruitStore, discountStore);

    @Test
    public void should_multiply_the_price_by_the_number_of_item() {
        fruitStore.storeFruit(BANANES, 150L);

        long price = fruitPriceComputer.getPriceWithDiscount(BANANES, 2);

        assertThat(price).isEqualTo(300);
    }

    @Test
    public void should_apply_the_discount_when_there_is_one() {
        fruitStore.storeFruit(BANANES, 150L);
        discountStore.storeFruitDiscount(BANANES, new FruitDiscount(150, 3));

        long price = fruitPriceComputer.getPriceWithDiscount(BANANES, 3);

        assertThat(price).isEqualTo(300);
    }

}