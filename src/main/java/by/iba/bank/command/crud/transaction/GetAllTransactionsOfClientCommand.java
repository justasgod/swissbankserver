package by.iba.bank.command.crud.transaction;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.entity.Client;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.TransactionService;
import com.google.gson.Gson;

public class GetAllTransactionsOfClientCommand implements Command {
    @Override
    public CommandResult execute(Request request, Response response){
        TransactionService transactionService = new TransactionService();

        Client requestClient = new Gson().fromJson(request.getRequestMessage(), Client.class);
        try {
            return new CommandResult( "Все кредиты клиента из БД" , new Gson().toJson(transactionService.findAllEntitiesByClient(requestClient)));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CommandResult(e.getMessage(), "");
        }

    }
}
