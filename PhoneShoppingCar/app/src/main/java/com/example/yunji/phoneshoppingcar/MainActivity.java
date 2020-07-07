package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
   private Button bt_ad,bt_vip,bt_phone,bt_opinion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_ad=(Button)findViewById(R.id.bt_ad);
        bt_vip=(Button)findViewById(R.id.bt_vip);
        bt_phone=(Button)findViewById(R.id.bt_phone);
        bt_opinion=(Button)findViewById(R.id.bt_opinion);
        //给管理员登录按钮啊绑定监听器
     bt_ad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,login.class) ;    //切换mainactivity Activity至loginActivity
                startActivity(intent);
            }
        });
        //给会员登录按钮啊绑定监听器
        bt_vip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,viplogin.class) ;    //切换mainactivity Activity至viploginActivity
                startActivity(intent);
            }
        });
        //给商城按钮绑定监听器
        bt_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,phonexinxiActivity.class) ;    //切换mainactivity Activity至手机信息界面
                startActivity(intent);
            }
        });
        //给意见按钮绑定监听器
        bt_opinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OpinionActivity.class) ;    //切换mainactivity Activity至意见信息界面
                startActivity(intent);
            }
        });

    }

}
