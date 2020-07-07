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

import com.example.yunji.phoneshoppingcar.Dao.PhoneDao;
import com.example.yunji.phoneshoppingcar.Model.Phone;

/**
 * Created by yunji on 2020/5/29.
 */

public class phoneaddWindow extends Activity implements View.OnClickListener{
    private EditText et_phone_add_name,et_phone_add_color,et_phone_add_ram,et_phone_add_price,et_phone_add_number;
    private Button et_phone_add_sure,et_phone_add_cancel;
    private PhoneDao phonedao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phoneaddwindow);
        phonedao=new PhoneDao(phoneaddWindow.this);
        et_phone_add_name=(EditText)findViewById(R.id.et_phone_add_name);
        et_phone_add_color=(EditText)findViewById(R.id.et_phone_add_color);
        et_phone_add_ram=(EditText)findViewById(R.id.et_phone_add_ram);
        et_phone_add_price=(EditText)findViewById(R.id.et_phone_add_price);
        et_phone_add_number=(EditText)findViewById(R.id.et_phone_add_number);
        et_phone_add_sure=(Button)findViewById(R.id.et_phone_add_sure);
        et_phone_add_cancel=(Button)findViewById(R.id.et_phone_add_cancel);

        et_phone_add_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Phone phone=new Phone(et_phone_add_name.getText().toString().trim(),et_phone_add_color.getText().toString().trim(),
                            et_phone_add_ram.getText().toString().trim(),Integer.parseInt(et_phone_add_price.getText().toString().trim()),
                            et_phone_add_number.getText().toString().trim());
                    phonedao.insert(phone);
                    AlertDialog.Builder builder = new AlertDialog.Builder(phoneaddWindow.this);
                    builder.setTitle("添加成功！");
                    builder.setPositiveButton("确认",null);
                    builder.show();
//                    Intent intent = new Intent(phoneaddWindow.this,FlowActivity.class) ;    //phoneaddWindow 至手机信息界面
//                    startActivity(intent);
                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(phoneaddWindow.this);
                    builder.setTitle("添加失败！");
                    builder.setNegativeButton("取消", null);
                    builder.show();

                }
                phonedao.query();
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
