package by.iba.bank.command;

import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;

import java.io.IOException;

public interface Command {
    CommandResult execute(Request request, Response response) throws IOException;

}
