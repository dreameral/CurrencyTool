package com.currencytool.model;

public class Currency {
    private final String name;

    public Currency(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Currency && name.equals(((Currency) obj).getName());
    }
}
