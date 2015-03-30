package example.com.trendingcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dripoll24 on 3/25/2015.
 */
public class GithubSearchResultDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    SQLiteDatabase db;

    public static final String DATABASE_NAME = "trending_code.db";

    public static final String REPOSITORY_TABLE_NAME = "repository";

    public static final String COLUMN_REPOSITORY_ID = "id";
    public static final String COLUMN_REPOSITORY_NAME = "name";
    public static final String COLUMN_REPOSITORY_FULL_NAME = "full_name";
    public static final String COLUMN_REPOSITORY_LANGUAGE = "language";
    public static final String COLUMN_REPOSITORY_DESCRIPTION = "description";
    public static final String COLUMN_REPOSITORY_STARS = "stars";
    public static final String COLUMN_REPOSITORY_WATCHERS = "watchers";

    public static final String CREATE_REPOSITORY_TABLE =
            "CREATE TABLE " +
                REPOSITORY_TABLE_NAME +
                "( " +
                    COLUMN_REPOSITORY_ID + " integer primary key, " +
                    COLUMN_REPOSITORY_NAME + " text, " +
                    COLUMN_REPOSITORY_FULL_NAME + " text, " +
                    COLUMN_REPOSITORY_LANGUAGE + " text, "+
                    COLUMN_REPOSITORY_DESCRIPTION + " text, " +
                    COLUMN_REPOSITORY_STARS + " integer, " +
                    COLUMN_REPOSITORY_WATCHERS + " integer " +
                ")";

    public GithubSearchResultDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_REPOSITORY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + REPOSITORY_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertRepo(Integer id, String name, String fullName, String url, String description, String startGazers, String watchers, String forks){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REPOSITORY_ID, id);
        cv.put(COLUMN_REPOSITORY_NAME, name);
        cv.put(COLUMN_REPOSITORY_FULL_NAME, fullName);
        cv.put(COLUMN_REPOSITORY_LANGUAGE, url);
        cv.put(COLUMN_REPOSITORY_DESCRIPTION, description);
        cv.put(COLUMN_REPOSITORY_STARS, startGazers);
        cv.put(COLUMN_REPOSITORY_WATCHERS, watchers);

        try{
            db.insert(REPOSITORY_TABLE_NAME, null, cv);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<GithubRepository> getAllRepo(){
        db = this.getReadableDatabase();

        List<GithubRepository> repos = new ArrayList<>();

        Cursor cursor = db.query(REPOSITORY_TABLE_NAME, new String[] {COLUMN_REPOSITORY_ID, COLUMN_REPOSITORY_NAME, COLUMN_REPOSITORY_FULL_NAME, COLUMN_REPOSITORY_LANGUAGE, COLUMN_REPOSITORY_DESCRIPTION, COLUMN_REPOSITORY_STARS, COLUMN_REPOSITORY_WATCHERS}, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            GithubRepository repo = cursorToRepo(cursor);
            repos.add(repo);
            cursor.moveToNext();
        }

        cursor.close();
        return repos;
    }

    private GithubRepository cursorToRepo(Cursor cursor){
        GithubRepository repo = new GithubRepository();

        repo.setID(cursor.getInt(0));
        repo.setName(cursor.getString(1));
        repo.setFullName(cursor.getString(2));
        repo.setLanguage(cursor.getString(3));
        repo.setDescription(cursor.getString(4));
        repo.setStargazersCount(cursor.getInt(5));
        repo.setWatchersCount(cursor.getInt(6));

        return repo;
    }

    public void clearRepositories() {
        onUpgrade(db, DATABASE_VERSION, DATABASE_VERSION);
    }
}