package com.example.a002;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public static final int contacts_DIR = 0;
    public static final int contacts_ITEM = 1;
    public static final String AUTHORITY = "com.example.text7.provider";
    private static UriMatcher uriMatcher;
    private MyDatabaseHelper dbHelper;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"contacts",contacts_DIR);
        uriMatcher.addURI(AUTHORITY,"contacts/#",contacts_ITEM);

    }
    public MyContentProvider() {

    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
//        throw new UnsupportedOperationException("Not yet implemented");
        switch (uriMatcher.match(uri)){
            case contacts_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.text7.provider.contacts";
            case contacts_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.text7.provider.contacts";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        dbHelper = new MyDatabaseHelper(getContext(),"people.db",null,1);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
//        throw new UnsupportedOperationException("Not yet implemented");
        //查询数据
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case contacts_DIR:
                cursor = db.query("contacts",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case contacts_ITEM:
                String manid = uri.getPathSegments().get(1);
                cursor = db.query("contacts",projection,"id=?",new String[]{manid},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
