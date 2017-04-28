package com.baway.yunifang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.baway.yunifang.R;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private XBanner xBanner;
    private List<Integer> images = new ArrayList<>();
    private Button bt;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        edit = config.edit();
        boolean flag=config.getBoolean("flag",false);
        if (flag){
            intentActivity();
        }
        //找控件
        initView();
        //初始化数据
        initData();
        //绑定数据
        xBanner.setData(images,null);
        //设置适配器
        xBanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(GuideActivity.this).load(images.get(position)).into((ImageView) view);
            }
        });
        //设置切换监听
        xBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==images.size()-1){
                    bt.setVisibility(View.VISIBLE);
                }else{
                    bt.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bt.setOnClickListener(this);
    }

    private void initData() {
        images.add(R.mipmap.xiaomi_guidance_1);
        images.add(R.mipmap.xiaomi_guidance_2);
        images.add(R.mipmap.xiaomi_guidance_3);
        images.add(R.mipmap.xiaomi_guidance_4);
        images.add(R.mipmap.xiaomi_guidance_5);
    }

    private void initView() {
        xBanner = (XBanner) findViewById(R.id.xBanner);
        bt = (Button) findViewById(R.id.bt);
    }

    @Override
    public void onClick(View v) {
        edit.putBoolean("flag",true);
        edit.commit();
        intentActivity();
    }

    private void intentActivity() {
        Intent intent=new Intent(this,FirstActivity.class);
        startActivity(intent);
        finish();
    }
}
