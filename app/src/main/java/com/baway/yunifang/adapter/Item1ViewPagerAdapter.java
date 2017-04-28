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
 * @date 2017/4/16 17:42
 */
public class Item1ViewPagerAdapter extends PagerAdapter{
    private Context context;
    private List<DataBean.Data.Ad1> ad1;

    public Item1ViewPagerAdapter(Context context, List<DataBean.Data.Ad1> ad1) {
        this.context = context;
        this.ad1 = ad1;
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
    public Object instantiateItem(final ViewGroup container, final int position) {
        ImageView imageView=new ImageView(context);
        Glide.with(context).load(ad1.get(position%ad1.size()).image).into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLogin= LoginActivity.isLogin;
                if (isLogin){
                    if (ad1.get(position%ad1.size()).ad_type_dynamic_data!=null){
                        Intent intent=new Intent(context, HomeWebViewActivity.class);
                        intent.putExtra("url",ad1.get(position%ad1.size()).ad_type_dynamic_data);
                        context.startActivity(intent);
                    }
                }else{
                    initDialog();
                }

            }
        });
        return imageView;
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
