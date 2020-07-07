package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yunji.phoneshoppingcar.Dao.OrderDao;
import com.example.yunji.phoneshoppingcar.Model.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/6/1.
 */

public class shoppingcaritemActivity extends Activity {
    private RecyclerView rv;
    private OrderDao orderDao;
    private List<Order> list;
    private shoppingcaritemAdapter adapter;
    private long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viporder);
        id=getIntent().getLongExtra("id",0);
        rv=(RecyclerView)findViewById(R.id.rv);
        orderDao=new OrderDao(shoppingcaritemActivity.this);
        list=new ArrayList<Order>();
        list=orderDao.queryName(id);
        adapter=new shoppingcaritemAdapter(shoppingcaritemActivity.this,list);
        rv.setAdapter(adapter);
        //通过layoutManager是指rv的显示风格:列表效果
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        rv.setItemAnimator(new DefaultItemAnimator());
    }
}
