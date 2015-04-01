package example.com.trendingcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository_description);

        Intent i = getIntent();
        GithubRepository repo = (GithubRepository)i.getSerializableExtra(MainActivity.REPO);

        TextView name = (TextView) findViewById(R.id.txtName);
        TextView fullName = (TextView) findViewById(R.id.txtFullName);
        TextView description = (TextView) findViewById(R.id.txtDescription);
        TextView stars = (TextView) findViewById(R.id.txtStarsGazers);
        TextView watchers = (TextView) findViewById(R.id.txtWatchers);
        TextView language = (TextView) findViewById(R.id.txtLanguage);

        try {
            name.setText(repo.getName());
            fullName.setText(repo.getFullName());
            description.setText(repo.getDescription());
            stars.setText(repo.getStargazersCount().toString());
            watchers.setText(repo.getWatchersCount().toString());
            language.setText(repo.getLanguage());
        }catch (Exception e){
            Log.e("DetailActivity", "Error occurred", e);
        }
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
