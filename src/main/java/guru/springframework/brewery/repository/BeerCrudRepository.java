package guru.springframework.brewery.repository;

import guru.springframework.brewery.domain.Beer;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BeerCrudRepository extends CrudRepository<Beer, UUID> {
}
