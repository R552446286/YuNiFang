package com.baway.yunifang.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.baway.yunifang.activity.HomeWebViewActivity;
import com.baway.yunifang.activity.LoginActivity;
import com.baway.yunifang.bean.DataBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/14 20:46
 */
public class MyViewpagerAdapter extends PagerAdapter{
    private List<DataBean.Data.ActivityInfo.ActivityInfoList> activityInfoList;
    private Context context;

    public MyViewpagerAdapter(List<DataBean.Data.ActivityInfo.ActivityInfoList> activityInfoList, Context context) {
        this.activityInfoList = activityInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView vp_iv= new ImageView(context);
        Glide.with(context).load(activityInfoList.get(position%activityInfoList.size()).activityImg).into(vp_iv);
        container.addView(vp_iv);
        vp_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag= LoginActivity.isLogin;
                if (flag){
                    Intent intent=new Intent(context, HomeWebViewActivity.class);
                    intent.putExtra("url",activityInfoList.get(position%activityInfoList.size()).activityData);
                    context.startActivity(intent);
                }else{
                    initDialog();
                }
            }
        });
        return vp_iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
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
}
