package com.example.open_login;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class RegisterActivity extends AppCompatActivity {


    private ImageView logo;
    private CardView c;
    private ImageView email_icon;
    private EditText email;
    private TextView email_bt;
    private ImageView code_icon;
    private EditText code;
    private ImageView lock;
    private EditText password;
    private ImageView eye;
    private ImageView lock2;
    private EditText password2;
    private ImageView eye2;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();

    }

    private void initView() {
        logo = (ImageView) findViewById(R.id.logo);
        c = (CardView) findViewById(R.id.c);
        email_icon = (ImageView) findViewById(R.id.email_icon);
        email = (EditText) findViewById(R.id.email);
        email_bt = (TextView) findViewById(R.id.email_bt);
        code_icon = (ImageView) findViewById(R.id.code_icon);
        code = (EditText) findViewById(R.id.code);
        lock = (ImageView) findViewById(R.id.lock);
        password = (EditText) findViewById(R.id.password);
        eye = (ImageView) findViewById(R.id.eye);
        lock2 = (ImageView) findViewById(R.id.lock2);
        password2 = (EditText) findViewById(R.id.password2);
        eye2 = (ImageView) findViewById(R.id.eye2);
        register = (TextView) findViewById(R.id.register);
    }

    private void submit() {
        // validate
        String emailString = email.getText().toString().trim();
        if (TextUtils.isEmpty(emailString)) {
            Toast.makeText(this, "请输入邮箱", Toast.LENGTH_SHORT).show();
            return;
        }

        String codeString = code.getText().toString().trim();
        if (TextUtils.isEmpty(codeString)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordString = password.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "密码0-16位", Toast.LENGTH_SHORT).show();
            return;
        }

        String password2String = password2.getText().toString().trim();
        if (TextUtils.isEmpty(password2String)) {
            Toast.makeText(this, "请再次输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
