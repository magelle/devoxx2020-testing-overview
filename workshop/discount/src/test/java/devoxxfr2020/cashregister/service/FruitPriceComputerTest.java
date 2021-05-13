package devoxxfr2020.cashregister.service;

import devoxxfr2020.cashregister.model.FruitDiscount;
import devoxxfr2020.cashregister.repository.DiscountStore;
import devoxxfr2020.cashregister.repository.FruitStore;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FruitPriceComputerTest {

    public static final String BANANES = "Bananes";
    private final FruitStore fruitStore = mock(FruitStore.class);
    private final DiscountStore discountStore = mock(DiscountStore.class);
    private final FruitPriceComputer fruitPriceComputer = new FruitPriceComputer(fruitStore, discountStore);

    @Test
    public void should_multiply_the_price_by_the_number_of_item() {
        when(fruitStore.getPrice(BANANES)).thenReturn(150L);

        long price = fruitPriceComputer.getPriceWithDiscount(BANANES, 2);

        assertThat(price).isEqualTo(300);
    }

    @Test
    public void should_apply_the_discount_when_there_is_one() {
        when(fruitStore.getPrice(BANANES)).thenReturn(150L);
        when(discountStore.getFruitDiscount(BANANES)).thenReturn(Optional.of(new FruitDiscount(150, 3)));

        long price = fruitPriceComputer.getPriceWithDiscount(BANANES, 3);

        assertThat(price).isEqualTo(300);
    }

}