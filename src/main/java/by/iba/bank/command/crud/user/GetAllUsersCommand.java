package by.iba.bank.command.crud.user;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.entity.User;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.ClientService;
import by.iba.bank.service.UserService;
import com.google.gson.Gson;

public class GetAllUsersCommand implements Command {

        @Override
        public CommandResult execute(Request request, Response response){
            UserService userService = new UserService();
            try {
                return new CommandResult( "Все пользователи из БД" , new Gson().toJson(userService.findAllEntities()));

            }catch (Exception e){
                System.out.println(e.getMessage());
                return new CommandResult(e.getMessage(), "");
            }

        }

}
