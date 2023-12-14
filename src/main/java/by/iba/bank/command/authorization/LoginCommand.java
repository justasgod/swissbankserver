package by.iba.bank.command.authorization;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.entity.User;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.UserService;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class LoginCommand implements Command {

    @Override
    public CommandResult execute(Request request, Response response){

        User requestUser = new Gson().fromJson(request.getRequestMessage(), User.class);
        UserService userService = new UserService();
        List<User> users = userService.findAllEntities();
        if (users.stream().anyMatch(x -> x.getUsername().equalsIgnoreCase(requestUser.getUsername())) && users.stream().anyMatch(x -> Arrays.equals(x.getPassword(),(requestUser.getPassword())))) {
            User user = userService.findAllEntities().stream().filter(x -> x.getUsername().equalsIgnoreCase(requestUser.getUsername())).findFirst().get();
            // user = userService.findEntity(user.getId());
            return new CommandResult("Готово!", new Gson().toJson(user));
        } else {
            return new CommandResult( "Такого пользователя не существует или неправильный пароль!", "");
        }
    }
}
