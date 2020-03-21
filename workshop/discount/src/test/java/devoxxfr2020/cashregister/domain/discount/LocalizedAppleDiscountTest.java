package devoxxfr2020.cashregister.domain.discount;

import devoxxfr2020.cashregister.domain.BasketItem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LocalizedAppleDiscountTest {

    private LocalizedAppleDiscountApplicable localizedAppleDiscount = new LocalizedAppleDiscountApplicable();

    @Test
    public void should_be_applicable_when_more_than_4_apples_in_different_languages() {
        List<BasketItem> fruits = List.of(new BasketItem("Apples", 1), new BasketItem("Mele", 1), new BasketItem("Pommes", 2));
        boolean applicable = localizedAppleDiscount.isApplicable(fruits);
        assertThat(applicable).isTrue();
    }

    @Test // parameterized tests ?
    public void should_be_applicable_when_more_than_4_apples_in_the_same_language() {
        discountShouldBeApplicableFor(new BasketItem("Apples", 4));
        discountShouldBeApplicableFor(new BasketItem("Mele", 4));
        discountShouldBeApplicableFor(new BasketItem("Pommes", 4));
    }

    @Test
    public void should_not_be_applicable_when_less_than_4_apples_in_different_languages() {
        List<BasketItem> fruits = List.of(new BasketItem("Apples", 1), new BasketItem("Mele", 1), new BasketItem("Pommes", 1));
        assertThat(localizedAppleDiscount.isApplicable(fruits)).isFalse();
    }

    @Test // parameterized tests ?
    public void should_not_be_applicable_when_less_than_4_apples_in_the_same_language() {
        discountShouldNotBeApplicableFor(new BasketItem("Apples", 3));
        discountShouldNotBeApplicableFor(new BasketItem("Mele", 3));
        discountShouldNotBeApplicableFor(new BasketItem("Pommes", 3));
    }

    private void discountShouldNotBeApplicableFor(BasketItem pommes) {
        assertThat(localizedAppleDiscount.isApplicable(List.of(pommes))).isFalse();
    }

    private void discountShouldBeApplicableFor(BasketItem apples) {
        assertThat(localizedAppleDiscount.isApplicable(List.of(apples))).isTrue();
    }

}