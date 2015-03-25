package example.com.trendingcode;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.Comparator;

/**
 * Created by francis on 03/25/15.
 */
public class GithubRepositoryArrayAdapter extends ArrayAdapter<GithubRepository> {
    private static final int MAX_COUNT = 5;

    public GithubRepositoryArrayAdapter(Context context, int viewId, int viewGroupId) {
        super(context, viewId, viewGroupId);
    }

    @Override
    public int getCount() {
        return Math.min(super.getCount(), MAX_COUNT);
    }

    @Override
    public void sort(Comparator<? super GithubRepository> comparator) {
        super.sort(new Comparator<GithubRepository>() {
            @Override
            public int compare(GithubRepository repo, GithubRepository repo2) {
                return repo.getStargazersCount() - repo2.getStargazersCount();
            }
        });
    }
}
