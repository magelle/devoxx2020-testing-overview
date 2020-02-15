package bdd.fixtures;

import devoxxfr2020.cashregister.domain.*;
import devoxxfr2020.cashregister.domain.discount.LocalizedAppleDiscount;
import devoxxfr2020.cashregister.domain.discount.MoreThan5FruitsDiscount;
import devoxxfr2020.cashregister.domain.util.DiscountStoreForTest;
import devoxxfr2020.cashregister.domain.util.FruitStoreForTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CashRegisterSteps {

    private FruitStoreForTest fruitStore = new FruitStoreForTest();
    private DiscountStoreForTest discountStore = new DiscountStoreForTest();
    private FruitPriceComputer fruitPriceComputer = new FruitPriceComputer(fruitStore, discountStore);
    private CashRegister cashRegister = new CashRegister(fruitPriceComputer, discountStore);

    private List<BasketItem> basketItems = new ArrayList<>();
    private Receipt receipt = null;

    @Given("the price of a {word} is {int}")
    public void thePriceOfAPommesIs(String fruit, long amount) {
        fruitStore.storeFruit(fruit, amount);
    }

    @Given("There is a discount of {int} every {int} {word}")
    public void thereIsADiscountOfEveryCerises(int discount, int threshold, String fruit) {
        discountStore.storeFruitDiscount(fruit, new FruitDiscount(discount, threshold));
    }

    @Given("I have a discount of 200 for 5 fruits")
    public void iHaveADiscountOfForFruits() {
        discountStore.storeBasketDiscount(new MoreThan5FruitsDiscount());
    }

    @Given("I have a discount of 100 for 4 localized Pommes")
    public void iHaveADiscountOfForLocalizedPommes() {
        discountStore.storeBasketDiscount(new LocalizedAppleDiscount());
    }

    @Given("the basket is empty")
    public void theBasketIsEmpty() {
        basketItems.clear();
    }

    @When("I add {int} {word} in the basket")
    public void iAddAPommesInTheBasket(int number, String fruit) {
        basketItems.add(new BasketItem(fruit, number));
    }

    @When("I ask for the receipt")
    public void iAskForTheReceipt() {
        receipt = cashRegister.editReceipt(basketItems);
    }

    @Then("the total is {int}")
    public void theTotalIs(long amount) {
        assertThat(receipt.getTotal()).isEqualTo(amount);
    }

    @And("the item list is empty")
    public void theItemListIsEmpty() {
        assertThat(receipt.getFruitPrices()).isEmpty();
    }

    @And("the receipt display the price of {int} for {int} {word}")
    public void theReceiptDisplayThePriceOfForPommes(int price, int quantity, String fruit) {
        assertThat(receipt.getFruitPrices())
                .contains(new ReceiptItem(fruit, quantity, price));
    }
}
