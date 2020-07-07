package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yunji.phoneshoppingcar.Dao.VipDao;
import com.example.yunji.phoneshoppingcar.Model.Vip;

/**
 * Created by yunji on 2020/5/29.
 */

public class addvipWindow extends Activity implements View.OnClickListener{
    private Button sure,cancel;
    private EditText et_vip_add_name,et_vip_add_sex,et_vip_add_number,et_vip_add_address,
            et_vip_add_password;
    private VipDao vipdao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vipaddwindow);
        vipdao=new VipDao(addvipWindow.this);
        sure=(Button)findViewById(R.id.sure);
        cancel=(Button)findViewById(R.id.cancel);
        et_vip_add_name=(EditText)findViewById(R.id.et_vip_add_name);
        et_vip_add_sex=(EditText)findViewById(R.id.et_vip_add_sex);
        et_vip_add_number=(EditText)findViewById(R.id.et_vip_add_number);
        et_vip_add_address=(EditText)findViewById(R.id.et_vip_add_address);
        et_vip_add_password=(EditText)findViewById(R.id.et_vip_add_password);

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Vip vip=new Vip(et_vip_add_name.getText().toString().trim(),et_vip_add_sex.getText().toString().trim(),
                            Integer.parseInt(et_vip_add_number.getText().toString().trim()),
                            et_vip_add_address.getText().toString().trim(),et_vip_add_password.getText().toString().trim());
                    vipdao.insert(vip);
                    AlertDialog.Builder builder = new AlertDialog.Builder(addvipWindow.this);
                    builder.setTitle("添加成功！");
                    builder.setPositiveButton("确认",null);
                    builder.show();
                    //Intent intent = new Intent(addvipWindow.this,Fragment02.class) ;    //phoneaddWindow 至手机信息界面
                    //startActivity(intent);
                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(addvipWindow.this);
                    builder.setTitle("添加失败！");
                    builder.setNegativeButton("取消", null);
                    builder.show();
                }
                //vipdao.query();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}
