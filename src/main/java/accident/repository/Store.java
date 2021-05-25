package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;

import java.util.Collection;

public interface Store {
    Collection<Accident> findAll();

    void save(Accident accident);

    Accident findById(int id);

    void delete(Accident accident);

    AccidentType findTypeById(int id);

    Collection<AccidentType> findAllTypes();

    Rule findRuleById(int id);

    Collection<Rule> findAllRules();

}
