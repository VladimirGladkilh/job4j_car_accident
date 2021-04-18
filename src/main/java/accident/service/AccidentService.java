package accident.service;

import accident.model.Accident;
import accident.repository.AccidentMem;
import accident.repository.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccidentService implements ServiceInterface{
    private final Store store;
    private int ids = 0;

    @Autowired
    public AccidentService() {
        store = new AccidentMem();
    }


    @Override
    public void add(Accident accident) {
        store.save(accident);
    }

    @Override
    public Collection<Accident> getAll() {
        return store.findAll();
    }
}
