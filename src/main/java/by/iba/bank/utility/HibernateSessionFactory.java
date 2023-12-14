package by.iba.bank.utility;

import by.iba.bank.model.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;
    private static String configFileName = "hibernate.cfg.xml";

    private HibernateSessionFactory() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {


                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Account.class);
                configuration.addAnnotatedClass(Client.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Deposit.class);
                configuration.addAnnotatedClass(Loan.class);
                configuration.addAnnotatedClass(LoanInterestRate.class);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(Transaction.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure(configFileName);
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
