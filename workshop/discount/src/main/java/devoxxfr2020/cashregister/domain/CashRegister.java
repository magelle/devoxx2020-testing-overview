package devoxxfr2020.cashregister.domain;

import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class CashRegister {

    private PriceWithDiscountCalculator priceWithDiscountCalculator;
    private DiscountStore discountStore;

    public CashRegister(PriceWithDiscountCalculator priceWithDiscountCalculator, DiscountStore discountStore) {
        this.priceWithDiscountCalculator = priceWithDiscountCalculator;
        this.discountStore = discountStore;
    }

    public Receipt editReceipt(List<BasketItem> basketItem) {
        List<ReceiptItem> receiptItems = getPrices(basketItem);

        List<AppliedBasketDiscount> basketDiscounts = getApplicableBasketDiscount(basketItem);

        long total = sumFruits(receiptItems) - getTotalDiscount(basketDiscounts);
        return new Receipt(receiptItems, basketDiscounts, total);
    }

    private long getTotalDiscount(List<AppliedBasketDiscount> basketDiscounts) {
        return basketDiscounts.stream()
                .mapToLong(AppliedBasketDiscount::getAmount)
                .sum();
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

    private List<AppliedBasketDiscount> getApplicableBasketDiscount(List<BasketItem> basketItem) {
        return discountStore.getBasketDiscount().stream()
                    .filter(discount -> discount.isApplicable(basketItem))
                    .map(applicable -> new AppliedBasketDiscount(applicable.getName(), applicable.getAmount(basketItem)))
                    .collect(toList());
    }

    private ReceiptItem createReceiptItem(BasketItem item) {
        int number = item.getQuantity();
        String fruit = item.getName();
        long total = priceWithDiscountCalculator.priceWithDiscount(fruit, number);
        return new ReceiptItem(fruit, number, total);
    }


}
