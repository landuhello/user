package com.dingtao.rrmmp.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dingtao.common.bean.Hunt;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDActivity;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.rrmmp.adapter.HuntAdapter;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.login.R2;
import com.dingtao.rrmmp.presenter.HuntPresenter;

import java.util.List;

import butterknife.BindView;

public class SeekActivity extends WDActivity {

    @BindView(R2.id.quit)
    ImageView quit;
    @BindView(R2.id.edit_sosuo)
    EditText editSosuo;
    @BindView(R2.id.thesearchbutton)
    Button thesearchbutton;
    @BindView(R2.id.recycler3)
    RecyclerView recycler3;
    private HuntPresenter huntPresenter;
    private LinearLayoutManager linearLayoutManager;
    private HuntAdapter huntAdapter;
    private String ss;
    private int sickCircleId;
    private String title;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_seek;

    }

    @Override
    protected void initView() {
          //创建p层
        huntPresenter = new HuntPresenter(new HuntCall());
        //创建管理器
        linearLayoutManager = new LinearLayoutManager(SeekActivity.this);
        recycler3.setLayoutManager(linearLayoutManager);
        //创建适配器
        huntAdapter = new HuntAdapter(SeekActivity.this);
        recycler3.setAdapter(huntAdapter);
        //点击退出
          quit.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  finish();
              }
          });
          //点击
        thesearchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的值
                ss = editSosuo.getText().toString();
                //请求
                huntPresenter.reqeust(ss);
            }
        });
    }

    @Override
    protected void destoryData() {

    }
    //创建获取搜索数据的类
    class HuntCall implements DataCall<List<Hunt>> {
        @Override
        public void success(final List<Hunt> data, Object... args) {
              huntAdapter.clear();
              huntAdapter.addAll(data);
              huntAdapter.notifyDataSetChanged();
              //点击
              huntAdapter.setOnItemClickListener(new HuntAdapter.OnItemClickListener() {
                  @Override
                  public void onClick(View view, int position) {
                      //传值
                      for (int i = 0; i <data.size() ; i++) {
                          title = data.get(position).title;
                          sickCircleId = data.get(position).sickCircleId;
                      }
                      //跳转
                      Intent intent = new Intent(SeekActivity.this,ConditionsActivity.class);
                      intent.putExtra("title",title);
                      intent.putExtra("sick",sickCircleId);
                      startActivity(intent);
                  }
              });
        }
        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
