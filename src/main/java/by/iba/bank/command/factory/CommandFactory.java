package by.iba.bank.command.factory;

import by.iba.bank.command.Command;
import by.iba.bank.command.converter.GetRateCommand;
import by.iba.bank.command.crud.account.GetAllAccountsCommand;
import by.iba.bank.command.crud.account.GetAllAccountsOfClientCommand;
import by.iba.bank.command.crud.account.PostNewAccountCommand;
import by.iba.bank.command.crud.loan.*;
import by.iba.bank.command.crud.transaction.GetAllTransactionsOfClientCommand;
import by.iba.bank.command.crud.transaction.PostNewTransactionCommand;
import by.iba.bank.command.crud.user.DeleteUserCommand;
import by.iba.bank.command.crud.user.GetAllUsersCommand;
import by.iba.bank.command.authorization.LoginCommand;
import by.iba.bank.command.authorization.RegisterNewUserCommand;
import by.iba.bank.command.crud.user.PutNewUserCommand;

public class CommandFactory {
    public static Command create(String command) {
        command = command.toUpperCase();
        System.out.println(command);
        CommandType commandEnum = CommandType.valueOf(command);
        Command resultCommand;
        switch (commandEnum) {
            case LOGIN: {
                resultCommand = new LoginCommand();
                break;
            }
            case REGISTER_NEW_USER: {
                resultCommand = new RegisterNewUserCommand();
                break;
            }
            case GET_ALL_USERS:{
                resultCommand = new GetAllUsersCommand();
                break;
            }
            case PUT_NEW_USER:
            {
                resultCommand = new PutNewUserCommand();
                break;
            }
            case DELETE_USER:{
                resultCommand = new DeleteUserCommand();
                break;
            }
            case POST_NEW_ACCOUNT:{
                resultCommand = new PostNewAccountCommand();
                break;
            }
            case GET_ALL_ACCOUNTS_OF_CLIENT:{
                resultCommand = new GetAllAccountsOfClientCommand();
                break;
            }
            case GET_ALL_ACCOUNTS:{
                resultCommand = new GetAllAccountsCommand();
                break;
            }
            case POST_LOAN_STATEMENT:{
                resultCommand = new PostLoanStatementCommand();
                break;
            }
            case POST_LOAN:{
                resultCommand = new PostLoanCommand();
                break;
            }
            case GET_ALL_LOANS_OF_CLIENT:{
                resultCommand = new GetAllLoansOfClientCommand();
                break;
            }
            case PUT_LOAN:{
                resultCommand = new PutLoanCommand();
                break;
            }
            case POST_NEW_TRANSACTION:{
                resultCommand = new PostNewTransactionCommand();
                break;
            }
            case GET_ALL_TRANSACTIONS_OF_CLIENT:{
                resultCommand = new GetAllTransactionsOfClientCommand();
                break;
            }
            case GET_RATE:{
                resultCommand = new GetRateCommand();
                break;
            }

            default: {
                throw new IllegalArgumentException("Invalid command" + commandEnum);
            }
        }
        return resultCommand;
    }
}
