package example.com.trendingcode;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by francis on 03/24/15.
 */
public class GithubRepositorySearchQuery {
    public static final String QUERY_FIELD = "q";
    public static final String LANGUAGE_FIELD = "language";
    public static final String LANGUAGE_SHORT_FIELD = "lang";
    public static final String LANGUAGE_FIELD_LETTER = "l";
    public static final String SORT_FIELD = "sort";
    public static final String SORT_FIELD_LETTER = "s";
    public static final String ORDER_FIELD = "order";
    public static final String ORDER_FIELD_LETTER = "o";

    private static final String TAG = GithubRepositorySearchQuery.class.getSimpleName();

    private final String mQuery;
    private final String mLang;
    private final String mSort;
    private final String mOrder;

    public GithubRepositorySearchQuery(String query, String lang, String sort, String order) {
        this.mQuery = query;
        this.mLang = lang;
        this.mSort = sort;
        this.mOrder = order;
    }

    public static GithubRepositorySearchQuery create(String query, String lang, String sort, String order) {
        return new GithubRepositorySearchQuery(query, lang, sort, order);
    }

    public static GithubRepositorySearchQuery fromString(String queryString) {
        Pattern regex = Pattern.compile("((?:l(?:ang(?:uage)?)?|s(?:ort)?|o(?:rder)?):(?:\\w+))");
        Matcher matcher = regex.matcher(queryString);

        String query = queryString
                .replaceAll(regex.toString(), "")
                .trim();

        String lang = "";
        String sort = "score";
        String order = "desc";

        while (matcher.find()) {
            String match = matcher.group();

            String[] parts = match.split(":");
            String key = parts[0];
            String value = parts[1];

            Log.d(TAG, "KEY: " + key + " " + "VALUE: " + value);

            switch (key) {
                case LANGUAGE_FIELD:
                case LANGUAGE_SHORT_FIELD:
                case LANGUAGE_FIELD_LETTER:
                    lang = value;
                    break;
                case SORT_FIELD:
                case SORT_FIELD_LETTER:
                    sort = value;
                    break;
                case ORDER_FIELD:
                case ORDER_FIELD_LETTER:
                    order = value;
                    break;
            }
        }

        return GithubRepositorySearchQuery.create(query, lang, sort, order);
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
