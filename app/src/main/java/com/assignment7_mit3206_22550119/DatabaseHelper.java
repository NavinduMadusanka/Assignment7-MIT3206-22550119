package com.assignment7_mit3206_22550119;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME="librarySystem";

    public static final String TABLE_NAME="Member";
    //public static final String COL = "CardNo";
    //public static final String COL_1="FirstName";
    //public static final String COL_2="LastName";
    //public static final String COL_3="Contact";
    //public static final String COL_4="Email";
    //public static final String COL_5="Address";
    //public static final String COL_6="UserName";
    //public static final String COL_7="Password";

    public static final String TABLE_NAME1="Book";
    public static final String coloum1="BookID";
    public static final String coloum2="BookName";
    public static final String coloum3="BookPublisher";
    public static final String coloum4="BookAuthor";
    public static final String coloum5="Branch";

    public static final String TABLE_NAME2="Publisher";
    public static final String COL1= "Name";
    public static final String COL2="Address";
    public static final String COL3="Phone";

    public static final String TABLE_NAME3="Branch";
    public static final String coloumm1="BranchID";
    public static final String coloumm2="BranchName";
    public static final String coloumm3="BranchAddress";

    public static final String TABLE_NAME4="BookLoan";

    //private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +COL+"INTEGER,"+ COL_1 + " TEXT," +
    //COL_2 +" TEXT," + COL_3 +" TEXT,"+ COL_4 +" TEXT,"+ COL_5 +" TEXT,"+ COL_6 +" TEXT,"+ COL_7 +" TEXT);";

    private static final String CREATE_TABLE1 = "create table " + TABLE_NAME1 + "(" + coloum1 + " TEXT," + coloum2 +" TEXT," +
            coloum3 +" TEXT,"+coloum4+" TEXT,"+coloum5+" TEXT);";

    private static final String CREATE_TABLE2 = "create table " + TABLE_NAME2 + "(" + COL1 + " TEXT," + COL2 +" TEXT," +
            COL3 +" TEXT);";

    private static final String CREATE_TABLE3 = "create table " + TABLE_NAME3 + "(" + coloumm1 + " TEXT," + coloumm2 +" TEXT," +
            coloumm3 +" TEXT);";

    public DatabaseHelper (@Nullable Context context){
        super (context, DATABASE_NAME,null,1);
        Log.e("first","OpenDatabase");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("Test2","OnCreate Table");
        //db.execSQL(CREATE_TABLE);
        db.execSQL("CREATE TABLE "
                + TABLE_NAME
                + "(CardNo INTEGER PRIMARY KEY AUTOINCREMENT,  FirstName VARCHAR(50), LastName VARCHAR(50), " +
                "Contact VARCHAR(50), Email VARCHAR, Address VARCHAR, UserName VARCHAR, Password VARCHAR)");

        db.execSQL(CREATE_TABLE1);
        //db.execSQL("CREATE TABLE "
        //+ TABLE_NAME1
        //+ "(BookID VARCHAR(50) PRIMARY KEY,  BookName VARCHAR(50), BookPublisher VARCHAR(50), " +
        //"BookAuthor VARCHAR(50))");
        db.execSQL(CREATE_TABLE2);
        db.execSQL(CREATE_TABLE3);

        db.execSQL("CREATE TABLE "
                + TABLE_NAME4
                + "(AccessNo INTEGER PRIMARY KEY AUTOINCREMENT,  BranchID VARCHAR(50), CardNo VARCHAR(50), " +
                "DateOut VARCHAR(50), DateDue VARCHAR(50), DateReturned VARCHAR(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME1);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME4);
        onCreate(db);
    }

    public Cursor searchUsers(String text){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+TABLE_NAME1+" WHERE "+coloum2+ "OR"+coloum4+" Like '@"+text+"@'";
        Cursor cursor = db.rawQuery(query,null);
        return cursor;
    }

    public Cursor viewData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+TABLE_NAME1;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    //search from query
    public Cursor searchUsers1(String text){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from "+TABLE_NAME1+" WHERE "+coloum2+ "OR"+coloum4+" Like '@"+text+"@'";
        Cursor cursor = db.rawQuery(query, null);
        return  cursor;
    }
}
