package com.baway.yunifang.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baway.yunifang.R;
import com.baway.yunifang.activity.LoginActivity;
import com.baway.yunifang.adapter.UserListAdapter;
import com.bumptech.glide.Glide;

/**
 * @author 任珏
 * @类的用途
 * @date 2017/4/13 19:28
 */
public class UserFragment extends Fragment {

    private ImageView user_head_icon;
    private ImageView user_setting;
    private Button user_login;
    private ListView user_listView;
    private int[] images = new int[]{R.mipmap.my_order_icon, R.mipmap.my_invite_gift_icon,
            R.mipmap.my_face_test_icon, R.mipmap.exchange_area_icon,
            R.mipmap.my_coupon_icon, R.mipmap.my_lottery_icon,
            R.mipmap.my_invite_gift_icon, R.mipmap.personal_center_contact_service_icon};
    private String[] titles = new String[]{"我的订单", "邀请有礼", "刷脸测尺寸", "兑换专区",
            "我的现金券", "我的抽奖单", "我收藏的商品", "联系客服"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        user_head_icon = (ImageView) view.findViewById(R.id.user_head_icon);
        user_setting = (ImageView) view.findViewById(R.id.user_setting);
        user_login = (Button) view.findViewById(R.id.user_login);
        user_listView = (ListView) view.findViewById(R.id.user_listView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        user_listView.setAdapter(new UserListAdapter(getActivity(), images, titles));
        boolean isLogin = LoginActivity.isLogin;
        if (isLogin) {
            Glide.with(getActivity()).load(R.mipmap.ic_launcher).into(user_head_icon);
            user_login.setVisibility(View.GONE);
            user_setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("是否确认退出登录？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            LoginActivity.isLogin = false;
                            Glide.with(getActivity()).load(R.mipmap.user_icon_no_sets).into(user_head_icon);
                            user_login.setVisibility(View.VISIBLE);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            });
        }
        user_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
