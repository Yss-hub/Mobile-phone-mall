package com.example.yunji.phoneshoppingcar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.yunji.phoneshoppingcar.Model.Vip;

import java.util.List;

/**
 * Created by yunji on 2020/5/28.
 */

public class VipAdapter extends RecyclerView.Adapter<VipAdapter.MyViewHolder> {
    private Context context;
    private List<Vip> list;

    public VipAdapter(Context context,List<Vip> list){
        this.context=context;
        this.list=list;
    }
    //定义监听器接口
    //定义item中的图片按钮的监听器
    //1、定义修改按钮的单击事件监听器
    public interface OnDeleteImageClickListener{
        void onDeletImageClick(View view,int position);
    }
    private VipAdapter.OnDeleteImageClickListener deleteListener;
    public void setOnDeleteImageClickListener(VipAdapter.OnDeleteImageClickListener listener){
        this.deleteListener=listener;
    }
    public interface OnUpdateImageClickListener{

        void onUpdateImageClick(EditText tv_id,EditText tv_name,EditText tv_sex,
                                EditText tv_pwd,EditText tv_number,EditText tv_adress,
                                View view,int position);
    }
    private VipAdapter.OnUpdateImageClickListener updateListener;
    public void setOnUpdateImageClickListener(VipAdapter.OnUpdateImageClickListener listener){
        this.updateListener=listener;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView=View.inflate(context, R.layout.itemfragment02,null);
        return new MyViewHolder(itemView);
    }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            Vip vip=list.get(position);
            Long id=vip.getId();
            String name=vip.getName();
            String gender=vip.getSex();
            String psw=vip.getPassword();
            int num=vip.getNumber();
            String address=vip.getAddress();

        holder.tv_id.setText(id+"");
        holder.tv_name.setText(name);
        holder.tv_sex.setText(gender);
        holder.tv_password.setText(psw);
        holder.tv_number.setText(num+"");
        holder.tv_address.setText(address);


            holder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteListener.onDeletImageClick( holder.imageButton,position);
                }
            });
            holder.itemvip_update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateListener.onUpdateImageClick( holder.tv_id,
                            holder.tv_name,
                            holder.tv_sex,
                            holder.tv_password,holder.tv_number,
                            holder.tv_address,holder.itemvip_update,position);
                }
            });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends  RecyclerView.ViewHolder{
        private EditText tv_id,tv_name,tv_sex,tv_number,tv_password,tv_address;
        private ImageView itemvip_update;
        private ImageView imageButton;
        public MyViewHolder(View itemView){
            super(itemView);
            itemvip_update=(ImageView)itemView.findViewById(R.id.itemvip_update);
            tv_id=(EditText)itemView.findViewById(R.id.tv_id);
            tv_name=(EditText)itemView.findViewById(R.id.tv_name);
            tv_sex=(EditText)itemView.findViewById(R.id.tv_sex);
            tv_number=(EditText)itemView.findViewById(R.id.tv_number);
            tv_address=(EditText)itemView.findViewById(R.id.tv_adress);
            tv_password=(EditText)itemView.findViewById(R.id.tv_pwd);
            imageButton=(ImageView)itemView.findViewById(R.id.imageButton);
        }
    }
}

