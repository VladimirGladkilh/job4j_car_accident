package accident.service;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.repository.AccidentJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccidentService implements ServiceInterface {
    private final AccidentJdbcTemplate store;

    @Autowired
    public AccidentService(AccidentJdbcTemplate store) {
        this.store = store;
    }

    @Override
    public void add(Accident accident) {
        store.save(accident);
    }

    @Override
    public Collection<Accident> getAll() {
        return store.findAll();
    }

    @Override
    public Collection<AccidentType> getAllTypes() {
        return store.findAllTypes();
    }
}
