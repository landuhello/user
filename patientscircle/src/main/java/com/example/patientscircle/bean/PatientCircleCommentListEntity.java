package com.example.patientscircle.bean;

import java.util.List;

/*
 *@Auther:陈浩
 *@Date: 2019/8/19
 *@Time:10:00
 *@Description:${DESCRIPTION}
 * */public class PatientCircleCommentListEntity {

    /**
     * result : [{"commentId":287,"commentTime":1564130286000,"commentUserId":69,"content":"长安删除哈哈哈","headPic":"http://172.17.8.100/images/health/user/head_pic/2019-08-02/6m3KVI20190802105537.jpg","nickName":"0d_GFBTC","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":240,"commentTime":1564022882000,"commentUserId":69,"content":"爽肤水","headPic":"http://172.17.8.100/images/health/user/head_pic/2019-08-02/6m3KVI20190802105537.jpg","nickName":"0d_GFBTC","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":239,"commentTime":1564022871000,"commentUserId":69,"content":"阿斯蒂芬","headPic":"http://172.17.8.100/images/health/user/head_pic/2019-08-02/6m3KVI20190802105537.jpg","nickName":"0d_GFBTC","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":238,"commentTime":1564022852000,"commentUserId":69,"content":"胜多负少","headPic":"http://172.17.8.100/images/health/user/head_pic/2019-08-02/6m3KVI20190802105537.jpg","nickName":"0d_GFBTC","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2},{"commentId":237,"commentTime":1564022848000,"commentUserId":69,"content":"发送到","headPic":"http://172.17.8.100/images/health/user/head_pic/2019-08-02/6m3KVI20190802105537.jpg","nickName":"0d_GFBTC","opinion":0,"opposeNum":0,"supportNum":0,"whetherDoctor":2}]
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
         * commentId : 287
         * commentTime : 1564130286000
         * commentUserId : 69
         * content : 长安删除哈哈哈
         * headPic : http://172.17.8.100/images/health/user/head_pic/2019-08-02/6m3KVI20190802105537.jpg
         * nickName : 0d_GFBTC
         * opinion : 0
         * opposeNum : 0
         * supportNum : 0
         * whetherDoctor : 2
         */

        private int commentId;
        private long commentTime;
        private int commentUserId;
        private String content;
        private String headPic;
        private String nickName;
        private int opinion;
        private int opposeNum;
        private int supportNum;
        private int whetherDoctor;

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public int getCommentUserId() {
            return commentUserId;
        }

        public void setCommentUserId(int commentUserId) {
            this.commentUserId = commentUserId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getOpinion() {
            return opinion;
        }

        public void setOpinion(int opinion) {
            this.opinion = opinion;
        }

        public int getOpposeNum() {
            return opposeNum;
        }

        public void setOpposeNum(int opposeNum) {
            this.opposeNum = opposeNum;
        }

        public int getSupportNum() {
            return supportNum;
        }

        public void setSupportNum(int supportNum) {
            this.supportNum = supportNum;
        }

        public int getWhetherDoctor() {
            return whetherDoctor;
        }

        public void setWhetherDoctor(int whetherDoctor) {
            this.whetherDoctor = whetherDoctor;
        }
    }
}
