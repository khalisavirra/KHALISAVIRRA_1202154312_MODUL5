package com.example.khalisavirra.khalisavirra_1202154312_modul5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by virra PC on 3/24/2018.
 */

public class Database extends SQLiteOpenHelper {

    //Deklarasi variable yang akan digunakan
    //Deklarasi versi Database yang digunakan
    private static final int DATABASE_VERSION = 2;

    //Deklarasi nama Database
    static final String DATABASE_NAME = "virra.db";

    //Deklarasi Table menggunakan SQLite
    public static final String TABLE_SQLite = "crud";

    //Deklarasi kolum di dalam Tabel
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_PRIORITY = "priority";

    //Konstruktor dari class Database
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ketika Tabel dan Kolom dibuat didalam Database
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + TABLE_SQLite + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_DESC + " TEXT NOT NULL," +
                COLUMN_PRIORITY + " TEXT NOT NULL" +
                " )";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    //Method yang bisa digunakan untuk melakukan perubahan data pada Database SQLite
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SQLite);
        onCreate(db);
    }

    //Method untuk mengakses dan melihat data dari Database
    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT * FROM " + TABLE_SQLite;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(COLUMN_ID, cursor.getString(0));
                map.put(COLUMN_NAME, cursor.getString(1));
                map.put(COLUMN_DESC, cursor.getString(2));
                map.put(COLUMN_PRIORITY, cursor.getString(3));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        Log.e("select sqlite ", "" + wordList);
        database.close();
        return wordList;
    }

    //Method untuk menginputkan atau menambahkan/insert data ke Database
    public void insert(String name, String address, String jumlah) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_SQLite + " (name, desc, priority) " +
                "VALUES ('" + name + "', '" + address + "','" + jumlah + "')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    //Method untuk menghapus data di Database
    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_SQLite + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
