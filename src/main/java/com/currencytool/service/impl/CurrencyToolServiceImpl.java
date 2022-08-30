package com.currencytool.service.impl;

import com.currencytool.model.Currency;
import com.currencytool.model.DataRow;
import com.currencytool.repository.CurrencyRepository;
import com.currencytool.repository.CurrencyToolRepository;
import com.currencytool.service.CurrencyToolService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.DoubleStream;

import static com.currencytool.util.Utils.*;

public class CurrencyToolServiceImpl implements CurrencyToolService {

    private final CurrencyToolRepository repository;
    private final CurrencyRepository currencyRepository;

    public CurrencyToolServiceImpl(CurrencyToolRepository repository, CurrencyRepository currencyRepository) {
        this.repository = repository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void loadDataFromFile(File file) throws IOException {
        CSVParser csvParser = new CSVParser(new FileReader(file), CSVFormat.EXCEL.withFirstRecordAsHeader());
        Map<String, Integer> header = csvParser.getHeaderMap();
        repository.saveColumns(header);
        currencyRepository.saveCurrencies(header);

        csvParser.iterator().forEachRemaining(csvRecord -> repository.saveRecord(csvRecord, (record) -> {
            DataRow.Builder builder = new DataRow.Builder();
            try {
                builder.withDate(parseDate(record.get(0)));
            } catch (Exception ignored) {
            }

            header.forEach((key, value) -> {
                if (value == 0) return; // Date column is the first one, so we skip it
                builder.withCurrency(key, parseDouble(record.get(value)));
            });

            return builder.build();
        }));
    }

    @Override
    public Optional<DataRow> getReferencesByDate(Date date) {
        return repository.findByDate(date);
    }

    @Override
    public List<String> getColumns() {
        return repository.getColumns();
    }

    @Override
    public Double convertCurrency(Date date, Currency source, Currency target, Double amount) throws Exception {
        if (!currencyRepository.exists(source)) {
            throw new Exception(String.format("Conversion failed. Could not find currency %s", source.getName()));
        } else if (!currencyRepository.exists(target)) {
            throw new Exception(String.format("Conversion failed. Could not find currency %s", target.getName()));
        }
        Optional<DataRow> dataRow = repository.findByDate(date);
        if (dataRow.isPresent()) {
            Optional<Double> sourceRef = dataRow.get().getCurrencyValue(source.getName());
            Optional<Double> targetRef = dataRow.get().getCurrencyValue(target.getName());

            if (sourceRef.isEmpty()) {
                throw new Exception(String.format("Conversion failed. Reference of %s was not available on %s", source.getName(), date));
            } else if (targetRef.isEmpty()) {
                throw new Exception(String.format("Conversion failed. Reference of %s was not available on %s", target.getName(), date));
            }

            return (1 / sourceRef.get()) * amount * targetRef.get();
        }

        return null;
    }

    @Override
    public Double findHighestReference(Date startDate, Date endDate, Currency currency) throws Exception {
        if (!currencyRepository.exists(currency)) {
            throw new Exception(String.format("Action failed. Could not find currency %s", currency.getName()));
        }
        List<DataRow> rows = repository.findByRange(toLocalDate(startDate), toLocalDate(endDate));
        Comparator<Double> comparator = Double::compareTo;
        Optional<Double> max = rows.stream()
                .map(dataRow -> dataRow.getCurrencyValue(currency.getName()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .max(comparator);

        return max.isPresent() ? max.get() : -1;
    }

    @Override
    public Double findAverageReference(Date startDate, Date endDate, Currency currency) throws Exception {
        if (!currencyRepository.exists(currency)) {
            throw new Exception(String.format("Action failed. Could not find currency %s", currency.getName()));
        }
        List<DataRow> rows = repository.findByRange(toLocalDate(startDate), toLocalDate(endDate));
        Function<List<Double>, Double> averageFunction = (doubles) -> {
            double sum = doubles.stream().mapToDouble(Double::doubleValue).sum();
            return doubles.size() > 0 ? sum / doubles.size() : 0;
        };
        List<Double> doubles = rows.stream()
                .map(dataRow -> dataRow.getCurrencyValue(currency.getName()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        return averageFunction.apply(doubles);
    }
}
