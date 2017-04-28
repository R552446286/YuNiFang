package com.baway.yunifang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baway.yunifang.R;
import com.baway.yunifang.bean.Address;
import com.baway.yunifang.bean.CartGoodsBean;
import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.Serializable;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class OrderActivity extends AppCompatActivity {

    private ImageView order_back;
    private ListView order_lv;
    private List<CartGoodsBean.CartItemList> cartItemList;
    private TextView order_count;
    private TextView order_totalprice;
    private TextView order_total;
    private Button order_sure_bt;
    private LinearLayout order_address;
    private TextView order_home;
    private TextView order_phone;
    private TextView order_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_order);
        cartItemList = (List<CartGoodsBean.CartItemList>) getIntent().getSerializableExtra("list");
        initView();
        order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        order_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderActivity.this,AddressManagerActivity.class);
                startActivityForResult(intent,1);
            }
        });
        order_lv.setAdapter(new CartGoodAdapter());
        int count=0;
        double price=0;
        for (int i = 0; i < cartItemList.size(); i++) {
            count+=cartItemList.get(i).count;
            price+=cartItemList.get(i).price;
        }
        order_count.setText("共计"+count+"件商品");
        order_totalprice.setText("总计：￥"+price);
        order_total.setText("总计：￥"+price);
        order_sure_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="http://lexue365.51dangao.cn/api/order/add_order";
                AsyncHttpClient client=new AsyncHttpClient();
                client.addHeader("userid",465+"");
                client.addHeader("cltid", "1");
                client.addHeader("token", "bbb6e99c4cd22930ea4c945d9932f98a");
                client.addHeader("mobile", "15718812708");
                RequestParams params=new RequestParams();
                params.put("activity_id",4);
                params.put("time_id",2927);
                params.put("child_num",1);
                params.put("contact","啊啊");
                params.put("mobile","15718812708");
                params.put("remark",1);
                client.post(url, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        Log.e("sss",new String(responseBody));
                        Intent intent=new Intent(OrderActivity.this,PayDemoActivity.class);
                        intent.putExtra("list", (Serializable) cartItemList);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                    }
                });
            }
        });
    }

    private void initView() {
        order_back = (ImageView) findViewById(R.id.order_back);
        order_lv = (ListView) findViewById(R.id.order_lv);
        order_count = (TextView) findViewById(R.id.order_count);
        order_totalprice = (TextView) findViewById(R.id.order_totalprice);
        order_total = (TextView) findViewById(R.id.order_total);
        order_sure_bt = (Button) findViewById(R.id.order_sure_bt);
        order_address = (LinearLayout) findViewById(R.id.order_address);
        order_home = (TextView) findViewById(R.id.order_home);
        order_phone = (TextView) findViewById(R.id.order_phone);
        order_name = (TextView) findViewById(R.id.order_name);
    }
    public class CartGoodAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return cartItemList.size();
        }

        @Override
        public Object getItem(int position) {
            return cartItemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(OrderActivity.this, R.layout.cart_lv_item,null);
            final CheckBox cart_item_cb = (CheckBox) convertView.findViewById(R.id.cart_item_cb);
            ImageView cart_item_iv = (ImageView) convertView.findViewById(R.id.cart_item_iv);
            TextView cart_item_name = (TextView) convertView.findViewById(R.id.cart_item_name);
            TextView cart_item_price = (TextView) convertView.findViewById(R.id.cart_item_price);
            TextView cart_item_count = (TextView) convertView.findViewById(R.id.cart_item_count);
            cart_item_cb.setVisibility(View.GONE);
            Glide.with(OrderActivity.this).load(cartItemList.get(position).pic).into(cart_item_iv);
            cart_item_name.setText(cartItemList.get(position).name);
            cart_item_price.setText("￥"+cartItemList.get(position).price);
            cart_item_count.setText("数量："+cartItemList.get(position).count);
            return convertView;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==100){
            Address address = (Address) data.getSerializableExtra("address");
            order_home.setText("收货人："+address.name);
            order_phone.setText(address.phone);
            order_name.setText("详细地址："+address.home);
        }
    }
}
