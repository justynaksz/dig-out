package com.digout.generator.outputwriter;

import com.digout.generator.model.AppUserGenerator;
import com.digout.generator.model.DeceasedGenerator;
import com.digout.generator.model.GraveOwnerGenerator;
import com.digout.generator.model.LocalizationGenerator;
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
    private final AppUserGenerator appUserGenerator = new AppUserGenerator();
    private final GraveOwnerGenerator graveOwnerGenerator = new GraveOwnerGenerator();

    public void generateSqlScripts(int expected) {
        generateLocalizationScripts(expected);
        generateGraveScripts();
        generateDeceasedScripts(expected);
        generateAppUserScripts(expected);
        generateGraveOwnerScripts(expected);
    }

    private void generateLocalizationScripts(int expected) {
        List<String> localizationList = localizationGenerator.getLocalizationList(expected);

        String localizationOutputPath = "dig-out-back\\src\\main\\resources\\output\\localization.sql";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(localizationOutputPath))) {

            for (String localization : localizationList) {
                String[] localizationAttributes = localization.split(",");
                String stringBuilder = "INSERT INTO localization (cemetery, quarter, localization_row, localization_column) VALUES (" +
                        "'" + localizationAttributes[0] + "'," +
                        "'" + localizationAttributes[1] + "'," +
                        "'" + localizationAttributes[2] + "'," +
                        "'" + localizationAttributes[3] + "');";
                bufferedWriter.write(stringBuilder);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            logger.debug("{} localizations has been created.", localizationList.size());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void generateGraveScripts() {
        List<String> graveList = localizationGenerator.getGraveList();

        String graveOutputPath = "dig-out-back\\src\\main\\resources\\output\\grave.sql";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(graveOutputPath))) {

            for (String grave : graveList) {
                String[] graveAttributes = grave.split(",");
                StringBuilder graveBuilder = new StringBuilder();
                graveBuilder.append("INSERT INTO grave (type, localization, grave_owner, photo) VALUES (");
                graveBuilder.append("'").append(graveAttributes[0]).append("',");
                graveBuilder.append("'").append(graveAttributes[1]).append("',");
                if (graveAttributes[2].equals("NULL")) {
                    graveBuilder.append(graveAttributes[2]).append(",");
                } else {
                    graveBuilder.append("'").append(graveAttributes[2]).append("',");
                }
                if (graveAttributes[3].equals("NULL")) {
                    graveBuilder.append(graveAttributes[3]).append(");");
                } else {
                    graveBuilder.append("'").append(graveAttributes[3]).append("');");
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

        String deceasedOutputPath = "dig-out-back\\src\\main\\resources\\output\\deceased.sql";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(deceasedOutputPath))) {

            for (String deceased : deceasedList) {
                String[] deceasedAttributes = deceased.split(",");
                String deceasedBuilder = "INSERT INTO deceased (first_name, last_name, birth_date, death_date, is_infectious_disease, grave) VALUES (" +
                        "'" + deceasedAttributes[0] + "'," +
                        "'" + deceasedAttributes[1] + "'," +
                        "'" + deceasedAttributes[2] + "'," +
                        "'" + deceasedAttributes[3] + "'," +
                        "'" + deceasedAttributes[4] + "'," +
                        "'" + deceasedAttributes[5] + "');";
                bufferedWriter.write(deceasedBuilder);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            logger.debug("{} deceased has been created.", deceasedList.size());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void generateAppUserScripts(int expected) {
        List<String> appUserList = appUserGenerator.getAppUserList(expected);

        String appUserOutputPath = "dig-out-back\\src\\main\\resources\\output\\app_user.sql";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(appUserOutputPath))) {

            for (String appUser : appUserList) {
                String[] appUserAttributes = appUser.split(",");

                StringBuilder appUserBuilder = new StringBuilder();
                appUserBuilder.append("INSERT INTO app_user (nickname, password, e_mail, role, grave_owner, avatar) VALUES (");
                appUserBuilder.append("'").append(appUserAttributes[0]).append("',");
                appUserBuilder.append("'").append(appUserAttributes[1]).append("',");
                appUserBuilder.append("'").append(appUserAttributes[2]).append("',");
                appUserBuilder.append("'").append(appUserAttributes[3]).append("',");
                if (appUserAttributes[4].equals("NULL")) {
                    appUserBuilder.append(appUserAttributes[4]).append(",");
                } else {
                    appUserBuilder.append("'").append(appUserAttributes[4]).append("',");
                }
                if (appUserAttributes[5].equals("NULL")) {
                    appUserBuilder.append(appUserAttributes[5]).append(");");
                } else {
                    appUserBuilder.append("'").append(appUserAttributes[5]).append("');");
                }
                bufferedWriter.write(appUserBuilder.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            logger.debug("{} app users has been created.", appUserList.size());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void generateGraveOwnerScripts(int expected) {
        List<String> graveOwnerList = graveOwnerGenerator.getGraveOwnerList(expected);

        String graveOwnerOutputPath = "dig-out-back\\src\\main\\resources\\output\\grave_owner.sql";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(graveOwnerOutputPath))) {

            for (String graveOwner : graveOwnerList) {
                String[] graveOwnerAttributes = graveOwner.split(",");

                StringBuilder graveOwnerBuilder = new StringBuilder();
                graveOwnerBuilder.append("INSERT INTO grave_owner (first_name, last_name, pesel, street, parcel, city, postal_code, country, phone_number) VALUES (");
                graveOwnerBuilder.append("'").append(graveOwnerAttributes[0]).append("',");
                graveOwnerBuilder.append("'").append(graveOwnerAttributes[1]).append("',");
                if (graveOwnerAttributes[2].equals("NULL")) {
                    graveOwnerBuilder.append(graveOwnerAttributes[2]).append(",");
                } else {
                    graveOwnerBuilder.append("'").append(graveOwnerAttributes[2]).append("',");
                }
                if (graveOwnerAttributes[3].equals("NULL")) {
                    graveOwnerBuilder.append(graveOwnerAttributes[3]).append(",");
                } else {
                    graveOwnerBuilder.append("'").append(graveOwnerAttributes[3]).append("',");
                }
                if (graveOwnerAttributes[4].equals("NULL")) {
                    graveOwnerBuilder.append(graveOwnerAttributes[4]).append(",");
                } else {
                    graveOwnerBuilder.append("'").append(graveOwnerAttributes[4]).append("',");
                }
                if (graveOwnerAttributes[5].equals("NULL")) {
                    graveOwnerBuilder.append(graveOwnerAttributes[5]).append(",");
                } else {
                    graveOwnerBuilder.append("'").append(graveOwnerAttributes[5]).append("',");
                }
                if (graveOwnerAttributes[6].equals("NULL")) {
                    graveOwnerBuilder.append(graveOwnerAttributes[6]).append(",");
                } else {
                    graveOwnerBuilder.append("'").append(graveOwnerAttributes[6]).append("',");
                }
                if (graveOwnerAttributes[7].equals("NULL")) {
                    graveOwnerBuilder.append(graveOwnerAttributes[7]).append(",");
                } else {
                    graveOwnerBuilder.append("'").append(graveOwnerAttributes[7]).append("',");
                }
                if (graveOwnerAttributes[8].equals("NULL")) {
                    graveOwnerBuilder.append(graveOwnerAttributes[8]).append(");");
                } else {
                    graveOwnerBuilder.append("'").append(graveOwnerAttributes[8]).append("');");
                }
                bufferedWriter.write(graveOwnerBuilder.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            logger.debug("{} grave owners has been created.", graveOwnerList.size());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

}
