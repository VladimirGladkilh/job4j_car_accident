package accident.repository;

import accident.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

@Repository
public class AccidentMem implements Store {
    private HashMap<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        init();
    }

    private void init() {
        accidents.put(1, new Accident(1, "First", "Subject 1", "Street 1"));
        accidents.put(2, new Accident(2, "Second", "Subject 2", "Street 2"));
        accidents.put(3, new Accident(3, "Third", "Subject 3", "Street 3"));
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
}
