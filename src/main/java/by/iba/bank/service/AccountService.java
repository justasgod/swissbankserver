package by.iba.bank.service;

import by.iba.bank.model.entity.Account;
import by.iba.bank.model.entity.Client;
import by.iba.bank.repository.AccountRepository;
import by.iba.bank.repository.RepositoryCreator;

import java.util.List;

public class AccountService implements Service<Account> {

    @Override
    public Account findEntity(Integer id) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            AccountRepository accountRepository = repositoryCreator.getAccountRepository();

            return accountRepository.findById(id);
        }
    }

    public List<Account> findEntityByClient(Client client) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            AccountRepository accountRepository = repositoryCreator.getAccountRepository();

            return accountRepository.findByClient(client);
        }
    }

    @Override
    public void saveEntity(Account entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            AccountRepository accountRepository = repositoryCreator.getAccountRepository();
            if(accountRepository.save(entity))
                System.out.println("OK");
            else
                System.out.println("Error");
        }

    }

    @Override
    public void deleteEntity(Account entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            AccountRepository accountRepository = repositoryCreator.getAccountRepository();
            if(accountRepository.delete(entity))
                System.out.println("OK");
            else
                System.out.println("Error");
        }
    }

    @Override
    public void updateEntity(Account entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            AccountRepository accountRepository = repositoryCreator.getAccountRepository();
            if(accountRepository.update(entity))
                System.out.println("OK");
            else
                System.out.println("Error");
        }
    }

    @Override
    public List<Account> findAllEntities() {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            AccountRepository accountRepository = repositoryCreator.getAccountRepository();
            return accountRepository.findAll(Account.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
