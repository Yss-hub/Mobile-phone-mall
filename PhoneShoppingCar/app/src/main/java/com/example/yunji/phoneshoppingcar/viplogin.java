package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yunji.phoneshoppingcar.Dao.VipDao;

/**
 * Created by yunji on 2020/5/25.
 */

public class viplogin extends Activity {
    private Button viplogin_btn_login,viplogin_btn_register;
    private EditText viplogin_name,viplogin_pwd;
    VipDao vipdao;
    public Static vipstatic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viplogin);
        vipdao=new VipDao(viplogin.this);
        viplogin_btn_login=(Button)findViewById(R.id.viplogin_btn_login);
        viplogin_btn_register=(Button)findViewById(R.id.viplogin_btn_register);
        viplogin_name=(EditText)findViewById(R.id.viplogin_name);
        viplogin_pwd=(EditText)findViewById(R.id.viplogin_pwd);
        viplogin_btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent=new Intent(viplogin.this,VipRegister.class);
                    startActivity(intent);
            }
        });
//        viplogin_btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name=viplogin_name.getText().toString().trim();
//                String psw=viplogin_pwd.getText().toString().trim();
//                if((!TextUtils.isEmpty(name))&&!(!TextUtils.isEmpty(psw))){
//                    List<Vip> data=vipdao.query(viplogin.this);
//                    boolean match = false;
//                    for (int i = 0; i < data.size(); i++) {
//                        Vip vip = data.get(i);
//                        if (name.equals(vip.getName()) && psw.equals(vip.getPassword())) {
//                            match = true;
//                            break;
//                        } else {
//                            match = false;
//                        }
//                    }
//                    if (match) {
//                        Toast.makeText(viplogin.this, "登录成功", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(viplogin.this, VipLoginAfter.class);
//                        startActivity(intent);
//                        finish();//销毁此Activity
//                    } else {
//                        Toast.makeText(viplogin.this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(viplogin.this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
//                }
//
//                }
//        });

        viplogin_btn_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final long id;
               String name=viplogin_name.getText().toString().trim();
               String psw=viplogin_pwd.getText().toString().trim();
//               Toast.makeText(viplogin.this,name,Toast.LENGTH_SHORT).show();
//                Toast.makeText(viplogin.this,psw,Toast.LENGTH_SHORT).show();
              id=vipdao.login(name,psw);

                if(id!=0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(viplogin.this);
                    builder.setTitle("登录成功！");
                    builder.setPositiveButton("确认",new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                            Intent intent = new Intent(viplogin.this,VipLoginAfter.class);
                            intent.putExtra("id",id);
                            startActivity(intent);
                        }
                    });
                    builder.show();
                    Static.vip_name=name;
                    Static.vip_password=psw;
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(viplogin.this);
                    builder.setTitle("登录失败！");
                    builder.setNegativeButton("取消", null);
                    builder.show();
                }

            }

        });
    }
}
