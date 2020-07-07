package com.example.yunji.phoneshoppingcar.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.yunji.phoneshoppingcar.Model.Ad;
import com.example.yunji.phoneshoppingcar.Tables.PhoneCarSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/22.
 */

public class AdDao {
    //定义数据库对象
    private PhoneCarSQLiteOpenHelper helper;
    List<Ad> list;
    public AdDao(Context context) {
        helper = new PhoneCarSQLiteOpenHelper(context);
        list=new ArrayList<Ad>();
    }

    //添加新用户，即注册
    public void insert(Ad ad) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("name",  ad.getName());
        vs.put("sex", ad.getSex());
        vs.put("number",ad.getNumber());
        vs.put("password", ad.getPassword());
            //插入数据库返回自增id
        Long id = db.insert("ad", null, vs);
        ad.setId(id);
        db.close();
    }

    //更新用户信息，如修改密码
    public int updateUserData(Ad ad) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues vs = new ContentValues();
        String name = ad.getName();
        String password = ad.getPassword();
        Long id = ad.getId();
        vs.put("name", name);
        vs.put("password", password);
        int count = db.update("ad", vs, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return count;
    }

    //
    public String fetchAdData(int id) {
        //可读数据库
        SQLiteDatabase db = helper.getReadableDatabase();
        ContentValues vs = new ContentValues();
        Cursor cursor = db.query("ad", null, "id=?", new String[]{String.valueOf(id)}, null, null, null);
        String result = "";
        if (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String password = cursor.getString(3);
            Integer number = cursor.getInt(4);
            String sex = cursor.getString(5);
            result = name + "," + password + "," + number + "," + sex;
        } else {
            result = "查询失败";
        }
        cursor.close();
        db.close();
        return result;
    }

    //根据id删除用户
    public int deletebyid(Long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = db.delete("ad", "id=?", new String[]{id + ""});
        return count;
    }

    //根据name删除用户
    public int deletebyname(String name) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = db.delete("ad", "name=?", new String[]{name+ ""});
        return count;
    }

    //删除用户
    public int delete(Long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int count = db.delete("ad", null, new String[]{id + ""});
        return count;
    }

    //根据用户名找用户，可以判断注册时用户名是否已经存在
    public int findUserByName(String name) {
        int result = 0;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor mCursor = db.query("ad", null, name + "=" + name, null, null, null, null);
        if (mCursor != null) {
            result = mCursor.getCount();
            mCursor.close();
            //Log.i(TAG,"findUserByName , result="+result);
        }
        return result;
    }

    //根据用户名和密码找用户，用于登录
    public int findUserByNameAndPwd(String name, String password) {
        SQLiteDatabase db = helper.getWritableDatabase();
        int result = 0;
        Cursor mCursor = db.query("ad", null, name + "=" + name + " and " + password + "=" + password,
                null, null, null, null);
        if (mCursor != null) {
            result = mCursor.getCount();
            mCursor.close();
        }
        return result;
    }

    public boolean login(String name,String password){
        SQLiteDatabase db=helper.getReadableDatabase();
        String sql="select * from ad where name=? and password=?";
        //查询sql语句rawQuery
        Cursor cursor=db.rawQuery(sql, new String[]{name,password});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }
//    public boolean register(Ad ad){
//        SQLiteDatabase db=helper.getWritableDatabase();
//        String sql="insert into ad(name,sex,number,password) values(?,?,?,?)";
//        Object obj[]={ad.getName(),ad.getSex(),ad.getNumber(),ad.getPassword()};
//        db.execSQL(sql, obj);
//        return true;
//    }
    //查询所有管理员信息
    public List<Ad> query(Context context) {
        helper=new PhoneCarSQLiteOpenHelper(context);
        //只读用getReadableDatabase
        SQLiteDatabase db = helper.getReadableDatabase();
        //查询数据库中所有信息
        Cursor cursor = db.query("ad", null, null, null, null, null,null);
        //准备用来放管理员的所有集合
        //List<Goods> list = new ArrayList<Goods>();
        while (cursor.moveToNext()) {
            //直接取列号
            long id = cursor.getLong(0);
            //根据列名取列号
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex =cursor.getString(cursor.getColumnIndex("sex"));
            int number=cursor.getInt(cursor.getColumnIndex("number"));
            String password=cursor.getString(cursor.getColumnIndex("password"));
            //将商品的3个字段放在集合里
            Ad ad = new Ad(id, name,sex,number,password);
            list.add(ad);
        }
        cursor.close();
        db.close();
        return list;
    }
    //根据name查询所有管理员信息
    public List<Ad> queryName(String name) {
        //只读用getReadableDatabase
        SQLiteDatabase db = helper.getReadableDatabase();
        //查询数据库中管理员所有信息
        Cursor cursor = db.query("ad", null, null, null, null, null,null);
        //准备用来放商品的所有集合
        //List<Goods> list = new ArrayList<Goods>();
        while (cursor.moveToNext()) {
            //直接取列号
            long id = cursor.getLong(0);
            //根据列名取列号
            String name1 = cursor.getString(cursor.getColumnIndex("name"));
            String sex =cursor.getString(cursor.getColumnIndex("sex"));
            int number=cursor.getInt(cursor.getColumnIndex("number"));
            String password=cursor.getString(cursor.getColumnIndex("password"));
            //将商品的3个字段放在集合里
            Ad ad = new Ad(id, name1,sex,number,password);
            list.add(ad);
        }
        cursor.close();
        db.close();
        return list;
    }
}