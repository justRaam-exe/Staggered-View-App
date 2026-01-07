package com.raam.weebapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "wallpaper.db";
    private static final int DB_VERSION = 1;
    private final Context context;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE images (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "image_name TEXT," + "title TEXT," + "description TEXT)"
        );

        seedData(db, context);
    }

    private void seedData(SQLiteDatabase db, Context context) {
        for (int i = 1; i <= 26; i++) {
            String name = "img_" + i;
            ContentValues cv = new ContentValues();
            cv.put("image_name", name);
            cv.put("title", "Wallpaper " + i);
            cv.put("description", "Auto seeded image");

            db.insert("images", null, cv);
        }
    }
}
