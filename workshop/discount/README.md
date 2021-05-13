Adding a new business needs

If more than 4 apple, apply a 100 cents discount

Test
```java
    @Test
    public void should_apply_a_100_cents_discount_if_more_than_4_apples() {
        FruitPriceComputer fruitPriceComputer = Mockito.mock(FruitPriceComputer.class);
        Mockito.when(fruitPriceComputer.getPriceWithDiscount("Pommes", 5)).thenReturn(500L);
        CashRegisterService cashRegisterService = new CashRegisterService(fruitPriceComputer, new DiscountStore());

        Receipt receipt = cashRegisterService.editReceipt(Collections.singletonList(new BasketItem("Pommes", 5)));
        assertEquals(400L, receipt.getTotal());
        AppliedBasketDiscount discount = receipt.getDiscounts().get(0);
        assertEquals("More than 4 Apples", discount.getName());
        assertEquals(100L, discount.getAmount());
    }
```

Domain : discount store
```java
    return List.of(
            new MoreThan5FruitsDiscountApplicable(),
            new MoreThan4ApplesDiscountApplicable()
    );
```

Domain : new discout
```java
package devoxxfr2020.cashregister.model.discount;

import devoxxfr2020.cashregister.model.ApplicableBasketDiscount;
import devoxxfr2020.cashregister.model.BasketItem;

import java.util.List;

public class MoreThan4ApplesDiscountApplicable implements ApplicableBasketDiscount {
    @Override
    public String getName() {
        return "More than 4 Apples";
    }

    @Override
    public long getAmount(List<BasketItem> fruits) {
        return 100L;
    }

    @Override
    public boolean isApplicable(List<BasketItem> fruits) {
        return fruits
                .stream()
                .filter(basketItem -> basketItem.getName().equals("Pommes"))
                .mapToLong(BasketItem::getQuantity)
                .sum() > 4;
    }

}
```