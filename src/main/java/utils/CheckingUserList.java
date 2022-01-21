package utils;

import models.post.PostModelForResponse;
import java.util.List;

public class CheckingUserList {

    public static boolean isAscendingUserIdOrder(List<PostModelForResponse> list) {
        Logger.logInfo("Checking list user for ascending id order");
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
