package com.example.yunji.phoneshoppingcar.Model;

import java.util.List;

/**
 * Created by yunji on 2020/5/22.
 */

public class Order {
    private  long id;
    private long vipid;
    private String phonename;
    private String phonecolor;
    private String phoneram;
    private Integer phoneprice;

    public Order(Long id,String phonename, String phonecolor,String phoneram,Integer phoneprice){
        this.id=id;
        this.phonename=phonename;
        this.phonecolor=phonecolor;
        this.phoneram=phoneram;
        this.phoneprice=phoneprice;
    }
    //封装商品参数,书写构造方法
    public Order(){

    }
    //封装商品参数,书写构造方法
    public Order(long id,Long vipid,String phonename,String phonecolor,String phoneram,Integer phoneprice){
        this.id=id;
        this.vipid=vipid;
        this.phonename=phonename;
        this.phonecolor=phonecolor;
        this.phoneram=phoneram;
        this.phoneprice=phoneprice;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setVipid(long vipid) {
        this.vipid = vipid;
    }

    public long getVipid() {
        return vipid;
    }

    public void setPhonename(String phonename) {
        this.phonename = phonename;
    }

    public String getPhonename() {
        return phonename;
    }

    public void setPhonecolor(String phonecolor) {
        this.phonecolor = phonecolor;
    }

    public String getPhonecolor() {
        return phonecolor;
    }

    public void setPhoneram(String phoneram) {
        this.phoneram = phoneram;
    }

    public String getPhoneram() {
        return phoneram;
    }

    public void setPhoneprice(Integer phoneprice) {
        this.phoneprice = phoneprice;
    }

    public Integer getPhoneprice() {
        return phoneprice;
    }
}
