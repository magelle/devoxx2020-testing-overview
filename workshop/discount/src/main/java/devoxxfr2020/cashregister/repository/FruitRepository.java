package devoxxfr2020.cashregister.repository;

import devoxxfr2020.cashregister.model.Fruit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends CrudRepository<Fruit, Long> {}
