package com.example.a002;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_contacts;

    static {
        CREATE_contacts = "create table contacts("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "phone text,"
                + "sex text)";
    }

    private Context mContext;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_contacts);
//        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


}