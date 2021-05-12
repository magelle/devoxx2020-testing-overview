package devoxxfr2020.cashregister.repository;

import devoxxfr2020.cashregister.model.Fruit;
import devoxxfr2020.cashregister.controller.FruitController;
import org.springframework.stereotype.Service;

@Service
public class FruitStore {
    private final FruitController fruitController;

    public FruitStore(FruitController fruitController) {
        this.fruitController = fruitController;
    }

    public long getPrice(String fruitName) {
        for (Fruit fruit : fruitController.get()) {
            if (fruit.getName().equals(fruitName)) {
                return fruit.getPrice();
            }
        }
        throw new IllegalArgumentException(("Unknown fruit " + fruitName));
    }
}
