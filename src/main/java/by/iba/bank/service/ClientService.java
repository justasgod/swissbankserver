package by.iba.bank.service;

import by.iba.bank.model.entity.Client;
import by.iba.bank.repository.ClientRepository;
import by.iba.bank.repository.RepositoryCreator;


import java.util.List;

public class ClientService implements Service<Client>{
    @Override
    public Client findEntity(Integer id) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            ClientRepository clientRepository = repositoryCreator.getClientRepository();

            return clientRepository.findById(id);
        }
    }

    @Override
    public void saveEntity(Client entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            if(clientRepository.save(entity))
                System.out.println("OK");
            else
                System.out.println("Error");
        }
    }

    @Override
    public void deleteEntity(Client entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            if(clientRepository.delete(entity))
                System.out.println("OK");
            else
                System.out.println("Error");
        }
    }

    @Override
    public void updateEntity(Client entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            clientRepository.update(entity);
        }
    }

    @Override
    public List<Client> findAllEntities() {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            return clientRepository.findAll(Client.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
