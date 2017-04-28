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
import com.baway.yunifang.adapter.ClassifyAdapter;
import com.baway.yunifang.bean.ClassifyBean;
import com.baway.yunifang.utils.MyOkhttp;
import com.baway.yunifang.view.MyDecoration;
import com.google.gson.Gson;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/13 19:28
 */
public class ClassifyFragment extends Fragment{

    private RecyclerView classify_recycler;
    private String jsonUrl="http://m.yunifang.com/yunifang/mobile/category/list?random=96333&encode=bf3386e14fe5bb0bcef234baebca2414";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_classify,null);
        classify_recycler = (RecyclerView) view.findViewById(R.id.classify_recycler);
        return view;
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
            ClassifyBean classifyBean = gson.fromJson(json, ClassifyBean.class);
            ClassifyBean.Data data = classifyBean.data;
            LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
            classify_recycler.setLayoutManager(layoutManager);
            MyDecoration myDecoration = new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST);
            classify_recycler.addItemDecoration(myDecoration);
            classify_recycler.setAdapter(new ClassifyAdapter(getActivity(),data));
        }
    }
}
