package com.baway.yunifang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baway.yunifang.R;
import com.baway.yunifang.bean.GoodBean;

import java.util.List;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/19 20:12
 */
public class GoodLvAdapter extends BaseAdapter{
    private Context context;
    private List<GoodBean.Data.Activity> activity;

    public GoodLvAdapter(Context context, List<GoodBean.Data.Activity> activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return activity.size();
    }

    @Override
    public Object getItem(int position) {
        return activity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.good_lv_item,null);
        TextView good_lv_item_title = (TextView) convertView.findViewById(R.id.good_lv_item_title);
        TextView good_lv_item_description = (TextView) convertView.findViewById(R.id.good_lv_item_description);
        good_lv_item_title.setText(activity.get(position).title);
        good_lv_item_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
}
