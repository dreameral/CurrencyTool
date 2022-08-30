package com.currencytool;

import com.currencytool.api.CurrencyTool;
import com.currencytool.api.impl.CurrencyToolImpl;
import com.currencytool.repository.impl.CurrencyRepositoryImpl;
import com.currencytool.repository.impl.CurrencyToolRepositoryImpl;
import com.currencytool.service.impl.CurrencyToolServiceImpl;
import com.currencytool.util.Constants;

import java.util.Scanner;

import static com.currencytool.util.Utils.parseDate;

public class Main {

    private CurrencyTool currencyTool;

    public static void main(String[] args) {
        Main m = new Main();
        m.registerDependencies();

        Scanner scanner = new Scanner(System.in);
        System.out.print("currencyTool>");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("exit")) {
                break;
            } else if (line.equals("help")) {
                System.out.println(Constants.HELP);
                System.out.print("currencyTool>");
                continue;
            }

            args = line.split(" ");

            if (args.length == 0) {
                invalidArguments();
                System.out.print("currencyTool>");
                continue;
            }

            String command = args[0];
            switch (command) {
                case "loadFile" -> m.loadFileCommand(args);
                case "showAllReferences" -> m.showAllReferencesCommand(args);
                case "convertCurrency" -> m.convertCurrencyCommand(args);
                case "highestReference" -> m.highestReferenceCommand(args);
                case "averageReference" -> m.averageReferenceCommand(args);
                default -> System.out.println("Unrecognized command. Please refer our documentation.");
            }

            System.out.print("currencyTool>");
        }

    }

    private void registerDependencies() {
        currencyTool = new CurrencyToolImpl(new CurrencyToolServiceImpl(new CurrencyToolRepositoryImpl(), new CurrencyRepositoryImpl()));
    }

    private void loadFileCommand(String[] args) {
        if (args.length < 2) {
            invalidArguments();
            return;
        }

        try {
            currencyTool.loadFile(args[1]);
            System.out.println("Data were successfully loaded.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void showAllReferencesCommand(String[] args) {
        if (args.length < 2) {
            invalidArguments();
            return;
        }

        try {
            String result = currencyTool.showAllReferences(parseDate(args[1]));
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void convertCurrencyCommand(String[] args) {
        if (args.length < 5) {
            invalidArguments();
            return;
        }

        try {
            Double result = currencyTool.convertCurrency(parseDate(args[1]), args[2], args[3], Double.parseDouble(args[4]));
            System.out.printf("%.5f", result);
            System.out.println();
        } catch (Exception e) {
            if (e instanceof NumberFormatException) {
                System.out.printf("Invalid amount %s%n", args[4]);
            } else
                System.out.println(e.getMessage());
        }
    }

    private void highestReferenceCommand(String[] args) {
        if (args.length < 4) {
            invalidArguments();
            return;
        }

        try {
            Double result = currencyTool.highestReference(parseDate(args[1]), parseDate(args[2]), args[3]);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void averageReferenceCommand(String[] args) {
        if (args.length < 4) {
            invalidArguments();
            return;
        }

        try {
            Double result = currencyTool.averageReference(parseDate(args[1]), parseDate(args[2]), args[3]);
            System.out.printf("%.5f", result);
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void invalidArguments() {
        System.out.println("Invalid arguments. Please refer the documentation:");
        System.out.println(Constants.HELP);
    }

}
