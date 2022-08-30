package com.currencytool.service;

import com.currencytool.model.Currency;
import com.currencytool.model.DataRow;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CurrencyToolService {

    void loadDataFromFile(File file) throws IOException;

    Optional<DataRow> getReferencesByDate(Date date);

    List<String> getColumns();

    Double convertCurrency(Date date, Currency source, Currency target, Double amount) throws Exception;

    Double findHighestReference(Date startDate, Date endDate, Currency currency);

    Double findAverageReference(Date startDate, Date endDate, Currency currency);
}
