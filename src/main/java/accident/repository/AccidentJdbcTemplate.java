package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public Collection<Accident> findAll() {
        return jdbc.query("select id, name from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            jdbc.update("insert into accident (name, text, address, type_id) values (?, ?, ?,?)",
                    accident.getName(),
                    accident.getText(),
                    accident.getAddress(),
                    accident.getType() != null ? accident.getType().getId() : 0);
        } else {
            jdbc.update("update accident set name = ?, text =? , address=?, type_id=?",
                    accident.getName(),
                    accident.getText(),
                    accident.getAddress(),
                    accident.getType() != null ? accident.getType().getId() : 0);
        }
    }

    public Accident findById(int id) {
        return jdbc.query("select * from accident e where id=?",
                rs -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                },
                id);
    }

    public void delete(Accident accident) {
        jdbc.update("delete from accident where id=?",
                accident.getId());
    }

    public AccidentType findTypeById(int id) {
        return jdbc.query("select * from accident_type e where id=?",
                rs -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                },
                id);
    }

    public Collection<AccidentType> findAllTypes() {
        return jdbc.query("select id, name from accident_type",
                (rs, row) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(rs.getInt("id"));
                    accidentType.setName(rs.getString("name"));
                    return accidentType;
                });
    }

    public Rule findRuleById(int id) {
        return jdbc.query("select * from rule e where id=?",
                rs -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id);
    }

    public Collection<Rule> findAllRules() {
        return jdbc.query("select id, name from rule",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                });
    }

    public Collection<Rule> findRulesByAccidientId(int id) {
        return null;
    }
}