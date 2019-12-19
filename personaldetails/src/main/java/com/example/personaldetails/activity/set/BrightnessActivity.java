package com.example.personaldetails.activity.set;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.blankj.utilcode.util.BrightnessUtils;
import com.example.personaldetails.R;
import com.example.personaldetails.R2;
import com.example.personaldetails.activity.BaseActivity;
import com.example.personaldetails.activity.BasePresenter;
import com.example.personaldetails.activity.BaseUserTitle;
import com.google.android.exoplayer2.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrightnessActivity extends BaseActivity {


    @BindView(R2.id.brightness_title)
    BaseUserTitle brightnessTitle;
    @BindView(R2.id.brightnessleft)
    ImageView brightnessleft;
    @BindView(R2.id.brightnessleft_seek)
    SeekBar brightnessleftSeek;
    @BindView(R2.id.brightnessright)
    ImageView brightnessright;

    @Override
    protected void iniData() {
        requestWriteSettings();
        brightnessTitle.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        brightnessTitle.tName.setText("屏幕亮度");
        //设置是否开启自动调节亮度
        BrightnessUtils.setAutoBrightnessEnabled(false);
        //获取屏幕亮度
        int brightness = BrightnessUtils.getBrightness();

        //设置当前亮度
        brightnessleftSeek.setProgress((int) (brightness / 2.55));

        brightnessleftSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BrightnessUtils.setAutoBrightnessEnabled(false);
                int v = (int) (progress * 2.55);
                //设置屏幕亮度
                BrightnessUtils.setBrightness(v);
                int brightness = BrightnessUtils.getBrightness();
                Log.d("BrightnessActivity", "brightness:" + brightness);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected int bindlayout() {
        return R.layout.activity_brightness;
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    /**
     * 申请权限
     */
    private void requestWriteSettings() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //大于等于23 请求权限
            if (!Settings.System.canWrite(getApplicationContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1);
            }
        } else {
            //小于23直接设置
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //Settings.System.canWrite方法检测授权结果
                if (Settings.System.canWrite(getApplicationContext())) {
                    Log.d("BrightnessActivity", "获取了权限");
                } else {
                    Log.d("BrightnessActivity", "您拒绝了权限");
                }
            }
        }

    }


}
