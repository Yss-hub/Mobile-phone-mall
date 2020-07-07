package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.yunji.phoneshoppingcar.Dao.PhoneDao;
import com.example.yunji.phoneshoppingcar.Model.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/27.
 */

public class FlowActivity extends Activity {

    private RecyclerView rv;
    private List<Phone> list;
    private PhoneDao dao;
    //使用Flowadapter
    private phonexinxiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonexinxi);
        //初始化控件对象
        dao=new PhoneDao(FlowActivity.this);
        //add = (Button) findViewById(R.id.add);
        //delete = (Button) findViewById(R.id.del);
        rv = (RecyclerView) findViewById(R.id.rv);
        //查询所有信息放入list

        list=new ArrayList<Phone>();
        list=dao.query();
        //Toast.makeText(FlowActivity.this,list.toString(),Toast.LENGTH_SHORT).show();

        //将list中的商品信息。放在recylevi以ew中显示出来
        //通过adapter将数据在recyleview中listview的方式显示出来
        adapter=new phonexinxiAdapter(FlowActivity.this,list);
        rv.setAdapter(adapter);

        //设置显示风格，为listview的方式显示数据
        rv.setLayoutManager(new LinearLayoutManager(FlowActivity.this, LinearLayoutManager.VERTICAL, false));


    }
}
