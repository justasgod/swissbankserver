package by.iba.bank.service;

import by.iba.bank.model.entity.User;
import by.iba.bank.repository.RepositoryCreator;
import by.iba.bank.repository.UserRepository;

import java.util.List;

public class UserService implements Service<User> {

    @Override
    public User findEntity(Integer id) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            UserRepository userRepository = repositoryCreator.getUserRepository();

            return userRepository.findById(id);
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
    public void saveEntity(User entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            UserRepository userRepository = repositoryCreator.getUserRepository();
            userRepository.save(entity);
        }
    }

    @Override
    public void deleteEntity(User entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            UserRepository userRepository = repositoryCreator.getUserRepository();
            userRepository.delete(entity);
        }
    }

    @Override
    public void updateEntity(User entity) {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            UserRepository userRepository = repositoryCreator.getUserRepository();
            userRepository.update(entity);
        }
    }

    @Override
    public List<User> findAllEntities() {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()){
            UserRepository userRepository = repositoryCreator.getUserRepository();
            return userRepository.findAll(User.class);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
