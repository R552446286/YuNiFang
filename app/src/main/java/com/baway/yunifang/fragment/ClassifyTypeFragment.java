package com.baway.yunifang.fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.yunifang.R;
import com.baway.yunifang.activity.GoodsContentActivity;
import com.baway.yunifang.adapter.HomeAdapter;
import com.baway.yunifang.bean.DataBean;
import com.baway.yunifang.bean.TypeBean;
import com.baway.yunifang.utils.MyOkhttp;
import com.baway.yunifang.view.MyDecoration;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/21 15:14
 */
public class ClassifyTypeFragment extends Fragment{

    private GridView classifytype_gridView;
    private String jsonUrl;
    private List<TypeBean.Data> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.classify_type_fragment,null);
        classifytype_gridView = (GridView) view.findViewById(R.id.classifytype_gridView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        jsonUrl = "http://m.yunifang.com/yunifang/mobile/goods/getall?random=91873&encode=68301f6ea0d6adcc0fee63bd21815d69&category_id="+id;
        getServerData();
    }

    private void getServerData() {
        MyAsyncTask task=new MyAsyncTask();
        task.execute(jsonUrl);
    }

    public static Fragment getInstance(String id){
        ClassifyTypeFragment fragment=new ClassifyTypeFragment();
        Bundle bundle=new Bundle();
        bundle.putString("id",id);
        fragment.setArguments(bundle);
        return fragment;
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
            classifytype_gridView.setAdapter(new ClassifyTypeGvAdapter());
            classifytype_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent(getActivity(), GoodsContentActivity.class);
                    intent.putExtra("id",data.get(position).id);
                    startActivity(intent);
                }
            });
        }
    }
    class ClassifyTypeGvAdapter extends BaseAdapter{

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
                convertView=View.inflate(getActivity(), R.layout.item5_recycler_item1,null);
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
            Glide.with(getActivity()).load(data.get(position).goods_img).into((v.item5_iv));
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
