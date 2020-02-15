package devoxxfr2020.basket.domain;

import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Service
public class CashRegister {

    private FruitPriceComputer fruitPriceComputer;
    private DiscountStore discountStore;

    public CashRegister(FruitPriceComputer fruitPriceComputer, DiscountStore discountStore) {
        this.fruitPriceComputer = fruitPriceComputer;
        this.discountStore = discountStore;
    }

    public Receipt editReceipt(List<BasketItem> basketItem) {
        List<ReceiptItem> receiptItems = getPrices(basketItem);

        List<BasketDiscount> basketDiscounts = getApplicableBasketDiscount(basketItem);

        long total = sumFruits(receiptItems) - getTotalDiscount(basketDiscounts, basketItem);
        return new Receipt(receiptItems, basketDiscounts, total);
    }

    private long getTotalDiscount(List<BasketDiscount> basketDiscounts, List<BasketItem> basketItem) {
        return basketDiscounts.stream().mapToLong(discount -> discount.getAmount(basketItem)).sum();
    }

    private List<ReceiptItem> getPrices(List<BasketItem> basketItem) {
        return basketItem.stream()
                .map(this::createReceiptItem)
                .collect(toList());
    }

    private long sumFruits(List<ReceiptItem> receiptItems) {
        return receiptItems.stream()
                .mapToLong(ReceiptItem::getTotal)
                .sum();
    }

    private List<BasketDiscount> getApplicableBasketDiscount(List<BasketItem> basketItem) {
        return discountStore.getBasketDiscount().stream()
                    .filter(discount -> discount.isApplicable(basketItem))
                    .collect(toList());
    }

    private ReceiptItem createReceiptItem(BasketItem item) {
        int number = item.getNumber();
        String fruit = item.getFruit();
        long total = fruitPriceComputer.getPriceWithDiscount(fruit, number);
        return new ReceiptItem(fruit, number, total);
    }


}
