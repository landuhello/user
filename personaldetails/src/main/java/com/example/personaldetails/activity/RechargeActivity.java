package com.example.personaldetails.activity;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.example.personaldetails.R;
import com.example.personaldetails.R2;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeActivity extends BaseActivity<RechargeModelImpl,RechargePresenterImpl> implements RechargeContract.RechargeView {

    @BindView(R2.id.user_title)
    BaseUserTitle userTitle;
    @BindView(R2.id.rmb_t2)
    EditText rmbT2;
    @BindView(R2.id.hb)
    TextView hb;
    @BindView(R2.id.xz1)
    RadioButton xz1;
    @BindView(R2.id.vx_bt)
    RelativeLayout vxBt;
    @BindView(R2.id.xz2)
    RadioButton xz2;
    @BindView(R2.id.zfb_bt)
    RelativeLayout zfbBt;
    private int i;

    @Override
    protected void iniData() {
        userTitle.tName.setText("充值");
        userTitle.tBack.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                finish();
            }
        });
        rmbT2.setSelection(rmbT2.getText().length());
        rmbT2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = rmbT2.getText().toString().trim();
                if (s1.equals("")) {
                    s1 = "0";
                }
                i = Integer.parseInt(s1);
                int numH = i * 100;
                hb.setText("" + numH);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected int bindlayout() {
        return R.layout.activity_recharge;
    }

    @Override
    public BasePresenter initPresenter() {
        return new RechargePresenterImpl();
    }


    @OnClick({R2.id.xz1, R2.id.xz2, R2.id.chongzhi})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.xz1) {
            if (xz1.isChecked()) {
                xz2.setChecked(false);
            }
        } else if (id == R.id.xz2) {
            if (xz2.isChecked()) {
                xz1.setChecked(false);
            }
        } else if (id == R.id.chongzhi) {
            int type = 0;
            if (xz1.isChecked()) {
                type = 1;
            } else if (xz2.isChecked()) {
                type = 2;
            }
            presenter.getMessage(i, type);
        }
    }

    @Override
    public void message(Object obj) {
        RechargeEntity rechargeEntity = (RechargeEntity) obj;
        if (rechargeEntity.getStatus().equals("0000")){
            ToastUtils.showShort(rechargeEntity.toString());

        }else {
            ToastUtils.showShort(rechargeEntity.getMessage());
        }
    }
}
