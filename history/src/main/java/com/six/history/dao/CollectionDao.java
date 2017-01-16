package com.six.history.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.six.history.bean.CollectionBean;
import com.six.history.db.Collection;

import java.util.ArrayList;
import java.util.List;


public class CollectionDao {

    private Collection historyhepler;

    public CollectionDao(Context context) {
        historyhepler = new Collection(context);
    }
    //添加
    public boolean insert(String id,String data,String title,String url){

        SQLiteDatabase db = historyhepler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("history_id",id);
       // values.put("data_id",data_id);
        values.put("data",data);
        values.put("title",title);
        values.put("url",url);
        long minsert = db.insert("collection",null,values);

        if (minsert!=-1){
            return true;
        }else{
            return false;
        }
    }

    //删除
    /**
     * 根据id将给定的数据从数据库中删除
     * @param
     * @param
     * @param id
     */
    //删除
    public boolean delete(String id){
        SQLiteDatabase db=historyhepler.getWritableDatabase();
        int delete = db.delete("collection","history_id=?",new String[]{id+""});
        if(delete>0){
            return true;
        }else{
            return false;
        }
    }

    public List<CollectionBean> select(){
        List<CollectionBean> list=new ArrayList<>();
        SQLiteDatabase db=historyhepler.getReadableDatabase();
        Cursor cursor=db.query("collection",null,null,null,null,null,null);
        while (cursor.moveToNext()){
            CollectionBean bean=new CollectionBean();
           // int id=cursor.getInt(cursor.getColumnIndex("id"));
            String history_id= cursor.getString(cursor.getColumnIndex("history_id"));
            String data = cursor.getString(cursor.getColumnIndex("data"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String url=cursor.getString(cursor.getColumnIndex("url"));
            bean.setHistory_id(history_id);
            bean.setData(data);
            bean.setTitle(title);
            bean.setUrl(url);
            list.add(bean);
        }
        return list;
    }

    // 读取数据
    public int idread(String id) {

        // 执行sql语句需要sqliteDatabase对象
        // 调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase database = historyhepler.getReadableDatabase();
        // table:表名, columns：查询的列名,如果null代表查询所有列； selection:查询条件,
        // selectionArgs：条件占位符的参数值,
        // groupBy:按什么字段分组, having:分组的条件, orderBy:按什么字段排序
        Cursor cursor = database.rawQuery("select * from collection where history_id=?", new String[]{id+""});
        int count = 0;
        // 解析Cursor中的数据
        List<CollectionBean> list = new ArrayList<CollectionBean>();
        if (cursor != null && cursor.getCount() > 0) {// 判断cursor中是否存在数据

            // 循环遍历结果集，获取每一行的内容
            while (cursor.moveToNext()) {// 条件，游标能否定位到下一行
                count = cursor.getInt(cursor.getColumnIndex("history_id"));
            }
            cursor.close();// 关闭结果集

        }
        // 关闭数据库对象
        database.close();
        return count;
    }

}
