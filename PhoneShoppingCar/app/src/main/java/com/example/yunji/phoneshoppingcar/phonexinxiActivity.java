package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.yunji.phoneshoppingcar.Dao.OrderDao;
import com.example.yunji.phoneshoppingcar.Dao.PhoneDao;
import com.example.yunji.phoneshoppingcar.Model.Order;
import com.example.yunji.phoneshoppingcar.Model.Phone;
import com.example.yunji.phoneshoppingcar.Model.Vip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/27.
 */

public class phonexinxiActivity extends Activity {
    private Button list1;
    private EditText textView11;
    private ImageButton phonexinxi_imgbt1,phone_query;
    private RecyclerView rv;
    private PhoneDao dao;
    private OrderDao orderDao;
    private List<Phone> list;
    private long id;
    //使用adapter
    private phonexinxiAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonexinxi);
        //初始化控件对象

        phonexinxi_imgbt1=(ImageButton) findViewById(R.id.phonexinxi_imgbt1);
        textView11=(EditText)findViewById(R.id.textView11);
        phone_query=(ImageButton)findViewById(R.id.phone_query);
        //delete=(Button)findViewById(R.id.del);
        //list1=(Button)findViewById(R.id.list);
        rv=(RecyclerView)findViewById(R.id.rv);
        id=getIntent().getLongExtra("id",0);
        //查询数据库中所有信息，放LIST中
        dao=new PhoneDao(phonexinxiActivity.this);
        orderDao=new OrderDao(phonexinxiActivity.this);
        list=new ArrayList<Phone>();
        list=dao.query();
        //给按钮绑定监听器
        //将list中的商品信息。放在recylevi以ew中显示出来
        //通过adapter将数据在recyleview中listview的方式显示出来
        //给rv,设置adapter适配器（将viewHolder中的控件和数据源绑定在一起）
        adapter=new phonexinxiAdapter(phonexinxiActivity.this,list);
        rv.setAdapter(adapter);
        //通过layoutManager是指rv的显示风格:列表效果
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        rv.setItemAnimator(new DefaultItemAnimator());
        //给phonexinxi_imgbt1按钮设置监听器
        phonexinxi_imgbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(phonexinxiActivity.this,VipLoginAfter.class) ;
                startActivity(intent);
            }
        });
        adapter.setOnPhoneCarClickListener(new phonexinxiAdapter.OnPhoneCarClickListener() {
            @Override
            public void onPhoneCarClick(TextView tv_1, TextView tv_2, TextView tv_3, TextView tv_4, View view, int position) {

                list.get(position).setName(tv_1.getText().toString().trim());
                list.get(position).setColor(tv_2.getText().toString().trim());
                list.get(position).setRam(tv_3.getText().toString().intern());
                list.get(position).setPrice(Integer.parseInt(tv_4.getText().toString().trim()));
                Order order=new Order();
                order.setVipid(id);
                order.setPhonename(list.get(position).getName());
                order.setPhonecolor(list.get(position).getColor());
                order.setPhoneram(list.get(position).getRam());
                order.setPhoneprice(list.get(position).getPrice());
                orderDao.insert(order);
                AlertDialog.Builder builer=new AlertDialog.Builder(phonexinxiActivity.this);
                builer.setTitle("确定要购买吗？");
                builer.setNegativeButton("确定",null);
                builer.show();
            }
        });

        phone_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=textView11.getText().toString().trim();
                if(name!=null){
                    list.clear();
                    list=dao.queryName(name);
                    //Log.i("M",list.size()+"");
                    adapter.notifyDataSetChanged();
                }
                if(name.equals("")){
                    list = dao.query();
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
}
