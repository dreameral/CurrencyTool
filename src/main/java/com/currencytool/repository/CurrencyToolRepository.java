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

    void saveRecord(CSVRecord record, Function<CSVRecord, DataRow> function);

    void saveColumns(Map<String, Integer> columns);

    List<String> getColumns();

    Optional<DataRow> findByDate(Date date);

    List<DataRow> findByRange(LocalDate startDate, LocalDate endDate);
}