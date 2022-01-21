package utils;

public class GeneratingRandomString {

    private static final int BOTTOM_VALUE = Integer.parseInt(ConfigManager.getTestDataValue("bottomValueFromTable"));
    private static final int TOP_VALUE = Integer.parseInt(ConfigManager.getTestDataValue("topValueFromTable"));

    public static String generate(int stringLength) {
        String randomString = null;
        for (int i = 0; i < stringLength; i++) {
            randomString += String.valueOf(Character.toChars(
                    (int) (Math.random() * (TOP_VALUE- BOTTOM_VALUE)) + BOTTOM_VALUE));
        }
        return randomString;
    }
}
