package utils;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPageUtils {

    private RegistrationPageUtils() {}

    public static String getRandomPassword() {
        String email = PropertiesManager.getTestDataValue("email");
        char[] emailToArray = email.toCharArray();
        String password = String.valueOf(emailToArray[(int) (Math.random() * (emailToArray.length))]);
        password += String.valueOf(Character.toChars((int) (Math.random() * (91 - 65)) + 65));
        password += String.valueOf(Character.toChars((int) (Math.random() * (58 - 48)) + 48));

        for (int i = 0; i <= 8 + (int) (Math.random() * 10); i++) {
            password += String.valueOf(Character.toChars((int) (Math.random() * (123 - 97)) + 97));
        }

        return password;
    }

    public static List<Integer> getRandomList(int sizeList, int interestsSize, Object[] interestsName, String[] unwantedInterests) {
        int unselectAllInterestId = 0;
        int selectAllId = 0;

        for (int i = 0; i < interestsName.length; i++) {
            if (interestsName[i].equals(unwantedInterests[0]))
                unselectAllInterestId = i;
            else if(interestsName[i].equals(unwantedInterests[1]))
                selectAllId = i;
        }

        List<Integer> randInterests = new ArrayList<>();
        int rand;

        while (randInterests.size() != sizeList) {
            rand = (int) (Math.random() * interestsSize);

            if(rand != unselectAllInterestId && rand != selectAllId)
                if (!randInterests.contains(rand)) {
                randInterests.add(rand);
            }
        }

        return randInterests;
    }
}
