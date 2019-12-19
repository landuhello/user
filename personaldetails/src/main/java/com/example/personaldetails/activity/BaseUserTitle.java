package com.example.personaldetails.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.personaldetails.R;
import com.example.personaldetails.R2;


/*
 *@Auther:老刘
 *@Date: 时间
 *@Description:功能
 * */
public class BaseUserTitle extends RelativeLayout {

    public TextView tName;
    public ImageView tBack;
    public RelativeLayout back;
    public View view;

    public BaseUserTitle(Context context) {
        this(context,null);
    }

    public BaseUserTitle(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseUserTitle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View inflate = LayoutInflater.from(context).inflate(R.layout.base_title2, this, true);
        view = inflate.findViewById(R.id.user_title_view);
        back = inflate.findViewById(R.id.user_title_back);
        tBack = inflate.findViewById(R.id.t_back);
        tName = inflate.findViewById(R.id.t_name);
    }
}
