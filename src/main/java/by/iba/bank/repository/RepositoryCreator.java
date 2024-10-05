package by.iba.bank.repository;

import by.iba.bank.connection.ConnectionPool;

import java.sql.Connection;

public class RepositoryCreator implements AutoCloseable{
    private ConnectionPool connectionPool;
    private Connection connection;

    public RepositoryCreator() {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.getConnection();
    }

    public UserRepository getUserRepository() {
        return new UserRepository(connection);
    }
    public ClientRepository getClientRepository() {
        return new ClientRepository(connection);
    }

    public AccountRepository getAccountRepository(){return new AccountRepository(connection);}

    public LoanRepository getLoanRepository(){return new LoanRepository(connection);}

    public LoanInterestRateRepository getLoanInterestRates(){return new LoanInterestRateRepository(connection);}

    public TransactionRepository getTransactionRepository(){return new TransactionRepository(connection);}

    @Override
    public void close() {
        connectionPool.releaseConnection(connection);
    }

}
