package soloviova.liudmyla.qa.data;

import org.testng.annotations.DataProvider;

/**
 * This class contains test data for search page API tests
 *
 * @author Liudmyla Soloviova
 */
public class SearchPageTestData {

    @DataProvider
    public static Object[][] validSearchKeywords() {
        return new Object[][] {
                {"Vodafone TV"},
                {"Роумінг"},
                {"test"},
                {"LTE"}
        };
    }

    @DataProvider
    public static Object[][] emptyStrings() {
        return new Object[][] {
                {""},
                {" "},
                {"      "}
        };
    }

    @DataProvider
    public static Object[][] singleChars() {
        return new Object[][] {
                {"t"},
                {"щ"},
                {"Ч"},
                {"Q"}
        };
    }

    @DataProvider
    public static Object[][] invalidStrings() {
        return new Object[][] {
                {"hfbwjbgjjwg4uy"},
                {"аьдйузщлпєц"}
        };
    }
}
