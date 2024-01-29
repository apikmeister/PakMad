package com.example.pakmad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DBNAME = "PakMad";
    private static final int DBVERSION = 1;
    private static final String TABLENAME = "pakmadtable";
    private static final String COLID = "id";
    private static final String COLENTITY = "entity";
    private static final String COLUSERNAME = "username";
    private static final String COLPASSWORD = "password";
    private static final String COLDESC = "description";

    public DBHandler(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLENAME + "("
                + COLID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLENTITY + " TEXT,"
                + COLUSERNAME + " TEXT,"
                + COLPASSWORD + " TEXT,"
                + COLDESC + " TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        String sql = "DROP TABLE IF EXISTS " + TABLENAME;

        db.execSQL(sql);
        onCreate(db);
    }

    public ArrayList<PakMad> getAllEntity() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor rs = db.rawQuery("SELECT * FROM " + TABLENAME, null);
        ArrayList<PakMad> entityList = new ArrayList<>();
        if (rs.moveToFirst()) {
            do {
                entityList.add(new PakMad(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            } while (rs.moveToNext());
        }
        rs.close();
        if (entityList.size() < 1) {
            entityList.add(new PakMad("No Data", "Found", "in", "Database"));
        }

        return entityList;
    }

    public long addNewEntity(String entity, String username, String password, String desc) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLENTITY, entity);
        cv.put(COLUSERNAME, username);
        cv.put(COLPASSWORD, password);
        cv.put(COLDESC, desc);

        long rValue = db.insert(TABLENAME, null, cv);

        return rValue;
    }

    public long updateEntity(String oldEntity, String newEntity, String newUsername, String newPassword, String newDesc) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLENTITY, newEntity);
        cv.put(COLUSERNAME, newUsername);
        cv.put(COLPASSWORD, newPassword);
        cv.put(COLDESC, newDesc);

        long rValue = db.update(TABLENAME, cv, "entity = ?", new String[]{oldEntity});

        return rValue;
    }

    public long deleteEntity(String oldEntity) {

        SQLiteDatabase db = this.getWritableDatabase();

        long rValue = db.delete(TABLENAME, "entity = ?", new String[]{oldEntity});

        return rValue;
    }
}
