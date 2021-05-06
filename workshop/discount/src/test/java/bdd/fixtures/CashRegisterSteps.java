package bdd.fixtures;

import devoxxfr2020.cashregister.domain.*;
import devoxxfr2020.cashregister.domain.discount.LocalizedAppleDiscountApplicable;
import devoxxfr2020.cashregister.domain.discount.MoreThan5FruitsDiscountApplicable;
import devoxxfr2020.cashregister.domain.testutil.DiscountStoreForTest;
import devoxxfr2020.cashregister.domain.testutil.FruitStoreForTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CashRegisterSteps {

    private final FruitStoreForTest fruitStore = new FruitStoreForTest();
    private final DiscountStoreForTest discountStore = new DiscountStoreForTest();
    private final FruitPriceComputer fruitPriceComputer = new FruitPriceComputer(fruitStore, discountStore);
    private final CashRegister cashRegister = new CashRegister(fruitPriceComputer, discountStore);

    private final List<BasketItem> basketItems = new ArrayList<>();
    private Receipt receipt = null;

    @Given("the price of a {word} is {int}")
    public void thePriceOfAPommesIs(String fruit, long amount) {
        fruitStore.storeFruit(fruit, amount);
    }

    @Given("there is a discount of {int} every {int} {word}")
    public void thereIsADiscountOfEveryCerises(int discount, int threshold, String fruit) {
        discountStore.storeFruitDiscount(fruit, new FruitDiscount(discount, threshold));
    }

    @Given("there is a discount of 200 for 5 fruits")
    public void iHaveADiscountOfForFruits() {
        discountStore.storeBasketDiscount(new MoreThan5FruitsDiscountApplicable());
    }

    @Given("there is a discount of 100 for 4 localized Pommes")
    public void iHaveADiscountOfForLocalizedPommes() {
        discountStore.storeBasketDiscount(new LocalizedAppleDiscountApplicable());
    }

    @Given("the basket is empty")
    public void theBasketIsEmpty() {
        basketItems.clear();
    }

    @Given("the basket contains {int} {word}")
    public void iAddAPommesInTheBasket(int number, String fruit) {
        basketItems.add(new BasketItem(fruit, number));
    }

    @When("the customer ask for the receipt")
    public void iAskForTheReceipt() {
        receipt = cashRegister.editReceipt(basketItems);
    }

    @Then("the total is {int}")
    public void theTotalIs(long amount) {
        assertThat(receipt.getTotal()).isEqualTo(amount);
    }

    @Then("the item list is empty")
    public void theItemListIsEmpty() {
        assertThat(receipt.getItems()).isEmpty();
    }

    @Then("the receipt display the price of {int} for {int} {word}")
    public void theReceiptDisplayThePriceOfForPommes(int price, int quantity, String fruit) {
        assertThat(receipt.getItems())
                .contains(new ReceiptItem(fruit, quantity, price));
    }
}
