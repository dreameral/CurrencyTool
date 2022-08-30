package com.currencytool.service;

import com.currencytool.model.Currency;
import com.currencytool.model.DataRow;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CurrencyToolService {

    /**
     * Saves the CSV data in memory
     * @param file the CSV data file
     * @throws IOException thrown when the file cannot be read
     */
    void loadDataFromFile(File file) throws IOException;

    /**
     * Get a DataRow instance of the currency data on the given date
     * @param date the given date
     * @return an optional of currency data on the given date. An empty optional if there is no data on the given date
     */
    Optional<DataRow> getReferencesByDate(Date date);

    /**
     * Get the list of data set columns
     * @return the list of columns
     */
    List<String> getColumns();

    /**
     * Converts an amount from a source currency to a target currency, based on the exchange rate on the given date
     * @param date The date to base on for the exchange rate
     * @param source the source currency
     * @param target the target currency
     * @param amount the amount to convert
     * @return the converted amount as double
     * @throws Exception throws exception if the currencies provided are not valid
     */
    Double convertCurrency(Date date, Currency source, Currency target, Double amount) throws Exception;

    /**
     * Returns the highest reference rate of the given currency on the specified date range
     * @param startDate the start of the date range, inclusive
     * @param endDate the end of the date range, inclusive
     * @param currency the name of the currency
     * @return the highest reference rate of the currency on the date range
     * @throws Exception throws exception if the given currency is not valid
     */
    Double findHighestReference(Date startDate, Date endDate, Currency currency) throws Exception;

    /**
     * Returns the average reference rate of the given currency on the specified date range
     * @param startDate the start of the date range, inclusive
     * @param endDate the end of the date range, inclusive
     * @param currency the name of the currency
     * @return the average reference rate of the currency on the date range
     * @throws Exception throws exception if the given currency is not valid
     */
    Double findAverageReference(Date startDate, Date endDate, Currency currency) throws Exception;
}
