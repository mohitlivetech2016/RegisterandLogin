package com.app.registerandlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;

/**
 * Created by Android on 22-Dec-16.
 */

public class Database extends SQLiteOpenHelper {

    public static final String mDatabase_Name = "loginandregisterdb";
    public static final String mTable_Name = "details";
    public static final String mCol1 = "firstname";
    public static final String mCol2 = "lastname";
    public static final String mCol3 = "username";
    public static final String mCol4 = "password";
    public static final String mCol5 = "contactno";
    // private static final String CREATE = "create table "+ mTable_Name + "( "+mCol1 +"varchar(20),"+mCol2 +" varchar(20) ,"+mCol3+" varchar(30) primary key,"+mCol4+" varchar(20), "+mCol5+" varchar(10));";
    private static final String CREATE = "create table details ( firstname varchar(20), lastname varchar(20), username varchar(20), password varchar(20), contactno varchar(20))";

    //public SQLiteDatabase db;



    public Database(Context context) {
        super(context, mDatabase_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // db.execSQL("Drop table if exists" + mTable_Name);
        //onCreate(db);

    }
    SQLiteDatabase sd;
    public void open(){
        sd=getWritableDatabase();
        sd=getReadableDatabase();
    }

    public void close(){
        sd.close();;
    }

    public boolean insertDatabasee(String fName, String lName, String userName, String password, String contactNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mCol1, fName);
        contentValues.put(mCol2, lName);
        contentValues.put(mCol3, userName);
        contentValues.put(mCol4, password);
        contentValues.put(mCol5, contactNo);
        System.out.print(contentValues);



        long result = db.insert(mTable_Name, null, contentValues);


        db.close();

        System.out.print(result);
        if (result == -1)
            return false;

        else
            return true;


    }

    public Cursor getlogin(String userName,String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor res = db.rawQuery("select * from " + mTable_Name + " WHERE TRIM  "+ mCol3 +"  = "+ userName ,null);
        Cursor res=db.rawQuery("select * from "+mTable_Name+" where "+mCol3+" = ? and "+mCol4+" = ? ",new String[]{userName,password});


        return res;
    }
}
