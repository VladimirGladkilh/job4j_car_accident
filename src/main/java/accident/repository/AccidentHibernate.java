package accident.repository;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.function.Function;

@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            session.close();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public Accident save(Accident accident) {
        return this.tx(session -> {
            session.saveOrUpdate(accident);
            return accident;
        });
    }

    public Collection<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
    }

    public Accident findById(int id) {
        return this.tx(session -> {
                    Query<Accident> query = session
                            .createQuery("select c from Accident c fetch all properties where c.id=:id ")
                            .setParameter("id", id);
                    return query.stream().findAny().orElse(null);
                }
        );
    }

    public void delete(Accident accident) {
        this.tx(session -> {
            session.delete(accident);
            return true;
        });
    }

    public AccidentType findTypeById(int id) {
        return this.tx(session -> {
                    Query<AccidentType> query = session
                            .createQuery("select c from AccidentType c where c.id=:id ")
                            .setParameter("id", id);
                    return query.stream().findAny().orElse(null);
                }
        );
    }

    public Collection<AccidentType> findAllTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    public Rule findRuleById(int id) {
        return this.tx(session -> {
                    Query<Rule> query = session
                            .createQuery("select c from Rule c where c.id=:id ")
                            .setParameter("id", id);
                    return query.stream().findAny().orElse(null);
                }
        );
    }

    public Collection<Rule> findAllRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }

    public Collection<Rule> findRulesByAccidientId(int id) {
        Accident accident = findById(id);
        if (accident == null) {
            return null;
        }
        return accident.getRules();
    }
}