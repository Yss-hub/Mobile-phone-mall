package com.example.yunji.phoneshoppingcar.Model;

/**
 * Created by yunji on 2020/5/22.
 */

public class Phone {
    private  Long id;
    private String name;
    private String color;
    private String ram;
    private Integer price;
    private String number;
    public Phone(String name,String color,String ram,Integer price,String number){
        this.name=name;
        this.color=color;
        this.ram=ram;
        this.price=price;
        this.number=number;
    }
    //封装商品参数,书写构造方法
    public Phone(){

    }
    //封装商品参数,书写构造方法
    public Phone(Long id,String name,String color,String ram,Integer price,String number){
        this.id=id;
        this.name=name;
        this.color=color;
        this.ram=ram;
        this.price=price;
        this.number=number;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getRam() {
        return ram;
    }
}
