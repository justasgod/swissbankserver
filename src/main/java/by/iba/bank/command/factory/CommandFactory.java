package by.iba.bank.command.factory;

import by.iba.bank.command.Command;
import by.iba.bank.command.crud.DeleteUserCommand;
import by.iba.bank.command.crud.GetAllUsersCommand;
import by.iba.bank.command.authorization.LoginCommand;
import by.iba.bank.command.authorization.RegisterNewUserCommand;
import by.iba.bank.command.crud.PutNewUserCommand;

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
            default: {
                throw new IllegalArgumentException("Invalid command" + commandEnum);
            }
        }
        return resultCommand;
    }
}
