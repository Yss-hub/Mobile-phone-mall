package com.example.yunji.phoneshoppingcar.Tables;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yunji on 2020/5/22.
 */

    public class PhoneCarSQLiteOpenHelper extends SQLiteOpenHelper {
    public PhoneCarSQLiteOpenHelper(Context context){
        super(context,"PhoneShoppingCart.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //数据库的数据表的创建
        db.execSQL("create table ad("+
                "id Integer primary key autoincrement,"+
                "name varchar(20),"+
                "sex varchar(20),"+
                "number Integer,"+
        "password varchar(20))");

        db.execSQL(
                "Insert into ad values"+
                        "(null,'123','男','17687678767','123')," +
                        "(null,'admin','女','17687678767','admin')," +
                        "(null,'admin1','女','17687678767','admin')," +
                        "(null,'admin2','女','17687678767','admin')," +
                        "(null,'admin3','女','17687678767','admin')," +
                        "(null,'admin4','男','17687678767','admin')"
        );

        //数据库的意见表的创建
        db.execSQL("create table Opinion1("+
                "id Integer primary key autoincrement,"+
                "opinion varchar(100))");

        //数据库的订单表的创建
        db.execSQL("create table orrder(" +
                "id Integer primary key autoincrement," +
                "vipid Integer," +
                "phonename varchar(20)," +
                "phonecolor varchar(20),"+
                "phoneram varchar(20),"+
                "phoneprice Integer" +
                ")");
        db.execSQL(
                "Insert into orrder values"+
                        "(null,'1','小米9','白','1','3200')," +
                        "(null,'2','小米9','黑','2','3200')," +
                        "(null,'3','小米9','红','1','3200')"
        );
        //数据库的手机表的创建
        db.execSQL("create table Phone("+
                "id Integer primary key autoincrement,"+
                "name varchar(20),"+
                "color varchar(20),"+
                "ram varchar(20),"+
                "price Integer,"+
                "number varchar(20))");
        db.execSQL(
                "Insert into Phone values"+
                        "(null,'小米9','白','64G','3200','123')," +
                        "(null,'小米9','蓝色','128G','3200','123')," +
                        "(null,'华为P30','蓝色','128G','3490','43')," +
                        "(null,'华为P30','黑色','128G','3490','33')," +
                        "(null,'华为P30','白色','128G','3490','22')," +
                        "(null,'iPhone11','蓝色','128G','4999','123')," +
                        "(null,'iPhone11','黑色','128G','4999','123')," +
                        "(null,'iPhone11','白色','128G','4999','123')," +
                        "(null,'小米9','紫色','128G','3200','123')"
        );
        //数据库的手机表的创建
//        db.execSQL("create table Car("+
//                "id Integer primary key autoincrement,"+
//                "vipid varchar(20),"+
//                "name varchar(20),"+
//                "color varchar(20),"+
//                "ram varchar(20),"+
//                "price Integer)");
//        db.execSQL(
//                "Insert into orrder values"+
//                        "(null,'1','1','1','1','123')," +
//                        "(null,'2','2','2','1','22')," +
//                        "(null,'3','1','3','1','77')"
//        );
        //数据库的会员表的创建
        db.execSQL("create table Vip("+
                "id Integer primary key autoincrement,"+
                "name varchar(20),"+
                "sex varchar(20),"+
                "number Integer,"+
                "address varchar(20),"+
                "password varchar(20))");
        db.execSQL(
                "Insert into Vip values"+
                        "(null,'123','男','17687678767','杭州','123')," +
                        "(null,'admin','女','17687678767','杭州','admin')," +
                        "(null,'admin1','女','17687678767','杭州','admin')," +
                        "(null,'admin2','女','17687678767','杭州','admin')," +
                        "(null,'admin3','女','17687678767','杭州','admin')," +
                        "(null,'admin4','女','17687678767','杭州','admin')," +
                        "(null,'admin5','女','17687678767','杭州','admin')," +
                        "(null,'456','男','17687678767','杭州','456')"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
