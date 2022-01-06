package utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class CooperationWithDialogWindow {
    public static void openFileDialogWindow(String resourcesPath, String nameFile) {
        try {
            Thread.sleep(ConfigManager.getConfigInt("sleepTime"));

            Robot robot = new Robot();
            StringSelection stringSelection = new StringSelection(System.getProperty("user.dir") + "\\" +
                    ConfigManager.getTestDataString(resourcesPath) + "\\" +
                    ConfigManager.getTestDataString(nameFile));
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(ConfigManager.getConfigInt("sleepTime"));
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
