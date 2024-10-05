package by.iba.bank.service;

import by.iba.bank.model.entity.Account;
import by.iba.bank.model.entity.Client;
import by.iba.bank.model.entity.Transaction;
import by.iba.bank.model.entity.User;
import by.iba.bank.repository.*;

import java.util.List;

public class TransactionService implements Service<Transaction> {
    @Override
    public Transaction findEntity(Integer id) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();

            return transactionRepository.findById(id);
        }
    }

    @Override
    public void saveEntity(Transaction entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            AccountRepository accountRepository = repositoryCreator.getAccountRepository();
            accountRepository.save(entity.getAccount());

            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            transactionRepository.save(entity);

        }
    }

    @Override
    public void deleteEntity(Transaction entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            transactionRepository.update(entity);
        }
    }

    @Override
    public void updateEntity(Transaction entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            transactionRepository.update(entity);
        }
    }


    @Override
    public List<Transaction> findAllEntities() {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            return transactionRepository.findAll(Transaction.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Transaction> findAllEntitiesByAccount(Account account){
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            return transactionRepository.findByAccount(account);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public List<Transaction> findAllEntitiesByClient(Client client){
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            TransactionRepository transactionRepository = repositoryCreator.getTransactionRepository();
            return transactionRepository.findByClient(client);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
