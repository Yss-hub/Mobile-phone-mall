package com.example.yunji.phoneshoppingcar.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.yunji.phoneshoppingcar.Model.Ad;
import com.example.yunji.phoneshoppingcar.Model.Opinion;
import com.example.yunji.phoneshoppingcar.Tables.PhoneCarSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/31.
 */

public class OpinionDao {
    //定义数据库对象
    private PhoneCarSQLiteOpenHelper helper;
    public OpinionDao(Context context) {
        helper = new PhoneCarSQLiteOpenHelper(context);

    }

    //添加新用户，即注册
    public void insert(Opinion opinion) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues vs = new ContentValues();
        vs.put("opinion",  opinion.getOpinion());
        //插入数据库返回自增id
        Long id = db.insert("Opinion1", null, vs);
        opinion.setId(id);
        db.close();
    }
}
