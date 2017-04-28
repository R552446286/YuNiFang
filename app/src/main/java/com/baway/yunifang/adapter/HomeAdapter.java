package com.baway.yunifang.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.yunifang.R;
import com.baway.yunifang.activity.GoodsContentActivity;
import com.baway.yunifang.activity.HomeWebViewActivity;
import com.baway.yunifang.activity.HuiyuanActivity;
import com.baway.yunifang.activity.LoginActivity;
import com.baway.yunifang.bean.DataBean;
import com.baway.yunifang.transformer.MyGallyPageTransformer;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/14 13:04
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context context;
    private DataBean.Data data;
    private Handler handler;

    public HomeAdapter(Context context, DataBean.Data data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==1){
            return new MyViewHolder1(View.inflate(context,R.layout.home_recycler_item1,null));
        }else if (viewType==2){
            return new MyViewHolder2(View.inflate(context,R.layout.home_recycler_item2,null));
        }else if (viewType==3){
            return new MyViewHolder3(View.inflate(context,R.layout.home_recycler_item3,null));
        }else if (viewType==4){
            return new MyViewHolder4(View.inflate(context,R.layout.home_recycler_item4,null));
        }else if (viewType==5){
            return new MyViewHolder5(View.inflate(context,R.layout.home_recycler_item5,null));
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MyViewHolder1){
            List<DataBean.Data.Ad1> ad1 = data.ad1;
            ((MyViewHolder1) holder).viewPager.setAdapter(new Item1ViewPagerAdapter(context,ad1));
            handler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what==0){
                        int current= ((MyViewHolder1) holder).viewPager.getCurrentItem();
                        ((MyViewHolder1) holder).viewPager.setCurrentItem(current+1);
                        handler.sendEmptyMessageDelayed(0,3000);
                    }
                }
            };
            new Thread(){
                @Override
                public void run() {
                    handler.sendEmptyMessageDelayed(0,3000);
                }
            }.start();
            ((MyViewHolder1) holder).linear1_tv.setText(data.ad5.get(0).title);
            ((MyViewHolder1) holder).linear2_tv.setText(data.ad5.get(1).title);
            ((MyViewHolder1) holder).linear3_tv.setText(data.ad5.get(2).title);
            ((MyViewHolder1) holder).linear4_tv.setText(data.ad5.get(3).title);
            Glide.with(context).load(data.ad5.get(0).image).into(((MyViewHolder1) holder).linear1_iv);
            Glide.with(context).load(data.ad5.get(1).image).into(((MyViewHolder1) holder).linear2_iv);
            Glide.with(context).load(data.ad5.get(2).image).into(((MyViewHolder1) holder).linear3_iv);
            Glide.with(context).load(data.ad5.get(3).image).into(((MyViewHolder1) holder).linear4_iv);

            ((MyViewHolder1) holder).item1_linear1.setOnClickListener(this);
            ((MyViewHolder1) holder).item1_linear2.setOnClickListener(this);
            ((MyViewHolder1) holder).item1_linear3.setOnClickListener(this);
            ((MyViewHolder1) holder).item1_linear4.setOnClickListener(this);
        }else if (holder instanceof MyViewHolder2){
            final DataBean.Data.BestSellers bestSellers = data.bestSellers.get(0);
            ((MyViewHolder2) holder).item2_title.setText("~~"+bestSellers.name+"~~");
            LinearLayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            ((MyViewHolder2) holder).item2_recycler.setLayoutManager(layoutManager);
            Item2Adapter item2Adapter = new Item2Adapter(context, bestSellers);
            ((MyViewHolder2) holder).item2_recycler.setAdapter(item2Adapter);
            item2Adapter.setOnRecyclerItemClickListener(new Item2Adapter.OnRecyclerItemClickListener() {
                @Override
                public void onRecyclerItemClick(View view) {
                    int position1 = ((MyViewHolder2) holder).item2_recycler.getChildAdapterPosition(view);
                    if (position1<bestSellers.show_number){
                        Intent intent=new Intent(context, GoodsContentActivity.class);
                        intent.putExtra("id",bestSellers.goodsList.get(position1).id);
                        context.startActivity(intent);
                    }else{
                        Toast.makeText(context, ""+position1+"ssssssssssssss", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context, HuiyuanActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("bestSellers",bestSellers);
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                }
            });
        }else if (holder instanceof MyViewHolder3){
            List<DataBean.Data.ActivityInfo.ActivityInfoList> activityInfoList = data.activityInfo.activityInfoList;
            ((MyViewHolder3) holder).item3_vp.setOffscreenPageLimit(3);
            int pagerWidth = (int) (context.getResources().getDisplayMetrics().widthPixels * 3.5f / 5.0f);
            ViewGroup.LayoutParams lp = ((MyViewHolder3) holder).item3_vp.getLayoutParams();
            if (lp == null) {
                lp = new ViewGroup.LayoutParams(pagerWidth, ViewGroup.LayoutParams.MATCH_PARENT);
            } else {
                lp.width = pagerWidth;
            }
            ((MyViewHolder3) holder).item3_vp.setLayoutParams(lp);
            //setPageMargin表示设置图片之间的间距
            ((MyViewHolder3) holder).item3_vp.setPageMargin(context.getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin));
            ((MyViewHolder3) holder).item3_vp.setPageTransformer(true,new MyGallyPageTransformer());
            ((MyViewHolder3) holder).item3_vp.setAdapter(new MyViewpagerAdapter(activityInfoList,context));
            ((MyViewHolder3) holder).item3_vp.setCurrentItem(2000);
        }else if (holder instanceof MyViewHolder4){
            List<DataBean.Data.Subjects> subjects = data.subjects;
            LinearLayoutManager layoutManager=new LinearLayoutManager(context);
            ((MyViewHolder4) holder).item4_recycler.setLayoutManager(layoutManager);
            ((MyViewHolder4) holder).item4_recycler.setAdapter(new Item4Adapter(context,subjects));
        }else if (holder instanceof MyViewHolder5){
            GridLayoutManager gridLayoutManager=new GridLayoutManager(context,2);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position==6){
                        return 2;
                    }
                    return 1;
                }
            });
            ((MyViewHolder5) holder).item5_recycler.setLayoutManager(gridLayoutManager);
            final List<DataBean.Data.DefaultGoodsList> defaultGoodsList = data.defaultGoodsList;
            Item5Adapter item5Adapter = new Item5Adapter(context, defaultGoodsList);
            ((MyViewHolder5) holder).item5_recycler.setAdapter(item5Adapter);

            item5Adapter.setOnRecyclerItemClickListener(new Item5Adapter.OnRecyclerItemClickListener() {
                @Override
                public void onRecyclerItemClick(View view) {
                    int position1 = ((MyViewHolder5) holder).item5_recycler.getChildAdapterPosition(view);
                    if (position1<6){
                        Intent intent=new Intent(context, GoodsContentActivity.class);
                        intent.putExtra("id",defaultGoodsList.get(position1).id);
                        context.startActivity(intent);
                    }else{

                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public void onClick(View v) {
        boolean flag= LoginActivity.isLogin;
        if (flag) {
            switch (v.getId()) {
                case R.id.item1_linear1:
                    intentActivity(0);
                    break;
                case R.id.item1_linear2:
                    intentActivity(1);
                    break;
                case R.id.item1_linear3:
                    intentActivity(2);
                    break;
                case R.id.item1_linear4:
                    intentActivity(3);
                    break;
            }
        }else{
            switch (v.getId()) {
                case R.id.item1_linear1:
                    initDialog();
                    break;
                case R.id.item1_linear2:
                    initDialog();
                    break;
                case R.id.item1_linear3:
                    initDialog();
                    break;
                case R.id.item1_linear4:
                    initDialog();
                    break;
            }
        }
    }

    private void intentActivity(int position) {
        Intent intent=new Intent(context, HomeWebViewActivity.class);
        intent.putExtra("url",data.ad5.get(position).ad_type_dynamic_data);
        context.startActivity(intent);
    }
    private void initDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("登录");
        builder.setMessage("此功能仅限登录用户使用，请先登录");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("去登陆", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(context,LoginActivity.class);
                context.startActivity(intent);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public static class MyViewHolder1 extends RecyclerView.ViewHolder{

        private final LinearLayout item1_linear1;
        private final LinearLayout item1_linear2;
        private final LinearLayout item1_linear3;
        private final LinearLayout item1_linear4;
        private final ImageView linear1_iv;
        private final ImageView linear2_iv;
        private final ImageView linear3_iv;
        private final ImageView linear4_iv;
        private final TextView linear1_tv;
        private final TextView linear2_tv;
        private final TextView linear3_tv;
        private final TextView linear4_tv;
        private final ViewPager viewPager;

        public MyViewHolder1(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewPager);
            item1_linear1 = (LinearLayout) itemView.findViewById(R.id.item1_linear1);
            item1_linear2 = (LinearLayout) itemView.findViewById(R.id.item1_linear2);
            item1_linear3 = (LinearLayout) itemView.findViewById(R.id.item1_linear3);
            item1_linear4 = (LinearLayout) itemView.findViewById(R.id.item1_linear4);
            linear1_iv = (ImageView) itemView.findViewById(R.id.linear1_iv);
            linear2_iv = (ImageView) itemView.findViewById(R.id.linear2_iv);
            linear3_iv = (ImageView) itemView.findViewById(R.id.linear3_iv);
            linear4_iv = (ImageView) itemView.findViewById(R.id.linear4_iv);
            linear1_tv = (TextView) itemView.findViewById(R.id.linear1_tv);
            linear2_tv = (TextView) itemView.findViewById(R.id.linear2_tv);
            linear3_tv = (TextView) itemView.findViewById(R.id.linear3_tv);
            linear4_tv = (TextView) itemView.findViewById(R.id.linear4_tv);
        }
    }
    public static class MyViewHolder2 extends RecyclerView.ViewHolder{

        private final TextView item2_title;
        private final RecyclerView item2_recycler;

        public MyViewHolder2(View itemView) {
            super(itemView);
            item2_title = (TextView) itemView.findViewById(R.id.item2_title);
            item2_recycler = (RecyclerView) itemView.findViewById(R.id.item2_recycler);
        }
    }
    public static class MyViewHolder3 extends RecyclerView.ViewHolder{

        private final ViewPager item3_vp;

        public MyViewHolder3(View itemView) {
            super(itemView);
            item3_vp = (ViewPager) itemView.findViewById(R.id.item3_vp);
        }
    }
    public static class MyViewHolder4 extends RecyclerView.ViewHolder{

        private final RecyclerView item4_recycler;

        public MyViewHolder4(View itemView) {
            super(itemView);
            item4_recycler = (RecyclerView) itemView.findViewById(R.id.item4_recycler);
        }
    }
    public static class MyViewHolder5 extends RecyclerView.ViewHolder{

        private final RecyclerView item5_recycler;

        public MyViewHolder5(View itemView) {
            super(itemView);
            item5_recycler = (RecyclerView) itemView.findViewById(R.id.item5_recycler);
        }
    }
}
