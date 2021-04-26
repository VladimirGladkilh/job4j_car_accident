package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem implements Store {
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final Map<Integer, AccidentType> types = new ConcurrentHashMap<>();

    public AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
        accidents.put(1, new Accident(1, "First", "Subject 1", "Street 1", types.get(3)));
        accidents.put(2, new Accident(2, "Second", "Subject 2", "Street 2", types.get(1)));
        accidents.put(3, new Accident(3, "Third", "Subject 3", "Street 3", types.get(2)));
    }

    @Override
    public Collection<Accident> findAll() {
        return accidents.values();
    }

    @Override
    public void save(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    @Override
    public Accident findById(int id) {
        return accidents.get(id);
    }

    @Override
    public void delete(Accident accident) {
        accidents.remove(accident.getId());
    }

    @Override
    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        return types.values();
    }

}
