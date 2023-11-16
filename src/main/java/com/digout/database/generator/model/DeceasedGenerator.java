package com.digout.database.generator.model;

import com.digout.database.generator.filereader.InputReader;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DeceasedGenerator {

    private final Map<Integer, Integer> graveCapacityMap = new HashMap<>();
    private final Random random = new Random();

    private final List<String> deceasedList = new ArrayList<>();

    int graveCount = 0;

    public List<String> getDeceasedList(int expected) {
        while(deceasedList.size() < expected) {
            generateDeceased();
        }
        return deceasedList;
    }

    private void generateDeceased() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getNameAndSurname()).append(",");
        stringBuilder.append(getBirthAndDeathDate()).append(",");
        stringBuilder.append(isInfectiousDisease()).append(",");
        stringBuilder.append(getGraveId());
        String deceased = stringBuilder.toString();
        deceasedList.add(deceased);
    }

    private String getNameAndSurname() {
        InputReader inputReader = new InputReader();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(inputReader.getFirstName());
        stringBuilder.append(",");
        stringBuilder.append(inputReader.getSurname());
        return stringBuilder.toString();
    }

    private String isInfectiousDisease(){
        int picked = random.nextInt(1,100);
        if(picked < 3) {
            return "true";
        } else {
            return "false";
        }
    }

    private String getBirthAndDeathDate() {
        int pickedParam = random.nextInt(1, 100);

        if(pickedParam <= 25) {
            return getDatesInPeriods(1900, 1925, 15, 25, 35, 45, 65,
                    85, 91, 95, 98);
        } else if (pickedParam <= 55) {
            return getDatesInPeriods(1926, 1950, 10, 18, 26, 36, 51,
                    71, 81, 90, 95);
        } else if (pickedParam <= 80) {
            return getDatesInPeriods(1951, 1975, 5, 10, 15, 23, 35,
                    55, 75, 90, 99);
        } else if (pickedParam <= 95) {
            return getDatesInPeriods(1976, 2000, 2, 4, 7, 12, 22,
                    42, 62, 82, 97);
        } else {
            return getDatesInPeriods(2001, 2023, 1, 3, 6, 11, 21,
                    36, 56, 76, 96);
        }
    }

    private String getDatesInPeriods(int startYear, int endYear, int ten, int twenty, int thirty, int forty, int fifty,
                                     int sixty, int seventy, int eighty, int ninety) {

        LocalDate birthDate = birthDateCalculator(startYear, endYear);

        LocalDate deathDate = LocalDate.now();
        boolean isDeathDateValid = false;

        while (!isDeathDateValid) {
            int pickedParam = random.nextInt(1, 100);

            if (pickedParam <= ten) {
                deathDate = deathDateCalculator(birthDate, 10);
            } else if (pickedParam <= twenty) {
                deathDate = deathDateCalculator(birthDate, 20);
            } else if (pickedParam <= thirty) {
                deathDate = deathDateCalculator(birthDate, 30);
            } else if (pickedParam <= forty) {
                deathDate = deathDateCalculator(birthDate, 40);
            } else if (pickedParam <= fifty) {
                deathDate = deathDateCalculator(birthDate, 50);
            } else if (pickedParam <= sixty) {
                deathDate = deathDateCalculator(birthDate, 60);
            } else if (pickedParam <= seventy) {
                deathDate = deathDateCalculator(birthDate, 70);
            } else if (pickedParam <= eighty) {
                deathDate = deathDateCalculator(birthDate, 80);
            } else if (pickedParam <= ninety) {
                deathDate = deathDateCalculator(birthDate, 90);
            } else {
                deathDate = deathDateCalculator(birthDate, 110);
            }
            isDeathDateValid = isDeathDateValid(deathDate);
        }

        return getDatesAsString(birthDate, deathDate);
    }

    private boolean isDeathDateValid(LocalDate deathDate) {
        return deathDate.isBefore(LocalDate.now());
    }

    private String getDatesAsString(LocalDate birthDate, LocalDate deathDate) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(birthDate).append(",");
        stringBuilder.append(deathDate);
        return stringBuilder.toString();
    }

    private LocalDate birthDateCalculator(int startYear, int endYear) {
        LocalDate startDate = LocalDate.of(startYear,
                Month.JANUARY, 1);
        LocalDate endDate = LocalDate.now();
        if (endYear != LocalDate.now().getYear()) {
            endDate = LocalDate.of(endYear,
                    Month.DECEMBER, 31);
        }
        return generateFromEpochDay(startDate, endDate);
    }

    private LocalDate deathDateCalculator(LocalDate birthDate, int years) {
        LocalDate startDate;
        LocalDate endDate;
        try {
            startDate = LocalDate.of(birthDate.getYear() + years - 10,
                    birthDate.getMonth(), birthDate.getDayOfMonth());
        } catch (DateTimeException exception) {
            startDate = LocalDate.of(birthDate.getYear() + years - 10,
                    birthDate.getMonth(), birthDate.getDayOfMonth() - 1);
        }
        try {
            endDate = LocalDate.of(birthDate.getYear() + years,
                    birthDate.getMonth(), birthDate.getDayOfMonth());
        } catch (DateTimeException exception) {
            endDate = LocalDate.of(birthDate.getYear() + years,
                    birthDate.getMonth(), birthDate.getDayOfMonth() - 1);
        }
        return generateFromEpochDay(startDate, endDate);
    }

    private LocalDate generateFromEpochDay(LocalDate startDate, LocalDate endDate) {
        long startEpochDate = startDate.toEpochDay();
        long endEpochDate = endDate.toEpochDay();

        long epochDate = random.nextLong(startEpochDate, endEpochDate);

        return LocalDate.ofEpochDay(epochDate);
    }

    private int getGraveId() {
        boolean isGraveAvailable = false;
        int graveId = 0;
        while(!isGraveAvailable) {
            graveId = random.nextInt(1, graveCapacityMap.size());
            isGraveAvailable = isGraveAvailable(graveId);
        }
        int graveCapacity = graveCapacityMap.get(graveId);
        graveCapacityMap.replace(graveId, graveCapacity, graveCapacity - 1);
        return graveId;
    }

    private boolean isGraveAvailable(int graveId) {
        int graveCapacity = 0;
            graveCapacity = graveCapacityMap.get(graveId);
        return graveCapacity != 0;
    }

    // Is adding grave to the grave capacity map
    // with graveID as Key and capacity as Value
    public void addGraveToCapacityMap(String[] graveAttributes) {
        graveCount++;
        int capacity = 0;
        switch (graveAttributes[0]) {
            case "coffin grave":
                capacity = 2;
                break;
            case "double coffin grave":
                capacity = 4;
                break;
            case "urn grave":
                capacity = 4;
                break;
            case "columbarium":
                capacity = 6;
                break;
            default: capacity = 2;
        }
        graveCapacityMap.put(graveCount, capacity);
    }
}
