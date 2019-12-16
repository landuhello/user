package com.dingtao.rrmmp.videofragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;

import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.dingtao.common.bean.LoginBean;
import com.dingtao.common.util.Constant;
import com.dingtao.rrmmp.activity.VideoBuyXPop;
import com.dingtao.rrmmp.activity.VideoReview_Xpop;
import com.dingtao.rrmmp.adapter.VideoAdpter;
import com.dingtao.rrmmp.bean.VideoCommentList;
import com.dingtao.rrmmp.bean.VideoListEntity;
import com.dingtao.rrmmp.bean.VideoTypeEntity;
import com.dingtao.rrmmp.fragment.VideoOneShow;
import com.dingtao.rrmmp.ibase.BaseFragment;
import com.dingtao.rrmmp.ibase.IvideoContract;
import com.dingtao.rrmmp.login.R;
import com.dingtao.rrmmp.login.R2;
import com.dingtao.rrmmp.presenter.BasePresenter;
import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kd.easybarrage.Barrage;
import com.kd.easybarrage.BarrageView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.shuyu.gsyvideoplayer.GSYVideoManager;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 *@Auther:陈浩requestReleaseBarrage
 *@Date: 2019/8/3
 *@Time:15:55
 *@Description:${DESCRIPTION}
 * */public class VideoFrag extends BaseFragment<VideoMmodelImpl, VideoPresenterImpl> implements IvideoContract.VideoView {
    @BindView(R2.id.video_xrecy)
    XRecyclerView videoXrecy;
    @BindView(R2.id.video_light)
    ImageView videoLightShow;
    @BindView(R2.id.video_tab)
    TabLayout videoTab;
    @BindView(R2.id.video_user_head)
    ImageView videoUserHead;
    @BindView(R2.id.video_user_message)
    ImageView videoUserMessage;
    @BindView(R2.id.show_video)
    RelativeLayout showVideo;
    @BindView(R2.id.reload_video)
    TextView reloadVideo;
    @BindView(R2.id.show_video_no_net)

    RelativeLayout showVideoNonet;
    @BindView(R2.id.video_danmu)
    BarrageView danmuview;

    private ArrayList<VideoListEntity.ResultBean> resultBeans = new ArrayList<>();
    private Boolean islight = false;
    private int page = 1;
    private BasePopupView loading;
    private int categorid = 1;
    private VideoAdpter videoAdpter;
    private int videoID;
    private int i;
    private RadioGroup radioGroup;
    private ImageView advisory;
    private int mvideoprice;
    private LinearLayoutManager linearLayoutManager;
    private int mId;
    private String mSessionId;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void iniData() {

        EventBus.getDefault().register(this);
        initUI();
        iniLight();
        //加载中
        loading = new XPopup.Builder(getContext())
                .asLoading("加载中");

        presenter.requestVideoType();
        presenter.requestVideoList(categorid + "", page + "", "5");
        linearLayoutManager = new LinearLayoutManager(getActivity());
        videoXrecy.setLayoutManager(linearLayoutManager);

        int itemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        //刷新加载
        videoXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                resultBeans.clear();
                page = 1;
                presenter.requestVideoList(categorid + "", page + "", "5");
                videoAdpter.notifyDataSetChanged();
                videoXrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.requestVideoList(categorid + "", page + "", "5");
                videoAdpter.notifyDataSetChanged();
                videoXrecy.loadMoreComplete();
            }
        });

        //选择类目
        videoTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                categorid = Integer.parseInt(tab.getTag().toString());
                page = 1;
                resultBeans.clear();
                presenter.requestVideoList(tab.getTag().toString(), page + "", "5");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //重新加载
        reloadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.show();
                presenter.requestVideoList(categorid + "", page + "", "5");
            }
        });
        //网络状态业务
        if (!NetworkUtils.isConnected()) {
            showVideo.setVisibility(View.GONE);
            showVideoNonet.setVisibility(View.VISIBLE);
        }

        //第一次寄来让用户向往下滑
        videoXrecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int firstVisibleItem, lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState) {
                    case 0:
                        // videoAdpter.misdammu=true;
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                //大于0说明有播放
                if (GSYVideoManager.instance().getPlayPosition() >= 0) {
                    //当前播放的位置
                    int position = GSYVideoManager.instance().getPlayPosition();
                    //对应的播放列表TAG
                    if ((position < firstVisibleItem || position > lastVisibleItem)) {
                        //如果滑出去了上面和下面就是否，和今日头条一样
                        //是否全屏
                        if (!GSYVideoManager.isFullState(getActivity())) {
                            GSYVideoManager.releaseAllVideos();
                            videoAdpter.notifyDataSetChanged();
                        }
                    }
                }

            }

        });
    }


    //第一次进来显示
    private void isone() {
        new XPopup.Builder(getContext())
                .asCustom(new VideoOneShow(getContext()))
                .show();
    }

    //收弹幕的的点击弹幕
    @Subscribe
    public void event(Integer id) {
        videoID = id;
        presenter.requestBarrageList(id + "");
    }

    //买视频的发弹幕
    @Subscribe
    public void eventbuy(Integer[] strings) {
        videoID = strings[0];//id
        mvideoprice = strings[1];//价格
        i = strings[2];//I
        //如果已经购买去评论
        if (resultBeans.get(i).getWhetherBuy() == 1) {
            final VideoReview_Xpop videoReview_xpop = new VideoReview_Xpop(getContext());
            new XPopup.Builder(getContext())
                    .setPopupCallback(new SimpleCallback() {  //监听弹窗create完毕
                        @Override
                        public void onCreated() {
                            final EditText videocoment = videoReview_xpop.findViewById(R.id.video_comment_edt);
                            RelativeLayout review_xpopViewsend = videoReview_xpop.findViewById(R.id.video_comment_send);
                            review_xpopViewsend.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    // danmuview.destroy();
                                    //添加到接口
                                    presenter.requestReleaseBarrage(videoID + "", "" + videocoment.getText().toString());
                                    danmuview.addBarrage(new Barrage(videocoment.getText().toString(), true));
                                    videoReview_xpop.dismiss();
                                    //请求弹幕评论列表
                                    // presenter.requestBarrageList(videoID + "");
                                }
                            });
                        }
                    })
                    .autoOpenSoftInput(true)
                    .hasStatusBarShadow(false) //
                    .asCustom(videoReview_xpop)
                    .show();
        } else {
            //请求钱包
            presenter.requestUserwallet();
        }
    }

    //收藏
    @Subscribe
    public void eventId(ArrayList<Integer> strings) {
        videoID = strings.get(0);
        i = strings.get(1);
        presenter.requestcollectionVideo(videoID + "");
    }

    //拉灯效果
    private void iniLight() {
        videoTab.setVisibility(View.GONE);
        ObjectAnimator.ofFloat(videoLightShow, "translationY", 0, -50).start();
        //父容器的
        radioGroup = getActivity().findViewById(R.id.rg);
        //父容器的病友圈
        advisory = getActivity().findViewById(R.id.advisory);
        RadioButton rb3 = getActivity().findViewById(R.id.rb3);
        //设置隐藏显示
        rb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    boolean onevideo = SPUtils.getInstance().getBoolean("onevideo");
                    if (!onevideo) {
                        isone();
                        SPUtils.getInstance().put("onevideo", true);
                    }
                    if (islight) {
                        return;
                    }
                    videoTab.setVisibility(View.GONE);
                    radioGroup.setVisibility(View.GONE);
                    advisory.setVisibility(View.GONE);
                    //点击显示
                    videoLightShow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            islight = !islight;
                            //显示出来
                            if (islight) {
                                videoTab.setVisibility(View.VISIBLE);
                                radioGroup.setVisibility(View.VISIBLE);
                                advisory.setVisibility(View.VISIBLE);
                                ObjectAnimator animator = ObjectAnimator.ofFloat(videoLightShow, "translationY", 0, -50);
                                ObjectAnimator show = ObjectAnimator.ofFloat(videoLightShow, "translationY", 0, 10);
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
                                radioGroup.setVisibility(View.GONE);
                                advisory.setVisibility(View.GONE);
                                ObjectAnimator.ofFloat(videoLightShow, "translationY", 0, -50).setDuration(1000).start();
                            }
                        }
                    });
                } else {
                    //点击显示
                    videoLightShow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            islight = !islight;
                            //显示出来
                            if (islight) {
                                videoTab.setVisibility(View.VISIBLE);
                                radioGroup.setVisibility(View.VISIBLE);
                                advisory.setVisibility(View.VISIBLE);
                                ObjectAnimator animator = ObjectAnimator.ofFloat(videoLightShow, "translationY", 0, -50);
                                ObjectAnimator show = ObjectAnimator.ofFloat(videoLightShow, "translationY", 0, 10);
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
                                radioGroup.setVisibility(View.GONE);
                                advisory.setVisibility(View.GONE);
                                ObjectAnimator.ofFloat(videoLightShow, "translationY", 0, -50).setDuration(1000).start();
                            }
                        }
                    });
                    radioGroup.setVisibility(View.VISIBLE);
                    advisory.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    protected int bindlayout() {
        return R.layout.videolook;
    }

    @Override
    public BasePresenter initPresenter() {
        return new VideoPresenterImpl();
    }

    //*类型
    @Override
    public void showVideoType(Object obj) {
        VideoTypeEntity videoType = (VideoTypeEntity) obj;
        for (int i = 0; i < videoType.getResult().size(); i++) {
            videoTab.addTab(videoTab.newTab().setTag(videoType.getResult().get(i).getId()).setText(videoType.getResult().get(i).getName()));
        }
    }

    @Override
    public void showVideoList(Object obj) {
        VideoListEntity listEntity = (VideoListEntity) obj;
        if (listEntity.getResult().size() > 0) {
            for (int i = 0; i < listEntity.getResult().size(); i++) {
                resultBeans.add(listEntity.getResult().get(i));
            }
            if (resultBeans.size() != 0 && videoAdpter == null) {
                showVideoNonet.setVisibility(View.GONE);
                showVideo.setVisibility(View.VISIBLE);
                loading.dismiss();
                videoAdpter = new VideoAdpter(resultBeans, getContext());
                videoXrecy.setAdapter(videoAdpter);
            }
        } else {
            Toast.makeText(getActivity(), "没数据", Toast.LENGTH_SHORT).show();
        }
        videoAdpter.notifyDataSetChanged();
    }

    //收藏
    @Override
    public void showcollection(Object obj) {

        if (obj.toString().equals("失败")) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                    .navigation(getContext());
            return;
        }

        String s = obj.toString();
        try {
            JSONObject object = new JSONObject(s);
            String message = object.getString("message");
            if (message.equals("收藏成功")) {
                resultBeans.get(i).setWhetherCollection(1);
                videoAdpter.setMiscollect(true);
                videoAdpter.notifyDataSetChanged();
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            } else if (message.equals("已收藏，不可重复收藏")) {
                presenter.requestCanclecollectionVideo(videoID + "");
            } else {
                Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                        .navigation(getContext());
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //取消收藏
    @Override
    public void showcanclecollection(Object obj) {
        if (obj.toString().equals("失败")) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                    .navigation(getContext());
            return;
        }
        try {
            JSONObject object = new JSONObject(obj.toString());
            String message = object.getString("message");
            if (message.equals("取消成功")) {
                resultBeans.get(i).setWhetherCollection(2);
                videoAdpter.setMiscollect(false);
                videoAdpter.notifyDataSetChanged();
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            } else if (message.equals("未收藏的视频不能进行此操作")) {
                presenter.requestcollectionVideo(videoID + "");
            } else {
                Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                        .navigation(getContext());
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //发弹幕
    @Override
    public void showReleaseBarrage(Object obj) {
        try {
            JSONObject object = new JSONObject(obj.toString());
            String message = object.getString("message");
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //弹幕列表
    @Override
    public void showBarrageList(Object obj) {
        VideoCommentList commentList = (VideoCommentList) obj;
        List<VideoCommentList.ResultBean> result = commentList.getResult();
        if (result.size() != 0 && videoAdpter.misdammu) {
            for (int j = 0; j < result.size(); j++) {
                danmuview.addBarrage(new Barrage(result.get(j).getContent(), false));
            }
        } else {
            danmuview.destroy();
            return;
        }
    }

    //购买视频
    @Override
    public void showBuyVideo(Object obj) {
        if (obj.toString().equals("失败")) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                    .navigation(getContext());
            return;
        }
        try {
            JSONObject object = new JSONObject(obj.toString());
            String message = object.getString("message");
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            if (message.equals("购买成功")) {
                videoAdpter.notifyDataSetChanged();
            } else if (message.equals("已购买")) {
                videoAdpter.notifyDataSetChanged();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //查询用户钱包
    @Override
    public void showUserwallet(Object obj) {
        if (obj.toString().equals("失败")) {
            Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                    .navigation(getContext());
            return;
        }
        final VideoBuyXPop videoBuyXPop = new VideoBuyXPop(getContext());
        //用户钱包
        String s = obj.toString();
        try {
            JSONObject object = new JSONObject(s);
            String message = object.getString("message");
            if (message.equals("请先登陆")) {
                Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                        .navigation(getContext());
                return;
            }
            final String result = object.getString("result");
            //比较余额是否够>
            if (Integer.parseInt(result) >= mvideoprice) {


                //本次视频将扣除
                final DoctordetailsRecharge recharge = new DoctordetailsRecharge(getActivity());
                //设置弹出标题
                recharge.setContent("购买本视频将扣除" + mvideoprice + "H币！");
                //跳转质询
                recharge.setButtontv("立即购买", null, null);
                new XPopup.Builder(getActivity())
                        .setPopupCallback(new SimpleCallback() {  //监听弹窗create完毕
                            @Override
                            public void onCreated() {
                                recharge.findViewById(R.id.doctor_details_pop_go_Recharge)
                                        .setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                presenter.requestBuyVideo(videoID + "", mvideoprice + "");
                                                recharge.dismiss();
                                            }
                                        });
                            }
                        })
                        .asCustom(recharge).show();
            } else {
                //不够;
                new XPopup.Builder(getContext())
                        .hasStatusBarShadow(true) //启用状态栏阴影
                        .setPopupCallback(new SimpleCallback() {  //监听弹窗create完毕
                            @Override
                            public void onCreated() {
                                TextView viewById = videoBuyXPop.findViewById(R.id.video_buy_pop_price);
                                //设置封面
                                TextView videoBuyXPopprompt = videoBuyXPop.findViewById(R.id.video_buy_pop_prompt);
                                videoBuyXPop.findViewById(R.id.video_buy_pop_recharge).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Toast.makeText(getActivity(), "去充值", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                //提示不足
                                videoBuyXPopprompt.setText("我的H币：" + result + ",H币不足?\t\t");
                                ImageView imgcover = videoBuyXPop.findViewById(R.id.video_buy_pop_cover);
                                imgcover.setImageBitmap(videoAdpter.fengmian);
                                LinearLayout hidebuyxpop = videoBuyXPop.findViewById(R.id.video_buy_pop_hide_linner);
                                //立即购买
                                videoBuyXPop.findViewById(R.id.video_buy_pop_nowbuy).setClickable(false);
                                videoBuyXPop.findViewById(R.id.video_buy_pop_nowbuy).setBackgroundColor(Color.GRAY);
                                videoBuyXPop.findViewById(R.id.video_buy_pop_nowbuy).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        presenter.requestBuyVideo(videoID + "", mvideoprice + "");
                                    }
                                });
                                //点击尖头隐藏
                                hidebuyxpop.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        //隐藏
                                        videoBuyXPop.dismiss();
                                    }
                                });
                                viewById.setText(mvideoprice + "H0币");
                            }
                        })
                        .asCustom(videoBuyXPop)
                        .show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 抖音滑动效果
     */
    @SuppressLint("WrongConstant")
    public void initUI() {


        // ---RecyclerView---
        videoXrecy.setNestedScrollingEnabled(false);
        // PagerSnapHelper
        PagerSnapHelper snapHelper = new PagerSnapHelper() {
            // 在 Adapter的 onBindViewHolder 之后执行
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                // TODO 找到对应的Index
                Log.e("xiaxl: ", "---findTargetSnapPosition---");
                int targetPos = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                Log.e("xiaxl: ", "targetPos: " + targetPos);
                return targetPos;
            }

            // 在 Adapter的 onBindViewHolder 之后执行
            @Nullable
            @Override
            public View findSnapView(RecyclerView.LayoutManager layoutManager) {
                // TODO 找到对应的View
                Log.e("xiaxl: ", "---findSnapView---");
                View view = super.findSnapView(layoutManager);
                Log.e("xiaxl: ", "tag: " + view.getTag());
                return view;
            }
        };
        snapHelper.attachToRecyclerView(videoXrecy);
        // ---布局管理器---
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        // 默认是Vertical (HORIZONTAL则为横向列表)
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //
        videoXrecy.setLayoutManager(linearLayoutManager);

        // TODO 这么写是为了获取RecycleView的宽高
        videoXrecy.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    videoXrecy.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    videoXrecy.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        EventBus.getDefault().unregister(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        danmuview.destroy();
        GSYVideoManager.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }
}
