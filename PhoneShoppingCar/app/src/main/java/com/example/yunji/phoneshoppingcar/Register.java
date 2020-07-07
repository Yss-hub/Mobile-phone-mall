package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yunji.phoneshoppingcar.Dao.AdDao;
import com.example.yunji.phoneshoppingcar.Model.Ad;

import java.util.ArrayList;
import java.util.List;

import static com.example.yunji.phoneshoppingcar.R.id.resetpwd_edit_name;
import static com.example.yunji.phoneshoppingcar.R.id.resetpwd_edit_sex;

/**
 * Created by yunji on 2020/5/24.
 */

public  class Register extends Activity{
    private Button sure,cancel;
    private EditText resetpwd_edit_name,resetpwd_edit_sex,resetpwd_edit_number,
            resetpwd_edit_pwd_old,resetpwd_edit_pwd_new;
    private AdDao adDao;         //用户数据管理类
    private List<Ad> list;

    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        initView();

        adDao = new AdDao(Register.this);
//        list = new ArrayList<Ad>();
//        list = adDao.query(Register.this);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Ad ad=new Ad(resetpwd_edit_name.getText().toString().trim(),
                            resetpwd_edit_sex.getText().toString().trim(),
                            Integer.parseInt(resetpwd_edit_number.getText().toString().trim()),
                            resetpwd_edit_pwd_old.getText().toString().trim());
                    adDao.insert(ad);
                    //adDao.insert(ad,Register.this);
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("添加成功！");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                            Intent intent = new Intent(Register.this, login.class);
                            startActivity(intent);
                        }
                    });
                    builder.show();
                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("添加失败！");
                    builder.setNegativeButton("取消", null);
                    builder.show();

                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,login.class);
                startActivity(intent);
            }
        });

    }
    //初始化控件
    private void initView(){
        sure=(Button)findViewById(R.id.sure);
        cancel=(Button)findViewById(R.id.cancel);
        resetpwd_edit_name=(EditText)findViewById(R.id.resetpwd_edit_name);
        resetpwd_edit_sex=(EditText)findViewById(R.id.resetpwd_edit_sex);
        resetpwd_edit_number=(EditText)findViewById(R.id.resetpwd_edit_number);
        resetpwd_edit_pwd_old=(EditText)findViewById(R.id.resetpwd_edit_pwd_old);
        resetpwd_edit_pwd_new=(EditText)findViewById(R.id.resetpwd_edit_pwd_new);

    }

}
