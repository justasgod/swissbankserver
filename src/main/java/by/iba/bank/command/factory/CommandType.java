package by.iba.bank.command.factory;

public enum CommandType {
    LOGIN("login"),
    REGISTER_NEW_USER("register_new_user"),
    GET_ALL_USERS("get_all_users"),
    PUT_NEW_USER("put_new_user"),
    DELETE_USER("delete_user");

    private String command;

    CommandType(String command) {
        this.command = command;
    }

}

