package devoxxfr2020.cashregister.service;

import devoxxfr2020.cashregister.testutil.NeverApplicableApplicableBasketDiscount;
import devoxxfr2020.cashregister.testutil.SimpleApplicableBasketDiscount;
import devoxxfr2020.cashregister.model.AppliedBasketDiscount;
import devoxxfr2020.cashregister.model.BasketItem;
import devoxxfr2020.cashregister.model.Receipt;
import devoxxfr2020.cashregister.model.ReceiptItem;
import devoxxfr2020.cashregister.repository.DiscountStore;
import devoxxfr2020.cashregister.repository.FruitStore;
import devoxxfr2020.cashregister.service.CashRegisterService;
import devoxxfr2020.cashregister.service.FruitPriceComputer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CashRegisterServiceTest {

    public static final String POMMES = "Pommes";
    public static final String BANANES = "Bananes";
    private FruitStore fruitStore = mock(FruitStore.class);
    private DiscountStore discountStore = mock(DiscountStore.class);
    private FruitPriceComputer fruitPriceComputer = new FruitPriceComputer(fruitStore, discountStore);
    private CashRegisterService cashRegisterService = new CashRegisterService(fruitPriceComputer, discountStore);

    @Test
    void should_return_an_empty_receipt_if_no_fruit() {
        List<BasketItem> fruits = emptyList();

        Receipt receipt = cashRegisterService
                .editReceipt(fruits);

        assertThat(receipt.getTotal()).isEqualTo(0);
    }

    @Test
    void should_return_the_price_of_one_fruit() {
        when(fruitStore.getPrice(POMMES)).thenReturn(100L);
        List<BasketItem> fruits = List.of(new BasketItem(POMMES, 1));

        Receipt receipt = cashRegisterService
                .editReceipt(fruits);

        assertThat(receipt.getTotal()).isEqualTo(100);
    }

    @Test
    void should_return_the_price_of__fruit() {
        when(fruitStore.getPrice(POMMES)).thenReturn(100L);
        List<BasketItem> fruits = List.of(new BasketItem(POMMES, 2));

        Receipt receipt = cashRegisterService
                .editReceipt(fruits);

        assertThat(receipt.getTotal()).isEqualTo(200);
    }

    @Test
    void should_return_the_price_of_different_fruits() {
        when(fruitStore.getPrice(POMMES)).thenReturn(100L);
        when(fruitStore.getPrice(BANANES)).thenReturn(150L);
        List<BasketItem> fruits = List.of(
                new BasketItem(POMMES, 1),
                new BasketItem(BANANES, 1)
        );

        Receipt receipt = cashRegisterService
                .editReceipt(fruits);

        assertThat(receipt.getTotal()).isEqualTo(250);
    }

    @Test
    void should_return_an_empty_receipt_item_list_if_no_fruit() {
        List<BasketItem> fruits = emptyList();

        Receipt receipt = cashRegisterService
                .editReceipt(fruits);

        assertThat(receipt.getItems()).isEmpty();
    }

    @Test
    void should_return_the_receipt_items_of_one_fruit() {
        when(fruitStore.getPrice(POMMES)).thenReturn(100L);
        List<BasketItem> fruits = List.of(new BasketItem(POMMES, 1));

        Receipt receipt = cashRegisterService
                .editReceipt(fruits);

        assertThat(receipt.getItems())
                .isEqualTo(List.of(new ReceiptItem(POMMES, 1, 100)));
    }

    @Test
    void should_return_sum_the_price_when_several_times_a_fruit() {
        when(fruitStore.getPrice(POMMES)).thenReturn(100L);
        List<BasketItem> fruits = List.of(new BasketItem(POMMES, 2));

        Receipt receipt = cashRegisterService
                .editReceipt(fruits);

        assertThat(receipt.getItems())
                .isEqualTo(List.of(new ReceiptItem(POMMES, 2, 200)));
    }

    @Test
    void should_return_receipt_items_when_several_fruits() {
        when(fruitStore.getPrice(POMMES)).thenReturn(100L);
        when(fruitStore.getPrice(BANANES)).thenReturn(150L);
        List<BasketItem> fruits = List.of(
                new BasketItem(POMMES, 1),
                new BasketItem(BANANES, 1)
        );

        Receipt receipt = cashRegisterService
                .editReceipt(fruits);

        assertThat(receipt.getItems())
                .isEqualTo(List.of(
                        new ReceiptItem(POMMES, 1, 100),
                        new ReceiptItem(BANANES, 1, 150))
                );
    }

    @Test
    void should_add_the_basket_discount_in_the_receipt() {
        SimpleApplicableBasketDiscount discount = new SimpleApplicableBasketDiscount(100);
        when(discountStore.getBasketDiscount()).thenReturn(List.of(discount));
        when(fruitStore.getPrice(BANANES)).thenReturn(150L);

        List<BasketItem> fruits = List.of(new BasketItem(BANANES, 1));
        Receipt receipt = cashRegisterService
                .editReceipt(fruits);

        assertThat(receipt.getDiscounts())
                .isEqualTo(List.of(new AppliedBasketDiscount("Simple discount for test", 100)));
    }

    @Test
    void should_apply_the_basket_discounts_to_the_total() {
        SimpleApplicableBasketDiscount discount = new SimpleApplicableBasketDiscount(100);
        when(discountStore.getBasketDiscount()).thenReturn(List.of(discount));
        when(fruitStore.getPrice(BANANES)).thenReturn(150L);


        List<BasketItem> fruits = List.of(new BasketItem(BANANES, 1));
        Receipt receipt = cashRegisterService
                .editReceipt(fruits);

        assertThat(receipt.getTotal()).isEqualTo(50);
    }

    @Test
    void should_not_apply_the_basket_discounts_whne_not_applcable() {
        NeverApplicableApplicableBasketDiscount discount = new NeverApplicableApplicableBasketDiscount();
        when(discountStore.getBasketDiscount()).thenReturn(List.of(discount));
        when(fruitStore.getPrice(BANANES)).thenReturn(150L);


        List<BasketItem> fruits = List.of(new BasketItem(BANANES, 1));
        Receipt receipt = cashRegisterService
                .editReceipt(fruits);

        assertThat(receipt.getTotal()).isEqualTo(150);
    }
}