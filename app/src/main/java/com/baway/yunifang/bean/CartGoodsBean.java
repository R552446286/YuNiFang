package com.baway.yunifang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/24 13:37
 */
public class CartGoodsBean {
    public List<CartItemList> cartItemList;

    @Override
    public String toString() {
        return "CartGoodsBean{" +
                "cartItemList=" + cartItemList +
                '}';
    }
    public class CartItemList implements Serializable{
        public int count;
        public int id;
        public String name;
        public String pic;
        public double price;
        public int productID;
        public int userID;
        public boolean ischecked=false;

        @Override
        public String toString() {
            return "CartItemList{" +
                    "count=" + count +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", pic='" + pic + '\'' +
                    ", price=" + price +
                    ", productID=" + productID +
                    ", userID=" + userID +
                    ", ischecked=" + ischecked +
                    '}';
        }
    }
}
