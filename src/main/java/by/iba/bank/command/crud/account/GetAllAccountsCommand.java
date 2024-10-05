package by.iba.bank.command.crud.account;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.AccountService;
import by.iba.bank.service.UserService;
import com.google.gson.Gson;

public class GetAllAccountsCommand implements Command {

    @Override
    public CommandResult execute(Request request, Response response){
        AccountService accountService = new AccountService();
        try {
            return new CommandResult( "Все счета из БД" , new Gson().toJson(accountService.findAllEntities()));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CommandResult(e.getMessage(), "");
        }

    }

}