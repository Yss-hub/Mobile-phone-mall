package com.example.yunji.phoneshoppingcar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yunji.phoneshoppingcar.Dao.AdDao;
import com.example.yunji.phoneshoppingcar.Dao.OpinionDao;
import com.example.yunji.phoneshoppingcar.Model.Ad;
import com.example.yunji.phoneshoppingcar.Model.Opinion;

/**
 * Created by yunji on 2020/5/31.
 */

public class OpinionActivity extends Activity {
    private EditText opinion_text;
    private Button opinion_up;
    private OpinionDao dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opinion);

        //初始化控件
        opinion_text=(EditText)findViewById(R.id.opinion_text);
        opinion_up=(Button)findViewById(R.id.opinion_up);
        dao=new OpinionDao(OpinionActivity.this);
        opinion_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    String opinion=opinion_text.getText().toString().trim();
                    Opinion opinion1=new Opinion(opinion);
                    dao.insert(opinion1);
                    AlertDialog.Builder builder = new AlertDialog.Builder(OpinionActivity.this);
                    builder.setTitle("添加成功！");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.dismiss();
                            Intent intent = new Intent(OpinionActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    builder.show();
                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(OpinionActivity.this);
                    builder.setTitle("添加失败！");
                    builder.setNegativeButton("取消", null);
                    builder.show();

                }



            }
        });
    }
}
