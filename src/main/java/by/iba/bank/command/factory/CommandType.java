package by.iba.bank.command.factory;

public enum CommandType {
    LOGIN("login"),
    REGISTER_NEW_USER("register_new_user");

    private String command;

    private CommandType(String command) {
        this.command = command;
    }

}

