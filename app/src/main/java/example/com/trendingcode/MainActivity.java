package example.com.trendingcode;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity {
    AutoCompleteTextView searchBox;
    Button searchBtn;
    ListView list;
    ProgressBar progressBar;

    ArrayAdapter<GithubRepository> searchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBox = (AutoCompleteTextView) findViewById(R.id.searchBox);
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

        list.setAdapter(searchResultAdapter);
        searchBox.setAdapter(autoCompletionAdapter);

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
            }
        };

        task.execute(query);
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
