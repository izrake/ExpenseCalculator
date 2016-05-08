package com.firebase.prashannt.exc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by prashannt on 5/8/2016.
 */
public class HotOrNot {

    private static final String KEY_ROWID="_id";
    private static final String KEY_BUDGET="_budget";
    private static final String DATABASE_NAME="Budget";
    private static final String DATABASE_TABLE1="total_budget";
    private static final String DATABASE_TABLE2="expense_detail";
    private static final String KEY_EXID="E_id";
    private static final String KEY_EXPAMNT="exp_amnt";
    private static final String KEY_EXDETAIL="exp_detail";
    private static final int DATABASE_VERSION=1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;



    private class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE "+DATABASE_TABLE1+" (" +KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_BUDGET+" TEXT NOT NULL )");

            db.execSQL("CREATE TABLE "+DATABASE_TABLE2+" (" +KEY_EXID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_EXPAMNT+" TEXT NOT NULL,"+KEY_EXDETAIL+" TEXT NOT NULL )"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE1);
            db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE2);
            onCreate(db);

        }
    }

    public HotOrNot(Context c) {
        ourContext=c;
    }

    public HotOrNot open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        ourHelper.close();
    }
    public long createEntry(String name) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_BUDGET, name);
        return ourDatabase.insert(DATABASE_TABLE1,null,cv);
    }
    public long createEntry(String amount,String detail) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_EXPAMNT, amount);
        cv.put(KEY_EXDETAIL,detail);
        return ourDatabase.insert(DATABASE_TABLE2,null,cv);
    }

    public String getData(){

        String[] coloumns= new String[]{KEY_EXID,KEY_EXDETAIL,KEY_EXPAMNT};
        Cursor c=ourDatabase.query(DATABASE_TABLE2,coloumns,null,null,null,null,null);
        String result="";
        int iRow=c.getColumnIndex(KEY_EXID);
        int iDetail=c.getColumnIndex(KEY_EXDETAIL);
        int iExpAmnt=c.getColumnIndex(KEY_EXPAMNT);

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            result= result+ c.getString(iRow)+" " +c.getString(iDetail)+" " +c.getString(iExpAmnt)+ "\n";
        }

        return result;
    }
}

