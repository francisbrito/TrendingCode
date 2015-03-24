package example.com.trendingcode;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by francis on 03/24/15.
 */
public class SearchGithubRepositories
        extends AsyncTask<RepositorySearchQuery, Void, GithubSearchResult> {
    private static final String TAG = SearchGithubRepositories.class.getSimpleName();
    private static final String GITHUB_REPOSITORY_SEARCH_API_ENDPOINT
            = "https://api.github.com/search/repositories";

    @Override
    protected GithubSearchResult doInBackground(RepositorySearchQuery... queries) {
        GithubSearchResult result = null;

        RepositorySearchQuery query = queries[0];

        String uri = new Uri.Builder()
                .encodedPath(GITHUB_REPOSITORY_SEARCH_API_ENDPOINT)
                .appendQueryParameter(RepositorySearchQuery.QUERY_FIELD, query.getQuery())
                .appendQueryParameter(
                        RepositorySearchQuery.LANGUAGE_FIELD,
                        query.getLanguage())
                .appendQueryParameter(RepositorySearchQuery.SORT_FIELD, query.getSort())
                .appendQueryParameter(RepositorySearchQuery.ORDER_FIELD, query.getOrder())
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
