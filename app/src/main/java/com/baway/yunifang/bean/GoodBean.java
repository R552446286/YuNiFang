package com.baway.yunifang.bean;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/19 19:10
 */
public class GoodBean {
    public Data data;

    @Override
    public String toString() {
        return "GoodBean{" +
                "data=" + data +
                '}';
    }
    public class Data{
        public List<Activity> activity;
        public boolean collected;
        public int commentNumber;
        public List<Comments> comments;
        public Goods goods;
        public List<GoodsRelDetails> goodsRelDetails;

        @Override
        public String toString() {
            return "Data{" +
                    "activity=" + activity +
                    ", collected=" + collected +
                    ", commentNumber=" + commentNumber +
                    ", comments=" + comments +
                    ", goods=" + goods +
                    ", goodsRelDetails=" + goodsRelDetails +
                    '}';
        }

        public class Activity{
            public String description;
            public String title;

            @Override
            public String toString() {
                return "Activity{" +
                        "description='" + description + '\'' +
                        ", title='" + title + '\'' +
                        '}';
            }
        }
        public class Comments{
            public String content;
            public String createtime;
            public User user;

            @Override
            public String toString() {
                return "Comments{" +
                        "content='" + content + '\'' +
                        ", createtime='" + createtime + '\'' +
                        ", user=" + user +
                        '}';
            }
            public class User{
                public String icon;
                public String nick_name;

                @Override
                public String toString() {
                    return "User{" +
                            "icon='" + icon + '\'' +
                            ", nick_name='" + nick_name + '\'' +
                            '}';
                }
            }
        }
        public class Goods{
            public List<Attributes> attributes;
            public int collect_count;
            public String efficacy;
            public List<Gallery> gallery;
            public String goods_desc;
            public String goods_img;
            public String goods_name;
            public String id;
            public double market_price;
            public int restrict_purchase_num;
            public int sales_volume;
            public double shipping_fee;
            public double shop_price;
            public int stock_number;

            @Override
            public String toString() {
                return "Goods{" +
                        "attributes=" + attributes +
                        ", collect_count=" + collect_count +
                        ", efficacy='" + efficacy + '\'' +
                        ", gallery=" + gallery +
                        ", goods_desc='" + goods_desc + '\'' +
                        ", goods_img='" + goods_img + '\'' +
                        ", goods_name='" + goods_name + '\'' +
                        ", id='" + id + '\'' +
                        ", market_price=" + market_price +
                        ", restrict_purchase_num=" + restrict_purchase_num +
                        ", sales_volume=" + sales_volume +
                        ", shipping_fee=" + shipping_fee +
                        ", shop_price=" + shop_price +
                        ", stock_number=" + stock_number +
                        '}';
            }

            public class Attributes{
                public String attr_name;
                public String attr_value;

                @Override
                public String toString() {
                    return "Attributes{" +
                            "attr_name='" + attr_name + '\'' +
                            ", attr_value='" + attr_value + '\'' +
                            '}';
                }
            }
            public class Gallery{
                public String normal_url;

                @Override
                public String toString() {
                    return "Gallery{" +
                            "normal_url='" + normal_url + '\'' +
                            '}';
                }
            }
        }
        public class GoodsRelDetails{
            public String goods_img;
            public String goods_name;
            public String id;
            public double shop_price;

            @Override
            public String toString() {
                return "GoodsRelDetails{" +
                        "goods_img='" + goods_img + '\'' +
                        ", goods_name='" + goods_name + '\'' +
                        ", id='" + id + '\'' +
                        ", shop_price=" + shop_price +
                        '}';
            }
        }
    }
}
