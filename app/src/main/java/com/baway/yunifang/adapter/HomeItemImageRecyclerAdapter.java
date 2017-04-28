package com.baway.yunifang.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.yunifang.R;
import com.baway.yunifang.bean.DataBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/18 20:39
 */
public class HomeItemImageRecyclerAdapter extends RecyclerView.Adapter<HomeItemImageRecyclerAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private List<DataBean.Data.Subjects.GoodsList> goodsList;
    private OnRecyclerViewItemClickListener listener;

    public HomeItemImageRecyclerAdapter(Context context, List<DataBean.Data.Subjects.GoodsList> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onItemClick(v);
        }
    }

    public interface OnRecyclerViewItemClickListener{
        void onItemClick(View view);
    }
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener){
        this.listener=listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item5_recycler_item1,null);
        view.setOnClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(goodsList.get(position).goods_img).into(holder.item5_iv);
        holder.item5_efficacy.setText(goodsList.get(position).efficacy);
        holder.item5_name.setText(goodsList.get(position).goods_name);
        holder.item5_shop.setText("￥"+goodsList.get(position).shop_price);
        holder.item5_market.setText("￥"+goodsList.get(position).market_price);
        holder.item5_market.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final ImageView item5_iv;
        private final TextView item5_efficacy;
        private final TextView item5_name;
        private final TextView item5_shop;
        private final TextView item5_market;
        public MyViewHolder(View itemView) {
            super(itemView);
            item5_iv = (ImageView) itemView.findViewById(R.id.item5_iv);
            item5_efficacy = (TextView) itemView.findViewById(R.id.item5_efficacy);
            item5_name = (TextView) itemView.findViewById(R.id.item5_name);
            item5_shop = (TextView) itemView.findViewById(R.id.item5_shop);
            item5_market = (TextView) itemView.findViewById(R.id.item5_market);
        }
    }
}
