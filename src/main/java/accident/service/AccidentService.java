package accident.service;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.repository.AccidentRepository;
import accident.repository.AccidentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccidentService implements ServiceInterface {
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;

    @Autowired
    public AccidentService(AccidentRepository store, AccidentTypeRepository accidentTypeRepository) {
        this.accidentRepository = store;
        this.accidentTypeRepository = accidentTypeRepository;
    }

    @Override
    public void add(Accident accident) {
        accidentRepository.save(accident);
    }

    @Override
    public Collection<Accident> getAll() {
        return (Collection<Accident>) accidentRepository.findAllWithType();
    }

    @Override
    public Collection<AccidentType> getAllTypes() {
        return (Collection<AccidentType>) accidentTypeRepository.findAll();
    }
}
