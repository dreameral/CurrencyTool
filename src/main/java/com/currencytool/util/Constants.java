package com.currencytool.util;

public class Constants {

    public static String DATE_FORMAT = "yyyy-MM-dd";
    public static String HELP = "" +
            "Usage:\n" +
            "  currencyTool> loadFile <filePath>\n" +
            "  currencyTool> showAllReferences <date>\n" +
            "  currencyTool> convertCurrency <date> <currency1> <currency2>\n" +
            "  currencyTool> highestReference <startDate> <endDate> <currency>\n" +
            "  currencyTool> averageReference <startDate> <endDate> <currency>\n" +
            "  currencyTool> help\n" +
            "  currencyTool> exit\n" +
            "\n" +
            "**NOTE: All date arguments must be in the format 'yyyy-MM-dd'\n" +
            "\n" +
            "Commands:\n" +
            "  loadFile             Loads a CSV file with currency data in the memory.\n" +
            "  showAllReferences    Retrieve the reference rate data for a given Date for all available currencies.\n" +
            "  convertCurrency      Given a Date, source Currency (eg. JPY), target Currency (eg. GBP), and an Amount, returns the Amount given converted from the first to the second Currency as it would have been on that Date (assuming zero fees).\n" +
            "  highestReference     Given a start Date, an end Date and a Currency, returns the highest reference exchange rate that the Currency achieved for the period.\n" +
            "  averageReference     Given a start Date, an end Date and a Currency, determines and returns the average reference exchange rate of that Currency for that period.\n" +
            "  help                 Show this screen.\n" +
            "  exit                 Exits application.\n" +
            "\n" +
            "";

}
