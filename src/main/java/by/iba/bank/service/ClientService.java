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
            Client entity = clientRepository.findById(id);

            return entity;
        }

       /* if (entity.getPersonData() != null) {
            for (Passenger passenger : entity.getPersonData().getPassengers()) {
                passenger.setPersonData(null);
                passenger.getFlight().setUserMarks(null);
                passenger.getFlight().setAircraft(null);
                passenger.getFlight().setRoute(null);
            }
            entity.getPersonData().setUsers(null);
        }
        for (UserMark userMark : entity.getUserMarks()) {
            userMark.getFlight().setUserMarks(null);
            userMark.getFlight().setRoute(null);
            userMark.getFlight().setAircraft(null);
            User tempUser = new User();
            tempUser.setId(userMark.getUser().getId());
            tempUser.setPassword(userMark.getUser().getPassword());
            tempUser.setName(userMark.getUser().getName());
            tempUser.setLogin(userMark.getUser().getLogin());
            tempUser.setRole(userMark.getUser().getRole());
            userMark.setUser(tempUser);
        }
        return entity;*/
    }

    @Override
    public void saveEntity(Client entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            clientRepository.save(entity);
        }
    }

    @Override
    public void deleteEntity(Client entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            ClientRepository clientRepository = repositoryCreator.getClientRepository();
            clientRepository.delete(entity);
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
