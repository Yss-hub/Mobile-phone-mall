package com.example.yunji.phoneshoppingcar.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yunji.phoneshoppingcar.Model.Vip;
import com.example.yunji.phoneshoppingcar.Tables.PhoneCarSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/24.
 */

public class VipDao {
    //定义数据库对象
    private PhoneCarSQLiteOpenHelper helper;
    List<Vip> list;
    public VipDao(Context context) {
        helper = new PhoneCarSQLiteOpenHelper(context);
        list=new ArrayList<Vip>();
    }

    //添加新用户，即注册
    public void insert(Vip vip) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("name", vip.getName());
        vs.put("sex", vip.getSex());
        vs.put("number", vip.getNumber());
        vs.put("address",vip.getAddress());
        vs.put("password", vip.getPassword());
        //插入数据库返回自增id
        Long id = db.insert("Vip", null, vs);
        vip.setId(id);
        db.close();
    }
    //修改
    public int update(Vip vip) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("name", vip.getName());
        vs.put("password", vip.getPassword());
        vs.put("sex", vip.getSex());
        vs.put("number", vip.getNumber());
        vs.put("address",vip.getAddress());
        int count = db.update("Vip", vs, "id=?", new String[]{vip.getId() + ""});
        db.close();
        Log.i("EDITTEXT","count:"+count);
        return count;
    }
    //按照name进行商品信息查询
    public List<Vip> queryName(String name) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("Vip", null, "name=?", new String[]{name}, null, null,null);
        //准备用来放商品的所有集合
        // List<Goods> list = new ArrayList<Goods>();
        while (cursor.moveToNext()) {
            //直接取列号
            long id = cursor.getLong(0);
            //根据列名取列号
            String name1 = cursor.getString(cursor.getColumnIndex("name"));
            String sex =cursor.getString(cursor.getColumnIndex("sex"));
            int number=cursor.getInt(cursor.getColumnIndex("number"));
            String address=cursor.getString(cursor.getColumnIndex("address"));
            String password=cursor.getString(cursor.getColumnIndex("password"));
            //将商品的6个字段放在集合里
            Vip vip = new Vip(id, name1,sex,number,address,password);
            list.add(vip);
        }
        cursor.close();
        db.close();
        return list;
    }
    public long login(String name,String password){
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="select * from Vip where name=? and password=?";
        //查询sql语句rawQuery
        Cursor cursor=db.rawQuery(sql, new String[]{name,password});
        if(cursor.moveToFirst()){
            long id=cursor.getLong(cursor.getColumnIndex("id"));
            cursor.close();
            return id;
        }
        cursor.close();
        return 0;
    }

    //查询所有商品信息
    public List<Vip> query() {
        //helper=new PhoneCarSQLiteOpenHelper(context);
        //只读用getReadableDatabase
        SQLiteDatabase db = helper.getReadableDatabase();
        //查询数据库中所有信息，并按照金额降序排列
        Cursor cursor = db.query("Vip", null, null, null, null, null,null);
        //准备用来放商品的所有集合
        //List<Goods> list = new ArrayList<Goods>();
        while (cursor.moveToNext()) {
            //直接取列号
            long id = cursor.getLong(0);
            //根据列名取列号
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex =cursor.getString(cursor.getColumnIndex("sex"));
            int number=cursor.getInt(cursor.getColumnIndex("number"));
            String address=cursor.getString(cursor.getColumnIndex("address"));
            String password=cursor.getString(cursor.getColumnIndex("password"));
            //将商品的5个字段放在集合里
            Vip vip = new Vip(id, name,sex,number,address,password);
            list.add(vip);
        }
        cursor.close();
        db.close();
        return list;
    }
    public int delete(Long id){
        SQLiteDatabase db=helper.getWritableDatabase();
        int count=db.delete("Vip","id=?",new String[]{id+""});
        db.close();
        return count;
    }

}
