package devoxxfr2020.fruits;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends CrudRepository<Fruit, Long> {}
