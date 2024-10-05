package by.iba.bank.repository;

import by.iba.bank.model.data.Rate;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRateRepository {
    private static final String API_URL = "https://api.nbrb.by/exrates/rates/";
    private static final String PARAM_MODE = "2"; // Формат аргумента cur_id: 2 – трехзначный буквенный код валюты (ИСО 4217)

    public static Rate getExchangeRateByCurrencyCode(String currencyCode) {
        try {
            String urlString = API_URL + currencyCode + "?parammode=" + PARAM_MODE;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Преобразование ответа в объект класса Rate
                return parseRateResponse(response.toString());
            } else {
                System.out.println("Ошибка HTTP: " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private static Rate parseRateResponse(String response) {
        Gson gson = new Gson();
        return gson.fromJson(response, Rate.class);
    }
}
