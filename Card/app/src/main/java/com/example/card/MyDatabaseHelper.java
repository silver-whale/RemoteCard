package com.example.card;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "business_card.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_card";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "card_name";
    private static final String COLUMN_COMPANY = "card_company";
    private static final String COLUMN_FIELD = "card_field";
    private static final String COLUMN_PHONE = "card_phone";
    private static final String COLUMN_EMAIL = "card_email";
    private static final String COLUMN_PHONE2 = "card_phone2";
    private static final String COLUMN_ADDRESS = "card_address";
    private static final String COLUMN_PRINTER = "card_printer";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_COMPANY + " TEXT, " +
                COLUMN_FIELD + " TEXT, " +
                COLUMN_PHONE + " INTEGER, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PHONE2 + " INTEGER, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_PRINTER + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addCard(String name, String company, String field, int phone, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_COMPANY, company);
        cv.put(COLUMN_FIELD, field);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_EMAIL, email);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){ //fail 시
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Succesfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}