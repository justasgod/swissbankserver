package by.iba.bank.repository;

import by.iba.bank.utility.HibernateSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.util.List;

import org.hibernate.query.Query;


public abstract class AbstractRepository<T>  implements Repository<T>{
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);

    AbstractRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<T> findAll(Class<T> type) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> cq = cb.createQuery(type);

            Root<T> root = cq.from(type);
            cq.select(root);
            Query<T> query = session.createQuery(cq);
            tx.commit();
            return query.getResultList();
        }
    }

    @Override
    public boolean save(T t) {
        boolean isAdded = false;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(t);
            session.getTransaction().commit();
            isAdded = true;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return isAdded;
    }

    @Override
    public boolean update(T t) {
        boolean isUpdated = false;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(t);
            session.getTransaction().commit();
            isUpdated = true;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(T t) {
        boolean isDeleted = false;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(t);
            session.getTransaction().commit();
            isDeleted = true;
        } catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return isDeleted;
    }

}
