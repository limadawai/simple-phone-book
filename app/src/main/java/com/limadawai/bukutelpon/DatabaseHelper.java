package com.limadawai.bukutelpon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "kontak.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE kontakdata (id INTEGER PRIMARY KEY AUTOINCREMENT, image LONGTEXT, nama TEXT, telpon TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS kontakdata");
        onCreate(db);
    }

    public boolean insertKontak(String image, String nama, String telpon, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("image", image);
        contentValues.put("nama", nama);
        contentValues.put("telpon", telpon);
        contentValues.put("email", email);
        long result = db.insert("KontakData", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<KontakDataset> getAllKontak() {
        ArrayList<KontakDataset> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM KontakData", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String image = cursor.getString(1);
            String nama = cursor.getString(2);
            String telpon = cursor.getString(3);
            String email = cursor.getString(4);
            KontakDataset kontakDataSet = new KontakDataset(id, image, nama, telpon, email);
            arrayList.add(kontakDataSet);
        }
        return arrayList;
    }

}
