package by.iba.bank.repository;

import by.iba.bank.model.entity.Account;
import by.iba.bank.model.entity.Client;
import by.iba.bank.utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.util.List;

public class AccountRepository extends AbstractRepository<Account>{

    AccountRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Account findById(Integer id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(Account.class, id);
        }
    }

    public List<Account> findByClient(Client client){
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Account> cq = cb.createQuery(Account.class);

            Root<Account> root = cq.from(Account.class);
            cq.select(root).where(cb.equal(root.get("client"), client));
            Query<Account> query = session.createQuery(cq);
            tx.commit();
            return query.getResultList();
        }
    }
}
