package com.baway.yunifang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.baway.yunifang.R;
import com.baway.yunifang.bean.Address;
import com.baway.yunifang.view.QQListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressManagerActivity extends AppCompatActivity {

    private QQListView address_lv;
    private Button add_address;
    private List<Address> list=new ArrayList<>();
    private AddressAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_address_manager);
        initView();
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddressManagerActivity.this,AddAddressActivity.class);
                startActivityForResult(intent,1);
            }
        });
        adapter = new AddressAdapter();
        address_lv.setAdapter(adapter);
        address_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(AddressManagerActivity.this,OrderActivity.class);
                intent.putExtra("address",list.get(position));
                setResult(100,intent);
                finish();
            }
        });
    }

    private void initView() {
        address_lv = (QQListView) findViewById(R.id.address_lv);
        add_address = (Button) findViewById(R.id.add_address);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==100){
            Address address = (Address) data.getSerializableExtra("address");
            list.add(address);
            adapter.notifyDataSetChanged();
        }
    }
    class AddressAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=View.inflate(AddressManagerActivity.this,R.layout.address_lv_item,null);
            TextView address_name = (TextView) convertView.findViewById(R.id.address_name);
            TextView address_phone = (TextView) convertView.findViewById(R.id.address_phone);
            TextView address_home = (TextView) convertView.findViewById(R.id.address_home);
            TextView address_delete = (TextView) convertView.findViewById(R.id.address_delete);
            address_name.setText("收货人："+list.get(position).name);
            address_phone.setText(list.get(position).phone);
            address_home.setText("详细地址："+list.get(position).home);
            address_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }
}
