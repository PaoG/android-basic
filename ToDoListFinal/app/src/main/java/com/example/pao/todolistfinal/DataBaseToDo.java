package com.example.pao.todolistfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pao on 6/30/2015.
 */
public class DataBaseToDo extends SQLiteOpenHelper {

    final static String TABLE_NAME = "todo";
    final static String TODO_TEXT = "textToDo";
    final static String _ID = "_id";
    final static String[] columns = { _ID, TODO_TEXT };

    final private static String CREATE_CMD =

            "CREATE TABLE todo (" + _ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TODO_TEXT + " TEXT NOT NULL)";

    final private static String NAME = "todo_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DataBaseToDo(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CMD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // N/A
    }

    void deleteDatabase() {
        mContext.deleteDatabase(NAME);
    }
}
