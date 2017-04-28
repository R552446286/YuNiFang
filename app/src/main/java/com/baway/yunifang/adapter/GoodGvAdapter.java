package com.baway.yunifang.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.yunifang.R;
import com.baway.yunifang.bean.GoodBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/19 20:12
 */
public class GoodGvAdapter extends BaseAdapter{
    private Context context;
    private List<GoodBean.Data.GoodsRelDetails> goodsRelDetails;

    public GoodGvAdapter(Context context, List<GoodBean.Data.GoodsRelDetails> goodsRelDetails) {
        this.context = context;
        this.goodsRelDetails = goodsRelDetails;
    }

    @Override
    public int getCount() {
        return goodsRelDetails.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsRelDetails.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.good_gv_item,null);
        ImageView good_gv_item_img = (ImageView) convertView.findViewById(R.id.good_gv_item_img);
        TextView good_gv_item_name = (TextView) convertView.findViewById(R.id.good_gv_item_name);
        TextView good_gv_item_shop = (TextView) convertView.findViewById(R.id.good_gv_item_shop);
        TextView good_gv_item_moren = (TextView) convertView.findViewById(R.id.good_gv_item_moren);
        Glide.with(context).load(goodsRelDetails.get(position).goods_img).into(good_gv_item_img);
        good_gv_item_name.setText(goodsRelDetails.get(position).goods_name);
        good_gv_item_shop.setText("￥"+goodsRelDetails.get(position).shop_price);
        good_gv_item_moren.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return convertView;
    }
}
