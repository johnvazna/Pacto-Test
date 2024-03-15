package com.johnvazna.local.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
  SQLite implementation if necessary for versions lower than SDK 14
  The module only changed the type of database to respect the single responsibility principle.
 */
public class SQLiteManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Pacto.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Implement if necessary
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Implement if necessary
    }
}
