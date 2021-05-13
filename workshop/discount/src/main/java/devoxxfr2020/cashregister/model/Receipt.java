package devoxxfr2020.cashregister.model;

import java.util.List;

public class Receipt {

    private final List<ReceiptItem> items;
    private final List<AppliedBasketDiscount> discounts;
    private final long total;

    public Receipt(List<ReceiptItem> items, List<AppliedBasketDiscount> discounts, long total) {
        this.items = items;
        this.discounts = discounts;
        this.total = total;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public List<AppliedBasketDiscount> getDiscounts() {
        return discounts;
    }

    public long getTotal() {
        return total;
    }
}
