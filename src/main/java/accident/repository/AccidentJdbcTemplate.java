package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.HashSet;

//@Repository
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    public Collection<Accident> findAll() {
        return jdbc.query("select id, name, text, address, type_id  from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findTypeById(rs.getInt("type_id")));
                    return accident;
                });
    }

    public void save(Accident accident) {
        if (accident.getId() == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(connection -> {
                PreparedStatement ps = connection.prepareStatement("insert into accident (name, text, address, type_id) values (?, ?, ?,?)", new String[]{"id"});
                ps.setString(1, accident.getName());
                ps.setString(2, accident.getText());
                ps.setString(3, accident.getAddress());
                ps.setInt(4, accident.getType() != null ? accident.getType().getId() : 0);
                return ps;
            }, keyHolder);
            accident.setId((Integer) keyHolder.getKey());
        } else {
            jdbc.update("update accident set name = ?, text =? , address=?, type_id=? where id=?",
                    accident.getName(),
                    accident.getText(),
                    accident.getAddress(),
                    accident.getType() != null ? accident.getType().getId() : 0,
                    accident.getId());
        }
        createLinks(accident);

    }

    private void createLinks(Accident accident) {
        jdbc.update("delete from accident_rules where acciden_id=?",
                accident.getId());
        if (accident.getRules() != null && !accident.getRules().isEmpty()) {
            accident.getRules()
                    .forEach(rule -> jdbc.update("insert into accident_rules (acciden_id, rule_id) values (?,?)",
                            accident.getId(),
                            rule.getId()));
        }
    }

    public Accident findById(int id) {
        return jdbc.queryForObject("select id,  name, text, address, type_id  from accident  where id=?",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    accident.setText(rs.getString("text"));
                    accident.setAddress(rs.getString("address"));
                    accident.setType(findTypeById(rs.getInt("type_id")));
                    accident.setRules(new HashSet<>(findRulesByAccidientId(accident.getId())));
                    return accident;
                },
                id);
    }

    public void delete(Accident accident) {
        jdbc.update("delete from accident where id=?",
                accident.getId());
    }

    public AccidentType findTypeById(int id) {
        if (id == 0) {
            return null;
        }
        return jdbc.queryForObject("select id, name from accident_type  where id=?",
                (rs, row) -> {
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
        return jdbc.queryForObject("select id, name from rule  where id=?",
                (rs, row) -> {
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
        return jdbc.query("select r.id, r.name from rule r join accident_rules a on a.rule_id=r.id where a.acciden_id=?",
                (rs, row) -> {
                    Rule rule = new Rule();
                    rule.setId(rs.getInt("id"));
                    rule.setName(rs.getString("name"));
                    return rule;
                },
                id);
    }
}