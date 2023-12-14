package by.iba.bank.command.authorization;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.entity.User;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.ClientService;
import by.iba.bank.service.UserService;
import com.google.gson.Gson;

public class RegisterNewUserCommand implements Command {
    @Override
    public CommandResult execute(Request request, Response response){

        UserService userService = new UserService();
        ClientService clientService = new ClientService();
        User user = new Gson().fromJson(request.getRequestMessage(), User.class);

        if (userService.findAllEntities().stream().noneMatch(x -> x.getUsername().equalsIgnoreCase(user.getUsername()))) {
            clientService.saveEntity(user.getClient());
            userService.saveEntity(user);
            userService.findAllEntities();
            User returnUser;
            returnUser = userService.findEntity(user.getId());
            return new CommandResult("Готово!", new Gson().toJson(returnUser));
        } else {
            return new CommandResult("Такой пользователь уже существует!", "");
        }
    }
}
