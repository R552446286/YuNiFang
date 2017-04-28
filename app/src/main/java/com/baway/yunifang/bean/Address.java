package com.baway.yunifang.bean;

import java.io.Serializable;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/25 17:40
 */
public class Address implements Serializable{
    public String name;
    public String phone;
    public String home;

    public Address(String name, String phone, String home) {
        this.name = name;
        this.phone = phone;
        this.home = home;
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", home='" + home + '\'' +
                '}';
    }
}
