package accident.repository;

import accident.model.Accident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {
    @Query("from Accident a join fetch a.type")
    List<Accident> findAllWithType();
}
