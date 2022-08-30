package com.currencytool.repository.impl;

import com.currencytool.model.Currency;
import com.currencytool.repository.CurrencyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyRepositoryImpl implements CurrencyRepository {

    private final List<Currency> currencies = new ArrayList<>();

    @Override
    public void saveCurrencies(List<String> currencyNames) {
        currencies.addAll(currencyNames.stream().map(Currency::new).toList());
    }

    @Override
    public boolean exists(Currency currency) {
        return currencies.contains(currency);
    }
}
