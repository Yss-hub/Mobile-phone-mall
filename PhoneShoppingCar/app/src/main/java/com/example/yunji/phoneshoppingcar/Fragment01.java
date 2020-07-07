package com.example.yunji.phoneshoppingcar;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.yunji.phoneshoppingcar.Dao.AdDao;
import com.example.yunji.phoneshoppingcar.Dao.VipDao;
import com.example.yunji.phoneshoppingcar.Model.Ad;
import com.example.yunji.phoneshoppingcar.Model.Vip;
import com.example.yunji.phoneshoppingcar.R;
import com.example.yunji.phoneshoppingcar.Static;
import com.example.yunji.phoneshoppingcar.vipxinxi;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yunji on 2020/5/25.
 */

public class Fragment01 extends Fragment {
    private EditText adet_id,adet_name,adet_sex,adet_number,adet_password;
    private ImageButton adimg_bt1;
    private Context context;
    private AdDao dao;
    private List<Ad> list;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.adfragment01,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adet_id=(EditText)getActivity().findViewById(R.id.adet_id);
        adet_name=(EditText)getActivity().findViewById(R.id.adet_name);
        adet_sex=(EditText)getActivity().findViewById(R.id.adet_sex);
        adet_number=(EditText)getActivity().findViewById(R.id.adet_number);
        adet_password=(EditText)getActivity().findViewById(R.id.adet_password);
        adimg_bt1=(ImageButton)getActivity().findViewById(R.id.adimg_bt1);

        dao=new AdDao(getActivity());
        list=new ArrayList<>();
        list=dao.queryName(Static.ad_name);
        //读数据
        for (Ad ad:list){
            Long id=ad.getId();
            String name=ad.getName();
            String sex=ad.getSex();
            int number=ad.getNumber();
            String password=ad.getPassword();

            //信息写在布局文件上
            adet_id.setText(id+"");
            adet_name.setText(name);
            adet_sex.setText(sex);
            adet_number.setText(number+"");
            adet_password.setText(password);

        }
        //给adimg_bt1按钮设置监听器，跳转到login界面
        adimg_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),login.class) ;
                startActivity(intent);
            }
        });

    }
}
