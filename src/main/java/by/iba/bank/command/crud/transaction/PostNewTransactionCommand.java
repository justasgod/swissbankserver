package by.iba.bank.command.crud.transaction;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.entity.Loan;
import by.iba.bank.model.entity.Transaction;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.LoanService;
import by.iba.bank.service.TransactionService;
import com.google.gson.Gson;

import java.math.BigDecimal;

public class PostNewTransactionCommand implements Command {
    @Override
    public CommandResult execute(Request request, Response response){
        TransactionService transactionService = new TransactionService();

        Transaction requestTransaction = new Gson().fromJson(request.getRequestMessage(), Transaction.class);
        if(requestTransaction.getTransactionType().equals("DEBIT"))
        {
            BigDecimal balance  = requestTransaction.getAccount().getBalance();
            balance = balance.subtract(requestTransaction.getAmount());
            requestTransaction.getAccount().setBalance(balance);
        }else {
            BigDecimal balance  = requestTransaction.getAccount().getBalance();
            balance = balance.add(requestTransaction.getAmount());
            requestTransaction.getAccount().setBalance(balance);
        }
        transactionService.saveEntity(requestTransaction);
            try {
            return new CommandResult( "Примененная транзакция" , new Gson().toJson(requestTransaction));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CommandResult(e.getMessage(), "");
        }
    }
}
