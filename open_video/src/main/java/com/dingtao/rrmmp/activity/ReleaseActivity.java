package com.dingtao.rrmmp.activity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dingtao.common.core.WDActivity;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.login.R2;
import com.facebook.drawee.view.SimpleDraweeView;
import com.suke.widget.SwitchButton;


import butterknife.BindView;
import butterknife.OnClick;

public class ReleaseActivity extends WDActivity {

    @BindView(R2.id.headportrait)
    ImageView headportrait;
    @BindView(R2.id.message)
    ImageView message;
    @BindView(R2.id.title)
    EditText title;
    @BindView(R2.id.administrative)
    EditText administrative;
    @BindView(R2.id.administrativelist)
    ImageView administrativelist;
    @BindView(R2.id.vitalsigns)
    EditText vitalsigns;
    @BindView(R2.id.listofconditions)
    ImageView listofconditions;
    @BindView(R2.id.conditionsfordetails)
    EditText conditionsfordetails;
    @BindView(R2.id.malady)
    EditText malady;
    @BindView(R2.id.hospital)
    EditText hospital;
    @BindView(R2.id.starttime)
    EditText starttime;
    @BindView(R2.id.time)
    ImageView time;
    @BindView(R2.id.endtime)
    EditText endtime;
    @BindView(R2.id.timeisover)
    ImageView timeisover;
    @BindView(R2.id.therapeutic)
    EditText therapeutic;
    @BindView(R2.id.relat1)
    RelativeLayout relat1;
    @BindView(R2.id.imageupload)
    SimpleDraweeView imageupload;
    @BindView(R2.id.relat2)
    RelativeLayout relat2;
    @BindView(R2.id.relat3)
    RelativeLayout relat3;
    @BindView(R2.id.publish)
    Button publish;
    @BindView(R2.id.switchbutton)
    SwitchButton switchbutton;
    @BindView(R2.id.promote_text_advice)
    TextView promoteTextAdvice;
    @BindView(R2.id.checkbox_10)
    CheckBox checkbox10;
    @BindView(R2.id.checkbox_20)
    CheckBox checkbox20;
    @BindView(R2.id.checkbox_50)
    CheckBox checkbox50;
    @BindView(R2.id.checkbox_custom)
    CheckBox checkboxCustom;
    @BindView(R2.id.text_unenough)
    TextView textUnenough;
    @BindView(R2.id.insufficient_balance_num)
    TextView insufficientBalanceNum;
    @BindView(R2.id.nuzu)
    TextView nuzu;
    @BindView(R2.id.insufficient_balance_rel)
    RelativeLayout insufficientBalanceRel;
    @BindView(R2.id.relat4)
    RelativeLayout relat4;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_release;
    }

    @Override
    protected void initView() {
        switchbuttonclick();

    }

    @Override
    protected void destoryData() {

    }

    @OnClick({R2.id.message, R2.id.administrativelist, R2.id.listofconditions, R2.id.time, R2.id.timeisover, R2.id.imageupload, R2.id.publish})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.message) {

        } else if (id == R.id.administrativelist) {

        } else if (id == R.id.listofconditions) {

        } else if (id == R.id.time) {

        } else if (id == R.id.timeisover) {

        } else if (id == R.id.imageupload) {

        }else if (id == R.id.publish) {
            //发表

        }
    }
    //创建点击自定义button开关按钮的方法
    public void switchbuttonclick(){
        switchbutton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked){
                    relat4.setVisibility(View.VISIBLE);
                    insufficientBalanceRel.setVisibility(View.VISIBLE);
                }else{
                    relat4.setVisibility(View.GONE);
                    insufficientBalanceRel.setVisibility(View.GONE);
                }
            }
        });
    }
    //价格

}
