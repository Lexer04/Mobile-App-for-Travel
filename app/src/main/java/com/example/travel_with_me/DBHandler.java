package com.example.travel_with_me;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String TAG = "login";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "db";
    private static final String TB_NAME = "users_data";
    private static final String ID = "id";
    private static final String USER = "nama";
    private static final String PASS = "password";

    public DBHandler (Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TB_NAME +" ("+ID+" INTEGER PRIMARY KEY,"+USER+" TEXT,"+PASS+" TEXT)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TB_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertUser (DataModel user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER, user.getNama());
        cv.put(PASS, user.getPassword());
        sqLiteDatabase.insert(TB_NAME, null, cv);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM "+TB_NAME+" WHERE id="+id,null);
        return result;
    }

    public Cursor getAllData() {
        String query = "SELECT * FROM "+TB_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor c = null;

        if(sqLiteDatabase != null){
            c = sqLiteDatabase.rawQuery(query,null);
        }
        return c;
    }

    public ArrayList getAll() {
        ArrayList Data = new ArrayList();
        String query = "SELECT * FROM "+TB_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery(query,null);

        if(c.moveToFirst()) {
            do {
                Data.add("\n"+c.getString(0)+".\tUsername : "+c.getString(1)+"\tPassword : "+c.getString(2));
            } while (c.moveToNext());
        };
        return Data;
    }
    public boolean deleteData(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TB_NAME, ID + "=" + id,null);
        return true;
    }
}
