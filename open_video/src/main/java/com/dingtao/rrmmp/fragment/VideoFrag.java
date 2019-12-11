package com.dingtao.rrmmp.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.SPUtils;
import com.dingtao.common.bean.LoginBean;
import com.dingtao.common.bean.Result;
import com.dingtao.common.bean.video.BulletBean;
import com.dingtao.common.bean.video.ResultBean;
import com.dingtao.common.bean.video.VideocatBean;
import com.dingtao.common.bean.video.VideovolBean;
import com.dingtao.common.core.DataCall;
import com.dingtao.common.core.WDFragment;
import com.dingtao.common.core.exception.ApiException;
import com.dingtao.common.util.Constant;
import com.dingtao.rrmmp.activity.VideoBuyXPop;
import com.dingtao.rrmmp.activity.VideoReview_Xpop;
import com.dingtao.rrmmp.adapter.VideoAdpter;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.login.R2;
import com.dingtao.rrmmp.presenter.VideoBuyPresenter;
import com.dingtao.rrmmp.presenter.VideobulletPresenter;
import com.dingtao.rrmmp.presenter.VideocatPresenter;
import com.dingtao.rrmmp.presenter.VideodisplayPresenter;
import com.dingtao.rrmmp.presenter.WalletPresenter;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kd.easybarrage.Barrage;
import com.kd.easybarrage.BarrageView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.SimpleCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */

public class VideoFrag extends WDFragment {


    @BindView(R2.id.video_xrecy)
    XRecyclerView videoXrecy;
    @BindView(R2.id.video_danmu)
    BarrageView videoDanmu;
    @BindView(R2.id.video_tab)
    TabLayout videoTab;
    @BindView(R2.id.video_user_head)
    ImageView videoUserHead;
    @BindView(R2.id.video_user_message)
    ImageView videoUserMessage;
    @BindView(R2.id.video_cc)
    RelativeLayout videoCc;
    @BindView(R2.id.video_light)
    ImageView videoLight;
    @BindView(R2.id.show_video)
    RelativeLayout showVideo;
    @BindView(R2.id.plache)
    ImageView plache;
    @BindView(R2.id.reload_video)
    TextView reloadVideo;
    @BindView(R2.id.show_video_no_net)
    RelativeLayout showVideoNoNet;

    List<VideocatBean> data = new ArrayList<>();
    List<VideovolBean> resultBeans=new ArrayList<>();
    private int page = 1;
    private int categorid = 1;
    private VideocatPresenter mVideocatPresenter;
    private ImageView mAdvisory;
    private LinearLayoutManager linearLayoutManager;
    private VideoAdpter videoAdpter;
    private VideodisplayPresenter mVideodisplayPresenter;
    private VideobulletPresenter mVideobulletPresenter;
    private RadioGroup mRadioGroup;
    private ImageView advisory;
    private Boolean islight = false;
    private int videoID;
    private String mSessionId;
    private int mId;
    private int mvideoId;
    private int mPrice;
    private int mResult;
    private WalletPresenter mWalletPresenter;
    private VideoBuyPresenter mVideoBuyPresenter;
    private int mvideoprice;
    private int i;
    private VideoReview_Xpop mVideoReview_xpop;

    @Override
    public String getPageName() {
        return "视频Fragment";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.videolook;

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND, sticky = true)
        public void dologin(LoginBean bean) {
            mSessionId = bean.sessionId;
            mId = bean.id;
    }
    @Subscribe
    public void eventbuy(Integer[] strings) {
//        videoID = strings[0];//id
//        mvideoprice = strings[1];//价格
//        i = strings[2];//I
//        //如果已经购买去评论
//        if (resultBeans.get(i).getWhetherBuy() == 1) {
//            mVideoReview_xpop = new VideoReview_Xpop(getContext());
//            new XPopup.Builder(getContext())
//                    .setPopupCallback(new SimpleCallback() {  //监听弹窗create完毕
//                        @Override
//                        public void onCreated() {
//                            EditText videocoment = mVideoReview_xpop.findViewById(R.id.video_comment_edt);
//                            RelativeLayout review_xpopViewsend = mVideoReview_xpop.findViewById(R.id.video_comment_send);
//                            review_xpopViewsend.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    // danmuview.destroy();
//                                    //添加到接口
//                                    //发弹幕的请求
//                                    presenter.requestReleaseBarrage(videoID + "", "" + videocoment.getText().toString());
//                                    danmuview.addBarrage(new Barrage(videocoment.getText().toString(), R.color.write, true));
//                                    mVideoReview_xpop.dismiss();
//                                    //请求弹幕评论列表
//                                    // presenter.requestBarrageList(videoID + "");
//                                }
//                            });
//                        }
//                    })
//                    .autoOpenSoftInput(true)
//                    .hasStatusBarShadow(false) //
//                    .asCustom(mVideoReview_xpop)
//                    .show();
//        } else {
//            //请求钱包
//            presenter.requestUserwallet();
//        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initView() {
        //视频类目id
        mVideocatPresenter = new VideocatPresenter(new Videocat());
        mVideocatPresenter.reqeust();
        //视频资源
        mVideodisplayPresenter = new VideodisplayPresenter(new display());
        mVideodisplayPresenter.reqeust(1, 1, 5);
        //查看视频弹幕
        mVideobulletPresenter = new VideobulletPresenter(new buttlt());
        mVideobulletPresenter.reqeust(1);
        //购买视频请求
        mVideoBuyPresenter = new VideoBuyPresenter(new Buy());

        //查询H币是否有钱
        mWalletPresenter = new WalletPresenter(new Hmoney());
        mWalletPresenter.reqeust(mId,mSessionId);
        //发送弹幕

        initdata();
        initlast();

    }


