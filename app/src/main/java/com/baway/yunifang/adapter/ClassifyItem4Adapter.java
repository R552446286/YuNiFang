package com.baway.yunifang.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.yunifang.R;
import com.baway.yunifang.bean.ClassifyBean;
import com.baway.yunifang.bean.DataBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/16 19:05
 */
public class ClassifyItem4Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<ClassifyBean.Data.GoodsBrief> goodsBrief;
    private OnRecyclerItemClickListener listener;

    public ClassifyItem4Adapter(Context context, List<ClassifyBean.Data.GoodsBrief> goodsBrief) {
        this.context = context;
        this.goodsBrief = goodsBrief;
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
            return new MyViewHolder2(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder1){
            Glide.with(context).load(goodsBrief.get(position).goods_img).into(((MyViewHolder1)holder).item5_iv);
            ((MyViewHolder1)holder).item5_efficacy.setText(goodsBrief.get(position).efficacy);
            ((MyViewHolder1)holder).item5_name.setText(goodsBrief.get(position).goods_name);
            ((MyViewHolder1)holder).item5_shop.setText("￥"+goodsBrief.get(position).shop_price);
            ((MyViewHolder1)holder).item5_market.setText("￥"+goodsBrief.get(position).market_price);
            ((MyViewHolder1)holder).item5_market.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }else{

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==goodsBrief.size()){
            return 2;
        }else{
            return 1;
        }

    }

    @Override
    public int getItemCount() {
        return goodsBrief.size()+1;
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
