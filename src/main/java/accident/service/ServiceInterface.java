package accident.service;

import accident.model.Accident;
import accident.model.AccidentType;

import java.util.Collection;

public interface ServiceInterface {
    void add(Accident accident);
    Collection<Accident> getAll();
    Collection<AccidentType> getAllTypes();
}
