package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by yunji on 2020/5/25.
 */
public class FragmentActivity extends Activity{
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private RadioButton bt1,bt2,bt3,bt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adloginafter);
        //设置初始内容为个人中心界面
        //获取manager事务
        manager=getFragmentManager();
        //开启事务
        transaction=manager.beginTransaction();
        //通过事务进行增删改
        transaction.add(R.id.content_layout,new Fragment01());
        //提交
        transaction.commit();
        //初始化控件
        bt1=(RadioButton)findViewById(R.id.radioButton1);
        bt2=(RadioButton)findViewById(R.id.radioButton2);
        bt3=(RadioButton)findViewById(R.id.radioButton3);
        bt4=(RadioButton)findViewById(R.id.radioButton4);
        //设置监听器对象
        RadioButtonListener listener=new RadioButtonListener();
        //给控件绑定监听器
        bt1.setOnClickListener(listener);
        bt2.setOnClickListener(listener);
        bt3.setOnClickListener(listener);
        bt4.setOnClickListener(listener);
    }
    //radioButton绑定监听器
    class RadioButtonListener implements View.OnClickListener{
        @Override
        //当点击不同radio时切换不同的fragment
        public void onClick(View v) {
            //开启新的transaction
            transaction=manager.beginTransaction();
            switch (v.getId()){
                case R.id.radioButton1:
                    //调用transaction的replace方法
                    transaction.replace(R.id.content_layout,new Fragment01());
                    break;
                case R.id.radioButton2:
                    //调用transaction的replace方法
                    transaction.replace(R.id.content_layout,new Fragment02());
                    break;
                case R.id.radioButton3:
                    //调用transaction的replace方法
                    transaction.replace(R.id.content_layout,new Fragment03());
                    break;
                case R.id.radioButton4:
                    //调用transaction的replace方法
                    transaction.replace(R.id.content_layout,new Fragment04());
                    break;
            }
            //关闭transaction
            transaction.commit();
        }
    }
}
