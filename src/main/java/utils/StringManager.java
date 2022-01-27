package utils;

import utils.PropertiesManager;

public class StringManager {

    private static final int BOTTOM_VALUE = Integer.parseInt(PropertiesManager.getTestDataValue("bottomValueFromTableASCII"));
    private static final int TOP_VALUE = Integer.parseInt(PropertiesManager.getTestDataValue("topValueFromTableASCII"));

    private StringManager() {
    }

    public static String generate(int stringLength) {
        String randomString = "";
        for (int i = 0; i < stringLength; i++) {
            randomString += String.valueOf(Character.toChars(
                    (int) (Math.random() * (TOP_VALUE - BOTTOM_VALUE)) + BOTTOM_VALUE));
        }
        return randomString;
    }
}
