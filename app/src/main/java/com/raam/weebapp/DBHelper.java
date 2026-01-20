//package com.raam.weebapp;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DBHelper extends SQLiteOpenHelper {
//    private static final String DB_NAME = "wallpaper.db";
//    private static final int DB_VERSION = 1;
//    private final Context context;
//
//    public DBHelper(Context context) {
//        super(context, DB_NAME, null, DB_VERSION);
//        this.context = context;
//    }
//
//    @Override
//    public void onCreate (SQLiteDatabase db) {
//        db.execSQL(
//                "CREATE TABLE images (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "image_name TEXT," + "title TEXT," + "description TEXT)"
//        );
//
//        seedData(db, context);
//    }
//
//    private void seedData(SQLiteDatabase db, Context context) {
//        int i = 1;
//
//        while(true) {
//            String name = "img_" + i;
//            int resId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
//            if (resId == 0) break;
//
//            ContentValues cv = new ContentValues();
//            cv.put("image_name", name);
//            cv.put("title", "Wallpaper " + i);
//            cv.put("description", "Auto seeded image");
//
//            db.insert("images", null, cv);
//            i++;
//        }
//    }
//
//    private void insertImage(SQLiteDatabase db, String imageName, String title, String desc) {
//        ContentValues cv = new ContentValues();
//        cv.put("image_name", imageName);
//        cv.put("title", title);
//        cv.put("description", desc);
//        db.insert("images", null, cv);
//    }
//
//    public List<ImageModel> getAllImages() {
//        List<ImageModel> list = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("SELECT * FROM images", null);
//
//        if (c.moveToFirst()) {
//            do {
//                list.add(new ImageModel(
//                   c.getInt(0),
//                   c.getString(1),
//                   c.getString(2),
//                   c.getString(3)
//                ));
//            } while (c.moveToNext());
//        }
//
//        c.close();
//        return list;
//    }
//
//    public List<ImageModel> searchImages(String keyword) {
//        List<ImageModel> list = new ArrayList<>();
//        SQLiteDatabase db = getReadableDatabase();
//
//        Cursor c = db.rawQuery(
//                "SELECT * FROM images WHERE title LIKE ?",
//                new String[]{"%" + keyword + "%"}
//        );
//
//        if (c.moveToFirst()) {
//            do {
//                list.add(new ImageModel(
//                        c.getInt(0),
//                        c.getString(1),
//                        c.getString(2),
//                        c.getString(3)
//                ));
//            } while (c.moveToNext());
//        }
//
//        c.close();
//        return list;
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS images");
//        onCreate(db);
//    }
//}
