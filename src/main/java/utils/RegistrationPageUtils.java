package utils;

import java.util.ArrayList;
import java.util.List;

public class RegistrationPageUtils {

    private static final int LENGTH_PASSWORD = Integer.parseInt(PropertiesManager.getTestDataValue("lengthPassword"));
    private static final int BOTTOM_VALUE_NUMERAL = Integer.parseInt(PropertiesManager.getTestDataValue("bottomValueAsciiNumeral"));
    private static final int TOP_VALUE_NUMERAL = Integer.parseInt(PropertiesManager.getTestDataValue("topValueAsciiNumeral"));
    private static final int BOTTOM_VALUE_LOWER_CASE = Integer.parseInt(PropertiesManager.getTestDataValue("bottomValueAsciiLowerCase"));
    private static final int TOP_VALUE_LOWER_CASE = Integer.parseInt(PropertiesManager.getTestDataValue("topValueAsciiLowerCase"));
    private static final int BOTTOM_VALUE_CAPITAL_LETTERS = Integer.parseInt(PropertiesManager.getTestDataValue("bottomValueAsciiCapitalLetters"));
    private static final int TOP_VALUE_CAPITAL_LETTERS = Integer.parseInt(PropertiesManager.getTestDataValue("topValueAsciiCapitalLetters"));

    private RegistrationPageUtils() {
    }

    public static String getRandomPassword() {
        String email = PropertiesManager.getTestDataValue("email");
        char[] emailToArray = email.toCharArray();

        String password = String.valueOf(emailToArray[(int) (Math.random() * (emailToArray.length))]);
        password += String.valueOf(Character.toChars((int) (Math.random()
                * (TOP_VALUE_CAPITAL_LETTERS - BOTTOM_VALUE_CAPITAL_LETTERS)) + BOTTOM_VALUE_CAPITAL_LETTERS));
        password += (String.valueOf(Character.toChars((int) (Math.random()
                * (TOP_VALUE_NUMERAL - BOTTOM_VALUE_NUMERAL)) + BOTTOM_VALUE_NUMERAL)));

        for (int i = 0; i <= LENGTH_PASSWORD; i++) {
            password += (String.valueOf(Character.toChars((int) (Math.random()
                    * (TOP_VALUE_LOWER_CASE - BOTTOM_VALUE_LOWER_CASE)) + BOTTOM_VALUE_LOWER_CASE)));
        }

        return password;
    }

    public static List<Integer> getRandomList(int sizeList, int interestsSize, Object[] interestsName, String[] unwantedInterests) {
        int unselectAllInterestId = 0;
        int selectAllId = 0;

        for (int i = 0; i < interestsName.length; i++) {
            if (interestsName[i].equals(unwantedInterests[0]))
                unselectAllInterestId = i;
            else if (interestsName[i].equals(unwantedInterests[1]))
                selectAllId = i;
        }

        List<Integer> randInterests = new ArrayList<>();
        int rand;

        while (randInterests.size() != sizeList) {
            rand = (int) (Math.random() * interestsSize);

            if (rand != unselectAllInterestId && rand != selectAllId)
                if (!randInterests.contains(rand)) {
                    randInterests.add(rand);
                }
        }

        return randInterests;
    }
}
