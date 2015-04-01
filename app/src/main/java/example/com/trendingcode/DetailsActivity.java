package example.com.trendingcode;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailsActivity extends ActionBarActivity {

    GithubSearchResultDatabaseHelper dbHelper = new GithubSearchResultDatabaseHelper(this);
    Context context = this;
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
        Button commentBtn = (Button) findViewById(R.id.buttonAddComment);
        final EditText text = (EditText) findViewById(R.id.editText);

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

        final Integer id = repo.getID();
        commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.insertComment(id,text.getText().toString());

                //Alert dialog, Comment added.
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Comentario agregado.");
                builder1.setPositiveButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

                text.setText("");
            }
        });
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
