package properties;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import devoxxfr2020.cashregister.domain.*;
import devoxxfr2020.cashregister.domain.discount.LocalizedAppleDiscountApplicable;
import devoxxfr2020.cashregister.domain.discount.MoreThan5FruitsDiscountApplicable;
import devoxxfr2020.cashregister.domain.testutil.DiscountStoreForTest;
import devoxxfr2020.cashregister.domain.testutil.FruitStoreForTest;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assume.assumeThat;


@RunWith(JUnitQuickcheck.class)
public class CashRegisterPropertyTest {

    public static final String CERISES = "Cerises";
    public static final String BANANES = "Bananes";
    public static final String POMMES = "Pommes";

    private final FruitStoreForTest fruitStore = new FruitStoreForTest();
    private final DiscountStoreForTest discountStore = new DiscountStoreForTest();
    private final PriceWithDiscountCalculator priceWithDiscountCalculator = new PriceWithDiscountCalculator(fruitStore, discountStore);
    private final CashRegister cashRegister = new CashRegister(priceWithDiscountCalculator, discountStore);

    @Before
    public void beforeEach() {
        this.fruitStore.storeFruit(CERISES, 75L);
        this.fruitStore.storeFruit(BANANES, 150L);
        this.fruitStore.storeFruit(POMMES, 50L);

        this.discountStore.storeFruitDiscount(CERISES, new FruitDiscount(20L, 2));
        this.discountStore.storeFruitDiscount(BANANES, new FruitDiscount(150L, 2));
        this.discountStore.storeFruitDiscount(POMMES, new FruitDiscount(50L, 4));

        this.discountStore.storeBasketDiscount(new LocalizedAppleDiscountApplicable());
        this.discountStore.storeBasketDiscount(new MoreThan5FruitsDiscountApplicable());
    }

    @Property(trials = 2000)
    public void notEmptyBasketShouldHaveAPositiveTotal(List<@From(BasketItems.class) BasketItem> basket) {
        assumeThat(basket.size(), greaterThan(0));

        Receipt receipt = this.cashRegister.editReceipt(basket);
        assertThat(receipt.getTotal()).isGreaterThan(0);
    }

}