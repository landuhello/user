package com.example.patientscircle.activity;


import com.dingtao.common.core.hello.BasePresenter;
import com.dingtao.common.core.hello.IBaseModel;
import com.dingtao.common.core.hello.IBaseView;

/*
 *@Auther:陈浩
 *@Date: 2019/8/18
 *@Time:19:23
 *@Description:${病友圈详细}
 * */public interface IPatientDetailsContract {
    interface IPatientDetailsView extends IBaseView {
        //详细
        void showPatientDetails(Object obj);

        //收藏病友圈
        void showPaddSickCollection(Object obj);

        void showCancelSickCollection(Object obj);

        //显示患者圈评论列表
        void showPatientCircleCommentList(Object obj);

        //采纳
        void showAdoptionProposal(Object obj);

        //观点
        void showExpressOpinion(Object obj);

        void showPatientcomment(Object obj);
    }

    interface IPatientDetailsModel extends IBaseModel {
        //详细
        void doGetPatientDetails(String sickCircleId, CallBackObj callBackObj);

        //  //收藏病友圈
        void doGetPaddSickCollection(String sickCircleId, CallBackObj callBackObj);

        void doGetCancelSickCollection(String sickCircleId, CallBackObj callBackObj);

        //显示患者圈评论列表
        void doGetPatientCircleCommentList(String sickCircleId, String page, String count, CallBackObj callBackObj);

        //  //采纳评论
        void doGetAdoptionProposal(String sickCircleId, String commentId, CallBackObj callBackObj);

        //观点
        void doGetExpressOpinion(String opinion, String commentId, CallBackObj callBackObj);


        //发表评论

        void doGetPatientcomment(String sickCircleId, String content, CallBackObj callBackObj);
    }

    abstract class IPatientDetailsPresenter extends BasePresenter<IPatientDetailsModel, IPatientDetailsView> {
        //详细
        public abstract void requestPatientDetails(String sickCircleId);

        public abstract void requestPaddSickCollection(String sickCircleId);

        public abstract void requestCancelSickCollection(String sickCircleId);

        public abstract void doGetPatientcomment(String sickCircleId, String content);

        //  //  //采纳评论
        public abstract void requestAdoptionProposal(String sickCircleId, String commentId);

        //观点
        public abstract void requestExpressOpinion(String opinion, String commentId);

        //显示患者圈评论列表
        public abstract void requestPatientCircleCommentList(String sickCircleId, String page, String count);

        @Override
        public IPatientDetailsModel getModel() {
            return new PatientDetailsModelImpl();
        }
    }
}
