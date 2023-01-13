package soloviova.liudmyla.qa.tests;

/**
 * A base class for search page API tests
 *
 * @author Liudmyla Soloviova
 */
public class SearchPageTestBase {
    private static final String VODAFONE_API_SEARCH_URL = "https://www.vodafone.ua/api/search";

    /**
     * Builds final URL with the keyword that will be used in tests
     */
    protected String buildUrlWithSearchKeyword(final String keyword) {
        return VODAFONE_API_SEARCH_URL + "/" + keyword;
    }
}
