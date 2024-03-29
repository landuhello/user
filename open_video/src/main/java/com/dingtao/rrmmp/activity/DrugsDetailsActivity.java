package com.dingtao.rrmmp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dingtao.common.bean.DrugsKnowBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.main.presenter.DrugsKnowPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import androidx.appcompat.app.AppCompatActivity;

public class DrugsDetailsActivity extends AppCompatActivity {

    private SimpleDraweeView home_simple;
    private ImageView home_message_img;
    private TextView drugs_name;
    private TextView drugs_chengfen;
    private TextView drugs_jinji;
    private TextView drugs_zhuzhi;
    private TextView drugs_yongfa;
    private TextView drugs_xingzhuang;
    private TextView drugs_baozhuang;
    private TextView drugs_fanying;
    private TextView drugs_chucang;
    private TextView drugs_shixiang;
    private TextView drugs_wenhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drugs_details);
        initView();

        Intent intent = getIntent();
        String idsss = intent.getStringExtra("idsss");

        DrugsKnowPresenter drugsKnowPresenter=new DrugsKnowPresenter(new DrugsKnowP());
        drugsKnowPresenter.reqeust(Integer.valueOf(idsss));

    }

    private void initView() {
        home_simple = (SimpleDraweeView) findViewById(R.id.home_simple);
        home_message_img = (ImageView) findViewById(R.id.home_message_img);
        drugs_name = (TextView) findViewById(R.id.drugs_name);
        drugs_chengfen = (TextView) findViewById(R.id.drugs_chengfen);
        drugs_jinji = (TextView) findViewById(R.id.drugs_jinji);
        drugs_zhuzhi = (TextView) findViewById(R.id.drugs_zhuzhi);
        drugs_yongfa = (TextView) findViewById(R.id.drugs_yongfa);
        drugs_xingzhuang = (TextView) findViewById(R.id.drugs_xingzhuang);
        drugs_baozhuang = (TextView) findViewById(R.id.drugs_baozhuang);
        drugs_fanying = (TextView) findViewById(R.id.drugs_fanying);
        drugs_chucang = (TextView) findViewById(R.id.drugs_chucang);
        drugs_shixiang = (TextView) findViewById(R.id.drugs_shixiang);
        drugs_wenhao = (TextView) findViewById(R.id.drugs_wenhao);
    }

    private class DrugsKnowP implements DataCall<DrugsKnowBean> {
        @Override
        public void success(DrugsKnowBean data, Object... args) {

            /**
             * approvalNumber :  国药准字Z11020452
             * component :  人工牛黄、雄黄、石膏、大黄、黄芩、桔梗、冰片、甘草。
             * createTime : 1547709514000
             * drugsCategoryId : 1
             * effect :  清热解毒。用于火热内盛，咽喉肿痛，牙龈肿痛，口舌生疮，目赤肿痛。
             * id : 1
             * mindMatter :  本品不宜久服。
             * name :  [同仁堂]牛黄解毒片(薄膜衣片)
             * packing :  10片x12板
             * picture : https://imgq.ddky.com/c/product/328654/big/z_1.jpg?t=9898&watermark%2F1%2Fimage%2FaHR0cHM6Ly9pbWdxLmRka3kuY29tL2Mvd2F0ZXJQaWMvMTA4MC5wbmc%3D%2Fdissolve%2F80%2Fgravity%2FCenter%2Fdx%2F0%2Fdy%2F0%7CimageMogr2%2Fauto-orient%2Fthumbnail%2F240x240%21%2Fquality%2F100
             * shape :  本品为薄膜衣片，除去包衣后显棕黄色；有冰片香气，味微苦、辛。
             * sideEffects :  尚不明确。
             * storage :  密封。
             * taboo :  孕妇禁用。
             * usage :  口服。一次3片，一日2次～3次。
             */

            if (data.name==null){
                drugs_name.setText("无");
            }else {
                drugs_name.setText(data.name);
            }
            if (data.component==null){
                drugs_chengfen.setText("无");
            }else {
                drugs_chengfen.setText(data.component);
            }

            if (data.taboo==null){
                drugs_jinji.setText("无");
            }else {
                drugs_jinji.setText(data.taboo);
            }

            if (data.effect==null){
                drugs_zhuzhi.setText("无");
            }else {
                drugs_zhuzhi.setText(data.effect);
            }

            if (data.usage==null){
                drugs_yongfa.setText("无");
            }else {
                drugs_yongfa.setText(data.usage);
            }

            if (data.shape==null){
                drugs_xingzhuang.setText("无");
            }else {
                drugs_xingzhuang.setText(data.shape);
            }

            if (data.packing==null){
                drugs_baozhuang.setText("无");
            }else {
                drugs_baozhuang.setText(data.packing);
            }

            if (data.effect==null){
                drugs_fanying.setText("无");
            }else {
                drugs_fanying.setText(data.effect);
            }

            if (data.storage==null){
                drugs_chucang.setText("无");
            }else {
                drugs_chucang.setText(data.storage);
            }

            if (data.mindMatter==null){
                drugs_shixiang.setText("无");
            }else {
                drugs_shixiang.setText(data.mindMatter);
            }

            if (data.approvalNumber==null){
                drugs_wenhao.setText("无");
            }else {
                drugs_wenhao.setText(data.approvalNumber);
            }
        }

        @Override
        public void fail(ApiException data, Object... args) {
            Toast.makeText(DrugsDetailsActivity.this, data.getDisplayMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
