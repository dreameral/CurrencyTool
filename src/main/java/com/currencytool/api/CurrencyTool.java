package com.currencytool.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public interface CurrencyTool {

    void loadFile(String filePath) throws IOException;

    String showAllReferences(Date date);

    Double convertCurrency(Date date, String sourceCurrency, String targetCurrency, Double amount) throws Exception;

    Double highestReference(Date startDate, Date endDate, String currency);

    Double averageReference(Date startDate, Date endDate, String currency);

}
