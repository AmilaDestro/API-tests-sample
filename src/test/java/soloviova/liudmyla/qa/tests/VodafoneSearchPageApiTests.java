package soloviova.liudmyla.qa.tests;

import io.restassured.http.ContentType;
import lombok.val;
import org.testng.annotations.Test;
import soloviova.liudmyla.qa.data.SearchPageTestData;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertTrue;


/**
 * This class contains API tests for 'vodafone.ua/search' page
 */
public class VodafoneSearchPageApiTests extends SearchPageTestBase {

    /*
    Test Case 1
     */
    @Test(dataProvider = "validSearchKeywords", dataProviderClass = SearchPageTestData.class)
    public void testSearchResultsForValidString(final String keyword) {
        val response = when()
                            .get(buildUrlWithSearchKeyword(keyword))
                                    .then()
                                            .statusCode(200)
                                            .contentType(ContentType.JSON)
                                            .body("data", notNullValue())
                                            .body("data", hasItems())
                .extract().response();

        final List<String> titles = response.path("data.title");
        final List<String> descriptions = response.path("data.short_description");

        final Optional<String> relevantTitle = titles.stream()
                .filter(title -> title.contains(keyword))
                .findAny();
        final Optional<String> relevantDescription = descriptions.stream()
                .filter(description -> description.contains(keyword))
                .findAny();
        assertTrue(relevantTitle.isPresent() || relevantDescription.isPresent(),
                String.format("No items with title or description containing [%s] were found", keyword));
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
