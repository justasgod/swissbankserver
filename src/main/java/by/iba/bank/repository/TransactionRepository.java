package by.iba.bank.repository;

import by.iba.bank.model.entity.Account;
import by.iba.bank.model.entity.Client;
import by.iba.bank.model.entity.Loan;
import by.iba.bank.model.entity.Transaction;
import by.iba.bank.service.TransactionService;
import by.iba.bank.utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.util.List;

public class TransactionRepository extends AbstractRepository<Transaction> {
    TransactionRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Transaction findById(Integer id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(Transaction.class, id);
        }
    }

    public List<Transaction> findByAccount(Account account){
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            org.hibernate.Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);

            Root<Transaction> root = cq.from(Transaction.class);
            cq.select(root).where(cb.equal(root.get("account"),account));
            Query<Transaction> query = session.createQuery(cq);
            tx.commit();
            return query.getResultList();
        }
    }

    public List<Transaction> findByClient(Client client){
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            org.hibernate.Transaction tx = session.beginTransaction();

            String hql = "SELECT t FROM Transaction t WHERE t.account.client = :client";
            List<Transaction> loans = session.createQuery(hql, Transaction.class)
                    .setParameter("client", client)
                    .getResultList();
            tx.commit();

            return loans;
        }
    }
}
