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
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "card_name";
    private static final String COLUMN_COMPANY = "card_company";
    private static final String COLUMN_FIELD = "card_field";
    private static final String COLUMN_PHONE = "card_phone";
    private static final String COLUMN_EMAIL = "card_email";
    private static final String COLUMN_ADDRESS = "card_address";
    private static final String COLUMN_PHONE2 = "card_phone2";
    private static final String COLUMN_PRINTER = "card_printer";


    MyDatabaseHelper(@Nullable Context context) {
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
                COLUMN_PHONE + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_PHONE2 + " TEXT, " +
                COLUMN_PRINTER + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addCard(String name, String company, String field, String phone, String email,
                 String address, String phone2, String printer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_COMPANY, company);
        cv.put(COLUMN_FIELD, field);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_PHONE2, phone2);
        cv.put(COLUMN_PRINTER, printer);

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

    void updateData(String row_id, String name, String company, String field, String phone, String email,
                    String address, String phone2, String printer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_COMPANY, company);
        cv.put(COLUMN_FIELD, field);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_ADDRESS, address);
        cv.put(COLUMN_PHONE2, phone2);
        cv.put(COLUMN_PRINTER, printer);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Fail to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
}
