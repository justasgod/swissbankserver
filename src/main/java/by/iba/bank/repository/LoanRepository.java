package by.iba.bank.repository;

import by.iba.bank.model.entity.Account;
import by.iba.bank.model.entity.Client;
import by.iba.bank.model.entity.Loan;
import by.iba.bank.utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository extends AbstractRepository<Loan>{

    LoanRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Loan findById(Integer id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(Loan.class, id);
        }
    }

    public List<Loan> findByClient(Client client){
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            String hql = "SELECT l FROM Loan l WHERE l.account.client = :client";
            List<Loan> loans = session.createQuery(hql, Loan.class)
                    .setParameter("client", client)
                    .getResultList();
            tx.commit();

            return loans;
        }
    }
}
