package com.teta_tm.sayeban.userprofile.userprofile4;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbHelper extends SQLiteOpenHelper {

    private Context mycontext;
    private String DB_PATH;

    private static String DB_NAME = "userprofile";
    public SQLiteDatabase myDataBase;


    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.mycontext = context;
        if (android.os.Build.VERSION.SDK_INT >= 4.2) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        boolean dbexist = checkdatabase();
        if (dbexist) {
            opendatabase();
        } else {
            try {
                createdatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean checkdatabase(){
        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();
        } catch (SQLiteException e) {
            System.out.println();

        }
        return checkdb;
    }
    public void opendatabase () throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if (!dbexist) {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }
    public synchronized void close() {
        if (myDataBase != null) {
            myDataBase.close();
        }
        super.close();
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte from inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer)) > 0) {
            myoutput.write(buffer, 0, length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Integer num_pro(String field){
        Cursor cursor= myDataBase.rawQuery("select distinct "+field+ " from userprofile ", null);
        int num=cursor.getCount();
        return num;
    }
    public String province(String field, int row){
        Cursor cursor= myDataBase.rawQuery("select distinct "+field+ " from userprofile", null);
        cursor.moveToPosition(row);
        String pro= cursor.getString(0);
        return pro;
    }
    public Integer num_city(String field){
        Cursor cursor= myDataBase.rawQuery("select "+"city"+" from userprofile "+"where province = '"+field+"'"  , null);
        int num=cursor.getCount();
        return num;
    }
    public String citis(String field, int row){
        Cursor cursor= myDataBase.rawQuery("select "+"city"+ " from userprofile "+"where province = '"+field+"'", null);
        cursor.moveToPosition(row);
        String pro= cursor.getString(0);
        return pro;
    }
}
