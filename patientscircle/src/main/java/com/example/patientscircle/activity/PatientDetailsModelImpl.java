package com.example.patientscircle.activity;



import com.example.patientscircle.bean.PatientCircleCommentListEntity;
import com.example.patientscircle.bean.PatientDetailsEntity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/*
 *@Auther:陈浩
 *@Date: 2019/8/18
 *@Time:19:26
 *@Description:${DESCRIPTION}
 * */public class PatientDetailsModelImpl implements IPatientDetailsContract.IPatientDetailsModel {
    @Override
    public void doGetPatientDetails(String sickCircleId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).PatientCircleDetails(sickCircleId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<PatientDetailsEntity>() {
                    @Override
                    public void accept(PatientDetailsEntity patientDetailsEntity) throws Exception {
                        callBackObj.success(patientDetailsEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                      throwable.printStackTrace();
                    }
                });

    }

    @Override
    public void doGetPaddSickCollection(String sickCircleId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).addSickCollection(sickCircleId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody patientDetailsEntity) throws Exception {
                        callBackObj.success(patientDetailsEntity.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                        callBackObj.success("失败");
                  throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void doGetCancelSickCollection(String sickCircleId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).cancelSickCollection(sickCircleId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody patientDetailsEntity) throws Exception {
                        callBackObj.success(patientDetailsEntity.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * 评论列表
     *
     * @param sickCircleId
     * @param page
     * @param count
     * @param callBackObj
     */
    @Override
    public void doGetPatientCircleCommentList(String sickCircleId, String page, String count, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).PatientCircleCommentList(sickCircleId, page, count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<PatientCircleCommentListEntity>() {
                    @Override
                    public void accept(PatientCircleCommentListEntity commentListEntity) throws Exception {
                        callBackObj.success(commentListEntity);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * 采纳
     * @param sickCircleId
     * @param commentId
     * @param callBackObj
     */
    @Override
    public void doGetAdoptionProposal(String sickCircleId, String commentId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).adoptionProposal(sickCircleId,commentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody commentListEntity) throws Exception {
                        callBackObj.success(commentListEntity.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * 点赞观点
     * @param opinion
     * @param commentId
     * @param callBackObj
     */
    @Override
    public void doGetExpressOpinion(String opinion, String commentId, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).expressOpinion(opinion,commentId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody commentListEntity) throws Exception {
                        callBackObj.success(commentListEntity.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBackObj.success("失败");
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public void doGetPatientcomment(String sickCircleId, String content, final CallBackObj callBackObj) {
        HttpUntils.getInstance().getcreate(ApiService.class).PatientPublishComment(sickCircleId,content)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody commentListEntity) throws Exception {
                        callBackObj.success(commentListEntity.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBackObj.success("失败");
                        throwable.printStackTrace();
                    }
                });
    }
}
