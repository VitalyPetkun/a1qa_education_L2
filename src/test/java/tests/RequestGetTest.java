package tests;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import models.Book;
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

    @Test
    public void foundAllBooks() {
        SmartLogger.logStep(1, "GET request for found all books");
        response = APIUtils.doGet(V2.getPoint().concat(ADDRESS.getPoint()), ContentType.XML);
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");
        XmlPath xml = new XmlPath(response.getBody());
        Assert.assertTrue(XmlPath.class.equals(xml.getClass()), "List of books returned not in XML format");
        List<Book> books = ObjectConverter.getListXml(response.getBody(), Book[].class);
        Assert.assertTrue(CheckingBookstList.isAscendingBookIdOrder(books), "List is not sorted by ID ascending");

        SmartLogger.logStep(2, "Search cheap book and expensive book");
        Book cheapBook = CheckingBookstList.getCheapBook(books);
        Book expensiveBook = CheckingBookstList.getExpensiveBook(books);
        Assert.assertNotEquals(cheapBook, expensiveBook,"Books are same");
    }
}

