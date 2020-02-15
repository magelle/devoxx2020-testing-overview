package devoxxfr2020.cashregister.domain;

import java.util.List;

public class Receipt {

    private List<ReceiptItem> fruitPrices;
    private List<BasketDiscount> basketDiscounts;
    private long total;

    public Receipt(List<ReceiptItem> fruitPrices, List<BasketDiscount> basketDiscounts, long total) {
        this.fruitPrices = fruitPrices;
        this.basketDiscounts = basketDiscounts;
        this.total = total;
    }

    public List<ReceiptItem> getFruitPrices() {
        return fruitPrices;
    }

    public List<BasketDiscount> getBasketDiscounts() {
        return basketDiscounts;
    }

    public long getTotal() {
        return total;
    }
}
