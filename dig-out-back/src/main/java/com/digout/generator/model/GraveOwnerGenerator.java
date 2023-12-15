package com.digout.generator.model;

import com.digout.generator.inputreader.InputReader;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GraveOwnerGenerator {

    private final Random random = new Random();
    private final InputReader inputReader = new InputReader();
    private final List<String> graveOwnerList = new ArrayList<>();

    private final String NULL = "NULL";

    public List<String> getGraveOwnerList(int expected) {
        while (graveOwnerList.size() < expected) {
            int randomNumber = random.nextInt();
            if(randomNumber < 30) {
                generateEmptyGraveOwner();
            } else {
                generateGraveOwner();
            }
        }
        return graveOwnerList;
    }

    private void generateGraveOwner() {
        String graveOwner = getNameAndSurname() + ',' + generatePesel() + ',' + getStreetName()
                + ',' + generateParcel() + ',' + getCityPostalCodeAndCountry() + ',' + getPhoneNumber();
        graveOwnerList.add(graveOwner);
    }

    private void generateEmptyGraveOwner() {
        String graveOwner = getNameAndSurname() + ',' + NULL + ',' + NULL + ',' + NULL + ',' + NULL + ',' + NULL
        + ',' + NULL +  ',' + NULL ;
        graveOwnerList.add(graveOwner);
    }

    private String getNameAndSurname() {
        return inputReader.getFirstName() + "," + inputReader.getSurname();
    }

    private String generatePesel() {
        int randomNumber = random.nextInt(1, 100);
        String birthDate = "";
        if (randomNumber < 10) {
            birthDate = getBirthDate(2003, 2012);
        } else if (randomNumber < 40) {
            birthDate = getBirthDate(1993, 2002);
        } else if (randomNumber < 70) {
            birthDate = getBirthDate(1983, 1992);
        } else if (randomNumber < 90) {
            birthDate = getBirthDate(1973, 1982);
        } else if (randomNumber < 95) {
            birthDate = getBirthDate(1963, 1972);
        } else if (randomNumber < 98) {
            birthDate = getBirthDate(1953, 1962);
        } else {
            birthDate = getBirthDate(1943, 1952);
        }
        birthDate = birthDate.substring(2);
        String suffix = String.valueOf(random.nextInt(10000, 99999));
        return (birthDate + suffix).replace("-", "");
    }

    private String getBirthDate(int startYear, int endYear) {
        LocalDate startDate = LocalDate.of(startYear,
                Month.JANUARY, 1);
        LocalDate endDate = LocalDate.now();
        if (endYear != LocalDate.now().getYear()) {
            endDate = LocalDate.of(endYear,
                    Month.DECEMBER, 31);
        }
        return generateFromEpochDay(startDate, endDate).toString();
    }

    private LocalDate generateFromEpochDay(LocalDate startDate, LocalDate endDate) {
        long startEpochDate = startDate.toEpochDay();
        long endEpochDate = endDate.toEpochDay();

        long epochDate = random.nextLong(startEpochDate, endEpochDate);

        return LocalDate.ofEpochDay(epochDate);
    }

    private String getStreetName() {
        return inputReader.getStreet();
    }

    private String generateParcel() {
        int randomNumber = random.nextInt(1, 100);

        if (randomNumber < 10) {
            String prefix = String.valueOf(random.nextInt(1, 30));
            String suffix = String.valueOf(random.nextInt(1, 30));
            return prefix + '/' + suffix;
        } else if (randomNumber < 20) {
            String prefix = String.valueOf(random.nextInt(1, 30));
            String suffix = String.valueOf(random.nextInt(1, 30));
            return prefix + "A/" + suffix;
        } else if (randomNumber < 30) {
            String prefix = String.valueOf(random.nextInt(1, 30));
            String suffix = String.valueOf(random.nextInt(1, 30));
            return prefix + "B/" + suffix;
        } else if (randomNumber < 40) {
            String prefix = String.valueOf(random.nextInt(1, 30));
            String suffix = String.valueOf(random.nextInt(1, 30));
            return prefix + "C/" + suffix;
        } else if (randomNumber < 50) {
            String prefix = String.valueOf(random.nextInt(1, 30));
            String suffix = String.valueOf(random.nextInt(1, 30));
            return prefix + "D/" + suffix;
        } else if (randomNumber < 60) {
            String prefix = String.valueOf(random.nextInt(1, 30));
            String suffix = String.valueOf(random.nextInt(1, 30));
            return prefix + "E/" + suffix;
        } else if (randomNumber < 80) {
            return String.valueOf(random.nextInt(1, 99));
        } else {
            return String.valueOf(random.nextInt(99, 250));
        }
    }

    private String getCityPostalCodeAndCountry() {
        return inputReader.getCityPostalCodeCountry();
    }

    private String getPhoneNumber() {
        int randomNumber = random.nextInt(1, 100);

        if (randomNumber < 30) {
            return NULL;
        } else {
            return String.valueOf(random.nextInt(random.nextInt(500, 699))) + '-'
                    + random.nextInt(random.nextInt(100, 999)) + '-'
                    + random.nextInt(random.nextInt(100, 999));
        }
    }
}
