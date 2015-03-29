package example.com.trendingcode;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by francis on 03/24/15.
 */
public class GithubSearchResult {
    private static final String TAG = GithubSearchResult.class.getSimpleName();
    private static final String JSON_KEY_ITEMS = "items";

    private int mTotalCount;
    private ArrayList<GithubRepository> mItems;

    public GithubSearchResult() {
        this.mTotalCount = 0;
        this.mItems = new ArrayList<>();
    }

    public static GithubSearchResult fromJSON(String jsonStr) throws JSONException {
        GithubSearchResult result = new GithubSearchResult();

        try {
            JSONObject json = new JSONObject(jsonStr);

            JSONArray jsonArray = json.getJSONArray(JSON_KEY_ITEMS);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                GithubRepository repo = GithubRepository.fromJSON(obj);
                result.mItems.add(repo);
            }

            result.mTotalCount = result.mItems.size();

        } catch (JSONException e) {
            Log.e(TAG, "Unable to parse JSON.", e);
        }

        return result;
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public ArrayList<GithubRepository> getItems() {
        return this.mItems;
    }
}
