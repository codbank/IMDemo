package com.example.blksupm.im.login;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blksupm.im.R;
import com.example.blksupm.im.register.RegisterActivity;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LoginActivity extends Activity {

    @Bind(R.id.username)
    TextView userName;

    @Bind(R.id.password)
    TextView password;

    @Bind(R.id.register)
    Button register;

    @Bind(R.id.login)
    Button login;

    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //绑定注解
        ButterKnife.bind(this);
        //初始化短信sdk
        SMSSDK.initSDK(this, "190953adb1fee", "9a79e7872b1f138f44ea5658122b5790");
    }

    @OnClick(R.id.register)
    public void register() {
        // 打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

                    phoneNumber = phone;

                    Log.e("PhoneNumber", phone);
                    // 提交用户信息（此方法可以不调用）
                    // registerUser(country, phone);

                }
            }
        });
        registerPage.show(this);

        Intent intent = new Intent(this, LoginActivity.class);
        //注册成功以后跳转到我的页面MyActivity，并且在MyActivity显示注册的手机号码
        intent.putExtra("phone", phoneNumber);
        startActivity(intent);
    }

    @OnClick(R.id.login)
    public void onLoginClick() {
        Toast.makeText(LoginActivity.this,"aaaa",Toast.LENGTH_LONG).show();
    }
}
