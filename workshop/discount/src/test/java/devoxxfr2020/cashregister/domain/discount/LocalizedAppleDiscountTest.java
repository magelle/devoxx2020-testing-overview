package devoxxfr2020.cashregister.domain.discount;

import devoxxfr2020.cashregister.domain.BasketItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LocalizedAppleDiscountTest {

    private final LocalizedAppleDiscountApplicable localizedAppleDiscount = new LocalizedAppleDiscountApplicable();

    @Test
    public void should_be_applicable_when_more_than_4_apples_in_different_languages() {
        List<BasketItem> fruits = List.of(
                new BasketItem("Apples", 1),
                new BasketItem("Mele", 1),
                new BasketItem("Pommes", 2)
        );
        boolean applicable = localizedAppleDiscount.isApplicable(fruits);
        assertThat(applicable).isTrue();
    }

    @Test
    public void should_be_applicable_when_more_than_4_apples_in_the_same_language() {
        assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Apples", 4)))).isTrue();
        assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Mele", 4)))).isTrue();
        assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Pommes", 4)))).isTrue();
    }

    @Test
    public void should_be_applicable_when_less_than_4_apples_in_different_languages() {
        List<BasketItem> fruits = List.of(
                new BasketItem("Apples", 1),
                new BasketItem("Mele", 1),
                new BasketItem("Pommes", 1)
        );
        boolean applicable = localizedAppleDiscount.isApplicable(fruits);
        assertThat(applicable).isFalse();
    }

    @Test
    public void should_be_applicable_when_less_than_4_apples_in_the_same_language() {
        assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Apples", 3)))).isFalse();
        assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Mele", 3)))).isFalse();
        assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Pommes", 3)))).isFalse();
    }

}