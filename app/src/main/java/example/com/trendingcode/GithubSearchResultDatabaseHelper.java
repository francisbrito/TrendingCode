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

    SQLiteDatabase db;

    public static final String DATABASE_NAME = "TrendingCode.db";

    public static final String TABLE_NAME = "repositories";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String FULL_NAME = "fullName";
    public static final String LANGUAGE = "language";
    public static final String DESCRIPTION = "description";
    public static final String STARTGAZERS = "starGazers";
    public static final String WATCHERS = "watchers";

    public static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID + "integer primary key" +  NAME + " text," +  FULL_NAME + " text," +  LANGUAGE + " text,"+
                                                                                     DESCRIPTION + " text," + STARTGAZERS + " integer," + WATCHERS + " integer" + ")";

    public GithubSearchResultDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertRepo(Integer id, String name, String fullName, String url, String description, String startGazers, String watchers, String forks){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ID, id);
        cv.put(NAME, name);
        cv.put(FULL_NAME, fullName);
        cv.put(LANGUAGE, url);
        cv.put(DESCRIPTION, description);
        cv.put(STARTGAZERS, startGazers);
        cv.put(WATCHERS, watchers);

        try{
            db.insert(TABLE_NAME, null, cv);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<GithubRepository> getAllRepo(){
        db = this.getReadableDatabase();

        List<GithubRepository> repos = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, new String[] {ID, NAME, FULL_NAME, LANGUAGE, DESCRIPTION, STARTGAZERS, WATCHERS}, null, null, null, null, null);
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
}