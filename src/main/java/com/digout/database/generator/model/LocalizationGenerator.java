package com.digout.database.generator.model;

import com.digout.database.generator.filereader.InputReader;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class LocalizationGenerator{

    private final GraveGenerator graveGenerator = new GraveGenerator();

    private final List<String> quarterList = new ArrayList<>();
    private final List<String> quarterSectorList = new ArrayList<>();
    private final List<String> localizationList = new ArrayList<>();

    private final Random random = new Random();

    private int localizationCount = 0;
    private int cemeteryCount = 0;

    public List<String> getLocalizationList(int expected) {
        while(localizationCount <= expected) {
            fulfillCemetery();
        }
        System.out.println(quarterList);
        return localizationList;
    }

    public List<String> getGraveList() {
        return graveGenerator.getGravesList();
    }

    private void fulfillCemetery() {
        cemeteryCount = 0;
        String cemeteryName = popNextCemeteryName();
        while(cemeteryCount <= 1000) {
            int nextId = localizationCount + 1;
            fulfillQuarter(cemeteryName, nextId);
        }
    }

    private String popNextCemeteryName() throws RuntimeException {
        InputReader inputReader = new InputReader();
        return inputReader.getCemetery();
    }

    private void fulfillQuarter(String cemeteryName, int nextId) {
        int rowsCount = generateRowOrColumnRange();
        int columnCount = generateRowOrColumnRange();
        String quarter = popNextQuarter();
        quarterList.add(quarter);

        int expectedCount = rowsCount * columnCount;

        for(int r = 1; r <= rowsCount; r++) {
            for(int c = 1; c <= columnCount; c++) {
                generateLocalizationLine(cemeteryName, quarter, r, c);
            }
        }
        graveGenerator.fulfillQuarter(nextId, expectedCount);
    }

    private void generateLocalizationLine(String cemetery, String quarter, int row, int column) {
        StringBuilder builder = new StringBuilder();
        builder.append(cemetery).append(",");
        builder.append(quarter).append(",");
        builder.append(row).append(",");
        builder.append(column);
        localizationList.add(builder.toString());
        incrementCounting();
    }

    private void incrementCounting() {
        localizationCount++;
        cemeteryCount++;
    }

    private String popNextQuarter() {
        if(quarterSectorList.isEmpty()) {
            generateNewQuarterSector();
        }
        String nextQuarter = quarterSectorList.get(0);
        quarterSectorList.remove(0);
        return nextQuarter;
    }

    private void generateNewQuarterSector() {
        char quarterLetter = generateQuarterLetter();
        int lastNum = getLastNumberForLetter(quarterLetter);
        int quarterMaxNumber = generateQuarterNumber(lastNum);
        System.out.println("New sector has been generated: " + quarterLetter + quarterMaxNumber);
        for (int i = lastNum + 1 ; i <= quarterMaxNumber; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append(quarterLetter);
            builder.append(i);
            quarterSectorList.add(builder.toString());
        }
    }

    private int generateRowOrColumnRange() {
        return random.nextInt(4,14);
    }

    private char generateQuarterLetter(){
        if(quarterList.isEmpty()) {
            return 'A';
        } else {
            String lastQuarter = quarterList.get(quarterList.size()-1);
            char lastQuarterLetter = lastQuarter.charAt(0);
            if(lastQuarterLetter == 'Z') {
                return 'A';
            }
            return (char) (lastQuarterLetter + 1);
        }
    }

    private int generateQuarterNumber(int lastNumber) {
            return random.nextInt(lastNumber + 1, lastNumber + 7);
    }

    private int getLastNumberForLetter(char letter) {
        int lastNumber = 0;
        for (String quarter : quarterList) {
            if (quarter.charAt(0) == letter) {
                lastNumber = Integer.parseInt(quarter.substring(1, quarter.length()));
            }
        }
        return lastNumber;
    }
}
