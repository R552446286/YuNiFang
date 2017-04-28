package com.baway.yunifang.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baway.yunifang.R;
import com.baway.yunifang.adapter.HomeAdapter;
import com.baway.yunifang.bean.DataBean;
import com.baway.yunifang.utils.MyOkhttp;
import com.baway.yunifang.view.MyDecoration;
import com.google.gson.Gson;
import com.liaoinstan.springview.widget.SpringView;

import java.net.URL;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/13 19:28
 */
public class HomeFragment extends Fragment{

    private SpringView springView;
    private RecyclerView home_recycler;
    private String jsonUrl="http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        springView = (SpringView) view.findViewById(R.id.springView);
        home_recycler = (RecyclerView) view.findViewById(R.id.home_recycler);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getServerData();
    }

    private void getServerData() {
        MyAsyncTask task=new MyAsyncTask();
        task.execute(jsonUrl);
    }
    class MyAsyncTask extends AsyncTask<String,Integer,String>{
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
            DataBean dataBean = gson.fromJson(json, DataBean.class);
            DataBean.Data data = dataBean.data;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            home_recycler.setLayoutManager(linearLayoutManager);
            MyDecoration myDecoration = new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST);
            home_recycler.addItemDecoration(myDecoration);
            home_recycler.setAdapter(new HomeAdapter(getActivity(),data));
        }
    }
}
