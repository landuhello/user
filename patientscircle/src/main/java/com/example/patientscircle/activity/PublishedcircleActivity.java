package com.example.patientscircle.activity;



import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dingtao.common.core.hello.BasePresenter;
import com.dingtao.common.util.Constant;
import com.example.patientscircle.R;
import com.example.patientscircle.R2;
import com.example.patientscircle.bean.HomeWzzxEntity;
import com.example.patientscircle.bean.IllnessListEntity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.lxj.xpopup.interfaces.SimpleCallback;
import com.lxj.xpopup.interfaces.XPopupCallback;
import com.suke.widget.SwitchButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 发布病友圈
 */
public class PublishedcircleActivity extends BaseActivity<ReleaseCrcleModelImpl, ReleaseCrclePresenterImpl> implements IReleaseCrcleContract.IReleaseCrcleView {


    @BindView(R2.id.release_circle_head)
    ImageView releaseCircleHead;
    @BindView(R2.id.release_circle_rbbell)
    ImageView releaseCircleRbbell;
    @BindView(R2.id.promote_view)
    View promoteView;
    @BindView(R2.id.promote_edit_title)
    EditText promoteEditTitle;
    @BindView(R2.id.promote_view1)
    View promoteView1;
    @BindView(R2.id.promote_view2)
    View promoteView2;
    @BindView(R2.id.promote_view3)
    View promoteView3;
    @BindView(R2.id.promote_edit_details)
    EditText promoteEditDetails;
    @BindView(R2.id.promote_text_count)
    TextView promoteTextCount;
    @BindView(R2.id.promote_view4)
    View promoteView4;
    @BindView(R2.id.promote_text_experience)
    TextView promoteTextExperience;
    @BindView(R2.id.promote_edit_name)
    EditText promoteEditName;
    @BindView(R2.id.promote_view5)
    View promoteView5;
    @BindView(R2.id.promote_view6)
    View promoteView6;
    @BindView(R2.id.promote_view7)
    View promoteView7;
    @BindView(R2.id.promote_edit_course)
    EditText promoteEditCourse;
    @BindView(R2.id.promote_text_order)
    TextView promoteTextOrder;
    @BindView(R2.id.promote_text_limit)
    TextView promoteTextLimit;
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
    @BindView(R2.id.promote_publish)
    Button promotePublish;
    @BindView(R2.id.SwitchButton)
    SwitchButton switchbutton;
    @BindView(R2.id.promote_relativelayout_advice)
    RelativeLayout promoterelativelayoutadvicel;
    @BindView(R2.id.promote_scrollview)
    NestedScrollView nestedScrollView;
    @BindView(R2.id.publish_department_img)
    ImageView publishdepartmentimg;
    @BindView(R2.id.publish_disease_img)
    ImageView publishdiseaseimg;
    @BindView(R2.id.publish_start_img)
    ImageView publishstartimg;
    @BindView(R2.id.promote_endTimeimg)
    ImageView promoteendTimeimg;
    //科室条目
    @BindView(R2.id.promote_office)
    RelativeLayout promoteoffice;
    //疾病
    @BindView(R2.id.promote_disease)
    RelativeLayout promotedisease;
    //开始时间
    @BindView(R2.id.promote_startTime)
    RelativeLayout promotestartTime;
    @BindView(R2.id.promote_endTime)
    RelativeLayout promoteendTime;
    @BindView(R2.id.publish_department_tv)
    TextView publishdepartmenttv;
    //主要病症
    @BindView(R2.id.publish_main_illness)
    TextView publishmainillness;
    @BindView(R2.id.publish_start_time)
    TextView publishstarttime;
    @BindView(R2.id.promote_endTimetv)
    TextView promoteendTimetv;
    @BindView(R2.id.publish_image_recy)
    RecyclerView publishRecy;

    private ReleaseDepartmentXpopAdpter departmentXpopAdpter;
    private boolean mishow = false;
    private ReleaseDiseaseXpopAdpter diseaseXpopAdpter;

    private ArrayList images;
    private PubliscImageAdapter imageAdapter;

    private List<MultipartBody.Part> files = new ArrayList<>();


    @Override
    protected void iniData() {
        //初始化Ui
        iniUi();
        if (SPUtils.getInstance("user") != null) {
            String head = SPUtils.getInstance("user").getString("headPic");
//            Glide.with(this).load(head).apply(new RequestOptions().circleCrop()).into(releaseCircleHead);
        }

    }

