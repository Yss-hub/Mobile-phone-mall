package com.example.yunji.phoneshoppingcar;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.yunji.phoneshoppingcar.Model.Phone;

import java.util.List;

/**
 * Created by yunji on 2020/5/29.
 */

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder>{
    private Context context;
    private List<Phone> list;

    public interface OnDeleteImageClickListener{
        void onDeletImageClick(View view,int position);
    }
    private PhoneAdapter.OnDeleteImageClickListener deleteListener;
    public void setOnDeleteImageClickListener(PhoneAdapter.OnDeleteImageClickListener listener){
        this.deleteListener=listener;
    }
    public PhoneAdapter(Context context, List<Phone> list){
        this.context=context;
        this.list=list;
    }

    //定义监听器接口
    //定义item中的图片按钮的监听器
    //1、定义修改按钮的单击事件监听器
    public interface OnUpdateImageClickListener{

        void onUpdateImageClick(EditText tv_1, EditText tv_2, EditText tv_3, EditText tv_4,View view, int position);
    }
    private PhoneAdapter.OnUpdateImageClickListener updateListener;
    public void setOnUpdateImageClickListener(PhoneAdapter.OnUpdateImageClickListener listener){
        this.updateListener=listener;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=View.inflate(context, R.layout.itemfragment03,null);
        return new PhoneAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Phone phone=list.get(position);

        String name=phone.getName();
        String color=phone.getColor();
        String ram=phone.getRam();
        int price=phone.getPrice();

        holder.tv_1.setText(name);
        holder.tv_2.setText(color);
        holder.tv_3.setText(ram);
        holder.tv_4.setText(price+"");
        if(name.indexOf("小米")!=-1){
            holder.phonetv.setImageResource(R.drawable.phone1);
        }else if(name.indexOf("华为")!=-1){
            holder.phonetv.setImageResource(R.drawable.phone2);
        }else if(name.indexOf("iPhone11")!=-1){
            holder.phonetv.setImageResource(R.drawable.phone3);
        }
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteListener.onDeletImageClick( holder.imageButton,position);
            }
        });
        holder.itemphone_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateListener.onUpdateImageClick( holder.tv_1,
                        holder.tv_2,
                        holder.tv_3,
                        holder.tv_4,holder.itemphone_update,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends  RecyclerView.ViewHolder{
        private EditText tv_1,tv_3,tv_2,tv_4;
        private ImageView phonetv;
        private ImageView imageButton;
        private ImageButton itemphone_update;
        public MyViewHolder(View itemView){
            super(itemView);
            itemphone_update=(ImageButton)itemView.findViewById(R.id.itemphone_update);
            phonetv=(ImageView)itemView.findViewById(R.id.phonetv);
            tv_1=(EditText)itemView.findViewById(R.id.tv_1);
            tv_2=(EditText)itemView.findViewById(R.id.tv_2);
            tv_3=(EditText)itemView.findViewById(R.id.tv_3);
            tv_4=(EditText)itemView.findViewById(R.id.tv_4);
            imageButton=(ImageView)itemView.findViewById(R.id.imageButton);
        }
    }
}
