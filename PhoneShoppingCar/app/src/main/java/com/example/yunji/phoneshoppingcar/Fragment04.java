package com.example.yunji.phoneshoppingcar;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.yunji.phoneshoppingcar.Dao.OrderDao;
import com.example.yunji.phoneshoppingcar.Dao.VipDao;
import com.example.yunji.phoneshoppingcar.Model.Order;
import com.example.yunji.phoneshoppingcar.Model.Phone;
import com.example.yunji.phoneshoppingcar.Model.Vip;
import com.example.yunji.phoneshoppingcar.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/25.
 */

public class Fragment04 extends Fragment {

    private RecyclerView rv;
    private List<Order> list;
    private OrderitemAdapter orderitemAdapter;
    private OrderDao orderDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.adfragment04,null);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化控件
//        itemvip_update=(ImageButton)getActivity().findViewById(R.id.itemvip_update);
        rv = (RecyclerView) getActivity().findViewById(R.id.rv);
        orderDao = new OrderDao(getActivity());
        list = new ArrayList<>();
        list = orderDao.query();
        orderitemAdapter = new OrderitemAdapter(getActivity(), list);
        rv.setAdapter(orderitemAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //给按钮控件添加监听器，点击弹出自定义窗口
        orderitemAdapter.setonDeleteImageClickListener(new OrderitemAdapter.OnDeleteImageClickListener() {
            @Override
            public void onDeleteImageClick(View view, final int position) {
                //确定删除对话框
                AlertDialog.Builder builer=new AlertDialog.Builder(getActivity());
                builer.setTitle("确定要删除吗？");
                builer.setNegativeButton("取消",null);
                builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Order order=list.get(position);
                        //首先在数据源中删除
                        list.remove(position);
                        //将数据源刷新到rv上
                        orderitemAdapter.notifyDataSetChanged();
                        //在数据库中删除
                        orderDao.delete(order.getId());
                    }
                });
                builer.show();
            }
        });

        //给修改按钮
        orderitemAdapter.setonUpdateImageClickListener(new OrderitemAdapter.OnUpdateImageClickListener() {
            @Override
            public void onUpdateImageClick(final EditText tv_orderid, final EditText tv_vipid,
                                           final EditText tv_name,
                                           final EditText tv_color,
                                           final EditText tv_ram,
                                           final EditText tv_price, View view, final int position) {
                AlertDialog.Builder builer=new AlertDialog.Builder(getActivity());
                builer.setTitle("确定要修改吗？");
                builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.get(position).setId(Integer.parseInt(tv_orderid.getText().toString().trim()));
                        list.get(position).setVipid(Integer.parseInt(tv_vipid.getText().toString().trim()));
                        list.get(position).setPhonename(tv_name.getText().toString().trim());
                        list.get(position).setPhonecolor(tv_color.getText().toString().trim());
                        list.get(position).setPhoneram(tv_ram.getText().toString().trim());
                        list.get(position).setPhoneprice(Integer.parseInt(tv_price.getText().toString().trim()));
                        Order order=new Order();
                        order.setId(list.get(position).getId());
                        order.setVipid(list.get(position).getVipid());
                        order.setPhonename(list.get(position).getPhonename());
                        order.setPhonecolor(list.get(position).getPhonecolor());
                        order.setPhoneram(list.get(position).getPhoneram());
                        order.setPhoneprice(list.get(position).getPhoneprice());

                        orderDao.update(order);
                        orderitemAdapter.notifyDataSetChanged();

                    }
                });
                builer.setNegativeButton("取消",null);
                builer.show();
            }
        });
    }
}
