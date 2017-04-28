package com.baway.yunifang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baway.yunifang.R;
import com.baway.yunifang.adapter.GoodGvAdapter;
import com.baway.yunifang.adapter.GoodLvAdapter;
import com.baway.yunifang.bean.GoodBean;
import com.baway.yunifang.utils.MyOkhttp;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsContentActivity extends AppCompatActivity {

    private XBanner good_content_images;
    private String jsonUrl;
    private List<GoodBean.Data.Goods.Gallery> gallery;
    private GoodBean.Data data;
    private TextView good_content_name;
    private TextView good_content_shop;
    private TextView good_content_market;
    private TextView good_content_yunfei;
    private TextView good_content_xiaoliang;
    private TextView good_content_shoucang;
    private ListView good_content_listView;
    private List<GoodBean.Data.Activity> activity;
    private GridView good_content_gridView;
    private List<GoodBean.Data.GoodsRelDetails> goodsRelDetails;
    private ScrollView good_content_scrollView;
    private ListView good_content_xq_lv;
    private List<String> images=new ArrayList<>();
    private ListView good_content_cs_lv;
    private List<GoodBean.Data.Goods.Attributes> attributes;
    private ListView good_content_pl_lv;
    private List<GoodBean.Data.Comments> comments;
    private RadioGroup good_content_radiogroup;
    private RadioButton good_content_rb_xq;
    private RadioButton good_content_rb_cs;
    private RadioButton good_content_rb_pl;
    private SharedPreferences.Editor edit;
    private ImageView good_content_back;
    private TextView good_content_add_cart;
    private PopupWindow popupWindow;
    private TextView pw_num;
    private RelativeLayout pw_jia;
    private RelativeLayout pw_jian;
    private ImageView pw_jiaimg;
    private ImageView pw_jianimg;
    private Button pw_queding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_goods_content);
        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        edit = config.edit();
        String id1 = config.getString("id", "0");
        String id = getIntent().getStringExtra("id");
        if (id1.equals("0")){
            jsonUrl="http://m.yunifang.com/yunifang/mobile/goods/detail?random=46389&encode=70ed2ed2facd7a812ef46717b37148d6&id="+ id;
        }else{
            jsonUrl="http://m.yunifang.com/yunifang/mobile/goods/detail?random=46389&encode=70ed2ed2facd7a812ef46717b37148d6&id="+ id1;
        }
        edit.clear();
        edit.commit();
        initView();
        getServerData();
        good_content_back.setOnClickListener(new View.OnClickListener() {
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
        good_content_images = (XBanner) findViewById(R.id.good_content_images);
        good_content_name = (TextView) findViewById(R.id.good_content_name);
        good_content_shop = (TextView) findViewById(R.id.good_content_shop);
        good_content_market = (TextView) findViewById(R.id.good_content_market);
        good_content_yunfei = (TextView) findViewById(R.id.good_content_yunfei);
        good_content_xiaoliang = (TextView) findViewById(R.id.good_content_xiaoliang);
        good_content_shoucang = (TextView) findViewById(R.id.good_content_shoucang);
        good_content_listView = (ListView) findViewById(R.id.good_content_listView);
        good_content_gridView = (GridView) findViewById(R.id.good_content_gridView);
        good_content_scrollView = (ScrollView) findViewById(R.id.good_content_scrollView);
        good_content_xq_lv = (ListView) findViewById(R.id.good_content_xq_lv);
        good_content_cs_lv = (ListView) findViewById(R.id.good_content_cs_lv);
        good_content_pl_lv = (ListView) findViewById(R.id.good_content_pl_lv);
        good_content_radiogroup = (RadioGroup) findViewById(R.id.good_content_radiogroup);
        good_content_rb_xq = (RadioButton) findViewById(R.id.good_content_rb_xq);
        good_content_rb_cs = (RadioButton) findViewById(R.id.good_content_rb_cs);
        good_content_rb_pl = (RadioButton) findViewById(R.id.good_content_rb_pl);
        good_content_back = (ImageView) findViewById(R.id.good_content_back);
        good_content_add_cart = (TextView) findViewById(R.id.good_content_add_cart);
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
            GoodBean goodBean = gson.fromJson(json, GoodBean.class);
            data = goodBean.data;
            gallery = data.goods.gallery;
            activity = data.activity;
            goodsRelDetails = data.goodsRelDetails;
            initXBanner();
            initTextView();
            initListView();
            initGridView();
            initXqListView();
            initCsListView();
            initPlListView();
            initRadioGroup();
            initAddCart();
        }
    }

    private void initAddCart() {
        good_content_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLogin=LoginActivity.isLogin;
                if (isLogin) {
                    initPopupWindow();
                    if (popupWindow.isShowing()) {
                        popupWindow.dismiss();
                    } else {
                        popupWindow.showAtLocation(good_content_add_cart, Gravity.BOTTOM, 0, 0);
                    }
                }else{
                    Intent intent=new Intent(GoodsContentActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initPopupWindow() {
        View view=View.inflate(GoodsContentActivity.this, R.layout.popupwindow,null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        ImageView pw_iv = (ImageView) view.findViewById(R.id.pw_iv);
        TextView pw_price = (TextView) view.findViewById(R.id.pw_price);
        TextView pw_kucun = (TextView) view.findViewById(R.id.pw_kucun);
        TextView pw_xiangou = (TextView) view.findViewById(R.id.pw_xiangou);
        pw_num = (TextView) view.findViewById(R.id.pw_num);
        pw_jia = (RelativeLayout) view.findViewById(R.id.pw_jia);
        pw_jian = (RelativeLayout) view.findViewById(R.id.pw_jian);
        pw_jiaimg = (ImageView) view.findViewById(R.id.pw_jiaimg);
        pw_jianimg = (ImageView) view.findViewById(R.id.pw_jianimg);
        pw_queding = (Button) view.findViewById(R.id.pw_queding);
        Glide.with(this).load(data.goods.goods_img).into(pw_iv);
        pw_price.setText("￥"+data.goods.shop_price);
        pw_kucun.setText("库存"+data.goods.restrict_purchase_num+"件");
        pw_xiangou.setText("限购"+data.goods.stock_number+"件");
        pw_jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(pw_num.getText().toString());
                if (a<data.goods.restrict_purchase_num){
                    a++;
                    pw_num.setText(a+"");
                    pw_jiaimg.setImageResource(R.mipmap.add_able);
                    pw_jianimg.setImageResource(R.mipmap.reduce_able);
                }else{
                    pw_num.setText(a+"");
                    pw_jiaimg.setImageResource(R.mipmap.add_unable);
                    pw_jianimg.setImageResource(R.mipmap.reduce_able);
                }
            }
        });
        pw_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a= Integer.parseInt(pw_num.getText().toString());
                if (a>1){
                    a--;
                    pw_num.setText(a + "");
                    pw_jiaimg.setImageResource(R.mipmap.add_able);
                    pw_jianimg.setImageResource(R.mipmap.reduce_able);
                }else{
                    pw_num.setText(a + "");
                    pw_jiaimg.setImageResource(R.mipmap.add_able);
                    pw_jianimg.setImageResource(R.mipmap.reduce_unable);
                }
            }
        });
        pw_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url="http://169.254.116.62:8080/bullking1/cart";
                final int count=Integer.parseInt(pw_num.getText().toString());
                final float price= (float) data.goods.shop_price;
                final String name=data.goods.goods_name;
                final int userID=LoginActivity.id;
                final String pic=data.goods.goods_img;
                RequestQueue queue = Volley.newRequestQueue(GoodsContentActivity.this);
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(GoodsContentActivity.this, "成功加入购物车", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(GoodsContentActivity.this, "加入购物车失败", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap<>();
                        map.put("productID",data.goods.id+"");
                        map.put("count", count+"");
                        map.put("price", price+"");
                        map.put("name", name);
                        map.put("userID", userID+"");
                        map.put("pic", pic);
                        return map;
                    }
                };
                queue.add(request);
            }
        });
    }

    private void initRadioGroup() {
        good_content_rb_xq.setText("产品详情");
        good_content_rb_cs.setText("产品参数");
        good_content_rb_pl.setText("评论("+data.commentNumber+")");
        good_content_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.good_content_rb_xq:
                        good_content_xq_lv.setVisibility(View.VISIBLE);
                        good_content_cs_lv.setVisibility(View.GONE);
                        good_content_pl_lv.setVisibility(View.GONE);
                        break;
                    case R.id.good_content_rb_cs:
                        good_content_xq_lv.setVisibility(View.GONE);
                        good_content_cs_lv.setVisibility(View.VISIBLE);
                        good_content_pl_lv.setVisibility(View.GONE);
                        break;
                    case R.id.good_content_rb_pl:
                        good_content_xq_lv.setVisibility(View.GONE);
                        good_content_cs_lv.setVisibility(View.GONE);
                        good_content_pl_lv.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
    }

    private void initPlListView() {
        comments = data.comments;
        good_content_pl_lv.setAdapter(new GoodPlAdapter());
        good_content_pl_lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                good_content_scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    private void initCsListView() {
        attributes = data.goods.attributes;
        good_content_cs_lv.setAdapter(new GoodCsAdapter());
        good_content_cs_lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                good_content_scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    private void initXqListView() {
        String json=data.goods.goods_desc;
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                images.add(jsonArray.getJSONObject(i).getString("url"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        good_content_xq_lv.setAdapter(new GoodXqAdapter());
        good_content_xq_lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                good_content_scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
    }

    private void initGridView() {
        if (goodsRelDetails!=null) {
            good_content_gridView.setVisibility(View.VISIBLE);
            good_content_gridView.setAdapter(new GoodGvAdapter(this, goodsRelDetails));
            good_content_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String id1 = goodsRelDetails.get(position).id;
                    edit.putString("id", id1);
                    edit.commit();
                    recreate();
                }
            });
        }else{
            good_content_gridView.setVisibility(View.GONE);
        }
    }

    private void initListView() {
        good_content_listView.setAdapter(new GoodLvAdapter(this,activity));
        good_content_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(GoodsContentActivity.this, HomeWebViewActivity.class);
                intent.putExtra("url",activity.get(position).description);
                startActivity(intent);
            }
        });
    }

    private void initTextView() {
        good_content_name.setText(data.goods.goods_name);
        good_content_shop.setText("￥"+data.goods.shop_price);
        good_content_market.setText("￥"+data.goods.market_price);
        good_content_market.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        good_content_yunfei.setText("￥"+data.goods.shipping_fee);
        good_content_xiaoliang.setText(""+data.goods.sales_volume);
        good_content_shoucang.setText(""+data.goods.collect_count);
    }

    private void initXBanner() {
        good_content_images.setData(gallery,null);
        good_content_images.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(GoodsContentActivity.this).load(gallery.get(position).normal_url).into((ImageView) view);
            }
        });
    }
    class GoodXqAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int position) {
            return images.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder v;
            if (convertView==null){
                convertView=View.inflate(GoodsContentActivity.this,R.layout.good_xq_lv_item,null);
                v=new ViewHolder();
                v.iv= (ImageView) convertView.findViewById(R.id.iv);
                convertView.setTag(v);
            }else{
                v= (ViewHolder) convertView.getTag();
            }
            Glide.with(GoodsContentActivity.this).load(images.get(position)).into(v.iv);
            return convertView;
        }
        class ViewHolder{
            ImageView iv;
        }
    }
    class GoodCsAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return attributes.size();
        }

        @Override
        public Object getItem(int position) {
            return attributes.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(GoodsContentActivity.this,R.layout.good_cs_lv_item,null);
            TextView good_cs_item_attr_name = (TextView) convertView.findViewById(R.id.good_cs_item_attr_name);
            TextView good_cs_item_attr_value = (TextView) convertView.findViewById(R.id.good_cs_item_attr_value);
            good_cs_item_attr_name.setText(attributes.get(position).attr_name);
            good_cs_item_attr_value.setText(attributes.get(position).attr_value);
            return convertView;
        }
    }
    class GoodPlAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return comments.size();
        }

        @Override
        public Object getItem(int position) {
            return comments.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder v;
            if (convertView==null){
                convertView=View.inflate(GoodsContentActivity.this,R.layout.good_pl_lv_item,null);
                v=new ViewHolder();
                v.good_pl_item_icon= (ImageView) convertView.findViewById(R.id.good_pl_item_icon);
                v.good_pl_item_nick_name= (TextView) convertView.findViewById(R.id.good_pl_item_nick_name);
                v.good_pl_item_createtime= (TextView) convertView.findViewById(R.id.good_pl_item_createtime);
                v.good_pl_item_content= (TextView) convertView.findViewById(R.id.good_pl_item_content);
                convertView.setTag(v);
            }else{
                v= (ViewHolder) convertView.getTag();
            }
            Glide.with(GoodsContentActivity.this).load(comments.get(position).user.icon).into(v.good_pl_item_icon);
            v.good_pl_item_nick_name.setText(comments.get(position).user.nick_name);
            v.good_pl_item_createtime.setText(comments.get(position).createtime);
            v.good_pl_item_content.setText(comments.get(position).content);
            return convertView;
        }
        class ViewHolder{
            ImageView good_pl_item_icon;
            TextView good_pl_item_nick_name,good_pl_item_createtime,good_pl_item_content;
        }
    }
}
