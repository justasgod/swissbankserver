package by.iba.bank.service;

import by.iba.bank.model.data.Rate;
import by.iba.bank.repository.ApiRateRepository;

import java.util.ArrayList;
import java.util.List;

public class RateService {
    public Rate getRate(String code){
        return ApiRateRepository.getExchangeRateByCurrencyCode(code);
    }

    public List<Rate> getRate(){
        List<Rate> list = new ArrayList<>();
        list.add(getRate("USD"));
        list.add(getRate("EUR"));

        return list;
    }
}
