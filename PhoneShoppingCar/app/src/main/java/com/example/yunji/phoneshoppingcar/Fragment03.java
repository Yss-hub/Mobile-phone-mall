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
import android.widget.ImageView;

import com.example.yunji.phoneshoppingcar.Dao.PhoneDao;
import com.example.yunji.phoneshoppingcar.Model.Phone;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunji on 2020/5/25.
 */

public class Fragment03 extends Fragment {
    private Button ad_phone_add;
    private RecyclerView rv;
    private PhoneDao dao;
    private List<Phone> list;
    private PhoneAdapter adapter;
    private ImageView imageButton5;
    private  EditText phone_query;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.adfragment03,null);
        return view;
    }
    @Override
    public void onActivityCreated(@android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rv=(RecyclerView)getActivity().findViewById(R.id.rv);
        dao=new PhoneDao(getActivity());
        phone_query=(EditText)getActivity().findViewById(R.id.phone_query);
        imageButton5=(ImageView)getActivity().findViewById(R.id.imageButton5);
        list=new ArrayList<>();
        list=dao.query();
        adapter=new PhoneAdapter(getActivity(),list);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));

        ad_phone_add=(Button)getActivity().findViewById(R.id.ad_phone_add);
        //给按钮控件添加监听器，点击弹出自定义窗口
        ad_phone_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),phoneaddWindow.class) ;
                startActivity(intent);
            }
        });

        adapter.setOnDeleteImageClickListener(new PhoneAdapter.OnDeleteImageClickListener() {
            @Override
            public void onDeletImageClick(View view, final int position) {
                //确定删除对话框
                AlertDialog.Builder builer=new AlertDialog.Builder(getActivity());
                builer.setTitle("确定要删除吗？");
                builer.setNegativeButton("取消",null);
                builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Phone phone=list.get(position);
                        //首先在数据源中删除
                        list.remove(position);
                        //将数据源刷新到rv上
                        adapter.notifyDataSetChanged();
                        //在数据库中删除
                        dao.delete(phone.getId());
                    }
                });
                builer.show();
            }
        });
        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=phone_query.getText().toString().trim();
                if(name!=null){
                    list.clear();
                    list=dao.queryName(name);
                    adapter.notifyDataSetChanged();
                }
                if(name.equals("")){
                    list=dao.query();
                    adapter.notifyDataSetChanged();
                }

            }
        });

        adapter.setOnUpdateImageClickListener(new PhoneAdapter.OnUpdateImageClickListener() {
            @Override
            public void onUpdateImageClick(final EditText tv_1, final EditText tv_2,
                                           final EditText tv_3, final EditText tv_4,
                                           View view, final int position) {

                AlertDialog.Builder builer=new AlertDialog.Builder(getActivity());
                builer.setTitle("确定要修改吗？");

                builer.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        list.get(position).setName(tv_1.getText().toString().trim());
                        list.get(position).setColor(tv_2.getText().toString().trim());
                        list.get(position).setRam(tv_3.getText().toString().trim());
                        list.get(position).setPrice(Integer.parseInt(tv_4.getText().toString().trim()));
                        Phone phone=new Phone();
                        phone.setName(list.get(position).getName());
                        phone.setColor(list.get(position).getColor());
                        phone.setRam(list.get(position).getRam());
                        phone.setPrice(list.get(position).getPrice());

                        dao.update(phone);
                        adapter.notifyDataSetChanged();
                    }
                });
                builer.setNegativeButton("取消",null);
                builer.show();

            }
        });


    }
}
