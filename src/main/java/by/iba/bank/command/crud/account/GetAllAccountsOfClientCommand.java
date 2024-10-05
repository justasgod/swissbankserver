package by.iba.bank.command.crud.account;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.entity.Client;
import by.iba.bank.model.entity.User;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.AccountService;
import com.google.gson.Gson;

public class GetAllAccountsOfClientCommand implements Command {
    @Override
    public CommandResult execute(Request request, Response response){
        AccountService accountService = new AccountService();

        Client requestClient = new Gson().fromJson(request.getRequestMessage(), Client.class);
        try {
            return new CommandResult( "Все счета клиента из БД" , new Gson().toJson(accountService.findEntityByClient(requestClient)));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CommandResult(e.getMessage(), "");
        }

    }
}
