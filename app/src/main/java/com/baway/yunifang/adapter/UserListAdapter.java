package com.baway.yunifang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.yunifang.R;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/18 13:14
 */
public class UserListAdapter extends BaseAdapter{
    private Context context;
    private int[] images;
    private String[] titles;

    public UserListAdapter(Context context, int[] images, String[] titles) {
        this.images = images;
        this.context = context;
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.user_lv_item,null);
        ImageView user_item_icon = (ImageView) convertView.findViewById(R.id.user_item_icon);
        TextView user_item_title = (TextView) convertView.findViewById(R.id.user_item_title);
        user_item_icon.setImageResource(images[position]);
        user_item_title.setText(titles[position]);
        return convertView;
    }
}
