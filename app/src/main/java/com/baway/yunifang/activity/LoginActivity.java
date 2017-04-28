package com.baway.yunifang.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baway.yunifang.R;
import com.baway.yunifang.utils.MyOkhttp;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView login_back;
    private TextView login_register;
    private EditText login_username;
    private EditText login_password;
    private Button login;
    public static boolean isLogin=false;
    public static int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_login);
        initView();
        login_back.setOnClickListener(this);
        login_register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    private void initView() {
        login_back = (ImageView) findViewById(R.id.login_back);
        login_register = (TextView) findViewById(R.id.login_register);
        login_username = (EditText) findViewById(R.id.login_username);
        login_password = (EditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.login);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_back:
                finish();
                break;
            case R.id.login_register:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login:
                String username=login_username.getText().toString();
                String password=login_password.getText().toString();
                if (!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
                    String url = "http://169.254.116.62:8080/bullking1/login?name=" + username + "&&pwd=" + password;
                    MyAsyncTask task=new MyAsyncTask();
                    task.execute(url);
                }else{
                    Toast.makeText(LoginActivity.this, "用户名密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
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
            try {
                String dataStr = new JSONObject(json).getString("dataStr");
                if (dataStr.equals("login succeed")){
                    isLogin=true;
                    id=new JSONObject(json).getInt("id");
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "账号不存在,请先注册", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
