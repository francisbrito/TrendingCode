package example.com.trendingcode;

/**
 * Created by francis on 03/24/15.
 */
public class RepositorySearchQuery {
    public static final String QUERY_FIELD = "q";
    public static final String LANGUAGE_FIELD = "lang";
    public static final String SORT_FIELD = "sort";
    public static final String ORDER_FIELD = "order";

    private final String mQuery;
    private final String mLang;
    private final String mSort;
    private final String mOrder;

    public RepositorySearchQuery(String query, String lang, String sort, String order) {
        this.mQuery = query;
        this.mLang = lang;
        this.mSort = sort;
        this.mOrder = order;
    }

    public static RepositorySearchQuery create(String query, String lang, String sort, String order) {
        return new RepositorySearchQuery(query, lang, sort, order);
    }

    public static RepositorySearchQuery fromString(String queryString) {
        String fieldsRegex = "(language|sort|order):(\\w+)";

        String query = queryString.replaceAll(fieldsRegex, "");
        String lang = "javascript";
        String sort = "stars";
        String order = "desc";

        return RepositorySearchQuery.create(query, lang, sort, order);
    }

    public String getQuery() {
        return mQuery;
    }

    public String getLanguage() {
        return mLang;
    }

    public String getSort() {
        return mSort;
    }

    public String getOrder() {
        return mOrder;
    }
}
