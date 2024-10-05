package by.iba.bank.command.crud.loan;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.entity.Account;
import by.iba.bank.model.entity.Loan;
import by.iba.bank.model.entity.LoanInterestRate;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.AccountService;
import by.iba.bank.service.ClientService;
import by.iba.bank.service.LoanInterestRateService;
import by.iba.bank.service.LoanService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class PostLoanCommand implements Command {
    @Override
    public CommandResult execute(Request request, Response response){
        TypeToken<List<LoanInterestRate>> token = new TypeToken<>() {};
        List<LoanInterestRate> rates = new Gson().fromJson(response.getResponseData(), token.getType());
        AccountService accountService = new AccountService();
        LoanService loanService = new LoanService();
        LoanInterestRateService loanInterestRateService = new LoanInterestRateService();

        /*accountService.saveEntity(rates.get(0).getLoan().getAccount());
        loanService.saveEntity(rates.get(0).getLoan());*/
        accountService.saveEntity(rates.get(0).getLoan().getAccount());
        loanService.saveEntity(rates.get(0).getLoan());


        for(LoanInterestRate loanInterestRate : rates) {
            loanInterestRate.setLoan(rates.get(0).getLoan());
            loanInterestRateService.saveEntity(loanInterestRate);
        }


        try {
            return new CommandResult( "Кредит добавлен" , new Gson().toJson(rates));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CommandResult(e.getMessage(), "");
        }
    }
}
