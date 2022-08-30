package com.currencytool.repository.impl;

import com.currencytool.model.DataRow;
import com.currencytool.repository.CurrencyToolRepository;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.currencytool.util.Utils.toLocalDate;

public class CurrencyToolRepositoryImpl implements CurrencyToolRepository {

    private List<DataRow> rows = new ArrayList<>();
    private final List<String> columns = new ArrayList<>();

    @Override
    public void saveRecord(CSVRecord record, Function<CSVRecord, DataRow> function) {
        rows.add(function.apply(record));
    }

    @Override
    public void saveColumns(Map<String, Integer> columnsMap) {
        columnsMap.forEach((key, value) -> this.columns.add(value, key));
    }

    @Override
    public List<String> getColumns() {
        return this.columns;
    }

    @Override
    public Optional<DataRow> findByDate(Date date) {
        return rows.stream().filter(dataRow -> dataRow.getDate().equals(date)).findFirst();
    }

    @Override
    public List<DataRow> findByRange(LocalDate startDate, LocalDate endDate) {
        return rows.stream().filter(dataRow ->  toLocalDate(dataRow.getDate()).isAfter(startDate) &&
                toLocalDate(dataRow.getDate()).isBefore(endDate))
                .collect(Collectors.toList());
    }
}
