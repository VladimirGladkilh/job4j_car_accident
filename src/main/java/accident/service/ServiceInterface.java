package accident.service;

import accident.model.Accident;

import java.util.Collection;

public interface ServiceInterface {
    void add(Accident accident);
    Collection<Accident> getAll();
}
