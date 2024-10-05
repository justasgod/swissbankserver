package by.iba.bank.command.crud.account;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.entity.Account;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.AccountService;
import by.iba.bank.service.ClientService;
import com.google.gson.Gson;

public class PostNewAccountCommand implements Command {

    @Override
    public CommandResult execute(Request request, Response response){
        AccountService accountService = new AccountService();
        ClientService clientService = new ClientService();

        Account requestAccount = new Gson().fromJson(request.getRequestMessage(), Account.class);
        accountService.saveEntity(requestAccount);
        try {
        return new CommandResult( "Счет добавлен" , new Gson().toJson(requestAccount));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CommandResult(e.getMessage(), "");
        }
    }
}
