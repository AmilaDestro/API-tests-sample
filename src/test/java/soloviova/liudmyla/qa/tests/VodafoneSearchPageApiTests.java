package soloviova.liudmyla.qa.tests;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import soloviova.liudmyla.qa.data.SearchPageTestData;


import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

/**
 * This class contains API tests for 'vodafone.ua/search' page
 */
public class VodafoneSearchPageApiTests extends SearchPageTestBase {

    /*
    Test Case 1
     */
    @Test(dataProvider = "validSearchKeywords", dataProviderClass = SearchPageTestData.class)
    public void testSearchResultsForValidString(final String keyword) {
        when()
                .get(buildUrlWithSearchKeyword(keyword))
                        .then()
                                .statusCode(200)
                                .contentType(ContentType.JSON)
                                .body("data", notNullValue())
                                .body("data", hasItems())
                                .body("data", hasSize(greaterThan(0)))
                                .body("data.title", hasItem(containsString(keyword)));
    }
    @Test(dataProviderClass = SearchPageTestData.class, dataProvider = "singleChars")
    public void testSearchResultsForOneChar(final String singleChar) {
        when()
                .get(buildUrlWithSearchKeyword(singleChar))
                        .then()
                                .statusCode(200)
                                .contentType(ContentType.JSON)
                                .body("data", empty());
    }

    @Test(dataProvider = "emptyStrings", dataProviderClass = SearchPageTestData.class)
    public void testSearchResultsForEmptyString(final String emptyString) {
        when()
                .get(buildUrlWithSearchKeyword(emptyString))
                        .then()
                                .statusCode(404);
    }
    @Test(dataProvider = "invalidStrings", dataProviderClass = SearchPageTestData.class)
    public void testSearchResultsForInvalidString(final String keyword) {
        when()
                .get(buildUrlWithSearchKeyword(keyword))
                        .then()
                                .statusCode(200)
                                .contentType(ContentType.JSON)
                                .body("data", empty());
    }

    @Test
    public void testSearchResultsForInvalidUrl() {
        when()
                .get("https://www.vodafone.ua/api/searchh")
                        .then()
                                .statusCode(404);
    }
}
