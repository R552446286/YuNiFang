package com.baway.yunifang.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baway.yunifang.R;
import com.baway.yunifang.bean.DataBean;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

public class HuiyuanActivity extends AppCompatActivity {

    private DataBean.Data.BestSellers bestSellers;
    private ListView huiyuan_listView;
    private List<DataBean.Data.BestSellers.GoodsList> goodsList;
    private ImageView huiyuan_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_huiyuan);
        bestSellers = (DataBean.Data.BestSellers) getIntent().getExtras().getSerializable("bestSellers");
        huiyuan_listView = (ListView) findViewById(R.id.huiyuan_listView);
        huiyuan_back = (ImageView) findViewById(R.id.huiyuan_back);
        goodsList = bestSellers.goodsList;
        huiyuan_listView.setAdapter(new HuiyuanAdapter());
        huiyuan_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(HuiyuanActivity.this, GoodsContentActivity.class);
                intent.putExtra("id",goodsList.get(position).id);
                startActivity(intent);
            }
        });
        huiyuan_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    class HuiyuanAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return goodsList.size();
        }

        @Override
        public Object getItem(int position) {
            return goodsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder v;
            if (convertView == null) {
                convertView=View.inflate(HuiyuanActivity.this,R.layout.huiyuan_lv_item,null);
                v=new ViewHolder();
                v.huiyuan_item_iv= (ImageView) convertView.findViewById(R.id.huiyuan_item_iv);
                v.huiyuan_item_name= (TextView) convertView.findViewById(R.id.huiyuan_item_name);
                v.huiyuan_item_shop= (TextView) convertView.findViewById(R.id.huiyuan_item_shop);
                v.huiyuan_item_market= (TextView) convertView.findViewById(R.id.huiyuan_item_market);
                convertView.setTag(v);
            }else{
                v= (ViewHolder) convertView.getTag();
            }
            Glide.with(HuiyuanActivity.this).load(goodsList.get(position).goods_img).into(v.huiyuan_item_iv);
            v.huiyuan_item_name.setText(goodsList.get(position).goods_name);
            v.huiyuan_item_shop.setText("￥"+goodsList.get(position).shop_price);
            v.huiyuan_item_market.setText("￥"+goodsList.get(position).market_price);
            v.huiyuan_item_market.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            return convertView;
        }
        class ViewHolder{
            ImageView huiyuan_item_iv;
            TextView huiyuan_item_name,huiyuan_item_shop,huiyuan_item_market;
        }
    }
}
