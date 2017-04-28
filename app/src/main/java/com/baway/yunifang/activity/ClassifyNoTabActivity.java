package com.baway.yunifang.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.yunifang.R;
import com.baway.yunifang.bean.TypeBean;
import com.baway.yunifang.utils.MyOkhttp;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

public class ClassifyNoTabActivity extends AppCompatActivity {

    private ImageView classifynotab_back;
    private TextView classifynotab_title;
    private GridView classifynotype_gridView;
    private String jsonUrl;
    private List<TypeBean.Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_classify_no_tab);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String id = intent.getStringExtra("id");
        initView();
        classifynotab_title.setText(title);
        jsonUrl = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=91873&encode=68301f6ea0d6adcc0fee63bd21815d69&category_id="+id;
        getServerData();
        classifynotab_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getServerData() {
        MyAsyncTask task=new MyAsyncTask();
        task.execute(jsonUrl);
    }

    private void initView() {
        classifynotab_back = (ImageView) findViewById(R.id.classifynotab_back);
        classifynotab_title = (TextView) findViewById(R.id.classifynotab_title);
        classifynotype_gridView = (GridView) findViewById(R.id.classifynotype_gridView);
    }
    class MyAsyncTask extends AsyncTask<String,Integer,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            return MyOkhttp.get(params[0]);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String json=s.toString();
            Gson gson=new Gson();
            TypeBean typeBean = gson.fromJson(json, TypeBean.class);
            data = typeBean.data;
            classifynotype_gridView.setAdapter(new ClassifyTypeGvAdapter());
            classifynotype_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(ClassifyNoTabActivity.this, GoodsContentActivity.class);
                    intent.putExtra("id",data.get(position).id);
                    startActivity(intent);
                }
            });
        }
    }
    class ClassifyTypeGvAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder v;
            if (convertView==null){
                convertView=View.inflate(ClassifyNoTabActivity.this, R.layout.item5_recycler_item1,null);
                v=new ViewHolder();
                v.item5_iv = (ImageView) convertView.findViewById(R.id.item5_iv);
                v.item5_efficacy = (TextView) convertView.findViewById(R.id.item5_efficacy);
                v.item5_name = (TextView) convertView.findViewById(R.id.item5_name);
                v.item5_shop = (TextView) convertView.findViewById(R.id.item5_shop);
                v.item5_market = (TextView) convertView.findViewById(R.id.item5_market);
                convertView.setTag(v);
            }else{
                v= (ViewHolder) convertView.getTag();
            }
            Glide.with(ClassifyNoTabActivity.this).load(data.get(position).goods_img).into((v.item5_iv));
            v.item5_efficacy.setText(data.get(position).efficacy);
            v.item5_name.setText(data.get(position).goods_name);
            v.item5_shop.setText("￥"+data.get(position).shop_price);
            v.item5_market.setText("￥"+data.get(position).market_price);
            v.item5_market.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            return convertView;
        }
        class ViewHolder{
            ImageView item5_iv;
            TextView item5_efficacy;
            TextView item5_name;
            TextView item5_shop;
            TextView item5_market;
        }
    }
}
