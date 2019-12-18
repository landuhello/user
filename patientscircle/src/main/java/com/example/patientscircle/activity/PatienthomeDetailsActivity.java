package com.example.patientscircle.activity;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.core.hello.BasePresenter;
import com.dingtao.common.util.Constant;
import com.example.patientscircle.R;
import com.example.patientscircle.R2;
import com.example.patientscircle.bean.PatientCircleCommentListEntity;
import com.example.patientscircle.bean.PatientDetailsEntity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.SimpleCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cc.shinichi.library.ImagePreview;

/**
 * 病友圈详情
 */
public class PatienthomeDetailsActivity extends BaseActivity<PatientDetailsModelImpl, PatientDetailsPresenterImpl> implements IPatientDetailsContract.IPatientDetailsView {


    @BindView(R2.id.patient_circle_name)
    TextView patientCircleName;
    @BindView(R2.id.patient_circle_illness)
    TextView patientCircleIllness;
    @BindView(R2.id.patient_circle_department)
    TextView patientCircleDepartment;
    @BindView(R2.id.patient_circle_details)
    TextView patientCircleDetails;
    @BindView(R2.id.patient_circle_hospital)
    TextView patientCircleHospital;
    @BindView(R2.id.patient_circle_time)
    TextView patientCircleTime;
    @BindView(R2.id.patient_circle_process)
    TextView patientCircleProcess;
    @BindView(R2.id.patient_circle_detail_image)
    LinearLayout patientCircleDetailImages;
    @BindView(R2.id.patient_circle_collection_image)
    ImageView patientCirclecollectionImage;
    @BindView(R2.id.patient_circle_collection_num)
    TextView patientCirclecollectionNum;
    @BindView(R2.id.patient_circle_dian_image)
    ImageView patientCircleDianImage;
    @BindView(R2.id.patient_circle_dian_num)
    TextView patientCircleDianNum;
    @BindView(R2.id.patient_circle_tv_advice)
    TextView patientCircleTvAdvice;
    @BindView(R2.id.adoptHeadPic)
    ImageView adoptHeadPic;
    @BindView(R2.id.adoptNickName)
    TextView adoptNickName;
    @BindView(R2.id.adoptTime)
    TextView adoptTime;
    @BindView(R2.id.adoptComment)
    TextView adoptComment;
    @BindView(R2.id.treatmentStartTime)
    TextView treatmentStartTime;
    @BindView(R2.id.increase_amount_num)
    TextView increaseAmountNum;
    @BindView(R2.id.increase_amount)
    LinearLayout increaseAmount;
    @BindView(R2.id.patient_circle_head)
    ImageView patientCircleHead;
    @BindView(R2.id.patient_circle_titile)
    TextView patientCircleTitile;
    @BindView(R2.id.patient_home_acount)
    TextView patienthomeacount;
    @BindView(R2.id.patient_circle_rbbell)
    ImageView patientCircleRbbell;
    private int SickCircleId;
    private int page = 1;
    private PatientReviewAdpter reviewAdpter;
    //是否是赞
    private boolean zan;
    private List<PatientCircleCommentListEntity.ResultBean> result = new ArrayList<>();
    private int i;

    private XRecyclerView reviewXpop;
    private PatientCircleCommentListEntity.ResultBean resultBean;

    @Override
    protected void iniData() {
        EventBus.getDefault().register(this);
        if (SPUtils.getInstance("user") != null) {
            String head = SPUtils.getInstance("user").getString("headPic");

        }
        //请求详情接口

        int sickCircleId = getIntent().getIntExtra("sickCircleId", 0);
        SickCircleId = sickCircleId;
        presenter.requestPatientDetails(SickCircleId + "");
        //跳转到消息
        patientCircleRbbell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //  presenter.requestPatientCircleCommentList(SickCircleId + "", page + "", "8");


        //点击查看评论列表
        patientCircleDianImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击评论接口
                page = 1;
                presenter.requestPatientCircleCommentList(SickCircleId + "", page + "", "8");

                final PatientCircleReviewXpop patientCircleReviewXpop = new PatientCircleReviewXpop(PatienthomeDetailsActivity.this);
                //评论pop
                new XPopup.Builder(PatienthomeDetailsActivity.this).setPopupCallback(new SimpleCallback() {


                    @Override
                    public void onCreated() {
                        //点击X 关闭
                        patientCircleReviewXpop.findViewById(R.id.patient_circle_collection_err).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                patientCircleReviewXpop.dismiss();
                            }
                        });
                        //XXXRecycle
                        reviewXpop = patientCircleReviewXpop.findViewById(R.id.patient_comment_xrecyclerview);
                        reviewXpop.setHasTransientState(true);
                    }

