package com.digout.generator.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GraveGenerator {

    private final Random random = new Random();
    private final String[] graveTypes = new String[]{"coffin grave", "double coffin grave", "urn grave", "columbarium"};
    private final String NULL = "NULL";

    private final List<String> gravesList = new ArrayList<>();

    public void fulfillQuarter(int initialId, int expectedCount) {
        int finalId = initialId + expectedCount - 1;
        List<String> quarterGraveList = new ArrayList<>();
        for(int id = initialId; id <= finalId; id++) {
            String graveBuilder = pickGraveType() + "," +
                    id + "," +
                    generateOwnerId() +","
                    + generatePhotoReference();
            quarterGraveList.add(graveBuilder);
        }
        gravesList.addAll(quarterGraveList);
    }

    public List<String> getGravesList() {
        return gravesList;
    }

    public List<String> getUpdateGravesList(Map<Integer, Integer> capacityMap) {
        List<String> gravesToBeUpdated = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : capacityMap.entrySet()) {
            if (entry.getValue() == 0) {
                gravesToBeUpdated.add(entry.getKey().toString());
            }
        }
        return gravesToBeUpdated;
    }
    private String pickGraveType() {
        int typeNumber = random.nextInt(0, 3);
        return graveTypes[typeNumber];
    }

    // 20% of graves should not have owners
    private String generateOwnerId() {
        int pickedParam = random.nextInt(1, 100);
        String graveOwnerId;
        if(pickedParam <= 20) {
            graveOwnerId = NULL;
        } else {
            graveOwnerId = String.valueOf(random.nextInt(1, 1000));
        }
        return graveOwnerId;
    }

    private String generatePhotoReference() {
        int randomNumber = random.nextInt(1, 100);
        if (randomNumber <= 90) {
            return NULL;
        } else {
            return LocalDateTime.now().toString()
                    .replace("T", "")
                    .replace(":", "")
                    .replace("-", "")
                    .replace(".", "")
                    + random.nextInt(100, 999);
        }
    }
}
