package com.example.patientscircle.activity;

/*
 *@Auther:陈浩
 *@Date: 2019/8/18
 *@Time:19:28
 *@Description:${DESCRIPTION}
 * */public class PatientDetailsPresenterImpl extends IPatientDetailsContract.IPatientDetailsPresenter {
    @Override
    public void requestPatientDetails(String sickCircleId) {
        model.doGetPatientDetails(sickCircleId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showPatientDetails(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestPaddSickCollection(String sickCircleId) {
        model.doGetPaddSickCollection(sickCircleId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showPaddSickCollection(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestCancelSickCollection(String sickCircleId) {
        model.doGetCancelSickCollection(sickCircleId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showCancelSickCollection(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }
    //用户评论
    @Override
    public void doGetPatientcomment(String sickCircleId, String content) {
        model.doGetPatientcomment(sickCircleId, content, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showPatientcomment(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    /**
     * 采纳
     *
     * @param sickCircleId
     * @param commentId
     */
    @Override
    public void requestAdoptionProposal(String sickCircleId, String commentId) {
        model.doGetAdoptionProposal(sickCircleId, commentId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showAdoptionProposal(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    /**
     * 发布观点
     *
     * @param opinion
     * @param commentId
     */
    @Override
    public void requestExpressOpinion(String opinion, String commentId) {
        model.doGetExpressOpinion(opinion, commentId, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showExpressOpinion(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }

    @Override
    public void requestPatientCircleCommentList(String sickCircleId, String page, String count) {
        model.doGetPatientCircleCommentList(sickCircleId, page, count, new CallBackObj() {
            @Override
            public void success(Object obj) {
                view.showPatientCircleCommentList(obj);
            }

            @Override
            public void err(String msg) {

            }
        });
    }
}
