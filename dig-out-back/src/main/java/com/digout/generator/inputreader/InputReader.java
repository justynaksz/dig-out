package com.digout.generator.inputreader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class InputReader {


    private static final String NAME_PATH = "dig-out-back\\src\\main\\resources\\input\\name.txt";
    private static final String SURNAME_PATH = "dig-out-back\\src\\main\\resources\\input\\surname.txt";
    private static final String CEMETERY_PATH = "dig-out-back\\src\\main\\resources\\input\\cemetery.txt";
    private static final String WORD_PATH = "dig-out-back\\src\\main\\resources\\input\\word.txt";
    private static final String EMAIL_PATH = "dig-out-back\\src\\main\\resources\\input\\e_mail.txt";
    private static final String STREET_PATH = "dig-out-back\\src\\main\\resources\\input\\street.txt";
    private static final String CITY_POSTAL_CODE_COUNTRY_PATH = "dig-out-back\\src\\main\\resources\\input\\city_postal_code_country.txt";

    private static final Integer NAME_COUNT = 800;
    private static final Integer SURNAME_COUNT = 665;
    private static final Integer CEMETERY_COUNT = 560;
    private static final Integer NOUN_COUNT = 200;
    private static final Integer ADJECTIVE_COUNT_START = 202;
    private static final Integer ADJECTIVE_COUNT_END = 401;
    private static final Integer EMAIL_COUNT = 1000;
    private static final Integer STREET_COUNT = 1000;
    private static final Integer CITY_POSTAL_CODE_COUNTRY_COUNT = 1200;

    private final Random random = new Random();

    public String getFirstName() {
        return getLine(NAME_PATH, NAME_COUNT);
    }

    public String getSurname() {
        return getLine(SURNAME_PATH, SURNAME_COUNT);
    }

    public String getCemetery() {
        return getLine(CEMETERY_PATH, CEMETERY_COUNT);
    }

    public String getNoun() {
        return getLine(WORD_PATH, NOUN_COUNT);
    }

    public String getAdjective() {
        return getLine(WORD_PATH, ADJECTIVE_COUNT_START, ADJECTIVE_COUNT_END);
    }

    public String getEmail() {
        return getLine(EMAIL_PATH, EMAIL_COUNT);
    }

    public String getStreet() {
        return getLine(STREET_PATH, STREET_COUNT);
    }

    public String getCityPostalCodeCountry() {
        return getLine(CITY_POSTAL_CODE_COUNTRY_PATH, CITY_POSTAL_CODE_COUNTRY_COUNT);
    }

    private String getLine(String filePath, int bound) {
        int lineNumber = random.nextInt(1, bound);
        return getLineOfNumber(filePath, lineNumber);
    }

    private String getLine(String filePath, int start, int end) {
        int lineNumber = random.nextInt(start, end);
        return getLineOfNumber(filePath, lineNumber);
    }

    private String getLineOfNumber(String filePath, int lineNumber) {
        String line = "";
        try (BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(filePath))) {
            int currentLine = 0;
            while (line != null) {
                currentLine++;
                line = bufferedReader.readLine();
                if(currentLine == lineNumber) {
                    return line;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return line;
    }
}
