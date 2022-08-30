# CurrencyTool
A simple shell application that loads a CSV file with currency data and offers some filtering by date or currencies.


### Shell Documentation

```
currencyTool>help
Usage:
  currencyTool> loadFile <filePath>
  currencyTool> showAllReferences <date>
  currencyTool> convertCurrency <date> <currency1> <currency2> <amount>
  currencyTool> highestReference <startDate> <endDate> <currency>
  currencyTool> averageReference <startDate> <endDate> <currency>
  currencyTool> help
  currencyTool> exit

**NOTE: All date arguments must be in the format 'yyyy-MM-dd'

Commands:
  loadFile             Loads a CSV file with currency data in the memory.
  showAllReferences    Retrieve the reference rate data for a given Date for all available currencies.
  convertCurrency      Given a Date, source Currency (eg. JPY), target Currency (eg. GBP), and an Amount, returns the Amount given converted from the first to the second Currency as it would have been on that Date (assuming zero fees).
  highestReference     Given a start Date, an end Date and a Currency, returns the highest reference exchange rate that the Currency achieved for the period.
  averageReference     Given a start Date, an end Date and a Currency, determines and returns the average reference exchange rate of that Currency for that period.
  help                 Show this screen.
  exit                 Exits application.
```
