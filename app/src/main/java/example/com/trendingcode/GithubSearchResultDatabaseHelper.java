package example.com.trendingcode;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dripoll24 on 3/25/2015.
 */
public class GithubSearchResultDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;

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
	
	public static final String[] ALL_COLUMNS_REPOSITORY = new String[] {COLUMN_REPOSITORY_ID, COLUMN_REPOSITORY_NAME, COLUMN_REPOSITORY_FULL_NAME, COLUMN_REPOSITORY_LANGUAGE,
                                                                                    COLUMN_REPOSITORY_DESCRIPTION, COLUMN_REPOSITORY_STARS, COLUMN_REPOSITORY_WATCHERS};
	
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

    private static final String COMMENT_TABLE_NAME = "comment";

    private static final String COLUMN_COMMENT_ID = "id";
    private static final String COLUMN_COMMENT_BODY = "body";
    private static final String COLUMN_COMMENT_FOREIGN_KEY_REPOSITORY_ID = "repository_id";

    public static final String[] ALL_COLUMNS_COMMENT = new String[] {COLUMN_COMMENT_ID, COLUMN_COMMENT_BODY, COLUMN_COMMENT_FOREIGN_KEY_REPOSITORY_ID};


    public static final String CREATE_COMMENT_TABLE =
            "CREATE TABLE " +
                COMMENT_TABLE_NAME +
                "( " +
                    COLUMN_COMMENT_ID + " integer primary key, " +
                    COLUMN_COMMENT_BODY + " text, " +
                    COLUMN_COMMENT_FOREIGN_KEY_REPOSITORY_ID + " integer, " +
                    "FOREIGN KEY(" + COLUMN_COMMENT_FOREIGN_KEY_REPOSITORY_ID + ") " +
                    "REFERENCES " + REPOSITORY_TABLE_NAME + "(" + COLUMN_REPOSITORY_ID  + ")" +
                ")";

    public GithubSearchResultDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_REPOSITORY_TABLE);
        db.execSQL(CREATE_COMMENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + REPOSITORY_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + COMMENT_TABLE_NAME);

        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    public boolean insertRepo(Integer id, String name, String fullName, String languange, String description, String startGazers, String watchers, String forks){
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_REPOSITORY_ID, id);
        cv.put(COLUMN_REPOSITORY_NAME, name);
        cv.put(COLUMN_REPOSITORY_FULL_NAME, fullName);
        cv.put(COLUMN_REPOSITORY_LANGUAGE, languange);
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

        Cursor cursor = db.query(REPOSITORY_TABLE_NAME, ALL_COLUMNS_REPOSITORY, null, null, null, null, null);

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

    public GithubRepository getRepo(GithubRepository repo){
        String select = "SELECT * FROM " + REPOSITORY_TABLE_NAME + " WHERE " + COLUMN_REPOSITORY_ID + " = " + repo.getID();
        Cursor c = db.query(REPOSITORY_TABLE_NAME, ALL_COLUMNS_REPOSITORY, select, null, null, null, null);
        return cursorToRepo(c);
	}



    public void insertComment(int repositoryId, String body) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_COMMENT_FOREIGN_KEY_REPOSITORY_ID, repositoryId);
        values.put(COLUMN_COMMENT_BODY, body);

        db.insert(COMMENT_TABLE_NAME, null, values);
    }

    public List<Comment> getAllComments(){
        SQLiteDatabase db = getWritableDatabase();

        List<Comment> comments = new ArrayList<>();

        Cursor cursor = db.query(COMMENT_TABLE_NAME, ALL_COLUMNS_COMMENT, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            Comment comment = cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();
        }
        cursor.close();

        return comments;
    }


    private Comment cursorToComment(Cursor cursor){
        Comment comment = new Comment();

        comment.setText(cursor.getString(0));
        comment.setrepoID(cursor.getInt(1));

        return comment;
    }

    public Comment getComment(Comment comment){
        String select = "SELECT * FROM " + COMMENT_TABLE_NAME + " WHERE " + COLUMN_COMMENT_ID + " = " + comment.getRepoID();
        Cursor c = db.query(COMMENT_TABLE_NAME, ALL_COLUMNS_COMMENT, select, null, null, null, null);
        return cursorToComment(c);
    }
}