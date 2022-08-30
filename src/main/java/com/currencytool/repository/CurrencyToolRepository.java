package com.currencytool.repository;

import com.currencytool.model.DataRow;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public interface CurrencyToolRepository {

    /**
     * Saves a DataRow object from a CSVRecord in the memory
     * @param record the CSV record parsed from the CSV data file
     * @param function the function to convert a CSVRecord instance to a DataRow instance
     */
    void saveRecord(CSVRecord record, Function<CSVRecord, DataRow> function);

    /**
     * Saves the list of columns processed from the header of the CSV data file. Order of the columns is handled to be the same as in the csv file.
     * @param columns the header of the CSV data file as a map, key is the name of the column, value is the index of the column
     */
    void saveColumns(Map<String, Integer> columns);

    /**
     * Get the list of columns with the same order as in the processed CSV data file
     * @return returns the list of columns
     */
    List<String> getColumns();

    /**
     * Returns on optional data row with currency data on the given date. Returns an empty optional if there is no data on the given date
     * @param date the given date
     * @return An optional of data row with the data on the given date. Returns an empty optional if there is no data on the given date
     */
    Optional<DataRow> findByDate(Date date);

    /**
     * Get a list of data rows on the given date range
     * @param startDate the start of the date range, inclusive
     * @param endDate the end of the date range, inclusive
     * @return a list of data rows of the given range
     */
    List<DataRow> findByRange(LocalDate startDate, LocalDate endDate);
}