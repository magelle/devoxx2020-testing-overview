package devoxxfr2020.cashregister.domain;

import java.util.List;

public class Receipt {

    private List<ReceiptItem> items;
    private List<AppliedBasketDiscount> discounts;
    private long total;

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
