package devoxxfr2020.cashregister.application;

import devoxxfr2020.cashregister.domain.FruitStore;
import devoxxfr2020.fruits.Fruit;
import devoxxfr2020.fruits.FruitController;
import org.springframework.stereotype.Service;

@Service
public class LocalFruitStore implements FruitStore {
    private FruitController fruitController;

    public LocalFruitStore(FruitController fruitController) {
        this.fruitController = fruitController;
    }

    @Override
    public long getPrice(String fruitName) {
        for (Fruit fruit : fruitController.get()) {
            if (fruit.getName().equals(fruitName)) {
                return fruit.getPrice();
            }
        }
        throw new IllegalArgumentException(("Unkown fruit " + fruitName));
    }
}
