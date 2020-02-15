package devoxxfr2020.basket.domain;

import java.util.Objects;

public class BasketItem {


    private final String fruit;
    private final int number;

    public BasketItem(String fruit, int number) {
        this.fruit = fruit;
        this.number = number;
    }

    public String getFruit() {
        return fruit;
    }

    public int getNumber() {
        return number;
    }
}
