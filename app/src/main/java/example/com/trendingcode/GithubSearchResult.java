package example.com.trendingcode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by francis on 03/24/15.
 */
public class GithubSearchResult {
    private int mTotalCount;
    private ArrayList<GithubRepository> mItems;

    public GithubSearchResult() {
        this.mTotalCount = 0;
        this.mItems = new ArrayList<>();
    }

    public int getTotalCount() {
        return this.mTotalCount;
    }

    public ArrayList<GithubRepository> getItems() {
        return this.mItems;
    }

    public static GithubSearchResult fromJSON(String jsonStr) throws JSONException {
        // TODO-Mie087: JSON Parsing.
        //              Check out GithubRepository.fromJSON as a example.
        GithubSearchResult result = new GithubSearchResult();

        JSONObject json = new JSONObject(jsonStr);

        Integer totalCount = json.getInt("total_count");
        result.mTotalCount = totalCount;

        JSONArray items = json.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);

            GithubRepository repo = GithubRepository.fromJSON(item);

            result.mItems.add(repo);
        }

        return result;
    }
}
