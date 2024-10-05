package by.iba.bank.repository;

import by.iba.bank.model.entity.Account;
import by.iba.bank.model.entity.LoanInterestRate;
import by.iba.bank.utility.HibernateSessionFactory;
import org.hibernate.Session;

import java.sql.Connection;

public class LoanInterestRateRepository extends AbstractRepository<LoanInterestRate>{
    LoanInterestRateRepository(Connection connection) {
        super(connection);
    }

    @Override
    public LoanInterestRate findById(Integer id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(LoanInterestRate.class, id);
        }
    }
}
