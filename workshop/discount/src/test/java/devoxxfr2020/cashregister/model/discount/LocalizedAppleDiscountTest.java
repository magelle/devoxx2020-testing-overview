package devoxxfr2020.cashregister.model.discount;

import devoxxfr2020.cashregister.model.BasketItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class LocalizedAppleDiscountTest {

    private LocalizedAppleDiscountApplicable localizedAppleDiscount = new LocalizedAppleDiscountApplicable();

    @Test
    public void should_be_applicable_when_more_than_4_apples_in_different_languages() {
        List<BasketItem> fruits = List.of(new BasketItem("Apples", 1), new BasketItem("Mele", 1), new BasketItem("Pommes", 2));
        boolean applicable = localizedAppleDiscount.isApplicable(fruits);
        Assertions.assertThat(applicable).isTrue();
    }

    @Test
    public void should_be_applicable_when_more_than_4_apples_in_the_same_language() {
        Assertions.assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Apples", 4)))).isTrue();
        Assertions.assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Mele", 4)))).isTrue();
        Assertions.assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Pommes", 4)))).isTrue();
    }

    @Test
    public void should_be_applicable_when_less_than_4_apples_in_different_languages() {
        List<BasketItem> fruits = List.of(new BasketItem("Apples", 1), new BasketItem("Mele", 1), new BasketItem("Pommes", 1));
        boolean applicable = localizedAppleDiscount.isApplicable(fruits);
        Assertions.assertThat(applicable).isFalse();
    }

    @Test
    public void should_be_applicable_when_less_than_4_apples_in_the_same_language() {
        Assertions.assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Apples", 3)))).isFalse();
        Assertions.assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Mele", 3)))).isFalse();
        Assertions.assertThat(localizedAppleDiscount.isApplicable(List.of(new BasketItem("Pommes", 3)))).isFalse();
    }

}