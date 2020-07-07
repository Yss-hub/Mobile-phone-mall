package com.example.yunji.phoneshoppingcar.Model;

/**
 * Created by yunji on 2020/5/22.
 */

public class Vip {
    private  Long id;
    private String name;
    private String sex;
    private Integer number;
    private String address;
    private String password;
    public Vip(String name,String sex,Integer number,String address,String password){
        this.name=name;
        this.sex=sex;
        this.number=number;
        this.address=address;
        this.password=password;
    }
    //封装商品参数,书写构造方法
    public Vip(Long id,String name,String sex,Integer number,String address,String password){
        this.id=id;
        this.name=name;
        this.sex=sex;
        this.number=number;
        this.address=address;
        this.password=password;
    }
    //封装商品参数,书写构造方法
    public Vip(){

    }
    public Vip(String name,String password){
        this.name=name;
        this.password=password;
    }

    public void setId(Long id){
        this.id=id;
    }
    public Long getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Vip{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", number=" + number +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