    private void initlast() {
        //setVisibility方法设置是否显示组件
        videoTab.setVisibility(View.GONE);
        ObjectAnimator.ofFloat(videoLight, "translationY", 0, -50).start();
        //父容器的
        mRadioGroup = getActivity().findViewById(R.id.rg);
        //父容器的病友圈
        advisory = getActivity().findViewById(R.id.advisory);
        RadioButton rb3 = getActivity().findViewById(R.id.rb3);
        //设置隐藏显示
        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //判断是否第一次进入
                    boolean onevideo = SPUtils.getInstance().getBoolean("onevideo");
                    if (!onevideo) {
                        isone();
                        SPUtils.getInstance().put("onevideo", true);
                    }
                    if (islight) {
                        return;
                    }
                    videoTab.setVisibility(View.GONE);
                    mRadioGroup.setVisibility(View.GONE);
                    advisory.setVisibility(View.GONE);
                    //点击显示
                    videoLight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            islight = !islight;
                            //显示出来
                            if (islight) {
                                videoTab.setVisibility(View.VISIBLE);
                                mRadioGroup.setVisibility(View.VISIBLE);
                                advisory.setVisibility(View.VISIBLE);
                                ObjectAnimator animator = ObjectAnimator.ofFloat(videoLight, "translationY", 0, -50);
                                ObjectAnimator show = ObjectAnimator.ofFloat(videoLight, "translationY", 0, 10);
                                AnimatorSet animatorSet = new AnimatorSet();
                                /**
                                 * AnimatorSet正是通过以下方法来控制动画播放顺序：
                                 * after（Animator anim）：将现有动画插入到传入的动画之后执行。
                                 * before（Animator anim）：将现有动画插入到传入的动画之前执行。
                                 * with（Animator anim）：将现有动画和传入的动画同时执行。
                                 */
                                //设置时长
                                animator.setDuration(0);
                                show.setDuration(1000);
                                animatorSet.play(animator);
                                animatorSet.play(show);
                                animatorSet.start();
                            } else {
                                videoTab.setVisibility(View.GONE);
                                mRadioGroup.setVisibility(View.GONE);
                                advisory.setVisibility(View.GONE);
                                ObjectAnimator.ofFloat(videoLight, "translationY", 0, -50).setDuration(1000).start();
                            }
                        }
                    });
                } else {
                    //点击显示
                    videoLight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            islight = !islight;
                            //显示出来
                            if (islight) {
                                videoTab.setVisibility(View.VISIBLE);
                                mRadioGroup.setVisibility(View.VISIBLE);
                                advisory.setVisibility(View.VISIBLE);
                                ObjectAnimator animator = ObjectAnimator.ofFloat(videoLight, "translationY", 0, -50);
                                ObjectAnimator show = ObjectAnimator.ofFloat(videoLight, "translationY", 0, 10);
                                AnimatorSet animatorSet = new AnimatorSet();
                                /**
                                 * AnimatorSet正是通过以下方法来控制动画播放顺序：
                                 * after（Animator anim）：将现有动画插入到传入的动画之后执行。
                                 * before（Animator anim）：将现有动画插入到传入的动画之前执行。
                                 * with（Animator anim）：将现有动画和传入的动画同时执行。
                                 */
                                animator.setDuration(0);
                                show.setDuration(1000);
                                animatorSet.play(animator);
                                animatorSet.play(show);
                                animatorSet.start();
                            } else {
                                videoTab.setVisibility(View.GONE);
                                mRadioGroup.setVisibility(View.GONE);
                                advisory.setVisibility(View.GONE);
                                ObjectAnimator.ofFloat(videoLight, "translationY", 0, -50).setDuration(1000).start();
                            }
                        }
                    });
                    mRadioGroup.setVisibility(View.VISIBLE);
                    advisory.setVisibility(View.VISIBLE);
                }
            }

            private void isone() {
                new XPopup.Builder(getContext())
                        .asCustom(new VideoOneShow(getContext()))
                        .show();
            }
        });
    }

    private void initdata() {
        //视频的瑞色口view
        linearLayoutManager = new LinearLayoutManager(getActivity());
        videoXrecy.setLayoutManager(linearLayoutManager);
        videoTab.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                categorid = Integer.parseInt(tab.getTag().toString());
                page = 1;
                data.clear();
                mVideocatPresenter.reqeust(tab.getTag().toString(), page + "", "5");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    //视频类目id、name
    private class Videocat implements DataCall<List<VideocatBean>> {

        @Override
        public void success(List<VideocatBean> data, Object... args) {

            for (int i = 0; i < data.size(); i++) {
                videoTab.addTab(videoTab.newTab().setTag(data.get(i).id).setText(data.get(i).name));
            }

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    //视频播放
    private class display implements DataCall<List<VideovolBean>> {


        private List<VideovolBean> mData1;

        @Override
        public void success(List<VideovolBean> data, Object... args) {
            mData1 = data;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            // 默认是Vertical (HORIZONTAL则为横向列表)
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            //设置适配器
            videoXrecy.setLayoutManager(linearLayoutManager);
            videoAdpter = new VideoAdpter(data, getContext());

            videoXrecy.setAdapter(videoAdpter);
            videoAdpter.notifyDataSetChanged();
            //购买视频请求接口
            videoAdpter.setOnclick(new VideoAdpter.Onclick() {




                @Override
                public void success(VideovolBean bean) {
                    if (mSessionId != null) {
                        //视频id和价格
                        mvideoId = bean.id;
                        mPrice = bean.price;
                        //弹出价格的pup
                        initpup();
                        Toast.makeText(getActivity(), mvideoId+"", Toast.LENGTH_SHORT).show();

                    }
                    if (mSessionId==null){
                        Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                        intentByRouter(Constant.ACTIVITY_URL_login);
                    }

                }
                //弹出价格的pup
                private void initpup() {
                    VideoOneShow videoOneShow = new VideoOneShow(getContext());
                    TextView textView = videoOneShow.findViewById(R.id.video_buy_pop_price);
                    //封面
                    TextView videoBuyXPopprompt = videoOneShow.findViewById(R.id.video_buy_pop_prompt);
                    videoOneShow.findViewById(R.id.video_buy_pop_recharge).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getActivity(), "去充值", Toast.LENGTH_SHORT).show();
                        }
                    });
                    //提示不足
                    videoBuyXPopprompt.setText("我的H币：" + mResult + ",H币不足?\t\t");
                    ImageView imageView = videoOneShow.findViewById(R.id.video_buy_pop_cover);
                    //设置封面
                    imageView.setImageBitmap(videoAdpter.fengmian);
                    LinearLayout linearLayout = videoOneShow.findViewById(R.id.video_buy_pop_hide_linner);
                    videoOneShow.findViewById(R.id.video_buy_pop_nowbuy).setClickable(false);
                    videoOneShow.findViewById(R.id.video_buy_pop_nowbuy).setBackgroundColor(Color.GRAY);
                    videoOneShow.findViewById(R.id.video_buy_pop_nowbuy).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mVideoBuyPresenter.reqeust(mId, mSessionId, mvideoId, 5);
                        }
                    });
                }
            });
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    //查看视频弹幕
    private class buttlt implements DataCall<List<BulletBean>> {


        @Override
        public void success(List<BulletBean> data, Object... args) {
            for (int i = 0; i < data.size(); i++) {
                videoDanmu.addBarrage(new Barrage(data.get(i).content, R.color.black));
            }

        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }

    //买视频
    private class Buy implements DataCall<Result> {
        @Override
        public void success(Result data, Object... args) {


        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
    //查看你有多少H币
    private class Hmoney implements DataCall<ResultBean> {




        @Override
        public void success(ResultBean data, Object... args) {
            //我有多少钱
            mResult = data.result;
        }

        @Override
        public void fail(ApiException data, Object... args) {

        }
    }
}
