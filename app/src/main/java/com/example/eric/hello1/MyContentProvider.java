package com.example.eric.hello1;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    public static final String MIME_DIR_PREFIX = "vnd.android.cursor.dir";
    public static final String MIME_ITEM_PREFIX = "vnd.android.cursor.item";
    public static final String MINE_ITEM = "com.inspur.student";
    public static final String AUTHORITY="com.inspur.edu";
    public static final String PATH_SINGLE = "student/#";
    public static final String PATH_MULTIPLE = "student";
    public static final String DB_TABLE="student";
    public static final String MINE_TYPE_SINGLE = MIME_ITEM_PREFIX + "/" + MINE_ITEM;
    public static final String MINE_TYPE_MULTIPLE = MIME_DIR_PREFIX + "/" + MINE_ITEM;

    public static final String CONTENT_URI_STRING = "content://" + AUTHORITY + "/" + PATH_MULTIPLE;
    public static final Uri  CONTENT_URI = Uri.parse(CONTENT_URI_STRING);


    private static final int MULTIPLE_STUDENT = 1;
    private static final int SINGLE_STUDENT = 2;
    private static  UriMatcher uriMatcher=null;
    private StudDbHelpler studDbHelpler;
    private SQLiteDatabase db;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, PATH_MULTIPLE, MULTIPLE_STUDENT);
        uriMatcher.addURI(AUTHORITY, PATH_SINGLE, SINGLE_STUDENT);
    }


    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch(uriMatcher.match(uri)){
            case MULTIPLE_STUDENT:
                count = db.delete(DB_TABLE, selection, selectionArgs);
                break;
            case SINGLE_STUDENT:
                String segment = uri.getPathSegments().get(1);
                count = db.delete(DB_TABLE,  "id=" + segment, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unsupported URI:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;

    }

    @Override
    public String getType(Uri uri) {
        switch(uriMatcher.match(uri)){
            case MULTIPLE_STUDENT:
                return MINE_TYPE_MULTIPLE;
            case SINGLE_STUDENT:
                return MINE_TYPE_SINGLE;
            default:
                throw new IllegalArgumentException("Unkown uri:"+uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db.insert(DB_TABLE, null, values);
        if ( id > 0 ){
            Uri newUri= ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Failed to insert row into " + uri);

    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        studDbHelpler=new StudDbHelpler(this.getContext());
        db=studDbHelpler.getWritableDatabase();
        if(db!=null){
            return true;
        }
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
       switch (uriMatcher.match(uri)){

           case SINGLE_STUDENT:
               if(selection.length()>0){
                   selection+=" and id="+uri.getPathSegments().get(1);
               }else {
                   selection+=" id="+uri.getPathSegments().get(1);
               }
               break;
       }
        return db.query("student",projection,selection,selectionArgs,null,null,sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count;
        switch(uriMatcher.match(uri)){
            case MULTIPLE_STUDENT:
                count = db.update(DB_TABLE, values, selection, selectionArgs);
                break;
            case SINGLE_STUDENT:
                String segment = uri.getPathSegments().get(1);
                count = db.update(DB_TABLE, values, "id="+segment, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknow URI:" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;

    }
}
