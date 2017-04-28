package com.baway.yunifang.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baway.yunifang.R;
import com.baway.yunifang.activity.HomeActivity;
import com.baway.yunifang.activity.LoginActivity;
import com.baway.yunifang.activity.OrderActivity;
import com.baway.yunifang.bean.CartGoodsBean;
import com.baway.yunifang.utils.MyOkhttp;
import com.baway.yunifang.view.QQListView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/13 19:28
 */
public class ShoppingFragment extends Fragment{

    private LinearLayout shopping_nothing;
    private Button shopping_guang;
    private LinearLayout shopping_show;
    private CheckBox shopping_cb_all;
    private TextView shopping_allprice;
    private Button shopping_totle_bt;
    private QQListView shopping_cart_lv;
    private List<CartGoodsBean.CartItemList> cartItemList;
    private CartGoodAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_shopping,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        shopping_nothing = (LinearLayout) view.findViewById(R.id.shopping_nothing);
        shopping_guang = (Button) view.findViewById(R.id.shopping_guang);
        shopping_show = (LinearLayout) view.findViewById(R.id.shopping_show);
        shopping_cb_all = (CheckBox) view.findViewById(R.id.shopping_cb_all);
        shopping_allprice = (TextView) view.findViewById(R.id.shopping_allprice);
        shopping_cart_lv = (QQListView) view.findViewById(R.id.shopping_cart_lv);
        shopping_totle_bt = (Button) view.findViewById(R.id.shopping_totle_bt);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isLogin= LoginActivity.isLogin;
        if (!isLogin){
            Intent intent=new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
        }else{
            getServerData();
        }
    }

    private void getServerData() {
        String jsonUrl="http://169.254.116.62:8080/bullking1/cart?userID="+LoginActivity.id;
        MyAsyncTask task=new MyAsyncTask();
        task.execute(jsonUrl);

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
            Log.e("ssssssssss",json);
            CartGoodsBean cartGoodsBean = gson.fromJson(json, CartGoodsBean.class);
            cartItemList = cartGoodsBean.cartItemList;
            if (cartItemList !=null&& cartItemList.size()>0){
                shopping_nothing.setVisibility(View.GONE);
                shopping_show.setVisibility(View.VISIBLE);
            }else{
                shopping_nothing.setVisibility(View.VISIBLE);
                shopping_show.setVisibility(View.GONE);
            }
            shopping_guang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }
            });
            initCartLv();
            shopping_totle_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<CartGoodsBean.CartItemList> cartItemList1=new ArrayList<>();
                    for (int i = 0; i < cartItemList.size(); i++) {
                        if (cartItemList.get(i).ischecked){
                            cartItemList1.add(cartItemList.get(i));
                        }
                    }
                    if (cartItemList1.size()>0){
                        Intent intent=new Intent(getActivity(), OrderActivity.class);
                        intent.putExtra("list", (Serializable) cartItemList1);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void initCartLv() {
        adapter = new CartGoodAdapter();
        shopping_cart_lv.setAdapter(adapter);
        int num=0;
        for (int i = 0; i < cartItemList.size(); i++) {
            if (!cartItemList.get(i).ischecked){
                num++;
            }
        }
        if (num>0){
            shopping_cb_all.setChecked(false);
        }else{
            shopping_cb_all.setChecked(true);
        }
        shopping_cb_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox)v).isChecked()){
                    for (int i = 0; i < cartItemList.size(); i++) {
                        cartItemList.get(i).ischecked=true;
                    }
                }else{
                    for (int i = 0; i < cartItemList.size(); i++) {
                        cartItemList.get(i).ischecked=false;
                    }
                }
                addPrice();
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void addPrice() {
        double sum=0;
        for (int i = 0; i < cartItemList.size(); i++) {
            if (cartItemList.get(i).ischecked){
                sum+= cartItemList.get(i).price;
            }
        }
        String price=sum+"";
        String[] split = price.split("\\.");
        String substring = split[1].substring(0, 1);
        String totalPrice=split[0]+"."+substring;
        shopping_allprice.setText("总价：￥"+totalPrice);
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
            convertView=View.inflate(getActivity(), R.layout.cart_lv_item,null);
            final CheckBox cart_item_cb = (CheckBox) convertView.findViewById(R.id.cart_item_cb);
            ImageView cart_item_iv = (ImageView) convertView.findViewById(R.id.cart_item_iv);
            TextView cart_item_name = (TextView) convertView.findViewById(R.id.cart_item_name);
            TextView cart_item_price = (TextView) convertView.findViewById(R.id.cart_item_price);
            TextView cart_item_count = (TextView) convertView.findViewById(R.id.cart_item_count);
            TextView delete = (TextView) convertView.findViewById(R.id.delete);
            cart_item_cb.setChecked(cartItemList.get(position).ischecked);
            Glide.with(getActivity()).load(cartItemList.get(position).pic).into(cart_item_iv);
            cart_item_name.setText(cartItemList.get(position).name);
            cart_item_price.setText("￥"+cartItemList.get(position).price);
            cart_item_count.setText("数量："+cartItemList.get(position).count);
            cart_item_cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((CheckBox)v).isChecked()){
                        cartItemList.get(position).ischecked=true;
                    }else{
                        cartItemList.get(position).ischecked=false;
                    }
                    int num=0;
                    for (int i = 0; i < cartItemList.size(); i++) {
                        if (!cartItemList.get(i).ischecked){
                            num++;
                        }
                    }
                    if (num>0){
                        shopping_cb_all.setChecked(false);
                    }else{
                        shopping_cb_all.setChecked(true);
                    }
                    addPrice();
                    notifyDataSetChanged();
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    String url="http://169.254.116.62:8080/bullking1/deletepro?id="+cartItemList.get(position).id;
                    StringRequest request=new StringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Toast.makeText(getActivity(), "删除成功", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(getActivity(), "删除失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                    queue.add(request);
                    cartItemList.remove(position);
                    addPrice();
                    notifyDataSetChanged();
                    if (cartItemList.size()==0){
                        shopping_nothing.setVisibility(View.VISIBLE);
                        shopping_show.setVisibility(View.GONE);
                    }
                }
            });
            return convertView;
        }
    }
}