    //初始化Ui
    private void iniUi() {
        //价格按钮
        iniHbchebox();
        //选择switch
        switchbuttonclick();
        //可以输入的文字字数
        inipromoteEditDetails();
        ///初始化科室选择
        iniDepartment();
        //疾病列表
        iniDisease();
        //时间选择
        iniTime(promotestartTime, publishstartimg, publishstarttime);
        //结束时间
        iniTime(promoteendTime, promoteendTimeimg, promoteendTimetv);
        iniImages();
        //点击发布
        clickPublish();
    }

    private void clickPublish() {
        promotePublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> hashMap = new HashMap<>();
                String title = promoteEditTitle.getText().toString().trim();
                if (title.equals("请输入标题（最多20个字）")) {
                    Toast.makeText(PublishedcircleActivity.this, "请输入标题", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (publishdepartmenttv.getText().toString().equals("请选择就诊科室")) {
                    Toast.makeText(PublishedcircleActivity.this, "请选择科室", Toast.LENGTH_SHORT).show();
                    return;
                }
                int departmentId = (int) publishdepartmenttv.getTag();
                if (publishmainillness.equals("请选择主要病症")) {
                    Toast.makeText(PublishedcircleActivity.this, "请选择主要病症", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (publishstarttime.getText().toString().equals("请选择开始时间")) {
                    Toast.makeText(PublishedcircleActivity.this, "", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (promoteendTimetv.getText().toString().equals("请选择开始时间")) {
                    Toast.makeText(PublishedcircleActivity.this, "", Toast.LENGTH_SHORT).show();
                    return;
                }

                //标题
                hashMap.put("title", title);
                //科室ID
                hashMap.put("departmentId", departmentId);
                hashMap.put("disease", publishmainillness.getText().toString());
                hashMap.put("detail", promoteEditDetails.getText().toString());
                hashMap.put("treatmentHospital", promoteEditName.getText().toString());
                hashMap.put("treatmentStartTime", publishstarttime.getText().toString());
                hashMap.put("treatmentEndTime", promoteendTimetv.getText().toString());
                hashMap.put("treatmentProcess", promoteEditCourse.getText().toString());
                //是否提升额度
                if (switchbutton.isChecked()) {
                    if (checkbox10.isChecked()) {
                        hashMap.put("amount", 10);
                    }
                    if (checkbox20.isChecked()) {
                        hashMap.put("amount", 20);
                    }
                    if (checkbox50.isChecked()) {
                        hashMap.put("amount", 50);
                    }
                    //自定义的Hb
                    if (checkboxCustom.isChecked()) {
                        hashMap.put("amount", checkboxCustom.getText().toString().substring(0, checkboxCustom.length() - 2));
                    }
                } else {
                    hashMap.put("amount", 0);
                }
                //发布病友圈
                presenter.requestPublishSickCircle(hashMap);
            }
        });

    }

    private void iniImages() {
        images = new ArrayList();

        publishRecy.setNestedScrollingEnabled(false);
        publishRecy.setLayoutManager(new GridLayoutManager(PublishedcircleActivity.this, 3, LinearLayoutManager.VERTICAL, false));
        imageAdapter = new PubliscImageAdapter(this, images);


        imageAdapter.setDelect(new PubliscImageAdapter.PuImgDelect() {
            @Override
            public void click(int i) {
                images.remove(i);
                files.remove(i);
                imageAdapter.notifyDataSetChanged();
            }
        });

        publishRecy.setAdapter(imageAdapter);
        imageAdapter.setClick(new PubliscImageAdapter.PuImgClick() {
            @Override
            public void click() {
                if (images.size() == 7) {
                    Toast.makeText(PublishedcircleActivity.this, "最大添加6张", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    PictureSelector.create(PublishedcircleActivity.this)
                            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                            .maxSelectNum(6)// 最大图片选择数量 int
                            .minSelectNum(1)// 最小选择数量 int
                            .imageSpanCount(4)// 每行显示个数 int
                            .compress(true)
                            .selectionMode(PictureConfig.TYPE_ALL)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                            .previewImage(true)// 是否可预览图片 true or false
                            .forResult(0);//结果回调onActivityResult code

                }
            }
        });

    }

    //时间选择
    private void iniTime(RelativeLayout relativeLayout, final ImageView publishstartimg, final TextView publishstarttime) {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator rotate = ObjectAnimator.ofFloat(publishstartimg, "rotation", 180f);
                rotate.setDuration(360);//时间
                rotate.start();//开始

                final AddressPopupWindow addressPopupWindow = new AddressPopupWindow(PublishedcircleActivity.this);
                new XPopup.Builder(PublishedcircleActivity.this).setPopupCallback(new SimpleCallback() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onCreated() {
                        super.onCreated();
                        DatePicker dpicker = findViewById(R.id.add_datepicker);
                        TextView addBt = findViewById(R.id.add_bt);
                        final TextView addTime = findViewById(R.id.add_time);


                        addTime.setText(dpicker.getYear() + "-" + dpicker.getMonth() + "-" + dpicker.getDayOfMonth());

                        dpicker.init(dpicker.getYear(), dpicker.getMonth() + 0, dpicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
                            @Override
                            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                int x = monthOfYear + 1;
                                addTime.setText(year + "-" + x + "-" + dayOfMonth);
                                //Toast.makeText(PublishedcircleActivity.this, "addTime:" + addTime, Toast.LENGTH_SHORT).show();

                            }
                        });
                        //确定按钮
                        addBt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                publishstarttime.setText("" + addTime.getText().toString());
                                ObjectAnimator rotate = ObjectAnimator.ofFloat(publishstartimg, "rotation", 360f);
                                rotate.setDuration(360);//时间
                                rotate.start();//开始
                                addressPopupWindow.dismiss();
                            }
                        });
                    }
                }).asCustom(addressPopupWindow).show();

            }
        });
    }

    //疾病列表
    private void iniDisease() {
        promotedisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mishow = !mishow;
                final ReleaseDepartmentXpop departmentXpop = new ReleaseDepartmentXpop(PublishedcircleActivity.this);
                final BasePopupView basePopupView = new XPopup.Builder(PublishedcircleActivity.this)
                        .setPopupCallback(new XPopupCallback() {
                            @Override
                            public void onCreated() {
                                RecyclerView departmentXpoprecy = departmentXpop.findViewById(R.id.release_department_xpop_recy);
                                departmentXpoprecy.setLayoutManager(new GridLayoutManager(PublishedcircleActivity.this, 4, LinearLayoutManager.HORIZONTAL, false));
                                departmentXpoprecy.setAdapter(diseaseXpopAdpter);
                            }

                            @Override
                            public void onShow() {
                                mishow = true;
                            }

                            @Override
                            public void onDismiss() {
                                mishow = false;
                            }

                            @Override
                            public boolean onBackPressed() {
                                //关闭
                                mishow = false;
                                ObjectAnimator rotate = ObjectAnimator.ofFloat(publishdiseaseimg, "rotation", 360f);
                                rotate.setDuration(360);//时间
                                rotate.start();//开始
                                return false;
                            }
                        })
                        .atView(promotedisease)  // 依附于所点击的View，内部会自动判断在上方或者下方显示
                        .hasShadowBg(true) // 是否有半透明的背景，默认为true
                        .hasStatusBarShadow(true) //启用状态栏阴影
                        .asCustom(departmentXpop);
                if (mishow) {
                    ObjectAnimator rotate = ObjectAnimator.ofFloat(publishdiseaseimg, "rotation", 180f);
                    rotate.setDuration(360);//时间
                    rotate.start();//开始
                    basePopupView.show();   //选转圈开始点击
                } else {
                    ObjectAnimator rotate = ObjectAnimator.ofFloat(publishdiseaseimg, "rotation", 360f);
                    rotate.setDuration(360);//时间
                    rotate.start();//开始
                    basePopupView.dismiss();
                }
                if (diseaseXpopAdpter == null) {
                    return;
                }
                diseaseXpopAdpter.setClick(new ReleaseDiseaseXpopAdpter.DiseasementClick() {
                    @Override
                    public void click(String departmentName, int id, int DepartmentId) {
                        //关闭
                        mishow = false;
                        ObjectAnimator rotate = ObjectAnimator.ofFloat(publishdiseaseimg, "rotation", 360f);
                        rotate.setDuration(360);//时间
                        rotate.start();//开始
                        basePopupView.dismiss();
                        publishmainillness.setText(departmentName);
                        publishmainillness.setTag(id);
                    }
                });
            }
        });
    }

    //初始化科室
    private void iniDepartment() {
        presenter.requestDepartmentList();
        promoteoffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mishow = !mishow;
                final ReleaseDepartmentXpop departmentXpop = new ReleaseDepartmentXpop(PublishedcircleActivity.this);
                final BasePopupView basePopupView = new XPopup.Builder(PublishedcircleActivity.this)
                        .setPopupCallback(new XPopupCallback() {
                            @Override
                            public void onCreated() {
                                RecyclerView departmentXpoprecy = departmentXpop.findViewById(R.id.release_department_xpop_recy);
                                departmentXpoprecy.setLayoutManager(new GridLayoutManager(PublishedcircleActivity.this, 4));
                                departmentXpoprecy.setAdapter(departmentXpopAdpter);
                            }

                            @Override
                            public void onShow() {
                                mishow = true;
                            }

                            @Override
                            public void onDismiss() {
                                mishow = false;
                            }

                            @Override
                            public boolean onBackPressed() {
                                //关闭
                                mishow = false;
                                ObjectAnimator rotate = ObjectAnimator.ofFloat(publishdepartmentimg, "rotation", 360f);
                                rotate.setDuration(360);//时间
                                rotate.start();//开始
                                return false;
                            }
                        })
                        .atView(promoteoffice)  // 依附于所点击的View，内部会自动判断在上方或者下方显示
                        .hasShadowBg(true) // 是否有半透明的背景，默认为true
                        .hasStatusBarShadow(true) //启用状态栏阴影
                        .asCustom(departmentXpop);
                if (mishow) {
                    ObjectAnimator rotate = ObjectAnimator.ofFloat(publishdepartmentimg, "rotation", 180f);
                    rotate.setDuration(360);//时间
                    rotate.start();//开始
                    basePopupView.show();   //选转圈开始点击
                } else {
                    ObjectAnimator rotate = ObjectAnimator.ofFloat(publishdepartmentimg, "rotation", 360f);
                    rotate.setDuration(360);//时间
                    rotate.start();//开始
                    basePopupView.dismiss();
                }
                if (departmentXpopAdpter == null) {
                    return;
                }
                departmentXpopAdpter.setClick(new ReleaseDepartmentXpopAdpter.DepartmentClick() {
                    @Override
                    public void click(String departmentName, int id) {
                        //关闭
                        mishow = false;
                        ObjectAnimator rotate = ObjectAnimator.ofFloat(publishdepartmentimg, "rotation", 360f);
                        rotate.setDuration(360);//时间
                        rotate.start();//开始
                        basePopupView.dismiss();
                        //请求药品
                        presenter.requestCorrespondingIllness(id + "");
                        publishdepartmenttv.setText(departmentName);
                        publishdepartmenttv.setTag(id);
                    }
                });
            }

        });

    }

    //可以输入的文字字数
    private void inipromoteEditDetails() {
        TextWatcher editclick = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int i = 300 - s.length();
                promoteTextCount.setText("还可以输入" + i + "个文字");
                if (i == 300) {
                    promoteTextCount.setText("不超过300字");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        promoteEditDetails.addTextChangedListener(editclick);
    }

    //选择
    private void switchbuttonclick() {
        switchbutton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    promoterelativelayoutadvicel.setVisibility(View.VISIBLE);
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            nestedScrollView.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });
                } else {
                    promoterelativelayoutadvicel.setVisibility(View.GONE);
                }
            }
        });
    }

    //价格按钮
    private void iniHbchebox() {
        checkboxCustom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkbox10.setChecked(false);
                    checkbox20.setChecked(false);
                    checkbox50.setChecked(false);
                    final SelHbXpop selHbXpop = new SelHbXpop(PublishedcircleActivity.this);
                    new XPopup.Builder(PublishedcircleActivity.this)
                            .setPopupCallback(new XPopupCallback() {

                                @Override
                                public void onCreated() {
                                    final EditText hbedt = selHbXpop.findViewById(R.id.hbedt);
                                    //取消
                                    selHbXpop.findViewById(R.id.hbcancle).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            selHbXpop.dismiss();
                                        }
                                    });
                                    //确认
                                    selHbXpop.findViewById(R.id.hbclick).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String trim = hbedt.getText().toString().trim();
                                            if (trim.length() == 0) {
                                                selHbXpop.dismiss();
                                                return;
                                            }
                                            int i = Integer.parseInt(trim);
                                            if (trim != null) {
                                                if (i == 0) {
                                                    checkboxCustom.setText("0H币");
                                                } else {
                                                    checkboxCustom.setText(trim + "H币");
                                                }
                                            }
                                            selHbXpop.dismiss();
                                        }
                                    });
                                }

                                @Override
                                public void onShow() {

                                }

                                @Override
                                public void onDismiss() {

                                }

                                @Override
                                public boolean onBackPressed() {
                                    return false;
                                }
                            })
                            .moveUpToKeyboard(true) // 软键盘弹出时，弹窗是否移动到软键盘上面，默认为true
                            .asCustom(selHbXpop)
                            .show();

                }
            }
        });
        checkbox10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkboxCustom.setChecked(false);
                    checkbox20.setChecked(false);
                    checkbox50.setChecked(false);
                }
            }
        });
        checkbox20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkboxCustom.setChecked(false);
                    checkbox10.setChecked(false);
                    checkbox50.setChecked(false);
                }
            }
        });
        checkbox50.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkboxCustom.setChecked(false);
                    checkbox10.setChecked(false);
                    checkbox20.setChecked(false);
                }
            }
        });
    }

    @Override
    protected int bindlayout() {
        return R.layout.activity_publishedcircle;
    }

    @Override
    public BasePresenter initPresenter() {
        return new ReleaseCrclePresenterImpl();
    }

    /**
     * 科室分类
     *
     * @param obj
     */
    @Override
    public void showDepartment(Object obj) {
        HomeWzzxEntity Departmenttype = (HomeWzzxEntity) obj;
        departmentXpopAdpter = new ReleaseDepartmentXpopAdpter(this, Departmenttype.getResult());
    }

    /**
     * 花根据科室查询病
     *
     * @param obj
     */
    @Override
    public void showCorrespondingIllness(Object obj) {
        IllnessListEntity disease = (IllnessListEntity) obj;
        List<IllnessListEntity.ResultBean> result = disease.getResult();
        diseaseXpopAdpter = new ReleaseDiseaseXpopAdpter(PublishedcircleActivity.this, result);
        Toast.makeText(this, disease.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPublishSickCircle(Object obj) {
        try {
            JSONObject object = new JSONObject(obj.toString());
            String message = object.getString("message");
            if (message.equals("请先登陆")) {
                ARouter.getInstance().build(Constant.ACTIVITY_URL_login)
                        .navigation(this);
            }
            String result = object.getString("result");
            presenter.requestUploadSickCirclePicture(result, files);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            if (message.equals("发表成功")) {
                finish();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传图片回调
     *
     * @param obj
     */
    @Override
    public void showUploadSickCirclePicture(Object obj) {
        Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 0:
                    List<LocalMedia> localMedia = PictureSelector.obtainMultipleResult(data);
                    //添加加号的那个
                    for (int i = 0; i < images.size(); i++) {
                        if (images.get(i).toString().equals("2131623936")) {
                            images.remove(i);
                        }
                    }
                    for (int i = 0; i < localMedia.size(); i++) {
                        //获取拿到的图片原始的
                        String path = localMedia.get(i).getCompressPath();
                        File file = new File(path);
                        //展示的图片地址
                        images.add(file);
                        //上传
                        RequestBody requestBody = MultipartBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part image = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);

                        //上传服务器的类型
                        files.add(image);
                    }
                    //设置适配器刷新
                    images.add(R.mipmap.add);
                    imageAdapter.notifyDataSetChanged();
                    in111i(publishRecy, imageAdapter, images);
                    break;
            }

        }
    }


    private void in111i(RecyclerView mRecyclerView, final RecyclerView.Adapter ap, final ArrayList<String> datas) {
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFrlg = 0;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFrlg = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    dragFrlg = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                }
                return makeMovementFlags(dragFrlg, 0);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //滑动事件  下面注释的代码，滑动后数据和条目错乱，被舍弃
//            Collections.swap(datas,viewHolder.getAdapterPosition(),target.getAdapterPosition());
//            ap.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());

                //得到当拖拽的viewHolder的Position
                int fromPosition = viewHolder.getAdapterPosition();
                //拿到当前拖拽到的item的viewHolder
                int toPosition = target.getAdapterPosition();
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(datas, i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(datas, i, i - 1);
                    }
                }
                ap.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                //侧滑删除可以使用；
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            /**
             * 长按选中Item的时候开始调用
             * 长按高亮
             * @param viewHolder
             * @param actionState
             */
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.RED);
                    //获取系统震动服务//震动70毫秒
                    Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                    vib.vibrate(70);

                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            /**
             * 手指松开的时候还原高亮
             * @param recyclerView
             * @param viewHolder
             */
            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
                ap.notifyDataSetChanged();  //完成拖动后刷新适配器，这样拖动后删除就不会错乱
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }

}
