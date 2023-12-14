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

    @Override
    public void close() {
        connectionPool.releaseConnection(connection);
    }

}
