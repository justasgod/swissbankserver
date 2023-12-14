package by.iba.bank.repository;

import by.iba.bank.model.entity.Client;
import by.iba.bank.utility.HibernateSessionFactory;
import org.hibernate.Session;

import java.sql.Connection;

public class ClientRepository extends AbstractRepository<Client>{

    ClientRepository(Connection connection) {
        super(connection);
    }

    @Override
    public Client findById(Integer id) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            return session.get(Client.class, id);
        }
    }

}
