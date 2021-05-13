package devoxxfr2020.cashregister.domain;

import devoxxfr2020.cashregister.domain.testutil.DiscountStoreForTest;
import devoxxfr2020.cashregister.domain.testutil.FruitStoreForTest;
import devoxxfr2020.cashregister.domain.testutil.NeverApplicableApplicableBasketDiscount;
import devoxxfr2020.cashregister.domain.testutil.SimpleApplicableBasketDiscount;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class CashRegisterTest {

    public static final String POMMES = "Pommes";
    public static final String BANANES = "Bananes";
    private final FruitStoreForTest fruitStore = new FruitStoreForTest();
    private final DiscountStoreForTest discountStore = new DiscountStoreForTest();
    private final FruitPriceComputer fruitPriceComputer = new FruitPriceComputer(fruitStore, discountStore);
    private final CashRegister cashRegister = new CashRegister(fruitPriceComputer, discountStore);

    @Before
    void before() {
        fruitStore.clear();
    }

    @Test
    void should_return_an_empty_receipt_if_no_fruit() {
        List<BasketItem> fruits = emptyList();

        Receipt receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getItems()).isEmpty();
        assertThat(receipt.getTotal()).isEqualTo(0);
        assertThat(receipt.getDiscounts()).isEmpty();
    }

    @Test // FIXME : duplicate
    void should_return_the_price_of_one_fruit() {
        fruitStore.storeFruit(POMMES, 100L);
        List<BasketItem> fruits = List.of(new BasketItem(POMMES, 1));

        Receipt receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getTotal()).isEqualTo(100);
    }

    @Test
    void should_return_the_price_of_fruit() {
        fruitStore.storeFruit(POMMES, 100L);
        List<BasketItem> fruits = List.of(new BasketItem(POMMES, 2));

        Receipt receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getTotal()).isEqualTo(200);
    }

    @Test
    void should_return_the_price_of_different_fruits() {
        fruitStore.storeFruit(POMMES, 100L);
        fruitStore.storeFruit(BANANES, 150L);
        List<BasketItem> fruits = List.of(
                new BasketItem(POMMES, 1),
                new BasketItem(BANANES, 1)
        );

        Receipt receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getTotal()).isEqualTo(250);
    }

    @Test // FIXME : duplicate
    void should_return_an_empty_receipt_item_list_if_no_fruit() {
        List<BasketItem> fruits = emptyList();

        Receipt receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getItems()).isEmpty();
    }

    @Test
    void should_return_the_receipt_items_of_one_fruit() {
        fruitStore.storeFruit(POMMES, 100L);
        List<BasketItem> fruits = List.of(new BasketItem(POMMES, 1));

        Receipt receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getItems())
                .isEqualTo(List.of(new ReceiptItem(POMMES, 1, 100)));
    }

    @Test // FIXME : duplicate
    void should_return_sum_the_price_when_several_times_a_fruit() {
        fruitStore.storeFruit(POMMES, 100L);
        List<BasketItem> fruits = List.of(new BasketItem(POMMES, 2));

        Receipt receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getItems())
                .isEqualTo(List.of(new ReceiptItem(POMMES, 2, 200)));
    }

    @Test
    void should_return_receipt_items_when_several_fruits() {
        fruitStore.storeFruit(POMMES, 100L);
        fruitStore.storeFruit(BANANES, 150L);
        List<BasketItem> fruits = List.of(
                new BasketItem(POMMES, 1),
                new BasketItem(BANANES, 1)
        );

        Receipt receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getItems())
                .isEqualTo(List.of(
                        new ReceiptItem(POMMES, 1, 100),
                        new ReceiptItem(BANANES, 1, 150))
                );
    }

    @Test
    void should_add_the_basket_discount_in_the_receipt() {
        SimpleApplicableBasketDiscount discount = new SimpleApplicableBasketDiscount(100);
        discountStore.storeBasketDiscount(discount);
        fruitStore.storeFruit(BANANES, 150L);

        List<BasketItem> fruits = List.of(new BasketItem(BANANES, 1));
        Receipt receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getDiscounts())
                .isEqualTo(List.of(new AppliedBasketDiscount("Simple discount for test", 100)));
    }

    @Test
    void should_apply_the_basket_discounts_to_the_total() {
        discountStore.storeBasketDiscount(new SimpleApplicableBasketDiscount(100));
        fruitStore.storeFruit(BANANES, 150L);

        List<BasketItem> fruits = List.of(new BasketItem(BANANES, 1));
        Receipt receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getTotal()).isEqualTo(50);
    }

    @Test
    void should_not_apply_the_basket_discounts_whne_not_applcable() {
        discountStore.storeBasketDiscount(new NeverApplicableApplicableBasketDiscount());
        fruitStore.storeFruit(BANANES, 150L);

        List<BasketItem> fruits = List.of(new BasketItem(BANANES, 1));
        Receipt receipt = cashRegister.editReceipt(fruits);

        assertThat(receipt.getTotal()).isEqualTo(150);
    }
}