package com.example.yunji.phoneshoppingcar;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.yunji.phoneshoppingcar.Dao.VipDao;
import com.example.yunji.phoneshoppingcar.Model.Vip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/25.
 */
public class Fragment02 extends Fragment {
    private Button add;
    private ImageView vip_query;
    private EditText textView11;
//    private ImageButton itemvip_update;
    private RecyclerView rv;
    private List<Vip> list;
    private VipAdapter adapter;
    private VipDao dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adfragment02, null);
        return view;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化控件
//        itemvip_update=(ImageButton)getActivity().findViewById(R.id.itemvip_update);
        rv = (RecyclerView) getActivity().findViewById(R.id.rv);
        add=(Button)getActivity().findViewById(R.id.vip_add);
        textView11=(EditText)getActivity().findViewById(R.id.textView11);
        dao = new VipDao(getActivity());
        list = new ArrayList<>();
        vip_query=(ImageView)getActivity().findViewById(R.id.vip_query);
        list = dao.query();
        adapter = new VipAdapter(getActivity(), list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //给按钮控件添加监听器，点击弹出自定义窗口
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),addvipWindow.class) ;
                startActivity(intent);
            }
        });
        vip_query.setOnClickListener(new View.OnClickListener() {
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
        adapter.setOnDeleteImageClickListener(new VipAdapter.OnDeleteImageClickListener() {
            @Override
            public void onDeletImageClick(View view, final int position) {
                //确定删除对话框
                AlertDialog.Builder builer=new AlertDialog.Builder(getActivity());
                builer.setTitle("确定要删除吗？");
                builer.setNegativeButton("取消",null);
                builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Vip vip=list.get(position);
                        //首先在数据源中删除
                        list.remove(position);
                        //将数据源刷新到rv上
                        adapter.notifyDataSetChanged();
                        //在数据库中删除
                        dao.delete(vip.getId());
                    }
                });
                builer.show();
            }
        });
        adapter.setOnUpdateImageClickListener(new VipAdapter.OnUpdateImageClickListener() {
            @Override
            public void onUpdateImageClick(final EditText tv_id, final EditText tv_name,
                                           final EditText tv_sex,final EditText tv_pwd,
                                           final EditText tv_number,final EditText tv_adress,
                                           View view, final int position) {

                AlertDialog.Builder builer=new AlertDialog.Builder(getActivity());
        builer.setTitle("确定要修改吗？");
        builer.setNegativeButton("取消",null);
        builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                list.get(position).setId(Long.parseLong(tv_id.getText().toString().trim()));
                list.get(position).setName(tv_name.getText().toString().trim());
                list.get(position).setSex(tv_sex.getText().toString().trim());
                list.get(position).setPassword(tv_pwd.getText().toString().trim());
                list.get(position).setNumber(Integer.parseInt(tv_number.getText().toString().trim()));
                list.get(position).setAddress(tv_adress.getText().toString().trim());
                Vip vip=new Vip();
                vip.setId(list.get(position).getId());
                vip.setName(list.get(position).getName());
                vip.setSex(list.get(position).getSex());
                vip.setPassword(list.get(position).getPassword());
                vip.setNumber(list.get(position).getNumber());
                vip.setAddress(list.get(position).getAddress());
                dao.update(vip);
                adapter.notifyDataSetChanged();
            }
        });
        builer.show();

            }
        });

    }
}
