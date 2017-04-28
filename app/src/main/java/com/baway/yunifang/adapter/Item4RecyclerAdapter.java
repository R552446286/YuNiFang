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

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/14 14:41
 */
public class Item4RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private DataBean.Data.Subjects subjectses;
    private OnRecyclerItemClickListener listener;

    public Item4RecyclerAdapter(Context context, DataBean.Data.Subjects subjectses) {
        this.context = context;
        this.subjectses = subjectses;
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
        if (viewType==1) {
            View view=View.inflate(context, R.layout.item2_recycler_item1, null);
            view.setOnClickListener(this);
            return new MyViewHolder1(view);
        }else{
            View view=View.inflate(context,R.layout.item2_recycler_item2,null);
            view.setOnClickListener(this);
            return new MyViewHolder2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder1) {
            Glide.with(context).load(subjectses.goodsList.get(position).goods_img).into(((MyViewHolder1) holder).item2_iv);
            ((MyViewHolder1) holder).item2_name.setText(subjectses.goodsList.get(position).goods_name);
            ((MyViewHolder1) holder).item2_market.setText("￥" + subjectses.goodsList.get(position).market_price);
            ((MyViewHolder1) holder).item2_shop.setText("￥" + subjectses.goodsList.get(position).shop_price);
            ((MyViewHolder1) holder).item2_market.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }else{

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position==subjectses.show_number){
            return 2;
        }else{
            return 1;
        }
    }

    @Override
    public int getItemCount() {
        return subjectses.show_number+1;
    }

    public static class MyViewHolder1 extends RecyclerView.ViewHolder{

        private final ImageView item2_iv;
        private final TextView item2_name;
        private final TextView item2_market;
        private final TextView item2_shop;

        public MyViewHolder1(View itemView) {
            super(itemView);
            item2_iv = (ImageView) itemView.findViewById(R.id.item2_iv);
            item2_name = (TextView) itemView.findViewById(R.id.item2_name);
            item2_market = (TextView) itemView.findViewById(R.id.item2_market);
            item2_shop = (TextView) itemView.findViewById(R.id.item2_shop);
        }
    }
    public static class MyViewHolder2 extends RecyclerView.ViewHolder{

        public MyViewHolder2(View itemView) {
            super(itemView);
        }
    }
}
