package com.example.open_login;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class LoginMainActivity extends AppCompatActivity {

    private ImageView logo;
    private CardView c;
    private EditText email;
    private ImageView lock;
    private EditText password;
    private ImageView eye;
    private TextView login;
    private TextView mes;
    private TextView reset_password;
    private TextView register;
    private ImageView weixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        initView();
    }

    private void initView() {
        logo = (ImageView) findViewById(R.id.logo);
        c = (CardView) findViewById(R.id.c);
        email = (EditText) findViewById(R.id.email);
        lock = (ImageView) findViewById(R.id.lock);
        password = (EditText) findViewById(R.id.password);
        eye = (ImageView) findViewById(R.id.eye);
        login = (TextView) findViewById(R.id.login);
        mes = (TextView) findViewById(R.id.mes);
        reset_password = (TextView) findViewById(R.id.reset_password);
        register = (TextView) findViewById(R.id.register);
        weixin = (ImageView) findViewById(R.id.weixin);
    }

    private void submit() {
        // validate
        String emailString = email.getText().toString().trim();
        if (TextUtils.isEmpty(emailString)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordString = password.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
