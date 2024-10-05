package by.iba.bank.command.crud.loan;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.command.factory.CommandType;
import by.iba.bank.model.entity.Loan;
import by.iba.bank.model.entity.User;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.ClientService;
import by.iba.bank.service.LoanInterestRateService;
import by.iba.bank.service.LoanService;
import by.iba.bank.service.UserService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PostLoanStatementCommand implements Command {

    @Override
    public CommandResult execute(Request request, Response response){
        LoanService loanService = new LoanService();
        Loan requestLoan = new Gson().fromJson(request.getRequestMessage(), Loan.class);

        try {
            return new CommandResult( "Итоговое предложение по кредиту" , new Gson().toJson(loanService.processApplication(requestLoan)));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CommandResult(e.getMessage(), "");
        }

    }
}
