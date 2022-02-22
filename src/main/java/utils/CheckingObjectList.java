package utils;

import models.BaseModel;

import java.util.List;

public class CheckingObjectList {

    private static String currentId;
    private static String nextId;

    private CheckingObjectList() {
    }

    public static <T extends BaseModel> boolean isAscendingObjectIdOrder(List<T> list) {
        SmartLogger.logInfo("Checking list for ascending id order");
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                String id = list.get(i).getId();
                currentId = list.get(i).getId().replaceAll("[^0-9]", "");
                nextId = list.get(i + 1).getId().replaceAll("[^0-9]", "");
                if (!(Integer.parseInt(currentId) < Integer.parseInt(nextId)))
                    return false;
            } else {
                return true;
            }
        }

        return false;
    }
}
