package com.baway.yunifang.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baway.yunifang.R;
import com.baway.yunifang.activity.GoodsContentActivity;
import com.baway.yunifang.activity.HomeItem4ImageActivity;
import com.baway.yunifang.bean.DataBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/16 14:59
 */
public class Item4Adapter extends RecyclerView.Adapter<Item4Adapter.MyViewHolder>{
    private Context context;
    private List<DataBean.Data.Subjects> subjects;

    public Item4Adapter(Context context, List<DataBean.Data.Subjects> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context,R.layout.item4_recycler_item,null));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final DataBean.Data.Subjects subjectses = this.subjects.get(position);
        Glide.with(context).load(this.subjects.get(position).image).into(holder.item4_item_iv);
        LinearLayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.item4_item_recycler.setLayoutManager(layoutManager);
        Item4RecyclerAdapter recyclerAdapter = new Item4RecyclerAdapter(context, subjectses);
        holder.item4_item_recycler.setAdapter(recyclerAdapter);

        holder.item4_item_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, HomeItem4ImageActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("subject",subjectses);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        recyclerAdapter.setOnRecyclerItemClickListener(new Item4RecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(View view) {
                int position1 = holder.item4_item_recycler.getChildAdapterPosition(view);
                if (position1<subjectses.show_number){
                    Intent intent=new Intent(context, GoodsContentActivity.class);
                    intent.putExtra("id",subjectses.goodsList.get(position1).id);
                    context.startActivity(intent);
                }else{
                    Intent intent=new Intent(context, HomeItem4ImageActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("subject",subjectses);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView item4_item_iv;
        private final RecyclerView item4_item_recycler;

        public MyViewHolder(View itemView) {
            super(itemView);
            item4_item_iv = (ImageView) itemView.findViewById(R.id.item4_item_iv);
            item4_item_recycler = (RecyclerView) itemView.findViewById(R.id.item4_item_recycler);
        }
    }
}
