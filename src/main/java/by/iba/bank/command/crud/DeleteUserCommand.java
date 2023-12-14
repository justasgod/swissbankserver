package by.iba.bank.command.crud;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.command.factory.CommandType;
import by.iba.bank.model.entity.User;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.ClientService;
import by.iba.bank.service.UserService;
import com.google.gson.Gson;

public class DeleteUserCommand implements Command {
    @Override
    public CommandResult execute(Request request, Response response){
        UserService userService = new UserService();
        ClientService clientService = new ClientService();

        User requestUser = new Gson().fromJson(request.getRequestMessage(), User.class);
        userService.deleteEntity(requestUser);
        clientService.deleteEntity(requestUser.getClient());
        try {
            return new CommandResult( "Пользователь кикнут из БД" , new Gson().toJson(CommandType.DELETE_USER));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CommandResult(e.getMessage(), "");
        }
    }
}
