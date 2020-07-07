package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.yunji.phoneshoppingcar.Dao.VipDao;
import com.example.yunji.phoneshoppingcar.Model.Vip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/28.
 */

public class vipxinxi extends Activity {
    private EditText et_id,et_name,et_sex,et_number,et_address,et_password;
    private ImageButton imgbt1;
    private Context context;
    private VipDao dao;
    private List<Vip> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//将app自带标题去掉
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vipxinxi);
        //初始化控件
        et_id=(EditText)findViewById(R.id.et_id);
        et_name=(EditText)findViewById(R.id.et_name);
        et_sex=(EditText)findViewById(R.id.et_sex);
        et_number=(EditText)findViewById(R.id.et_number);
        et_address=(EditText)findViewById(R.id.et_address);
        et_password=(EditText)findViewById(R.id.et_password);
        imgbt1=(ImageButton)findViewById(R.id.imgbt1);

        imgbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vipxinxi.this,VipLoginAfter.class);
                startActivity(intent);
            }
        });

        dao=new VipDao(vipxinxi.this);
        list=new ArrayList<Vip>();
        list=dao.queryName(Static.vip_name);
        //读数据
        for (Vip vip:list){
            Long id=vip.getId();
            String name=vip.getName();
            String sex=vip.getSex();
            int number=vip.getNumber();
            String address=vip.getAddress();
            String password=vip.getPassword();

            //信息写在布局文件上
            et_id.setText(id+"");
            et_name.setText(name);
            et_sex.setText(sex);
            et_number.setText(number+"");
            et_address.setText(address);
            et_password.setText(password);
            Static.vip_id=id;

        }


    }

}
