package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yunji.phoneshoppingcar.Dao.AdDao;
import com.example.yunji.phoneshoppingcar.Dao.VipDao;
import com.example.yunji.phoneshoppingcar.Model.Ad;
import com.example.yunji.phoneshoppingcar.Model.Vip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/26.
 */

public class VipRegister extends Activity {
    private Button sure,cancel;
    private EditText et_name,et_sex,et_number,et_address,et_password;
    private VipDao vipDao;         //用户数据管理类
    //private List<Vip> list;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vipregister);
        initView();
        vipDao = new VipDao(VipRegister.this);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Vip vip=new Vip(et_name.getText().toString().trim(),et_sex.getText().toString().trim(),
                            Integer.parseInt(et_number.getText().toString().trim()),et_address.getText().toString().trim(),
                            et_password.getText().toString().trim());
                    vipDao.insert(vip);
                    AlertDialog.Builder builder = new AlertDialog.Builder(VipRegister.this);
                    builder.setTitle("添加成功！");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                            Intent intent = new Intent(VipRegister.this, viplogin.class);
                            startActivity(intent);
                        }
                    });
                    builder.show();
                }catch(Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(VipRegister.this);
                    builder.setTitle("添加失败！");
                    builder.setNegativeButton("取消", null);
                    builder.show();
                }

            }


        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VipRegister.this,login.class);
                startActivity(intent);
            }
        });

    }
    //初始化控件
    private void initView(){
        sure=(Button)findViewById(R.id.sure);
        cancel=(Button)findViewById(R.id.cancel);
        et_name=(EditText)findViewById(R.id.et_name);
        et_sex=(EditText)findViewById(R.id.et_sex);
        et_number=(EditText)findViewById(R.id.et_number);
        et_address=(EditText)findViewById(R.id.et_address);
        et_password=(EditText)findViewById(R.id.et_address);

    }
}
