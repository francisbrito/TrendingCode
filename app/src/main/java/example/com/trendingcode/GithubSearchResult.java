package example.com.trendingcode;

import java.util.ArrayList;

/**
 * Created by francis on 03/24/15.
 */
public class GithubSearchResult {
    private final int mTotalCount;
    private final ArrayList<GithubRepository> mItems;

    public GithubSearchResult() {
        this.mTotalCount = 0;
        this.mItems = new ArrayList<>();
    }

    public static GithubSearchResult fromJSON(String jsonStr) {
        // TODO-Mie087: JSON Parsing.
        //              Check out GithubRepository.fromJSON as a example.
        return null;
    }
}
