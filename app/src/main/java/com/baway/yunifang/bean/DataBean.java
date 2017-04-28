package com.baway.yunifang.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/14 9:25
 */
public class DataBean {
    public Data data;

    @Override
    public String toString() {
        return "DataBean{" +
                "data=" + data +
                '}';
    }
    public class Data{
        public ActivityInfo activityInfo;
        public List<Ad1> ad1;
        public List<Ad5> ad5;
        public List<BestSellers> bestSellers;
        public List<DefaultGoodsList> defaultGoodsList;
        public List<Subjects> subjects;

        @Override
        public String toString() {
            return "Data{" +
                    "activityInfo=" + activityInfo +
                    ", ad1=" + ad1 +
                    ", ad5=" + ad5 +
                    ", bestSellers=" + bestSellers +
                    ", defaultGoodsList=" + defaultGoodsList +
                    ", subjects=" + subjects +
                    '}';
        }
        public class ActivityInfo{
            public List<ActivityInfoList> activityInfoList;

            @Override
            public String toString() {
                return "ActivityInfo{" +
                        "activityInfoList=" + activityInfoList +
                        '}';
            }
            public class ActivityInfoList{
                public String activityData;
                public String activityImg;

                @Override
                public String toString() {
                    return "ActivityInfoList{" +
                            "activityData='" + activityData + '\'' +
                            ", activityImg='" + activityImg + '\'' +
                            '}';
                }
            }
        }
        public class Ad1{
            public String ad_type_dynamic_data;
            public String image;
            public String title;

            @Override
            public String toString() {
                return "Ad1{" +
                        "ad_type_dynamic_data='" + ad_type_dynamic_data + '\'' +
                        ", image='" + image + '\'' +
                        ", title='" + title + '\'' +
                        '}';
            }
        }
        public class Ad5{
            public String ad_type_dynamic_data;
            public String image;
            public String title;

            @Override
            public String toString() {
                return "Ad5{" +
                        "ad_type_dynamic_data='" + ad_type_dynamic_data + '\'' +
                        ", image='" + image + '\'' +
                        ", title='" + title + '\'' +
                        '}';
            }
        }
        public class BestSellers implements Serializable{
            public List<GoodsList> goodsList;
            public String name;
            public int show_number;

            @Override
            public String toString() {
                return "BestSellers{" +
                        "goodsList=" + goodsList +
                        ", name='" + name + '\'' +
                        ", show_number=" + show_number +
                        '}';
            }
            public class GoodsList implements Serializable{
                public String goods_img;
                public String goods_name;
                public String id;
                public double market_price;
                public double shop_price;

                @Override
                public String toString() {
                    return "GoodsList{" +
                            "goods_img='" + goods_img + '\'' +
                            ", goods_name='" + goods_name + '\'' +
                            ", market_price=" + market_price +
                            ", shop_price=" + shop_price +
                            '}';
                }
            }
        }
        public class DefaultGoodsList{
            public String efficacy;
            public String goods_img;
            public String goods_name;
            public String id;
            public double market_price;
            public double shop_price;

            @Override
            public String toString() {
                return "DefaultGoodsList{" +
                        "efficacy='" + efficacy + '\'' +
                        ", goods_img='" + goods_img + '\'' +
                        ", goods_name='" + goods_name + '\'' +
                        ", market_price=" + market_price +
                        ", shop_price=" + shop_price +
                        '}';
            }
        }
        public class Subjects implements Serializable{
            public String detail;
            public List<GoodsList> goodsList;
            public String image;
            public int show_number;
            public String title;

            @Override
            public String toString() {
                return "Subjects{" +
                        "detail='" + detail + '\'' +
                        ", goodsList=" + goodsList +
                        ", image='" + image + '\'' +
                        ", show_number=" + show_number +
                        ", title='" + title + '\'' +
                        '}';
            }
            public class GoodsList implements Serializable{
                public String efficacy;
                public String goods_img;
                public String goods_name;
                public String id;
                public double market_price;
                public double shop_price;

                @Override
                public String toString() {
                    return "GoodsList{" +
                            "efficacy='" + efficacy + '\'' +
                            ", goods_img='" + goods_img + '\'' +
                            ", goods_name='" + goods_name + '\'' +
                            ", market_price=" + market_price +
                            ", shop_price=" + shop_price +
                            '}';
                }
            }
        }
    }
}
