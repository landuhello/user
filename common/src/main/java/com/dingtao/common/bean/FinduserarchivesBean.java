package com.dingtao.common.bean;

/**
 * data:档案
 * author:老刘（）
 * function:这是干什么的
 */
public class FinduserarchivesBean {
    /**
     * result : {"diseaseBefore":"不知道","diseaseMain":"感冒","diseaseNow":"没有","id":597,"picture":"http://172.17.8.100/images/health/user/archives/2019-12-13/maCXa420191213220309.jpg,http://172.17.8.100/images/health/user/archives/2019-12-13/S0wLqC20191213220309.jpg","treatmentEndTime":1576166400000,"treatmentHospitalRecent":"不知道","treatmentProcess":"","treatmentStartTime":1576166400000,"userId":403}
     * message : 用户档案查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * diseaseBefore : 不知道
         * diseaseMain : 感冒
         * diseaseNow : 没有
         * id : 597
         * picture : http://172.17.8.100/images/health/user/archives/2019-12-13/maCXa420191213220309.jpg,http://172.17.8.100/images/health/user/archives/2019-12-13/S0wLqC20191213220309.jpg
         * treatmentEndTime : 1576166400000
         * treatmentHospitalRecent : 不知道
         * treatmentProcess :
         * treatmentStartTime : 1576166400000
         * userId : 403
         */

        private String diseaseBefore;
        private String diseaseMain;
        private String diseaseNow;
        private int id;
        private String picture;
        private long treatmentEndTime;
        private String treatmentHospitalRecent;
        private String treatmentProcess;
        private long treatmentStartTime;
        private int userId;

        public String getDiseaseBefore() {
            return diseaseBefore;
        }

        public void setDiseaseBefore(String diseaseBefore) {
            this.diseaseBefore = diseaseBefore;
        }

        public String getDiseaseMain() {
            return diseaseMain;
        }

        public void setDiseaseMain(String diseaseMain) {
            this.diseaseMain = diseaseMain;
        }

        public String getDiseaseNow() {
            return diseaseNow;
        }

        public void setDiseaseNow(String diseaseNow) {
            this.diseaseNow = diseaseNow;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public long getTreatmentEndTime() {
            return treatmentEndTime;
        }

        public void setTreatmentEndTime(long treatmentEndTime) {
            this.treatmentEndTime = treatmentEndTime;
        }

        public String getTreatmentHospitalRecent() {
            return treatmentHospitalRecent;
        }

        public void setTreatmentHospitalRecent(String treatmentHospitalRecent) {
            this.treatmentHospitalRecent = treatmentHospitalRecent;
        }

        public String getTreatmentProcess() {
            return treatmentProcess;
        }

        public void setTreatmentProcess(String treatmentProcess) {
            this.treatmentProcess = treatmentProcess;
        }

        public long getTreatmentStartTime() {
            return treatmentStartTime;
        }

        public void setTreatmentStartTime(long treatmentStartTime) {
            this.treatmentStartTime = treatmentStartTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
