package devoxxfr2020.fruits;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/fruit")
public class FruitController {

    private FruitRepository fruitRepository;

    public FruitController(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @PostMapping
    public Fruit add(@RequestBody Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    @PutMapping(value = "$id")
    public Fruit update(@PathVariable("id") long id, @RequestBody Fruit fruit) {
        if (fruitRepository.existsById(id)) {
            fruitRepository.delete(fruit);
            return fruitRepository.save(fruit);
        }
        return null;
    }

    @GetMapping(value = "$id")
    public Optional<Fruit> get(@PathVariable("$id") long id) {
        return fruitRepository.findById(id);
    }

    @GetMapping
    public Iterable<Fruit> get() {
        return fruitRepository.findAll();
    }

    @DeleteMapping
    public Optional<Fruit> delete(@PathVariable("id") long id) {
        Optional<Fruit> fruit = fruitRepository.findById(id);
        if (fruit.isPresent()) fruitRepository.deleteById(id);
        return fruit;
    }
}
