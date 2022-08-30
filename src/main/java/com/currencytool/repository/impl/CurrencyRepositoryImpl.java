package com.currencytool.repository.impl;

import com.currencytool.model.Currency;
import com.currencytool.repository.CurrencyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CurrencyRepositoryImpl implements CurrencyRepository {

    private final List<Currency> currencies = new ArrayList<>();

    @Override
    public void saveCurrencies(Map<String, Integer> columns) {
        columns.keySet().forEach(key -> {
            if (!key.equals("Date")) {
                // the only column that's not a currency is Date
                currencies.add(new Currency(key));
            }
        });
    }

    @Override
    public boolean exists(Currency currency) {
        return currencies.contains(currency);
    }
}
