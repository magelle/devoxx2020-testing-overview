package devoxxfr2020.cashregister.bdd;

import devoxxfr2020.cashregister.model.Fruit;
import devoxxfr2020.cashregister.repository.FruitRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/fruits")
public class FruitController {

    private final FruitRepository fruitRepository;

    public FruitController(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @PostMapping
    public Fruit add(@RequestBody Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    @PutMapping(value = "{id}")
    public Fruit update(@PathVariable long id, @RequestBody Fruit fruit) {
        if (fruitRepository.existsById(id)) {
            fruitRepository.delete(fruit);
            return fruitRepository.save(fruit);
        }
        return null;
    }

    @GetMapping(value = "{id}")
    public Optional<Fruit> get(@PathVariable long id) {
        return fruitRepository.findById(id);
    }

    @GetMapping
    public Iterable<Fruit> get() {
        return fruitRepository.findAll();
    }

    @DeleteMapping(value = "{id}")
    public Optional<Fruit> delete(@PathVariable long id) {
        Optional<Fruit> fruit = fruitRepository.findById(id);
        if (fruit.isPresent()) fruitRepository.deleteById(id);
        return fruit;
    }
}
