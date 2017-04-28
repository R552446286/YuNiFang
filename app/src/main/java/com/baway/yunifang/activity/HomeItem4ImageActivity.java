package com.baway.yunifang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.yunifang.R;
import com.baway.yunifang.adapter.HomeItemImageRecyclerAdapter;
import com.baway.yunifang.bean.DataBean;

import java.io.Serializable;
import java.util.List;

public class HomeItem4ImageActivity extends AppCompatActivity {

    private ImageView home_item4_back;
    private TextView home_item4_title;
    private TextView home_item4_detail;
    private RecyclerView home_item4_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_home_item4_image);
        DataBean.Data.Subjects subject = (DataBean.Data.Subjects) getIntent().getExtras().getSerializable("subject");
        initView();
        home_item4_title.setText(subject.title);
        home_item4_detail.setText(subject.detail);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        gridLayoutManager.setAutoMeasureEnabled(true);
        home_item4_recycler.setLayoutManager(gridLayoutManager);
        final List<DataBean.Data.Subjects.GoodsList> goodsList = subject.goodsList;
        HomeItemImageRecyclerAdapter adapter = new HomeItemImageRecyclerAdapter(this, goodsList);
        home_item4_recycler.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new HomeItemImageRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view) {
                int position = home_item4_recycler.getChildAdapterPosition(view);
                Intent intent=new Intent(HomeItem4ImageActivity.this, GoodsContentActivity.class);
                intent.putExtra("id",goodsList.get(position).id);
                startActivity(intent);
            }
        });
        home_item4_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        home_item4_back = (ImageView) findViewById(R.id.home_item4_back);
        home_item4_title = (TextView) findViewById(R.id.home_item4_title);
        home_item4_detail = (TextView) findViewById(R.id.home_item4_detail);
        home_item4_recycler = (RecyclerView) findViewById(R.id.home_item4_recycler);
    }
}
