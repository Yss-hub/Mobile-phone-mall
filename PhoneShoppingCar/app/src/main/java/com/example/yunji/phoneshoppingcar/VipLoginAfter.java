package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yunji.phoneshoppingcar.Dao.VipDao;
import com.example.yunji.phoneshoppingcar.Model.Vip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/27.
 */

public class VipLoginAfter extends Activity{
   private TextView vip_name1,vip_number1;
    private ImageButton imgbt_1,imgbt1,imgbt_2,imgbt_3,imgbt_4;
    private Context context;
    private VipDao dao;
    private List<Vip> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viploginafter);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//将app自带标题去掉
//        //初始化控件
       vip_name1=(TextView) findViewById(R.id.user_name);
       vip_number1=(TextView) findViewById(R.id.user_val);
        imgbt1=(ImageButton)findViewById(R.id.imgbt1);
       imgbt_1=(ImageButton)findViewById(R.id.imgbt_1);
        imgbt_2=(ImageButton)findViewById(R.id.loginafter_imgbt_2);
      imgbt_3=(ImageButton)findViewById(R.id.imgbt_3);
        imgbt_4=(ImageButton)findViewById(R.id.imgbt_4);
//
        Intent intent1=getIntent();
        final long id=intent1.getLongExtra("id",0);
        dao=new VipDao(VipLoginAfter.this);
        list=new ArrayList<Vip>();
        list=dao.queryName(Static.vip_name);
//        //读数据
        for (Vip vip:list){
            String name=vip.getName();
            int number=vip.getNumber();
            //信息写在布局文件上
            vip_name1.setText(name);
            vip_number1.setText(number+"");

        }
//
        imgbt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipLoginAfter.this,vipxinxi.class) ;
                startActivity(intent);
            }
        });
        //返回按钮把规定监听器
        imgbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipLoginAfter.this,viplogin.class) ;
                startActivity(intent);
            }
        });
        //imgbt_2跳转到修改信息界面
        imgbt_2=(ImageButton)findViewById(R.id.loginafter_imgbt_2);
        imgbt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipLoginAfter.this,Vipchang.class) ;
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        //点击imgbt_3跳转到手机商城
        imgbt_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipLoginAfter.this,phonexinxiActivity.class) ;
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
        //点击imgbt_4跳转到我的订单
        imgbt_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipLoginAfter.this,shoppingcaritemActivity.class) ;
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}
