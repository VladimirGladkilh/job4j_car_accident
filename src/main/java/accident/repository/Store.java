package accident.repository;

import accident.model.Accident;

import java.util.Collection;

public interface Store {
    Collection<Accident> findAll();

    void save(Accident accident);

    Accident findById(int id);

    void delete(Accident accident);

}
