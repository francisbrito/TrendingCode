package example.com.trendingcode;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class CommentsActivity extends ActionBarActivity {

    public static final String TAG = CommentsActivity.class.getSimpleName();
    GithubSearchResultDatabaseHelper dbHelper = new GithubSearchResultDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        ListView listView = (ListView) findViewById(R.id.commentsListView);


        List<Comment> commentsArray = dbHelper.getAllComments();

        Log.e("Probando","# de arreglo de comentarios: " + commentsArray.size());

        ArrayList<String> arrayList = new ArrayList<>();


        for(int i = 0; i <= commentsArray.size(); i++){

            Log.i(TAG,String.valueOf(i));
            Log.i(TAG,commentsArray.get(i).getText());

            Comment comment = commentsArray.get(i);
            arrayList.add(comment.getRepoID() + ":  " + comment.getText());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                arrayList );


        listView.setAdapter(arrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comments, menu);
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
