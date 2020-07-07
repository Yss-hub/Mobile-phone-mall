package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yunji.phoneshoppingcar.Dao.VipDao;
import com.example.yunji.phoneshoppingcar.Model.Vip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/29.
 */

public class Vipchang extends Activity{
    private EditText chang_name,chang_sex,chang_address,chang_password;
    private EditText chang_number;
    private Button chang_sure;
    private Context context;
    private VipDao dao;
    private List<Vip> list;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vipchang);
        chang_name=(EditText) findViewById(R.id.chang_name);
        chang_sex=(EditText) findViewById(R.id.chang_sex);
        chang_number=(EditText) findViewById(R.id.chang_number);
        chang_address=(EditText) findViewById(R.id.chang_address);
        chang_password=(EditText) findViewById(R.id.chang_password);
        chang_sure=(Button)findViewById(R.id.chang_sure);

        dao=new VipDao(Vipchang.this);
        list=new ArrayList<Vip>();
        list=dao.queryName(Static.vip_name);
        //list=dao.update(Static.vip_name);
        //读数据
        for (Vip vip:list){
            Long id=vip.getId();
            String name=vip.getName();
            String sex=vip.getSex();
            int number=vip.getNumber();
            String address=vip.getAddress();
            String password=vip.getPassword();

            //信息写在布局文件上
            chang_name.setText(name);
            chang_sex.setText(sex);
            chang_number.setText(number+"");
            chang_address.setText(address);
            chang_password.setText(password);
        }
        //确认按钮写监听器
        chang_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将数据写入
                String name=chang_name.getText().toString().trim();
                String sex=chang_sex.getText().toString().trim();
                Integer number=Integer.parseInt(chang_number.getText().toString().trim());
                String address=chang_address.getText().toString().trim();
                String password=chang_password.getText().toString().trim();
                Intent intent=getIntent();
                long id=intent.getLongExtra("id",0);
                Vip vip=new Vip(name,sex,number,address,password);
                vip.setId(id);
                   Toast.makeText(Vipchang.this,vip.toString(),Toast.LENGTH_SHORT).show();
//                dao.update(vip);
                if (dao.update(vip)!=0){
                    Toast.makeText(Vipchang.this,"修改成功",Toast.LENGTH_SHORT).show();

                }else {

                    Toast.makeText(Vipchang.this,"修改失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
