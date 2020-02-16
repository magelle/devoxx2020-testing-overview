package properties;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import devoxxfr2020.cashregister.domain.*;
import devoxxfr2020.cashregister.domain.discount.LocalizedAppleDiscount;
import devoxxfr2020.cashregister.domain.discount.MoreThan5FruitsDiscount;
import devoxxfr2020.cashregister.domain.testutil.DiscountStoreForTest;
import devoxxfr2020.cashregister.domain.testutil.FruitStoreForTest;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assume.assumeThat;


@RunWith(JUnitQuickcheck.class)
public class CashRegisterPropertyTest {

    public static final String CERISES = "Cerises";
    public static final String BANANES = "Bananes";
    public static final String APPLES = "Apples";
    public static final String MELE = "Mele";
    public static final String POMMES = "Pommes";

    private FruitStoreForTest fruitStore = new FruitStoreForTest();
    private DiscountStoreForTest discountStore = new DiscountStoreForTest();
    private FruitPriceComputer fruitPriceComputer = new FruitPriceComputer(fruitStore, discountStore);
    private CashRegister cashRegister = new CashRegister(fruitPriceComputer, discountStore);

    @Before
    public void beforeEach() {
        this.fruitStore.storeFruit(CERISES, 75L);
        this.fruitStore.storeFruit(BANANES, 150L);
        this.fruitStore.storeFruit(POMMES, 100L);
        this.fruitStore.storeFruit(APPLES, 100L);
        this.fruitStore.storeFruit(MELE, 100L);

        this.discountStore.storeFruitDiscount(CERISES, new FruitDiscount(20, 2));
        this.discountStore.storeFruitDiscount(BANANES, new FruitDiscount(150, 2));
        this.discountStore.storeFruitDiscount(APPLES, new FruitDiscount(100, 3));
        this.discountStore.storeFruitDiscount(MELE, new FruitDiscount(100, 2));

        this.discountStore.storeBasketDiscount(new LocalizedAppleDiscount());
        this.discountStore.storeBasketDiscount(new MoreThan5FruitsDiscount());
    }

    @Property
    public void notEmptyBasketShouldHaveAPositiveTotal(List<@From(BasketItems.class) BasketItem> basket) {
        assumeThat(basket.size(), greaterThan(0));

        Receipt receipt = this.cashRegister.editReceipt(basket);
        assertThat(receipt.getTotal()).isGreaterThan(0);
    }

}