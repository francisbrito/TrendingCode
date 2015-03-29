package example.com.trendingcode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by francis on 03/24/15.
 */
public class GithubSearchResult {
    private static int mTotalCount;
    public static ArrayList<GithubRepository> mItems;
    public static GithubSearchResult result;

    public GithubSearchResult() {
        this.mTotalCount = 0;
        this.mItems = new ArrayList<>();
        result = new GithubSearchResult();
    }

    public static GithubSearchResult fromJSON(String jsonStr) {
        // TODO-Mie087: JSON Parsing.
        //              Check out GithubRepository.fromJSON as a example.
        try {
            JSONObject json = new JSONObject(jsonStr);

            JSONArray jsonArray = json.getJSONArray(jsonStr);

            for(int i = 0; i <= jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);

                GithubRepository repo = GithubRepository.fromJSON(obj);
                result.mItems.add(repo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        result.mTotalCount = mItems.size();

        return result;
    }
}
