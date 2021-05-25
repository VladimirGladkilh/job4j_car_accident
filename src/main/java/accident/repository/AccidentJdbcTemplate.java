package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class AccidentJdbcTemplate implements Store {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public Collection<Accident> findAll() {
        return jdbc.query("select id, name from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }

    @Override
    public void save(Accident accident) {

    }

    @Override
    public Accident findById(int id) {

        return jdbc.query("select e from accident e where id=" + id, rs -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            return accident;
        })
                ;
    }

    @Override
    public void delete(Accident accident) {
        jdbc.execute("delete from accident where id=" + accident.getId());
    }

    @Override
    public AccidentType findTypeById(int id) {
        return null;
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        return null;
    }

    @Override
    public Rule findRuleById(int id) {
        return null;
    }

    @Override
    public Collection<Rule> findAllRules() {
        return null;
    }

    @Override
    public Collection<Rule> findRulesByAccidientId(int id) {
        return null;
    }
}