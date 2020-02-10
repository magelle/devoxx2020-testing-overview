package devoxxfr2020.discount.resource;

import devoxxfr2020.discount.repository.Discount;
import devoxxfr2020.discount.repository.DiscountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/discount")
public class DiscountController {

    private DiscountRepository discountRepository;

    public DiscountController(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @PostMapping
    public Discount add(@RequestBody Discount discount) {
        return discountRepository.save(discount);
    }

    @PutMapping(value = "$id")
    public Discount update(@PathVariable("id") long id, @RequestBody Discount discount) {
        if (discountRepository.existsById(id)) {
            discountRepository.delete(discount);
            return discountRepository.save(discount);
        }
        return null;
    }

    @GetMapping(value = "$id")
    public Optional<Discount> get(@PathVariable("$id") long id) {
        return discountRepository.findById(id);
    }

    @GetMapping
    public Iterable<Discount> get() {
        return discountRepository.findAll();
    }

    @DeleteMapping
    public Optional<Discount> delete(@PathVariable("id") long id) {
        Optional<Discount> discount = discountRepository.findById(id);
        if (discount.isPresent()) discountRepository.deleteById(id);
        return discount;
    }
}
