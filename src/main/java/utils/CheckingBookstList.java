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

    public static Book getExpensiveBook(List<Book> list, boolean cheapBook, boolean expensiveBook) {
        String book;
        String cheap = "cheap";
        String expensive = "expensive";

        if(cheapBook){
            book = cheap;
        } else {
            book = expensive;
        }

        int minPrice = 0;
        int maxPrice = 0;

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getPrice() < list.get(minPrice).getPrice())
                minPrice = i;
            if(list.get(i).getPrice() > list.get(maxPrice).getPrice())
                maxPrice = i;
        }

        switch (book) {
            case ("cheap"):
                SmartLogger.logInfo("Get cheap book");
                return list.get(minPrice);
            case ("expensive"):
                SmartLogger.logInfo("Get expensive book");
                return list.get(maxPrice);
        }

        return null;
    }
 }
