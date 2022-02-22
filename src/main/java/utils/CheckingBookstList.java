package utils;

import models.BaseModel;
import models.Book;

import java.util.List;

public class CheckingBookstList {

    private static String currentId;
    private static String nextId;

    private CheckingBookstList() {
    }

    public static <T extends BaseModel> boolean isAscendingBookIdOrder(List<T> list) {
        SmartLogger.logInfo("Checking list for ascending id order");
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
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

    public static Book getCheapBook(List<Book> list) {
        SmartLogger.logInfo("Get cheap book");
        int minPrice = 0;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getPrice() < list.get(minPrice).getPrice())
                minPrice = i;
        }

        return list.get(minPrice);
    }

    public static Book getExpensiveBook(List<Book> list) {
        SmartLogger.logInfo("Get expensive book");
        int maxPrice = 0;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getPrice() > list.get(maxPrice).getPrice())
                maxPrice = i;
        }

        return list.get(maxPrice);
    }
 }
