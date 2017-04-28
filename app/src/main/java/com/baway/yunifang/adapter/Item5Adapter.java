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
 * @date 2017/4/16 19:05
 */
public class Item5Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<DataBean.Data.DefaultGoodsList> defaultGoodsList;
    private OnRecyclerItemClickListener listener;

    public Item5Adapter(Context context, List<DataBean.Data.DefaultGoodsList> defaultGoodsList) {
        this.context = context;
        this.defaultGoodsList = defaultGoodsList;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onRecyclerItemClick(v);
        }
    }

    //定义一个接口
    public interface OnRecyclerItemClickListener{
        void onRecyclerItemClick(View view);
    }
    //公共方法传入接口
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1){
            View view=View.inflate(context, R.layout.item5_recycler_item1,null);
            view.setOnClickListener(this);
            return new MyViewHolder1(view);
        }else if (viewType==2){
            View view=View.inflate(context, R.layout.item5_recycler_item2,null);
            view.setOnClickListener(this);
            return new MyViewHolder2(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder1){
            Glide.with(context).load(defaultGoodsList.get(position).goods_img).into(((MyViewHolder1)holder).item5_iv);
            ((MyViewHolder1)holder).item5_efficacy.setText(defaultGoodsList.get(position).efficacy);
            ((MyViewHolder1)holder).item5_name.setText(defaultGoodsList.get(position).goods_name);
            ((MyViewHolder1)holder).item5_shop.setText("￥"+defaultGoodsList.get(position).shop_price);
            ((MyViewHolder1)holder).item5_market.setText("￥"+defaultGoodsList.get(position).market_price);
            ((MyViewHolder1)holder).item5_market.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }else{

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==6){
            return 2;
        }else{
            return 1;
        }

    }

    @Override
    public int getItemCount() {
        return defaultGoodsList.size()+1;
    }

    public static class MyViewHolder1 extends RecyclerView.ViewHolder{

        private final ImageView item5_iv;
        private final TextView item5_efficacy;
        private final TextView item5_name;
        private final TextView item5_shop;
        private final TextView item5_market;

        public MyViewHolder1(View itemView) {
            super(itemView);
            item5_iv = (ImageView) itemView.findViewById(R.id.item5_iv);
            item5_efficacy = (TextView) itemView.findViewById(R.id.item5_efficacy);
            item5_name = (TextView) itemView.findViewById(R.id.item5_name);
            item5_shop = (TextView) itemView.findViewById(R.id.item5_shop);
            item5_market = (TextView) itemView.findViewById(R.id.item5_market);
        }
    }
    public static class MyViewHolder2 extends RecyclerView.ViewHolder{

        public MyViewHolder2(View itemView) {
            super(itemView);
        }
    }
}
