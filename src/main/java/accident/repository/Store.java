package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;

import java.util.Collection;

public interface Store {
    Collection<Accident> findAll();

    void save(Accident accident);

    Accident findById(int id);

    void delete(Accident accident);

    AccidentType findTypeById(int id);
    Collection<AccidentType> findAllTypes();

}
