package com.example.personaldetails.activity.set;



import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.personaldetails.R;
import com.example.personaldetails.R2;
import com.example.personaldetails.activity.BaseActivity;
import com.example.personaldetails.activity.BasePresenter;
import com.example.personaldetails.activity.BaseUserTitle;
import com.example.personaldetails.activity.bean.IUserPresenterImpl;
import com.example.personaldetails.activity.bean.UserEntity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/*
* 用户详细信息  (设置三级页)
*
* */
public class UserMessageActivity extends BaseActivity<IUserModelImpl, IUserPresenterImpl> implements IUserContract.IUserView {

    @BindView(R2.id.user_title)
    BaseUserTitle userTitle;
    @BindView(R2.id.simple_head)
    SimpleDraweeView head;
    @BindView(R2.id.tv_name)
    TextView name;
    @BindView(R2.id.iv_sb)
    ImageView sex;
    @BindView(R2.id.tv_1)
    TextView height;
    @BindView(R2.id.tv_2)
    TextView weight;
    @BindView(R2.id.tv_3)
    TextView age;
    @BindView(R2.id.tv_email)
    TextView email;
    @BindView(R2.id.tv_weixin)
    TextView weixin;
    @BindView(R2.id.tv_idCard)
    TextView idCard;
    @BindView(R2.id.tv_bankCard)
    TextView bankCard;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.UserMessage();
    }

    @Override
    protected void iniData() {
        userTitle.tName.setText("我的资料");
        userTitle.tBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected int bindlayout() {
        return R.layout.activity_user_message;
    }

    @Override
    public BasePresenter initPresenter() {
        return new IUserPresenterImpl();
    }

    @OnClick({R2.id.rela_header, R2.id.rela_sex, R2.id.rela_body, R2.id.rela_weixin, R2.id.rela_idCard, R2.id.rela_bankCard})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.rela_header) {
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .isCamera(true)
                    .imageFormat(PictureMimeType.PNG)
                    .minSelectNum(1)
                    .selectionMode(PictureConfig.SINGLE)
                    .maxSelectNum(1)
                    .compress(true)
                    .previewImage(true)
                    .circleDimmedLayer(true)
                    .cropCompressQuality(90)
                    .minimumCompressSize(100)
                    .scaleEnabled(true)
                    .enableCrop(true)
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        } else if (id == R.id.rela_sex) {
        } else if (id == R.id.rela_body) {
        } else if (id == R.id.rela_weixin) {
        } else if (id == R.id.rela_idCard) {
        } else if (id == R.id.rela_bankCard) {
        }
    }

    @Override
    public void UserMessage(Object obj) {
        UserEntity userEntity = (UserEntity) obj;

        if (userEntity.getStatus().equals("0000")) {
            Uri pic = Uri.parse(userEntity.getResult().getHeadPic());
            head.setImageURI(pic);
            name.setText(userEntity.getResult().getNickName());
            email.setText(userEntity.getResult().getEmail());
            height.setText(userEntity.getResult().getHeight() + "cm");
            age.setText(userEntity.getResult().getAge() + "");
            weight.setText(userEntity.getResult().getWeight() + "kg");
            if (userEntity.getResult().getSex() == 1) {
                sex.setImageResource(R.mipmap.common_icon_boy_n);
            } else {
                sex.setImageResource(R.mipmap.common_icon_girl_n);
            }
            SPUtils.getInstance("user").put("headPic",userEntity.getResult().getHeadPic());
            SPUtils.getInstance("user").put("nickName",userEntity.getResult().getNickName());
            SPUtils.getInstance("user").put("sex",userEntity.getResult().getSex());
            SPUtils.getInstance("user").put("height",userEntity.getResult().getHeight());
            SPUtils.getInstance("user").put("age",userEntity.getResult().getAge());
            SPUtils.getInstance("user").put("weight",userEntity.getResult().getWeight());
        }else {
            ToastUtils.showShort(userEntity.getMessage());
        }
    }

    /**
     * 上传头像
     * @param obj
     */
    @Override
    public void UserWhetherSign(Object obj) {
        MessageEntity messageEntity= (MessageEntity) obj;
        if (messageEntity.getStatus().equals("0000")){
            presenter.UserMessage();
        }else {
            ToastUtils.showShort(messageEntity.getMessage());
        }
    }

    @Override
    public void UserSign(Object obj) {

    }

    @Override
    public void UserTask(Object obj) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> localMediaList = PictureSelector.obtainMultipleResult(data);
                    String path = localMediaList.get(0).getPath();
                    File file = new File(path);
                    RequestBody requestBody = MultipartBody.create(MediaType.parse("multipart/form-data"), file);
                    MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                    presenter.UserPic(image);
                    break;
            }
        }
    }
}
