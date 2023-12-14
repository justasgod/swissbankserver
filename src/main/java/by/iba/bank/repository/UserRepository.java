package by.iba.bank.repository;

import by.iba.bank.model.entity.User;
import by.iba.bank.utility.HibernateSessionFactory;
import org.hibernate.Session;

import java.sql.Connection;


public class UserRepository extends AbstractRepository<User>{

    public UserRepository(Connection connection) {
        super(connection);
    }

    @Override
    public User findById(Integer id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

}
