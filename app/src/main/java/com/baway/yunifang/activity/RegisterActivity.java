package com.baway.yunifang.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baway.yunifang.R;
import com.baway.yunifang.utils.MyOkhttp;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView register_back;
    private EditText register_username;
    private EditText register_password;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去头
        if (getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_register);
        initView();
        register_back.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void initView() {
        register_back = (ImageView) findViewById(R.id.register_back);
        register_username = (EditText) findViewById(R.id.register_username);
        register_password = (EditText) findViewById(R.id.register_password);
        register = (Button) findViewById(R.id.register);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_back:
                finish();
                break;
            case R.id.register:
                String username=register_username.getText().toString();
                String password=register_password.getText().toString();
                if (!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
                    if (username.length()<8||username.length()>20){
                        Toast.makeText(RegisterActivity.this, "请输入8~20位英文或数字", Toast.LENGTH_SHORT).show();
                    }else{
                        if (password.length()<8){
                            Toast.makeText(RegisterActivity.this, "密码不少于8位", Toast.LENGTH_SHORT).show();
                        }else{
                            String url = "http://169.254.116.62:8080/bullking1/register?name=" + username + "&pwd=" + password;
                            MyAsyncTask task=new MyAsyncTask();
                            task.execute(url);
                        }
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
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
                if (dataStr.equals("register succeed")){
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
