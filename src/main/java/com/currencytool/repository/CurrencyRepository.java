package com.currencytool.repository;

import com.currencytool.model.Currency;

import java.util.Map;

public interface CurrencyRepository {

    /**
     * Saves the list of currencies from the header of the CSV data file
     * @param columns the header of the CSV data file as a map, where the key is the name of column and the value is the index of the column
     */
    void saveCurrencies(Map<String, Integer> columns);

    /**
     * Check if currency exists
     * @param currency the currency to test
     * @return true if currency exists, false otherwise
     */
    boolean exists(Currency currency);

}
