package devoxxfr2020.discount.repository;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "DISCOUNT")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "FRUIT")
    private String fruit;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "NUMBER")
    private int number;

    @Column(name = "DISCOUNT")
    private int discount;

    public long getId() {
        return id;
    }

    public String getFruit() {
        return fruit;
    }

    public int getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount1 = (Discount) o;
        return id == discount1.id &&
                price == discount1.price &&
                number == discount1.number &&
                discount == discount1.discount &&
                Objects.equals(fruit, discount1.fruit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fruit, price, number, discount);
    }
}
