package devoxxfr2020.cashregister.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FruitDiscountTest {

    @Test
    public void should_return_0_when_threshold_not_reached() {
        FruitDiscount fruitDiscount = new FruitDiscount(100, 3);
        long discount = fruitDiscount.getDiscountAmount(2);
        assertThat(discount).isEqualTo(0);
    }

    @Test
    public void should_return_the_discount_when_threshold_is_reached() {
        FruitDiscount fruitDiscount = new FruitDiscount(100, 3);
        long discount = fruitDiscount.getDiscountAmount(3);
        assertThat(discount).isEqualTo(100);
    }

    @Test
    public void should_return_two_times_the_discount_when_threshold_is_outdo_by_two() {
        FruitDiscount fruitDiscount = new FruitDiscount(100, 3);
        long discount = fruitDiscount.getDiscountAmount(6);
        assertThat(discount).isEqualTo(200);
    }

}