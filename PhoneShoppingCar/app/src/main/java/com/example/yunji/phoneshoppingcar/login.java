package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yunji.phoneshoppingcar.Dao.AdDao;

/**
 * Created by yunji on 2020/5/23.
 */

public class login extends Activity {

    public int pwdresetFlag=0;
    private EditText mAccount;                        //用户名编辑
    private EditText mPwd;                            //密码编辑
    private Button mRegisterButton;                   //注册按钮
    private Button mLoginButton;                      //登录按钮
    private CheckBox mRememberCheck;

    private SharedPreferences login_sp;

    private View loginView;                           //登录
    private View loginSuccessView;
    private TextView loginSuccessShow;
    private TextView mChangepwdText;
    private AdDao mUserDataManager;         //用户数据管理类
    private AdDao adDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        adDao = new AdDao(login.this);
        //通过id找到相应的控件
        mAccount=(EditText)findViewById(R.id.login_edit_account);
        mPwd=(EditText)findViewById(R.id.login_edit_pwd);
        mRegisterButton = (Button) findViewById(R.id.login_btn_register);
        mLoginButton = (Button) findViewById(R.id.login_btn_login);
        loginView=findViewById(R.id.login_view);
        loginSuccessView=findViewById(R.id.login_success_view);
        loginSuccessShow=(TextView) findViewById(R.id.login_success_show);
        mRememberCheck = (CheckBox) findViewById(R.id.Login_Remember);

        login_sp = getSharedPreferences("userInfo", 0);
        String name=login_sp.getString("name", "");
        String pwd =login_sp.getString("password", "");
        boolean choseRemember =login_sp.getBoolean("mRememberCheck", false);
        boolean choseAutoLogin =login_sp.getBoolean("mAutologinCheck", false);
        //如果上次选了记住密码，那进入登录页面也自动勾选记住密码，并填上用户名和密码
        if(choseRemember==true){
            mAccount.setText(name);
            mPwd.setText(pwd);
            mRememberCheck.setChecked(true);
        }else{
            mAccount.setText(name);
            mRememberCheck.setChecked(false);
        }
        //采用OnClickListener方法设置不同按钮按下之后的监听事件
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this,Register.class) ;
                startActivity(intent);
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name123=mAccount.getText().toString().trim();
                final String pwd=mPwd.getText().toString().trim();
                final SharedPreferences.Editor editor =login_sp.edit();
                final boolean isflag=adDao.login(mAccount.getText().toString().trim(),
                        mPwd.getText().toString().trim());
                if(isflag){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                    builder.setTitle("登录成功！");
                    builder.setPositiveButton("确认",new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();                                           //返回1说明用户名和密码均正确
                                //保存用户名和密码
                                editor.putString("name123", name123);
                                editor.putString("pwd", pwd);

                                //是否记住密码
                                if(mRememberCheck.isChecked()){
                                    editor.putBoolean("mRememberCheck", true);
                                }else{
                                    editor.putBoolean("mRememberCheck", false);
                                }
                                editor.commit();
                                Intent intent = new Intent(login.this, FragmentActivity.class);
                            startActivity(intent);

                        }
                    });
                    builder.show();
                    Static.ad_name=name123;

                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                    builder.setTitle("登录失败！");
                    builder.setNegativeButton("取消", null);
                    builder.show();
                }
            }
        });


    }



    public boolean isUserNameAndPwdValid() {
        if (mAccount.getText().toString().trim().equals("")) {
            return false;
        } else if (mPwd.getText().toString().trim().equals("")) {
            return false;
        }
        return true;
}


}

