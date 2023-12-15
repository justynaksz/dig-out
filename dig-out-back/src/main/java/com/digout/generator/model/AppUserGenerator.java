package com.digout.generator.model;

import com.digout.generator.inputreader.InputReader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AppUserGenerator {

    private final Random random = new Random();
    private final InputReader inputReader = new InputReader();
    private final List<String> allUserList = new ArrayList<>();
    private final List<String> nicknames = new ArrayList<>();
    private final List<String> emails = new ArrayList<>();
    private final List<Integer> graveOwnerList = new ArrayList<>();
    private final String NULL = "NULL";

    public List<String> getAppUserList(int expected) {
        generateAdmin();
        while (allUserList.size() < expected) {
            int number = random.nextInt(1, 100);
            if (number < 2) {
                generateAdmin();
            } else {
                generateAppUser(expected);
            }
        }
        return allUserList;
    }

    private void generateAdmin() {
        String admin = "ADMIN";
        String nickname = getAdminNickname();
        String adminUser = nickname + ',' + generateNicknameOrPassword() + ','
                + generateAminEmail(nickname) + ',' + admin + ',' + NULL + ','
                + generateAvatarReference();
        allUserList.add(adminUser);
    }

    private void generateAppUser(int graveOwnersCount) {
        String user = "USER";
        String appUser = getNickname() + ',' + generateNicknameOrPassword() + ','
                + generateEmail() + ',' + user + ',' + getGraveOwnerNumber(graveOwnersCount)
                +',' + generateAvatarReference();
        allUserList.add(appUser);
    }

    private String getNickname() {
        String nickname = generateNicknameOrPassword();
        return getUniqueNickname(nickname);
    }

    private String getAdminNickname() {
        String nickname = inputReader.getAdjective() + ".admin";
        return getUniqueNickname(nickname);
    }

    private String getUniqueNickname(String nickname) {
        while (nicknames.contains(nickname)) {
            nickname = decorateWord(nickname);
        }
        nicknames.add(nickname);
        return nickname;
    }

    private String generateNicknameOrPassword() {
        String adjective = inputReader.getAdjective();
        String noun = inputReader.getNoun();
        return appendNicknameOrPassword(adjective, noun);
    }

    private String appendNicknameOrPassword(String adjective, String noun) {
        int randomNumber = random.nextInt(1, 100);
        if (randomNumber < 30) {
            noun = noun.substring(0, 1).toUpperCase() + noun.substring(1);
            return decorateWord(adjective + noun);
        } else if (randomNumber < 50) {
            return decorateWord(adjective + '_' + noun);
        } else if (randomNumber < 70) {
            return decorateWord(adjective + '.' + noun);
        } else if (randomNumber < 90) {
            return decorateWord(adjective + '-' + noun);
        } else {
            return decorateWord(adjective + "__" + noun);
        }
    }

    private String decorateWord(String word) {
        int randomNumber = random.nextInt(1, 100);
        if (randomNumber < 40) {
            String suffix = String.valueOf(random.nextInt(1, 2025));
            return word + suffix;
        } else if (randomNumber < 50) {
            return word + '!';
        } else if (randomNumber < 60) {
            return word + '_';
        } else if (randomNumber < 70) {
            return word + '$';
        } else {
            return word;
        }
    }

    private String generateEmail() {
        String email = inputReader.getEmail();
        boolean isNotUnique = true;
        while (isNotUnique) {
            if (emails.contains(email)) {
                String[] emailParts = email.split("@");
                email = decorateWord(emailParts[0]) + '@' + emailParts[1];
            } else {
                isNotUnique = false;
            }
        }
        emails.add(email);
        return email;
    }

    private String generateAminEmail(String nickname) {
        return nickname + "@dig-out.com";
    }

    private String getGraveOwnerNumber(int graveOwnersCount) {
        int randomNumber = random.nextInt(1, 100);

        if(randomNumber <= 30) {
            int graveOwnerNumber = random.nextInt(1, graveOwnersCount);
            while(graveOwnerList.contains(graveOwnerNumber)) {
                graveOwnerNumber = random.nextInt(1, graveOwnersCount);
            }
            graveOwnerList.add(graveOwnerNumber);
            return String.valueOf(graveOwnerNumber);
        } else {
            return NULL;
        }
    }

    private String generateAvatarReference() {
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
