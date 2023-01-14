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
                {"test-"},
                {"LTE"},
                {"4G"},
                {"1800"}
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
                {"Q"},
                {"$"},
                {"1"}
        };
    }

    @DataProvider
    public static Object[][] invalidStrings() {
        return new Object[][] {
                {"hfbwjbgjjwg4uy"},
                {"аьдйузщлпєц"},
                {"@bracadabra123#~"},
                {"@#$%^&*()"}
        };
    }

    @DataProvider
    public static Object[][] invalidUrls() {
        return new Object[][] {
                {"https://www.vodafone.ua/api/searchh"},
                {"https://www.vodafone.ua/api/searc"},
                {"https://www.vodafone.ua/api/ search"},
                {"https://www.vodafone.ua/api//search"}
        };
    }
}
