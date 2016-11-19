package com.cse535.jerry.project_final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Yanzhu on 2016/11/15.
 */

public class DBUtil {

    SQLiteDatabase db = null;
    private Context context;

    public DBUtil(Context ct){
        this.context = ct;
    }

    public void createdb(){
        db = context.openOrCreateDatabase("yyzdb.db", Context.MODE_PRIVATE, null);
    }

    public void createTable(String tName, String tableColumn){
        boolean checkTableExist = ifTableExist(tName);
        if(checkTableExist){

        }else{
            db.execSQL("CREATE TABLE " +tName +" ("+ tableColumn +")");
        }
    }

    public void insertData(String tName, List<String> ColumnName, List<String> ColumnType, List<String> ColumnValue){
        ContentValues cv = new ContentValues();
        for(int i=0;i<ColumnName.size();i++){
            cv.put(ColumnName.get(i), ColumnValue.get(i));
        }

        db.insert(tName, null, cv);
    }

    public boolean ifTableExist(String tName){
        boolean ifTableExist = true;

        Cursor c = db.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='" + tName + "'", null);
        if(c.moveToNext()){
            if (c.getInt(0) == 0) {
                ifTableExist = false;
            }
        }
        c.close();

        return ifTableExist;
    }


    public String getPath(){
        String path = db.getPath();
        return path;
    }


}