                    @Override
                    public void onShow() {

                        LinearLayoutManager linearLayoutManager = new MyLinnerLayoutManger(PatienthomeDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
                        reviewXpop.setLayoutManager(linearLayoutManager);
                        reviewXpop.setPullRefreshEnabled(false);
                        reviewXpop.setLoadingMoreEnabled(true);

                        //适配器
                        if (reviewAdpter == null) {
                            reviewAdpter = new PatientReviewAdpter(PatienthomeDetailsActivity.this, result);
                            reviewXpop.setAdapter(reviewAdpter);
                        }
                        //评论加载更多
                        CommentsLoadMore(reviewXpop);
                        zanAndCancle();
                        if (result.size() == 0) {
                            //XXX显示没数据
                            patientCircleReviewXpop.findViewById(R.id.none_comment).
                                    setVisibility(View.VISIBLE);
                            TextView textView = patientCircleReviewXpop.findViewById(R.id.patient_circle_collection_edt);
                            textView.setText("暂无评论快来抢沙发！！");
                        }
                        if (result.size() != 0) {
                            //XXX显示没数据
                            patientCircleReviewXpop.findViewById(R.id.none_comment).
                                    setVisibility(View.GONE);
                            TextView textView = patientCircleReviewXpop.findViewById(R.id.patient_circle_collection_edt);
                            textView.setText("在此留下高见");
                        }
                    }

                    @Override
                    public void onDismiss() {
                        page = 1;
                        result.clear();
                        reviewAdpter = null;
                    }

                    @Override
                    public boolean onBackPressed() {
                        return false;
                    }
                }).dismissOnTouchOutside(false)
                        .enableDrag(true)
                        .asCustom(patientCircleReviewXpop)
                        .show();
            }
        });
    }

    //评论加载更多
    private void CommentsLoadMore(final XRecyclerView xRecyclerView) {
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                result.clear();
                presenter.requestPatientCircleCommentList(SickCircleId + "", page + "", "8");
                reviewAdpter.notifyDataSetChanged();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                presenter.requestPatientCircleCommentList(SickCircleId + "", page + "", "8");
                reviewAdpter.notifyDataSetChanged();
                xRecyclerView.loadMoreComplete();
            }
        });
    }

    //点赞
    private void zanAndCancle() {
        if (reviewAdpter != null) {
            reviewAdpter.setZanclick(new PatientReviewAdpter.Zanclick() {
                @Override
                public void zanclick(int id, boolean iszan, int i2) {
                    i = i2;
                    zan = iszan;
                    //点赞
                    presenter.requestExpressOpinion("1", id + "");
                }

                @Override
                public void cancleclick(int id, boolean iszan, int i2) {
                    zan = iszan;
                    i = i2;
                    presenter.requestExpressOpinion("2", id + "");

                }
            });
        }
    }

    //点击收藏
    private void Clickcollect() {
        patientCirclecollectionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SickCircleId != 0) {
                    presenter.requestPaddSickCollection(SickCircleId + "");
                }
            }
        });
    }

    /**
     * 点击评论
     *
     * @param contents
     */
    @Subscribe
    public void even(String contents) {
        presenter.doGetPatientcomment(SickCircleId + "", contents);
        resultBean = new PatientCircleCommentListEntity.ResultBean();
        resultBean.setNickName(SPUtils.getInstance("user").getString("nickName"));
        resultBean.setCommentTime(System.currentTimeMillis());
        resultBean.setHeadPic(SPUtils.getInstance("user").getString("headPic"));
        resultBean.setContent(contents);
    }


    @Override
    protected int bindlayout() {
        return R.layout.activity_patienthome_details;
    }

    @Override
    public BasePresenter initPresenter() {
        return new PatientDetailsPresenterImpl();
    }

    @Override
    public void showPatientDetails(Object obj) {
        PatientDetailsEntity patientDetails = (PatientDetailsEntity) obj;
        if (patientDetails.getMessage().equals("服务端错误")) {
            Toast.makeText(this, "" + patientDetails.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        PatientDetailsEntity.ResultBean result = patientDetails.getResult();
        //获取作者用户ID名字
        patientCircleName.setText(result.getAuthorUserId() + "");
        //得病名字
        patientCircleIllness.setText(result.getDisease());
        if (result.getAmount() == 0) {
            increaseAmount.setVisibility(View.GONE);
        } else {
            increaseAmountNum.setText(result.getAmount() + "");
        }

        //得到科室
        patientCircleDepartment.setText(result.getDepartment());
        //得到细节
        patientCircleDetails.setText(result.getDetail());
        //得到治疗医院
        patientCircleHospital.setText(result.getTreatmentHospital());
        //得到治疗过程
        patientCircleProcess.setText(result.getTreatmentProcess());
        //收藏数
        patientCirclecollectionNum.setText(result.getCollectionNum() + "");
        //评论数
        patientCircleDianNum.setText(result.getCommentNum() + "");
        //得到治疗开始时间
        treatmentStartTime.setText(TimeUtils.getFriendlyTimeSpanByNow(result.getTreatmentStartTime()) + "");
        //收藏
        if (result.getCollectionFlag() == 1) {
            patientCirclecollectionImage.setImageResource(R.mipmap.common_button_collection_small_s);
        } else {
            patientCirclecollectionImage.setImageResource(R.mipmap.common_button_collection_small_n);
        }
        //采纳的建议
//        Glide.with(this).load(result.getAdoptHeadPic()).into(adoptHeadPic);
        //采用尼克名字
        adoptNickName.setText(result.getAdoptNickName());
        //采纳时间
        adoptTime.setText(TimeUtils.getFriendlyTimeSpanByNow(result.getAdoptTime()));
        //采纳内容
        adoptComment.setText(result.getAdoptComment());
        String picture = result.getPicture();
        final List<String> strings = new ArrayList<>();
        patientCircleTitile.setText(result.getTitle() + "");
        //图片
        if (picture != null) {
            strings.clear();
            patientCircleDetailImages.removeAllViews();
            String[] split = result.getPicture().split(",");
            for (int i = 0; i < split.length; i++) {
                ImageView imageView = new ImageView(this);
                ViewGroup.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                ((LinearLayout.LayoutParams) params).bottomMargin = 20;
                imageView.setLayoutParams(params);

//                Glide.with(this).load(split[i])
//                        .into(imageView);

                patientCircleDetailImages.addView(imageView);
                strings.add(split[i]);
                final int finalI = i;
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 最简单的调用，即可实现大部分需求，如需定制，可参考下一步的自定义代码：
                        ImagePreview
                                .getInstance()
                                // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                                .setContext(PatienthomeDetailsActivity.this)
                                .setIndex(finalI)
                                .setImageList(strings)
                                //设置是否开启下拉图片退出
                                .setEnableDragClose(true)
                                .setEnableClickClose(true)
                                // 开启预览
                                .start();
                    }
                });
            }
        } else {
            patientCircleDetailImages.setVisibility(View.GONE);
        }

        //点击收藏
        Clickcollect();
    }

    //收藏
    @Override
    public void showPaddSickCollection(Object obj) {
        if (obj.toString().equals("失败")) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(Constant.ACTIVITY_URL_login);

            return;
        }
        try {
            JSONObject object = new JSONObject(obj.toString());
            String message = object.getString("message");
            if (message.equals("病友圈收藏成功")) {
                // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                patientCirclecollectionImage.setImageResource(R.mipmap.common_button_collection_large_s);
                //设置收藏数量
                String snum = patientCirclecollectionNum.getText().toString();
                int anInt = Integer.parseInt(snum);
                anInt++;
                patientCirclecollectionNum.setText(anInt + "");
            }
            if (message.equals("已收藏，不可重复收藏")) {
                presenter.requestCancelSickCollection(SickCircleId + "");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //取消收藏
    @Override
    public void showCancelSickCollection(Object obj) {
        try {
            JSONObject object = new JSONObject(obj.toString());
            String message = object.getString("message");
            if (message.equals("取消成功")) {
                patientCirclecollectionImage.setImageResource(R.mipmap.common_button_collection_large_n);
                String snum = patientCirclecollectionNum.getText().toString();
                int anInt = Integer.parseInt(snum);
                anInt--;
                patientCirclecollectionNum.setText(anInt + "");
                //  Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //评论列表
    @Override
    public void showPatientCircleCommentList(Object obj) {
        PatientCircleCommentListEntity patientCircletList = (PatientCircleCommentListEntity) obj;
        if (patientCircletList.getResult().size() != 0) {
            for (int i = 0; i < patientCircletList.getResult().size(); i++) {
                result.add(patientCircletList.getResult().get(i));
            }
        } else {
          //  Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();
        }
        //翻转内容
        //Collections.reverse(result);
        reviewAdpter.notifyDataSetChanged();
    }

    /**
     * 采纳
     *
     * @param obj
     */
    @Override
    public void showAdoptionProposal(Object obj) {

    }

    /**
     * *
     * 点赞取消点赞
     *
     * @param obj
     */
    @Override
    public void showExpressOpinion(Object obj) {
        if (obj.toString().equals("失败")) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(Constant.ACTIVITY_URL_login);
            return;
        }

        try {
            JSONObject object = new JSONObject(obj.toString());
            String message = object.getString("message");
            //  Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            if (message.equals("发表成功")) {
                if (zan) {
                    result.get(i).setOpinion(1);
                    result.get(i).setSupportNum(result.get(i).getSupportNum() + 1);
                    reviewAdpter.notifyDataSetChanged();
                    //  Toast.makeText(this, "我是点咋", Toast.LENGTH_SHORT).show();
                } else {
                    result.get(i).setOpinion(2);
                    result.get(i).setOpposeNum(result.get(i).getOpposeNum() + 1);
                    reviewAdpter.notifyDataSetChanged();
                    // Toast.makeText(this, "差评", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //评论的回调
    @Override
    public void showPatientcomment(Object obj) {
        if (obj.toString().equals("失败")) {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            ARouter.getInstance().build(Constant.ACTIVITY_URL_login);
            return;
        }
        try {
            JSONObject object = new JSONObject(obj.toString());
            String message = object.getString("message");
            if (message.equals("评论成功")) {
                presenter.requestPatientDetails(SickCircleId + "");
                result.add(resultBean);
                reviewXpop.scrollToPosition(result.size());
                reviewXpop.removeAllViews();
                reviewAdpter.notifyDataSetChanged();
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
