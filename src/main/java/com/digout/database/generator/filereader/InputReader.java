package com.digout.database.generator.filereader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

public class InputReader {


    private static final String NAME_PATH = "src\\main\\resources\\inputs\\name.txt";
    private static final String SURNAME_PATH = "src\\main\\resources\\inputs\\surname.txt";
    private static final String CEMETERY_PATH = "src\\main\\resources\\inputs\\cemetery.txt";

    private static final Integer nameCount = 800;
    private static final Integer surnameCount = 665;
    private static final Integer cemeteryCount = 560;

    private final Random random = new Random();

    public String getFirstName() {
        return getLine(NAME_PATH, nameCount);
    }

    public String getSurname() {
        return getLine(SURNAME_PATH, surnameCount);
    }

    public String getCemetery() {
        return getLine(CEMETERY_PATH, cemeteryCount);
    }

    private String getLine(String filePath, int bound) {
        int lineNumber = random.nextInt(1, bound);
        String line = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(filePath));
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
