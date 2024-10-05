package by.iba.bank.command.factory;

public enum CommandType {
    LOGIN("login"),
    REGISTER_NEW_USER("register_new_user"),
    GET_ALL_USERS("get_all_users"),
    PUT_NEW_USER("put_new_user"),
    DELETE_USER("delete_user"),
    POST_NEW_ACCOUNT("post_new_account"),
    GET_ALL_ACCOUNTS_OF_CLIENT("get_all_accounts_of_client"),
    GET_ALL_ACCOUNTS("get_all_accounts"),
    POST_LOAN_STATEMENT("post_loan_statement"),
    POST_LOAN("post_loan"),
    GET_ALL_LOANS("get_all_loans"),
    GET_ALL_LOANS_OF_CLIENT("get_all_loans_of_client"),
    PUT_LOAN("put_loan"),
    POST_NEW_TRANSACTION("post_new_transaction"),
    GET_ALL_TRANSACTIONS("get_all_transactions"),
    GET_ALL_TRANSACTIONS_OF_CLIENT("get_all_transactions_of_client"),
    GET_RATE("get_rate");


    private String command;

    CommandType(String command) {
        this.command = command;
    }

}

