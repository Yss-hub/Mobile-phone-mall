package com.example.yunji.phoneshoppingcar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.yunji.phoneshoppingcar.Model.Order;

import java.util.List;

/**
 * Created by yunji on 2020/6/2.
 */

public class OrderitemAdapter extends RecyclerView.Adapter <OrderitemAdapter.MyViewHolder>{
    private Context context;
    private List<Order> list;

    //定义删除接口
    public interface OnDeleteImageClickListener{
        void onDeleteImageClick(View view,int position);
    }
    private OrderitemAdapter.OnDeleteImageClickListener deleteListener;
    public void setonDeleteImageClickListener(OrderitemAdapter.OnDeleteImageClickListener listener){
        this.deleteListener=listener;
    }
    //定义修改接口
    public interface OnUpdateImageClickListener{
        void onUpdateImageClick(EditText tv_orderid,EditText tv_vipid,EditText tv_name,
                                EditText tv_color,EditText tv_ram,EditText tv_price,
                                View view,int position);
    }
    private OrderitemAdapter.OnUpdateImageClickListener updateImageClickListener;
    public void setonUpdateImageClickListener(OrderitemAdapter.OnUpdateImageClickListener listener){
        this.updateImageClickListener=listener;
    }
    //GoodsAdapter构造方法，对成员变量赋值
    public OrderitemAdapter(Context context,List<Order> list){
        this.context=context;
        this.list=list;
    }
    //根据item的布局去创建VH视图容器
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //itemView就是将每个item对象加载进来作为view对象返回
        View itemView=View.inflate(context,R.layout.viporderitem,null);
        return new OrderitemAdapter.MyViewHolder(itemView);
    }
    //将VH中的控件盒具体的数据绑定在一起
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //获取数据,显示在item中
        Order order=list.get(position);
        long orderid=order.getId();
        long vipid=order.getVipid();
        String phonename=order.getPhonename();
        String phonecolor=order.getPhonecolor();
        String phoneram=order.getPhoneram();
        int phoneprice=order.getPhoneprice();

        holder.tv_orderid.setText(orderid+"");
        holder.tv_vipid.setText(vipid+"");
        holder.tv_name.setText(phonename);
        holder.tv_color.setText(phonecolor);
        holder.tv_ram.setText(phoneram);
        holder.tv_price.setText(phoneprice+"");
        holder.order_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.onDeleteImageClick(holder.order_delete,position);
            }
        });
        holder.order_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateImageClickListener.onUpdateImageClick(holder.tv_orderid,
                        holder.tv_vipid,holder.tv_name,holder.tv_color,holder.tv_ram,
                        holder.tv_price,holder.order_update,position);
            }
        });


    }
    //返回数据源中数据的个数
    @Override
    public int getItemCount() {
        return  list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private EditText tv_vipid,tv_name,tv_color,tv_ram,tv_price,tv_orderid;
        private ImageButton order_delete,order_update;
        public MyViewHolder(View itemView){
            super(itemView);
            //itemView即item布局文件转换成view对象，故包括item布局文件中所有控件对象
            tv_orderid=(EditText)itemView.findViewById(R.id.tv_orderid);
            tv_vipid=(EditText) itemView.findViewById(R.id.tv_vipid);
            tv_name=(EditText) itemView.findViewById(R.id.tv_name);
            tv_color=(EditText) itemView.findViewById(R.id.tv_color);
            tv_ram=(EditText) itemView.findViewById(R.id.tv_ram);
            tv_price=(EditText) itemView.findViewById(R.id.tv_price);
            order_delete=(ImageButton)itemView.findViewById(R.id.order_delete);
            order_update=(ImageButton)itemView.findViewById(R.id.order_update);

        }

    }






}
