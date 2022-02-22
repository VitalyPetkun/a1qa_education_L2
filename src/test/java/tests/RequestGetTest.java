package tests;

import com.google.gson.JsonArray;
import models.Book;
import utils.api.APIUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.*;
import utils.api.Response;

import java.util.List;
import java.util.logging.XMLFormatter;

import static services.EndPointsMocky.*;

public class RequestGetTest extends BaseTest {

    private Response response;

    @Test(priority = 1)
    public void foundAllBooks() {
        SmartLogger.logStep("STEP №1: GET request for found all 'posts'");
        response = APIUtils.doGet(V2.getPoint().concat(ADDRESS.getPoint()));
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_OK, "Wrong status code returned");

        System.out.println(response.getBody().toString());
        XMLFormatter responseBody = JsonConverter.getObject(response.getBody(), XMLFormatter.class);
        Assert.assertTrue(XMLFormatter.class.equals(responseBody.getClass()), "List of posts returned not in JSON format");

        List<Book> books = JsonConverter.getList(response.getBody(), Book.class);
        Assert.assertTrue(CheckingObjectList.isAscendingObjectIdOrder(books), "List is not sorted by ID ascending");
    }
}

