package com.example.patientscircle.activity;



import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.patientscircle.R;
import com.example.patientscircle.R2;
import com.example.patientscircle.bean.PatientCircleCommentListEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@Auther:陈浩
 *@Date: 2019/8/19
 *@Time:11:27
 *@Description:${DESCRIPTION}
 * */public class PatientReviewAdpter extends RecyclerView.Adapter<PatientReviewAdpter.PatientReviewViewHolder> {
    private Activity context;
    private List<PatientCircleCommentListEntity.ResultBean> result;

    public PatientReviewAdpter(Activity context, List<PatientCircleCommentListEntity.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public PatientReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(context);
        return new PatientReviewViewHolder(from.inflate(R.layout.patient_comments_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PatientReviewViewHolder patientReviewViewHolder, final int i) {
        final PatientCircleCommentListEntity.ResultBean resultBean = result.get(i);
//        Glide.with(context).load(resultBean.getHeadPic())
//                .apply(new RequestOptions().circleCrop())
//                .into(patientReviewViewHolder.commentItemImage);
        patientReviewViewHolder.commentItemName.setText(resultBean.getNickName());
        patientReviewViewHolder.commentItemMessage.setText(resultBean.getContent());
        if (resultBean.getWhetherDoctor() == 1) {
            patientReviewViewHolder.whetherdoctor.setVisibility(View.VISIBLE);
        } else {
            patientReviewViewHolder.whetherdoctor.setVisibility(View.GONE);
        }

        patientReviewViewHolder.commentItemTime.setText(GetTimeAgo.getTimeAgo(resultBean.getCommentTime()));
        //点击点赞评论
        patientReviewViewHolder.commentItemImageAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zanclick.zanclick(resultBean.getCommentId(), true,i);
            }
        });
        //踩
        patientReviewViewHolder.commentItemImageDisagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zanclick.cancleclick(resultBean.getCommentId(), false,i);
            }
        });
        //攒的数量
        patientReviewViewHolder.commentTextAgree.setText(resultBean.getSupportNum() + "");
        //反对数量
        patientReviewViewHolder.commentTextDisagree.setText(resultBean.getOpposeNum() + "");
        if (resultBean.getOpinion() == 1) {
            patientReviewViewHolder.commentItemImageAgree.setImageResource(R.mipmap.common_icon_agree_s);
        }else {
            patientReviewViewHolder.commentItemImageAgree.setImageResource(R.mipmap.common_icon_agree_n);
        }
        if (resultBean.getOpinion() == 2) {
            patientReviewViewHolder.commentItemImageDisagree.setImageResource(R.mipmap.common_icon_disagree_s);
        }else{
            patientReviewViewHolder.commentItemImageDisagree.setImageResource(R.mipmap.common_icon_disagree_n);
        }
        //跳转到用户发发的病友圈
        patientReviewViewHolder.commentItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserpageActivity.class);
                intent.putExtra("patientUserId",resultBean.getCommentUserId());
                intent.putExtra("headPic",resultBean.getHeadPic());
                intent.putExtra("hickName",resultBean.getNickName());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    private Zanclick zanclick;

    public void setZanclick(Zanclick zanclick) {
        this.zanclick = zanclick;
    }

    public interface Zanclick {
        void zanclick(int id, boolean iszan, int i);

        void cancleclick(int id, boolean iszan, int i);

    }

    class PatientReviewViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.comment_item_image)
        ImageView commentItemImage;
        @BindView(R2.id.comment_item_name)
        TextView commentItemName;
        @BindView(R2.id.comment_item_message)
        TextView commentItemMessage;
        @BindView(R2.id.comment_item_time)
        TextView commentItemTime;
        @BindView(R2.id.comment_item_image_agree)
        ImageView commentItemImageAgree;
        @BindView(R2.id.comment_text_agree)
        TextView commentTextAgree;
        @BindView(R2.id.comment_item_image_disagree)

        ImageView commentItemImageDisagree;
        @BindView(R2.id.whether_doctor)
        ImageView whetherdoctor;
        @BindView(R2.id.comment_text_disagree)
        TextView commentTextDisagree;

        public PatientReviewViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
