package com.currencytool.model;

import com.currencytool.util.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DataRow {

    private final Date date;
    private final Map<String, Optional<Double>> currencies;

    private DataRow(Date date, Map<String, Optional<Double>> currencies) {
        this.date = date;
        this.currencies = currencies;
    }

    public Optional<Double> getCurrencyValue(String name) {
        return currencies.get(name);
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(new SimpleDateFormat(Constants.DATE_FORMAT).format(date)).append(",");
        currencies.forEach((key, value) -> sb.append(value.isPresent() ? value.get() : "N/A").append(","));
        return sb.append("\n").toString();
    }

    public static class Builder {
        private Date date;
        private final Map<String, Optional<Double>> currencies = new HashMap<>();

        public DataRow build() {
            return new DataRow(date, currencies);
        }

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withCurrency(String name, Optional<Double> amount) {
            currencies.put(name, amount);
            return this;
        }

    }

}
