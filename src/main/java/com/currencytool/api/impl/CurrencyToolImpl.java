package com.currencytool.api.impl;

import com.currencytool.api.CurrencyTool;
import com.currencytool.model.Currency;
import com.currencytool.model.DataRow;
import com.currencytool.service.CurrencyToolService;
import com.currencytool.util.Constants;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CurrencyToolImpl implements CurrencyTool {

    private final CurrencyToolService service;

    public CurrencyToolImpl(CurrencyToolService service) {
        this.service = service;
    }

    @Override
    public void loadFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(String.format("Could not find file from the file path '%s'", filePath));
        }

        service.loadDataFromFile(file);
    }

    @Override
    public String showAllReferences(Date date) {
        Optional<DataRow> row = service.getReferencesByDate(date);
        return getOrderedDataset(service.getColumns(), row);
    }

    @Override
    public Double convertCurrency(Date date, String sourceCurrency, String targetCurrency, Double amount) throws Exception {
        return service.convertCurrency(date, new Currency(sourceCurrency), new Currency(targetCurrency), amount);
    }

    @Override
    public Double highestReference(Date startDate, Date endDate, String currency) {
        return service.findHighestReference(startDate, endDate, new Currency(currency));
    }

    @Override
    public Double averageReference(Date startDate, Date endDate, String currency) {
        return service.findAverageReference(startDate, endDate, new Currency(currency));
    }

    private static String getOrderedDataset(List<String> columns, Optional<DataRow> row) {
        StringBuilder result = new StringBuilder();
        result.append(String.join(",", columns)).append("\n");
        if (row.isEmpty())
            return result.toString();

        for (String column : columns) {
            if (column.equals("Date")) {
                result.append(new SimpleDateFormat(Constants.DATE_FORMAT).format(row.get().getDate()));
            } else {
                result.append(row.get().getCurrencyValue(column).isPresent() ? row.get().getCurrencyValue(column).get() : "N/A");
            }
            result.append(",");
        }
        return result.toString();
    }
}
