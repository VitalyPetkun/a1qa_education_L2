package utils;

import models.BaseModel;
import java.util.List;

public class CheckingUserList {

    private CheckingUserList() {
    }

    public static <T extends BaseModel> boolean isAscendingUserIdOrder(List<T> list) {
        SmartLogger.logInfo("Checking list for ascending id order");
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                if (!(list.get(i).getId() < list.get(i + 1).getId()))
                    return false;
            } else {
                return true;
            }
        }

        return false;
    }
}
