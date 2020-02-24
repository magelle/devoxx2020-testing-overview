package devoxxfr2020.cashregister.domain.discount;

import devoxxfr2020.cashregister.domain.BasketItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MoreThan5FruitsDiscountTest {

    private MoreThan5FruitsDiscountApplicable moreThan5FruitsDiscount = new MoreThan5FruitsDiscountApplicable();

    @Test
    public void should_be_applicable_when_there_is_more_than_5_fruits() {
        List<BasketItem> fruits = List.of(
                new BasketItem("Bananes", 2),
                new BasketItem("Cerises", 3));

        boolean applicable = moreThan5FruitsDiscount.isApplicable(fruits);

        assertThat(applicable).isTrue();
    }

    @Test
    public void should_be_applicable_when_there_is_less_than_5_fruits() {
        List<BasketItem> fruits = List.of(
                new BasketItem("Bananes", 2),
                new BasketItem("Cerises", 2));

        boolean applicable = moreThan5FruitsDiscount.isApplicable(fruits);

        assertThat(applicable).isFalse();
    }

}