package example.com.trendingcode;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by francis on 03/24/15.
 */
public class SearchGithubRepositories
        extends AsyncTask<GithubRepositorySearchQuery, Void, GithubSearchResult> {
    private static final String TAG = SearchGithubRepositories.class.getSimpleName();
    private static final String GITHUB_REPOSITORY_SEARCH_API_ENDPOINT
            = "https://api.github.com/search/repositories";
    private static final String GITHUB_RATE_LIMIT_HEADER = "X-RateLimit-Limit";
    private static final String GITHUB_RATE_LIMIT_REMAINING_HEADER = "X-RateLimit-Remaining";

    @Override
    protected GithubSearchResult doInBackground(GithubRepositorySearchQuery... queries) {
        GithubSearchResult result = null;

        GithubRepositorySearchQuery query = queries[0];

        String uri = new Uri.Builder()
                .encodedPath(GITHUB_REPOSITORY_SEARCH_API_ENDPOINT)
                .encodedQuery(GithubRepositorySearchQuery.QUERY_FIELD + "=" + query.buildQuery())
                .appendQueryParameter(GithubRepositorySearchQuery.SORT_FIELD, query.getSort())
                .appendQueryParameter(GithubRepositorySearchQuery.ORDER_FIELD, query.getOrder())
                .build()
                .toString();

        Log.d(TAG, "URI: " + uri);

        URL url;
        HttpURLConnection connection;

        try {
            url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            String rateRemaining = connection.getHeaderField(GITHUB_RATE_LIMIT_REMAINING_HEADER);
            String rateLimit = connection.getHeaderField(GITHUB_RATE_LIMIT_HEADER);

            Log.d(TAG, "Rate Limit: " + rateRemaining + "/" + rateLimit);

            InputStream inputStream = connection.getInputStream();
            StringBuffer stringBuffer = new StringBuffer();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }

            String jsonStr = stringBuffer.toString();

            Log.d(TAG, "JSON: " + jsonStr);

            result = GithubSearchResult.fromJSON(jsonStr);

        } catch (Exception e) {
            Log.e(TAG, "Error while executing task.", e);
        }

        return result;
    }
}
