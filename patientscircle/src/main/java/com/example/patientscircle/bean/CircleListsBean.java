package com.example.patientscircle.bean;

import java.util.List;

/**
 * Create by ysn
 * TIME: 2019/8/14
 * Doing:  病友圈列表展示的Bean类
 */
public class CircleListsBean {
    /**
     * result : [{"amount":10,"collectionNum":0,"commentNum":0,"detail":"傻呵呵","releaseTime":1565712000000,"sickCircleId":1065,"title":"听哦疼"},{"amount":100,"collectionNum":0,"commentNum":1,"detail":"哦哦哦我的姐","releaseTime":1565625600000,"sickCircleId":1062,"title":"考虑考虑"},{"amount":10,"collectionNum":0,"commentNum":0,"detail":"ss","releaseTime":1565366400000,"sickCircleId":1060,"title":"sss"},{"amount":10,"collectionNum":0,"commentNum":0,"detail":"很喜欢睡觉诶","releaseTime":1565020800000,"sickCircleId":1027,"title":"教学就是说"},{"amount":10,"collectionNum":0,"commentNum":1,"detail":"阿萨德","releaseTime":1565020800000,"sickCircleId":1018,"title":"第飒飒打"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * amount : 10
         * collectionNum : 0
         * commentNum : 0
         * detail : 傻呵呵
         * releaseTime : 1565712000000
         * sickCircleId : 1065
         * title : 听哦疼
         */

        private int amount;
        private int collectionNum;
        private int commentNum;
        private String detail;
        private long releaseTime;
        private int sickCircleId;
        private String title;

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getSickCircleId() {
            return sickCircleId;
        }

        public void setSickCircleId(int sickCircleId) {
            this.sickCircleId = sickCircleId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }



}
