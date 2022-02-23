package utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class CooperationWithDialogWindow {

    private CooperationWithDialogWindow() {
    }

    public static void openFileDialogWindow(String resourcesPath, String nameFile) {
        SmartLogger.logInfo("Selecting a file to upload");
        try {
            Thread.sleep(Integer.parseInt(PropertiesManager.getConfigValue("sleepTime")));
            Robot robot = new Robot();
            StringSelection stringSelection = new StringSelection(System.getProperty("user.dir") + "\\" +
                    resourcesPath + "\\" + nameFile);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(Integer.parseInt(PropertiesManager.getConfigValue("sleepTime")));
        } catch (InterruptedException | AWTException e) {
            SmartLogger.logError("Robot not create");
        }
    }
}
