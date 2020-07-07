package com.example.yunji.phoneshoppingcar.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yunji.phoneshoppingcar.Model.Ad;
import com.example.yunji.phoneshoppingcar.Model.Phone;
import com.example.yunji.phoneshoppingcar.Model.Vip;
import com.example.yunji.phoneshoppingcar.Tables.PhoneCarSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/28.
 */

public class PhoneDao {
    //定义数据库对象
    private PhoneCarSQLiteOpenHelper helper;
    List<Phone> list;

    public PhoneDao(Context context) {
        helper = new PhoneCarSQLiteOpenHelper(context);
        list = new ArrayList<Phone>();
    }
    //查询所有商品信息
    public List<Phone> query( ){
        //helper=new PhoneCarSQLiteOpenHelper();
        SQLiteDatabase db=helper.getReadableDatabase();
        //查询所有信息
        Cursor cursor=db.query("Phone",null,null,null,null,null,null);
        //准备用来放所有商品的集合
        while (cursor.moveToNext()) {
            //直接取列号
            long id = cursor.getLong(0);
            //根据列名取列名
            String name=cursor.getString(cursor.getColumnIndex("name"));
            String color=cursor.getString(cursor.getColumnIndex("color"));
            String ram=cursor.getString(cursor.getColumnIndex("ram"));
            int price = cursor.getInt(cursor.getColumnIndex("price"));
            String number=cursor.getString(cursor.getColumnIndex("number"));
            //将商品的5个字段放在集合里
            Phone phone=new Phone(id,name,color,ram,price,number);
            list.add(phone);
        }
        cursor.close();
        db.close();
        return list;


    }
    public void insert(Phone phone){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("name",  phone.getName());
        vs.put("color", phone.getColor());
        vs.put("ram",phone.getRam());
        vs.put("price", phone.getPrice());
        vs.put("number",phone.getNumber());
        //插入数据库返回自增id
        Long id = db.insert("Phone", null, vs);
        phone.setId(id);
        db.close();
    }
    public int delete(Long id){
        SQLiteDatabase db=helper.getWritableDatabase();
        int count=db.delete("Phone","id=?",new String[]{id+""});
        db.close();
        return count;
    }

    //修改
    public int update(Phone phone) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("name", phone.getName());
        vs.put("color", phone.getColor());
        vs.put("ram", phone.getRam());
        vs.put("price", phone.getPrice());
        int count = db.update("Phone", vs, "name=?", new String[]{phone.getName() + ""});
        db.close();
        //Log.i("EDITTEXT","count:"+count);
        return count;
    }
    public List<Phone> queryName(String name) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("Phone", null, "name=?", new String[]{name}, null, null,null);
        //准备用来放商品的所有集合
        // List<Goods> list = new ArrayList<Goods>();
        while (cursor.moveToNext()) {
            //直接取列号
            long id = cursor.getLong(0);
            //根据列名取列号
            String name1 = cursor.getString(cursor.getColumnIndex("name"));
            String color =cursor.getString(cursor.getColumnIndex("color"));
            String ram=cursor.getString(cursor.getColumnIndex("ram"));
            Integer price=cursor.getInt(cursor.getColumnIndex("price"));
            String number=cursor.getString(cursor.getColumnIndex("number"));
            //将商品的6个字段放在集合里
            Phone phone = new Phone(id, name1,color,ram,price,number);
            list.add(phone);
        }
        cursor.close();
        db.close();
        return list;
    }

}
