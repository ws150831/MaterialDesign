package com.six.history.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Effect:创建一个数据库
 * autour: 张玉杰
 * date: 2016/12/19 20:51
 * update: 2016/12/19
 */

public class ScUserHelper extends SQLiteOpenHelper {

    //为数据库命名
    public ScUserHelper(Context context) {
        super(context, "scuser.db", null, 1);
    }

    // 创建一个数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table scusers(id integer primary key autoincrement,data varchar(20),title varchar(20),image varchar(20),e_id varchar(20))");
    }

    //修改数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}