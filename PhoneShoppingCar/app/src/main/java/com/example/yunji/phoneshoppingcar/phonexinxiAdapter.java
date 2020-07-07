package com.example.yunji.phoneshoppingcar;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yunji.phoneshoppingcar.Model.Phone;

import java.util.List;

/**
 * Created by yunji on 2020/5/27.
 */

public class phonexinxiAdapter extends RecyclerView.Adapter <phonexinxiAdapter.MyViewHolder>{
    private Context context;
    //private ArrayList<String> datas;
    private List<Phone> list;
    //MyAdapter构造方法,对成员变量context上下文，datas数据源赋值
    public phonexinxiAdapter(Context context, List<Phone> list){
        this.context=context;
        this.list=list;
    }


    //通过adapter给viewHolder中的控件设置监听器（点击、长按等监听器）
    //1、声明监听器接口，事件处理函数
    public interface OnItemClickLisetener{
        //监听器的方法声明
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    //2、声明监听器接口对象
    private OnItemClickLisetener liseten;
    //3、定义设置监听器的set方法，可供外界调用，给自己绑定监听器
    public void setOnItemClickListener(OnItemClickLisetener liseten){
        this.liseten=liseten;
    }
    //4、onBindViewHolder中设置监听器在什么事后触发
    //5、在mainactivity中设置监听器
    public interface OnPhoneCarClickListener{

        void onPhoneCarClick(TextView tv_1, TextView tv_2,TextView tv_3,TextView tv4,View view,int position);
    }
    private phonexinxiAdapter.OnPhoneCarClickListener phonecarListener;
    public void setOnPhoneCarClickListener(phonexinxiAdapter.OnPhoneCarClickListener listener){
        this.phonecarListener=listener;
    }
    //创建ViewHolder视图容器
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //将item布局文件转换为view对象加载进来
        View itemview=View.inflate(context, R.layout.phonexinxiitem,null);
        return new MyViewHolder(itemview);
    }
    //将ViewHolder的控件和数据源datas中的数据对应绑定在一起
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //获取当前处理到的数据
        Phone phone=list.get(position);
        String name=phone.getName();
        String color=phone.getColor();
        String ram=phone.getRam();
        Integer price=phone.getPrice();
        //将数据绑定上去
        holder.tv_1.setText(name);
        holder.tv_2.setText(color);
        holder.tv_3.setText(ram);
        holder.tv_4.setText(price+"");
        //换图片
        if(name.indexOf("小米")!=-1){
            holder.tv.setImageResource(R.drawable.phone1);
        }else if(name.indexOf("华为")!=-1){
            holder.tv.setImageResource(R.drawable.phone2);
        }else if(name.indexOf("iPhone11")!=-1){
            holder.tv.setImageResource(R.drawable.phone3);

        }
        holder.phone_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonecarListener.onPhoneCarClick(holder.tv_1,holder.tv_2,holder.tv_3,holder.tv_4,holder.phone_car,position);
            }
        });

    }
    //返回item数据的个数
    @Override
    public int getItemCount() {
        //返回数据源中数据的个数
        return list.size();
    }

    //ViewHolder内部类视图
    class MyViewHolder extends RecyclerView.ViewHolder{
        //item中只有一个文本框，先进行定义
        private TextView tv_1,tv_2,tv_3,tv_4;
        private ImageButton phone_car;
        private ImageView tv;
        public MyViewHolder (View itemView){
            super(itemView);
            //将item中的控件找出来，并赋值给ViewHolder的成员变量
            phone_car=(ImageButton)itemView.findViewById(R.id.phone_car);
            tv=(ImageView) itemView.findViewById(R.id.tv);
            tv_1=(TextView) itemView.findViewById(R.id.tv_1);
            tv_2=(TextView) itemView.findViewById(R.id.tv_2);
            tv_3=(TextView) itemView.findViewById(R.id.tv_3);
            tv_4=(TextView) itemView.findViewById(R.id.tv_4);

        }
    }
}
