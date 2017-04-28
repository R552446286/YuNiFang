package com.baway.yunifang.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.yunifang.R;
import com.baway.yunifang.fragment.ClassifyTypeFragment;

import java.util.ArrayList;

public class ClassifyTabActivity extends AppCompatActivity {

    private ImageView classifytab_back;
    private TextView classifytab_title;
    private TabLayout classifytab_tablayout;
    private ViewPager classify_viewPager;
    private ArrayList<String> ids;
    private ArrayList<String> tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_classifyab);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        ids = intent.getStringArrayListExtra("ids");
        tabs = intent.getStringArrayListExtra("tabs");
        int position=intent.getIntExtra("position",0);
        initView();
        classifytab_title.setText(title);
        for (int i = 0; i < tabs.size(); i++) {
            classifytab_tablayout.addTab(classifytab_tablayout.newTab().setText(tabs.get(i)));
        }
        classify_viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        classifytab_tablayout.setupWithViewPager(classify_viewPager);
        classify_viewPager.setCurrentItem(position);
        classifytab_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        classifytab_back = (ImageView) findViewById(R.id.classifytab_back);
        classifytab_title = (TextView) findViewById(R.id.classifytab_title);
        classifytab_tablayout = (TabLayout) findViewById(R.id.classifytab_tablayout);
        classify_viewPager = (ViewPager) findViewById(R.id.classify_viewPager);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter{

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ClassifyTypeFragment.getInstance(ids.get(position));
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs.get(position);
        }
    }
}
