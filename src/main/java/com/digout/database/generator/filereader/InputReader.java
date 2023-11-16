package com.digout.database.generator.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class InputReader {


    private static final String NAME_PATH = "src\\main\\resources\\inputs\\name.txt";
    private static final String SURNAME_PATH = "src\\main\\resources\\inputs\\surname.txt";
    private static final String CEMETERY_PATH = "src\\main\\resources\\inputs\\cemetery.txt";

    private static final Integer NAME_COUNT = 800;
    private static final Integer SURNAME_COUNT = 665;
    private static final Integer CEMETERY_COUNT = 560;

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

    private String getLine(String filePath, int bound) {
        int lineNumber = random.nextInt(1, bound);
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
