package accident.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String text;
    private String address;
    @ManyToOne
    @JoinColumn(name = "type_id")
    @Fetch(FetchMode.JOIN)
    private AccidentType type;
    @ManyToMany
    @JoinTable(name = "accident_rules",
            joinColumns = @JoinColumn(name = "acciden_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    @Fetch(FetchMode.JOIN)
    private Set<Rule> rules;

    public Accident() {
    }

    public Accident(int id, String name, String text, String address, AccidentType accidentType) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
        this.type = accidentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accident accident = (Accident) o;
        return id == accident.id &&
                name.equals(accident.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
