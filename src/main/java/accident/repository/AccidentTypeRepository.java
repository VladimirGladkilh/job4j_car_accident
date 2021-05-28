package accident.repository;

import accident.model.AccidentType;
import org.springframework.data.repository.CrudRepository;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
}
