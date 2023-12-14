package by.iba.bank.command.authorization;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.entity.User;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.UserService;
import com.google.gson.Gson;

import java.util.Arrays;

public class LoginCommand implements Command {

    @Override
    public CommandResult execute(Request request, Response response){

        User requestUser = new Gson().fromJson(request.getRequestMessage(), User.class);
        UserService userService = new UserService();
        if (userService.findAllEntities().stream().anyMatch(x -> x.getUsername().toLowerCase().equals(requestUser.getUsername().toLowerCase())) && userService.findAllEntities().stream().anyMatch(x -> Arrays.equals(x.getPassword(),(requestUser.getPassword())))) {
            User user = userService.findAllEntities().stream().filter(x -> x.getUsername().toLowerCase().equals(requestUser.getUsername().toLowerCase())).findFirst().get();
            user = userService.findEntity(user.getId());
            return new CommandResult("Готово!", new Gson().toJson(user));
        } else {
            return new CommandResult( "Такого пользователя не существует или неправильный пароль!", "");
        }
        
        /*boolean isUserFind = false;
        Optional<String> login = of(request).map(request -> request.getRequestMessage());
        Optional<String> password = of(request).map(httpServletRequest -> httpServletRequest.getParameter(PASSWORD));

        if (isEmpty(login.get()) || isEmpty(password.get())) {
            return forwardLoginWithError(request, ERROR_MESSAGE, ERROR_MESSAGE_TEXT);
        }
        byte[] pass = HashPassword.getHash(password.get());
        isUserFind = initializeUserIfExist(login.get(), pass, request);
        if (!isUserFind) {
            logger.info("user with such login and password doesn't exist");
            return forwardLoginWithError(request, ERROR_MESSAGE, AUTHENTICATION_ERROR_TEXT);
        } else {
            logger.info("user has been authorized: login:" + login + " password:" + password);
            return new CommandResult(COMMAND_WELCOME, false);
        }
*/
       // return new CommandResult();
    }
}
