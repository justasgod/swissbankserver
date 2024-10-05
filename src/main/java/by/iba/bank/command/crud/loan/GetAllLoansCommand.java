package by.iba.bank.command.crud.loan;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.AccountService;
import by.iba.bank.service.LoanService;
import com.google.gson.Gson;

public class GetAllLoansCommand implements Command {
    @Override
    public CommandResult execute(Request request, Response response){
        LoanService loanService = new LoanService();
        try {
            return new CommandResult( "Все кредиты из БД" , new Gson().toJson(loanService.findAllEntities()));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CommandResult(e.getMessage(), "");
        }

    }
}
