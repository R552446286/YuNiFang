package com.baway.yunifang.bean;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/17 18:37
 */
public class ClassifyBean {
    public Data data;

    @Override
    public String toString() {
        return "ClassifyBean{" +
                "data=" + data +
                '}';
    }

    public class Data {
        public List<Category> category;
        public List<GoodsBrief> goodsBrief;

        @Override
        public String toString() {
            return "Data{" +
                    "category=" + category +
                    ", goodsBrief=" + goodsBrief +
                    '}';
        }

        public class Category {
            public String cat_name;
            public List<Children> children;

            @Override
            public String toString() {
                return "Category{" +
                        "cat_name='" + cat_name + '\'' +
                        ", children=" + children +
                        '}';
            }
            public class Children{
                public String cat_name;
                public String id;

                @Override
                public String toString() {
                    return "Children{" +
                            "cat_name='" + cat_name + '\'' +
                            ", id='" + id + '\'' +
                            '}';
                }
            }
        }

        public class GoodsBrief {
            public String efficacy;
            public String goods_img;
            public String goods_name;
            public String id;
            public double market_price;
            public double shop_price;

            @Override
            public String toString() {
                return "GoodsBrief{" +
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
}
