package com.baway.yunifang.bean;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/21 15:28
 */
public class TypeBean {
    public List<Data> data;

    @Override
    public String toString() {
        return "TypeBean{" +
                "data=" + data +
                '}';
    }

    public class Data{
        public String efficacy;
        public String goods_img;
        public String goods_name;
        public String id;
        public double market_price;
        public double shop_price;

        @Override
        public String toString() {
            return "Data{" +
                    "efficacy='" + efficacy + '\'' +
                    ", goods_img='" + goods_img + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    ", id='" + id + '\'' +
                    ", market_price=" + market_price +
                    ", shop_price=" + shop_price +
                    '}';
        }
    }
}
