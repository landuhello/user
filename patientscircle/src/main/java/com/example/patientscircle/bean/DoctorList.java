package com.example.patientscircle.bean;

import java.util.List;

/*
 *@Auther:陈浩
 *@Date: 2019/8/9
 *@Time:16:58
 *@Description:${医生列表}
 * */public class DoctorList {
    /**
     * result : [{"badNum":1,"doctorId":36,"doctorName":"杜亚星","imagePic":"http://172.17.8.100/images/health/doctor/image_pic/2019-08-09/epHM8B20190809152536.jpg","inauguralHospital":"清华大学附属医院","jobTitle":"主任","praise":"50.00%","praiseNum":5,"serverNum":59,"servicePrice":500},{"badNum":0,"doctorId":50,"doctorName":"樊腾亚","imagePic":"http://172.17.8.100/images/health/doctor/image_pic/2019-08-09/xeZ5uA20190809144024.png","inauguralHospital":"北京八维医院","jobTitle":"主任医师3","praise":"0.00%","praiseNum":0,"serverNum":37,"servicePrice":500},{"badNum":1,"doctorId":25,"doctorName":"陈焮","imagePic":"http://172.17.8.100/images/health/doctor/image_pic/2019-08-09/Ga3Ode20190809100121.png","inauguralHospital":"北京第一军事医院","jobTitle":"主任医师","praise":"50.00%","praiseNum":10,"serverNum":130,"servicePrice":500},{"badNum":0,"doctorId":73,"doctorName":"张志宇","inauguralHospital":"清华大学附属医院","jobTitle":"主任","praise":"0.00%","praiseNum":0,"serverNum":0,"servicePrice":500},{"badNum":0,"doctorId":66,"doctorName":"张志宇","inauguralHospital":"清华大学附属医院","jobTitle":"主任","praise":"0.00%","praiseNum":0,"serverNum":1,"servicePrice":500}]
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
         * badNum : 1
         * doctorId : 36
         * doctorName : 杜亚星
         * imagePic : http://172.17.8.100/images/health/doctor/image_pic/2019-08-09/epHM8B20190809152536.jpg
         * inauguralHospital : 清华大学附属医院
         * jobTitle : 主任
         * praise : 50.00%
         * praiseNum : 5
         * serverNum : 59
         * servicePrice : 500
         */

        private int badNum;
        private int doctorId;
        private String doctorName;
        private String imagePic;
        private String inauguralHospital;
        private String jobTitle;
        private String praise;
        private int praiseNum;
        private int serverNum;
        private int servicePrice;
        private Boolean ischeked;



        public int getBadNum() {
            return badNum;
        }

        public void setBadNum(int badNum) {
            this.badNum = badNum;
        }

        public int getDoctorId() {
            return doctorId;
        }

        public void setDoctorId(int doctorId) {
            this.doctorId = doctorId;
        }

        public String getDoctorName() {
            return doctorName;
        }

        public void setDoctorName(String doctorName) {
            this.doctorName = doctorName;
        }

        public String getImagePic() {
            return imagePic;
        }

        public void setImagePic(String imagePic) {
            this.imagePic = imagePic;
        }

        public String getInauguralHospital() {
            return inauguralHospital;
        }

        public void setInauguralHospital(String inauguralHospital) {
            this.inauguralHospital = inauguralHospital;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public int getServerNum() {
            return serverNum;
        }

        public void setServerNum(int serverNum) {
            this.serverNum = serverNum;
        }

        public int getServicePrice() {
            return servicePrice;
        }

        public void setServicePrice(int servicePrice) {
            this.servicePrice = servicePrice;
        }

        public Boolean getIscheked() {
            return ischeked;
        }

        public void setIscheked(Boolean ischeked) {
            this.ischeked = ischeked;
        }
    }
}
