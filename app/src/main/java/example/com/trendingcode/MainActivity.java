package example.com.trendingcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    public static final String REPO = "repo";
    MultiAutoCompleteTextView searchBox;
    Button searchBtn;
    ListView list;
    ProgressBar progressBar;

    ArrayAdapter<GithubRepository> searchResultAdapter;

    GithubSearchResultDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBox = (MultiAutoCompleteTextView) findViewById(R.id.searchBox);
        searchBtn = (Button) findViewById(R.id.searchBtn);
        list = (ListView) findViewById(R.id.resultsList);
        searchResultAdapter = new GithubRepositoryArrayAdapter(
                this,
                R.layout.search_result_item,
                R.id.search_result_item_text
        );
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        String[] autoCompletionArray = getResources()
                .getStringArray(R.array.autocompletion_array);
        ArrayAdapter<String> autoCompletionAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                autoCompletionArray
        );
        dbHelper = new GithubSearchResultDatabaseHelper(this);

        loadFromCacheIfAvailable(dbHelper, searchResultAdapter);

        list.setAdapter(searchResultAdapter);
        searchBox.setAdapter(autoCompletionAdapter);
        searchBox.setTokenizer(new SpaceTokenizer());

        // Hook-up events.
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = searchBox
                        .getText()
                        .toString();

                if (!isValid(searchText)) {
                    searchBox.setError("Invalid search query.");
                } else {
                    GithubRepositorySearchQuery query = GithubRepositorySearchQuery.fromString(searchText);

                    searchForRepositories(query);

                    showProgressBar();
                }
            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                GithubRepository repo = searchResultAdapter.getItem(position);
                intent.putExtra(REPO, repo);
                startActivity(intent);
            }
        });
    }

    private void loadFromCacheIfAvailable(GithubSearchResultDatabaseHelper dbHelper,
                                          ArrayAdapter<GithubRepository> adapter) {
        ArrayList<GithubRepository> cachedRepos = (ArrayList<GithubRepository>) dbHelper.getAllRepo();

        if (cachedRepos.size() > 0) {
            // Display cached values...
            adapter.addAll(cachedRepos);
        }

    }

    private boolean isValid(String searchText) {
        return !searchText.trim().isEmpty();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void searchForRepositories(GithubRepositorySearchQuery query) {
        SearchGithubRepositories task = new SearchGithubRepositories() {
            @Override
            protected void onPostExecute(GithubSearchResult result) {
                searchResultAdapter.clear();
                searchResultAdapter.addAll(result.getItems());

                hideProgressBar();
                updateCache(dbHelper, result.getItems());
            }
        };

        task.execute(query);
    }

    private void updateCache(GithubSearchResultDatabaseHelper dbHelper,
                             ArrayList<GithubRepository> repositories) {

        dbHelper.clearRepositories();

        for (int i = 0; i < repositories.size(); i++) {
            GithubRepository repository = repositories.get(i);

            Integer id = repository.getID();
            String name = repository.getName();
            String fullName = repository.getFullName();
            // TODO-francisbrito: Add this field.
            String url = "";
            String description = repository.getDescription();
            Integer stars = repository.getStargazersCount();
            Integer watchers = repository.getWatchersCount();
            // TODO-francisbrito: Add this field.
            Integer forks = 0;
            // TODO-francisbrito: Fix #toString calls.
            dbHelper.insertRepo(id, name, fullName, url, description,
                    stars.toString(), watchers.toString(), forks.toString());
        }
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
