package com.example.yunji.phoneshoppingcar.Model;

/**
 * Created by yunji on 2020/5/22.
 */

public class Opinion {
    private  Long id;
    private String opinion;

    //封装商品参数,书写构造方法
    public Opinion(){

    }
    //封装商品参数,书写构造方法
    public Opinion( String opinion){

        this.opinion=opinion;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getOpinion() {
        return opinion;
    }
}
