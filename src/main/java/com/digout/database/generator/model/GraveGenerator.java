package com.digout.database.generator.model;

import com.digout.database.generator.filereader.InputReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraveGenerator {

    private final Random random = new Random();
    private final String[] graveTypes = new String[]{"coffin grave", "double coffin grave", "urn grave", "columbarium"};

    private final List<String> gravesList = new ArrayList<>();

    public void fulfillQuarter(int initialId, int expectedCount) {
        int finalId = initialId + expectedCount - 1;
        List<String> quarterGraveList = new ArrayList<>();
        for(int id = initialId; id <= finalId; id++) {
            StringBuilder graveBuilder = new StringBuilder();
            graveBuilder.append(pickGraveType()).append(",");
            graveBuilder.append(id).append(",");
            graveBuilder.append(generateOwnerName());
            quarterGraveList.add(graveBuilder.toString());
        }
        gravesList.addAll(quarterGraveList);
    }

    public List<String> getGravesList() {
        return gravesList;
    }

    private String pickGraveType() {
        int typeNumber = random.nextInt(0, 3);
        return graveTypes[typeNumber];
    }

    // 20% of graves should not have owners
    private String generateOwnerName() {
        int pickedParam = random.nextInt(1, 100);
        InputReader inputReader = new InputReader();
        String ownerName;
        if(pickedParam <= 20) {
            ownerName = "NULL";
        } else {
            StringBuilder ownerBuilder = new StringBuilder();
            ownerBuilder.append(inputReader.getFirstName());
            ownerBuilder.append(" ");
            ownerBuilder.append(inputReader.getSurname());
            ownerName = ownerBuilder.toString();
        }
        return ownerName;
    }
}
