package example.com.trendingcode;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

/**
 * Created by francis on 03/30/15.
 */
public class GithubSearchResultDatabaseHelperTest extends AndroidTestCase {
    public void testCanCreateDatabase() {
        mContext.deleteDatabase(GithubSearchResultDatabaseHelper.DATABASE_NAME);

        GithubSearchResultDatabaseHelper dbHelper =
                new GithubSearchResultDatabaseHelper(getContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        assertEquals(db.isOpen(), true);
        db.close();
    }
}
