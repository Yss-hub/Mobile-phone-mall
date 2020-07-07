package com.example.yunji.phoneshoppingcar.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.yunji.phoneshoppingcar.Model.Order;

import com.example.yunji.phoneshoppingcar.Tables.PhoneCarSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/6/2.
 */

public class OrderDao {
    private PhoneCarSQLiteOpenHelper helper;
    private List<Order> list;
    public OrderDao(Context context){
        helper = new PhoneCarSQLiteOpenHelper(context);
        list = new ArrayList<Order>();
    }
    public void insert(Order order){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("vipid",order.getVipid());
        vs.put("phonename",order.getPhonename());
        vs.put("phonecolor",order.getPhonecolor());
        vs.put("phoneram",order.getPhoneram());
        vs.put("phoneprice",order.getPhoneprice());
        //插入数据库返回自增id
        Long id = db.insert("Orrder", null, vs);
        order.setId(id);
        db.close();
    }
    public List<Order> queryName(long vipid1) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("orrder", null, "vipid=?", new String[]{String.valueOf(vipid1)}, null, null,null);
        //准备用来放商品的所有集合
        // List<Goods> list = new ArrayList<Goods>();
        while (cursor.moveToNext()) {
            //直接取列号
            long orderid = cursor.getLong(0);
            //根据列名取列号
            Long vipid = cursor.getLong(cursor.getColumnIndex("vipid"));
            String phonename=cursor.getString(cursor.getColumnIndex("phonename"));
            String phonecolor=cursor.getString(cursor.getColumnIndex("phonecolor"));
            String phoneram=cursor.getString(cursor.getColumnIndex("phoneram"));
            int phoneprice=cursor.getInt(cursor.getColumnIndex("phoneprice"));

            //将商品的6个字段放在集合里
            Order order=new Order(orderid,vipid,phonename,phonecolor,phoneram,phoneprice);
            list.add(order);
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<Order> query() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query("Orrder", null, null, null, null, null,null);
        //准备用来放商品的所有集合
        // List<Goods> list = new ArrayList<Goods>();
        while (cursor.moveToNext()) {
            //直接取列号
            long id = cursor.getLong(0);
            //根据列名取列号
            Long vipid = cursor.getLong(cursor.getColumnIndex("vipid"));
            String phonename=cursor.getString(cursor.getColumnIndex("phonename"));
            String phonecolor=cursor.getString(cursor.getColumnIndex("phonecolor"));
            String phoneram=cursor.getString(cursor.getColumnIndex("phoneram"));
            int phoneprice=cursor.getInt(cursor.getColumnIndex("phoneprice"));

            //将商品的6个字段放在集合里
            Order order=new Order(id,vipid,phonename,phonecolor,phoneram,phoneprice);
            list.add(order);
        }
        cursor.close();
        db.close();
        return list;
    }
    public int delete(Long id){
        SQLiteDatabase db=helper.getWritableDatabase();
        int count=db.delete("orrder","id=?",new String[]{id+""});
        db.close();
        return count;
    }
    //修改
    public int update(Order order){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("id",order.getId());
        vs.put("vipid",order.getVipid());
        vs.put("phonename",order.getPhonename());
        vs.put("phonecolor",order.getPhonecolor());
        vs.put("phoneram",order.getPhoneram());
        vs.put("phoneprice",order.getPhoneprice());
        int count=db.update("orrder",vs,"id=?",new String[]{order.getId()+""});
        db.close();
        return count;
    }
}
