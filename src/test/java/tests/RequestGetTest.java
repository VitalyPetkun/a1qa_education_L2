package tests;

import models.Book;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlRun;
import utils.api.APIUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;
import utils.api.Response;

import java.util.List;

import static services.EndPointsMocky.*;

public class RequestGetTest extends BaseTest {

    private Response response;

    @Test(priority = 1)
    public void foundAllBooks() {
        SmartLogger.logStep("STEP №1: GET request for found all 'posts'");
        response = APIUtils.doGet(V2.getPoint().concat(ADDRESS.getPoint()));
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");

        List<Book> books = ObjectConverter.getListXml(response.getBody(), Book[].class);
        Assert.assertTrue(CheckingBookstList.isAscendingBookIdOrder(books), "List is not sorted by ID ascending");

        Book cheapBook = CheckingBookstList.getExpensiveBook(books, true, false);
        Book expensiveBook = CheckingBookstList.getExpensiveBook(books, false, true);
        Assert.assertNotEquals(cheapBook, expensiveBook,"Books are same");
    }
}

