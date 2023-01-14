package soloviova.liudmyla.qa.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.restassured.http.ContentType;
import lombok.extern.slf4j.Slf4j;
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
 *
 * @author Liudmyla Soloviova
 */
@Slf4j
public class VodafoneSearchPageApiTests extends SearchPageTestBase {

    @Test(dataProvider = "validSearchKeywords", dataProviderClass = SearchPageTestData.class)
    @Description("Case 1. Send GET request to valid URL with relevant keyword")
    @Link("https://www.vodafone.ua/api/search/{keyword}")
    public void testSearchResultsForValidString(final String keyword) {
        log.info("Keyword for search is [{}]", keyword);

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
    @Description("Case 2. Send GET request to valid URL with a single character")
    @Link("https://www.vodafone.ua/api/search/{s}")
    public void testSearchResultsForOneChar(final String singleChar) {
        log.info("Keyword for search is [{}]", singleChar);

        when()
              .get(buildUrlWithSearchKeyword(singleChar))
        .then()
              .statusCode(200)
              .contentType(ContentType.JSON)
              .body("data", empty());
    }

    @Test(dataProvider = "emptyStrings", dataProviderClass = SearchPageTestData.class)
    @Description("Case 3. Send GET request to valid URL with empty string as a keyword")
    @Link("https://www.vodafone.ua/api/search/{}")
    public void testSearchResultsForEmptyString(final String emptyString) {
        log.info("Keyword for search is [{}]", emptyString);

        when()
              .get(buildUrlWithSearchKeyword(emptyString))
        .then()
              .statusCode(404);
    }

    @Test(dataProvider = "invalidStrings", dataProviderClass = SearchPageTestData.class)
    @Description("Case 4. Send GET request to valid URL with a keyword consisting of random characters")
    @Link("https://www.vodafone.ua/api/search/{@bracadabra}")
    public void testSearchResultsForInvalidString(final String keyword) {
        log.info("Keyword for search is [{}]", keyword);

        when()
              .get(buildUrlWithSearchKeyword(keyword))
        .then()
              .statusCode(200)
              .contentType(ContentType.JSON)
              .body("data", empty());
    }
    @Test
    @Description("Case 5. Send GET request to invalid URL")
    @Link("https://www.vodafone.ua/api/searchh")
    public void testSearchResultsForInvalidUrl() {
        when()
              .get("https://www.vodafone.ua/api/searchh")
        .then()
              .statusCode(404);
    }
}
