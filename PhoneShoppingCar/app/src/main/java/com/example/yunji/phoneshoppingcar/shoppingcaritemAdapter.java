package com.example.yunji.phoneshoppingcar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yunji.phoneshoppingcar.Model.Order;

import java.util.List;

/**
 * Created by yunji on 2020/6/1.
 */

public class shoppingcaritemAdapter extends RecyclerView.Adapter <shoppingcaritemAdapter.MyViewHolder>{
    private Context context;
    private List<Order> list;
    public shoppingcaritemAdapter(Context context,List<Order> list){
        this.context=context;
        this.list=list;
    }
    //根据item的布局去创建VH视图容器
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //itemView就是将每个item对象加载进来作为view对象返回
        View itemView=View.inflate(context,R.layout.itemfragment04,null);
        return new MyViewHolder(itemView);
    }
    //将VH中的控件盒具体的数据绑定在一起
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //获取数据,显示在item中

        Order order=list.get(position);
        //long id=order.getId();
        long id=2;
        String name=order.getPhonename();
        String ram=order.getPhoneram();
        String color=order.getPhonecolor();
        Integer price=order.getPhoneprice();

       holder.car_id.setText(id+"");

        holder.car_name.setText(name);
        holder.car_ram.setText(ram);
        holder.car_color.setText(color);
        holder.car_price.setText(price+"");
    }
    //返回数据源中数据的个数
    @Override
    public int getItemCount() {
        return  list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView car_id,car_name,car_color,car_ram,car_price;

        public MyViewHolder(View itemView){
            super(itemView);
            //itemView即item布局文件转换成view对象，故包括item布局文件中所有控件对象
            car_id=(TextView)itemView.findViewById(R.id.car_id);
            car_name=(TextView)itemView.findViewById(R.id.car_name);
            car_color=(TextView)itemView.findViewById(R.id.car_color);
            car_ram=(TextView)itemView.findViewById(R.id.car_ram);
            car_price=(TextView) itemView.findViewById(R.id.car_price);

        }

    }
}
