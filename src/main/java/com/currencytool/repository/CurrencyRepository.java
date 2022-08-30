package com.currencytool.repository;

import com.currencytool.model.Currency;
import java.util.List;

public interface CurrencyRepository {

    void saveCurrencies(List<String> currencyNames);

    boolean exists(Currency currency);

}
