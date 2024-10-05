package by.iba.bank.command.converter;

import by.iba.bank.command.Command;
import by.iba.bank.command.CommandResult;
import by.iba.bank.model.data.Rate;
import by.iba.bank.model.entity.Account;
import by.iba.bank.model.message.Request;
import by.iba.bank.model.message.Response;
import by.iba.bank.service.AccountService;
import by.iba.bank.service.RateService;
import com.google.gson.Gson;

public class GetRateCommand implements Command {
    @Override
    public CommandResult execute(Request request, Response response){
        RateService rateService = new RateService();
       // Rate rate = new Gson().fromJson(request.getRequestMessage(), Rate.class);
        try {
            return new CommandResult( "Курс валюты на сегодня" , new Gson().toJson(rateService.getRate()));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CommandResult(e.getMessage(), "");
        }

    }
}
