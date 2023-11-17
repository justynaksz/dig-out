package com.digout.database.generator;

import com.digout.database.generator.model.DeceasedGenerator;
import com.digout.database.generator.model.LocalizationGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ScriptGenerator {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final LocalizationGenerator localizationGenerator = new LocalizationGenerator();
    private final DeceasedGenerator deceasedGenerator = new DeceasedGenerator();

    public void generateSqlScripts(int expected) {
        generateLocalizationScripts(expected);
        generateGraveScripts();
        generateDeceasedScripts(expected);
    }

    private void generateLocalizationScripts(int expected) {
        List<String> localizationList = localizationGenerator.getLocalizationList(expected);

        String localizationOutputPath = "C:\\Users\\justy\\OneDrive\\Pulpit\\DIG_OUT\\dig-out\\src\\main\\resources\\outputs\\localization.sql";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(localizationOutputPath))) {

            for (String localization : localizationList) {
                String[] localizationAttributes = localization.split(",");
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES (");
                stringBuilder.append("'").append(localizationAttributes[0]).append("',");
                stringBuilder.append("'").append(localizationAttributes[1]).append("',");
                stringBuilder.append("'").append(localizationAttributes[2]).append("',");
                stringBuilder.append("'").append(localizationAttributes[3]).append("');");
                bufferedWriter.write(stringBuilder.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            logger.error("{} localizations has been created.", localizationList.size());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void generateGraveScripts() {
        List<String> graveList = localizationGenerator.getGraveList();

        String graveOutputPath = "C:\\Users\\justy\\OneDrive\\Pulpit\\DIG_OUT\\dig-out\\src\\main\\resources\\outputs\\grave.sql";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(graveOutputPath))) {

            for (String grave : graveList) {
                String[] graveAttributes = grave.split(",");
                StringBuilder graveBuilder = new StringBuilder();
                graveBuilder.append("INSERT INTO grave (type, localization, grave_owner) VALUES (");
                graveBuilder.append("'").append(graveAttributes[0]).append("',");
                graveBuilder.append("'").append(graveAttributes[1]).append("',");
                if (graveAttributes[2].equals("NULL")) {
                    graveBuilder.append(graveAttributes[2]).append(");");
                } else {
                    graveBuilder.append("'").append(graveAttributes[2]).append("');");
                }
                bufferedWriter.write(graveBuilder.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();

                deceasedGenerator.addGraveToCapacityMap(graveAttributes);
            }
            logger.debug("{} graves has been created.", graveList.size());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void generateDeceasedScripts(int expected) {
        List<String> deceasedList = deceasedGenerator.getDeceasedList(expected);

        String deceasedOutputPath = "C:\\Users\\justy\\OneDrive\\Pulpit\\DIG_OUT\\dig-out\\src\\main\\resources\\outputs\\deceased.sql";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(deceasedOutputPath))) {

            for (String deceased : deceasedList) {
                String[] deceasedAttributes = deceased.split(",");
                StringBuilder graveBuilder = new StringBuilder();
                graveBuilder.append("INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES (");
                graveBuilder.append("'").append(deceasedAttributes[0]).append("',");
                graveBuilder.append("'").append(deceasedAttributes[1]).append("',");
                graveBuilder.append("'").append(deceasedAttributes[2]).append("',");
                graveBuilder.append("'").append(deceasedAttributes[3]).append("',");
                graveBuilder.append("'").append(deceasedAttributes[4]).append("',");
                graveBuilder.append("'").append(deceasedAttributes[5]).append("');");
                bufferedWriter.write(graveBuilder.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            logger.debug("{} deceased has been created.", deceasedList.size());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
