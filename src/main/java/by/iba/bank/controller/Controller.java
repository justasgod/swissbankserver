package by.iba.bank.controller;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.command.factory.CommandFactory;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.model.message.ResponseStatus;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor
public class Controller {

    private static final String COMMAND = "command";
    private static final String ERROR_MESSAGE = "error_message";
    private static final Logger LOGGER = LogManager.getLogger(Controller.class.getName());

    public void processRequest(@NotNull Request request, Response response){
        String command = request.getRequestType().toString();

        LOGGER.info(COMMAND + "= " + command);

        Command action = CommandFactory.create(command);
        CommandResult commandResult;

        try {
            commandResult = action.execute(request, response);
            if(commandResult.getData().isEmpty())
                response.setResponseStatus(ResponseStatus.ERROR);
            else
                response.setResponseStatus(ResponseStatus.OK);
        } catch (Exception e) {
            commandResult = new CommandResult(ERROR_MESSAGE, "");
            LOGGER.error(e.getMessage(), e);
            response.setResponseStatus(ResponseStatus.ERROR);
        }

        response.setResponseMessage(commandResult.getMessage());
        response.setResponseData(commandResult.getData());
    }
}
