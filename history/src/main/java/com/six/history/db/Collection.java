package com.six.history.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * autour: 曾光春
 * date: $date$ $time$
 * update: $date$
 */
public class Collection extends SQLiteOpenHelper{

    public Collection(Context context) {
        super(context,"Collection.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    // 创建表
        sqLiteDatabase.execSQL("create table collection(id integer primary key autoincrement,history_id varchar(20),data varchar(20),title varchar(20),url varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
