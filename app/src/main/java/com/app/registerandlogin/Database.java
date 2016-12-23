package com.app.registerandlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Android on 22-Dec-16.
 */

public class Database extends SQLiteOpenHelper {

    public static final String mDatabase_Name = "LoginAndRegisterDB";
    public static final String mTable_Name = "Detail";
    public static final String mCol1 = "FirstName";
    public static final String mCol2 = "LastName";
    public static final String mCol3 = "UserName";
    public static final String mCol4 = "Password";
    public static final String mCol5 = "Contact_No";
    private static final String CREATE = "create table StudentDetail (FirstName varchar(20),LastName varchar(20) ,UserId varchar(30) primary key,Password varchar(20), Contact_No varchar(10));";
    public SQLiteDatabase db;



    public Database(Context context) {
        super(context, mDatabase_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if exists" + mTable_Name);
        onCreate(db);

    }

         boolean insertDatabase(String fName, String lName, String userName, String password, String contactNo) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mCol1, fName);
        contentValues.put(mCol2, lName);
        contentValues.put(mCol3, userName);
        contentValues.put(mCol4, password);
        contentValues.put(mCol5, contactNo);


        long result = db.insert(mTable_Name, null, contentValues);

        System.out.print(result);
        if (result == -1)
            return false;

        else
            return true;


    }

    public Cursor getlogin(String userName,String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery(" select * from " + mTable_Name + " WHERE TRIM ( "+ userName+" ) = "+mCol3 ,null);


        return res;
    }



}
