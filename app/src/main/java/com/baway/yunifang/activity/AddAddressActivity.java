package com.baway.yunifang.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.baway.yunifang.R;
import com.baway.yunifang.bean.Address;

public class AddAddressActivity extends AppCompatActivity {

    private EditText add_name;
    private EditText add_phone;
    private EditText add_address;
    private Button bt_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_add_address);
        initView();
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Address address = new Address(add_name.getText().toString(),add_phone.getText().toString(),add_address.getText().toString());
                Intent intent=new Intent(AddAddressActivity.this,AddressManagerActivity.class);
                intent.putExtra("address",address);
                setResult(100,intent);
                finish();
            }
        });
    }

    private void initView() {
        add_name = (EditText) findViewById(R.id.add_name);
        add_phone = (EditText) findViewById(R.id.add_phone);
        add_address = (EditText) findViewById(R.id.add_address);
        bt_save = (Button) findViewById(R.id.bt_save);
    }
}
