package com.currencytool.api;

import java.io.IOException;
import java.util.Date;

public interface CurrencyTool {

    /**
     * Loads CSV data into memory from specified filepath
     * @param filePath path to the CSV file containing the data
     * @throws IOException if file is not found
     */
    void loadFile(String filePath) throws IOException;

    /**
     * Returns a CSV format data for all the currency values on the given date
     * @param date the given date
     * @return CSV data of all the currencies
     */
    String showAllReferences(Date date);

    /**
     * Converts an amount from a source currency to a target currency, based on the exchange rate on the given date
     * @param date The date to base on for the exchange rate
     * @param sourceCurrency the source currency
     * @param targetCurrency the target currency
     * @param amount the amount to convert
     * @return the converted amount as double
     * @throws Exception throws exception if the currencies provided are not valid
     */
    Double convertCurrency(Date date, String sourceCurrency, String targetCurrency, Double amount) throws Exception;

    /**
     * Returns the highest reference rate of the given currency on the specified date range
     * @param startDate the start of the date range, inclusive
     * @param endDate the end of the date range, inclusive
     * @param currency the name of the currency
     * @return the highest reference rate of the currency on the date range
     * @throws Exception throws exception if the given currency is not valid
     */
    Double highestReference(Date startDate, Date endDate, String currency) throws Exception;

    /**
     * Returns the average reference rate of the given currency on the specified date range
     * @param startDate the start of the date range, inclusive
     * @param endDate the end of the date range, inclusive
     * @param currency the name of the currency
     * @return the average reference rate of the currency on the date range
     * @throws Exception throws exception if the given currency is not valid
     */
    Double averageReference(Date startDate, Date endDate, String currency) throws Exception;

}
