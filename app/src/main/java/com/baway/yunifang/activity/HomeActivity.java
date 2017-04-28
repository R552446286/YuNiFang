package com.baway.yunifang.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.baway.yunifang.R;
import com.baway.yunifang.fragment.ClassifyFragment;
import com.baway.yunifang.fragment.HomeFragment;
import com.baway.yunifang.fragment.ShoppingFragment;
import com.baway.yunifang.fragment.UserFragment;

public class HomeActivity extends AppCompatActivity {

    private RadioGroup rg;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_home);
        //找控件
        initView();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameLayout,new HomeFragment());
        transaction.commit();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        replaceFragment(new HomeFragment());
                        break;
                    case R.id.rb_classify:
                        replaceFragment(new ClassifyFragment());
                        break;
                    case R.id.rb_shopping:
                        replaceFragment(new ShoppingFragment());
                        break;
                    case R.id.rb_user:
                        replaceFragment(new UserFragment());
                        break;
                }
            }
        });
    }

    private void initView() {
        rg = (RadioGroup) findViewById(R.id.rg);
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out);
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();
    }
}
