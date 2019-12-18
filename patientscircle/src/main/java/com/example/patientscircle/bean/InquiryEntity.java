package com.example.patientscircle.bean;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public class InquiryEntity {
    /**
     * result : {"department":"小儿科","departmentId":5,"doctorId":74,"doctorName":"高宇孟","evaluateStatus":1,"inquiryTime":1565788293000,"jiGuangPwd":"Fu4RWyH2E/+DbKsD0JUIolIuVxKZI9ZxeHbP0gaGQmHAgmjoNBLYcJ4njX/c0DMIFeS1d+LL1OMFV9BsX316fCP9tFlPYZmhZwV2a0vfadP0dBm/030mEkop9XMC0OWe8/sL8j5GesmllmXNPTuuKReTyG4J+BoPdANdsSyxxu8=","jobTitle":"主任","recordId":2096,"userName":"nHbamZ15214565560"}
     * message : 查询成功
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
         * department : 小儿科
         * departmentId : 5
         * doctorId : 74
         * doctorName : 高宇孟
         * evaluateStatus : 1
         * inquiryTime : 1565788293000
         * jiGuangPwd : Fu4RWyH2E/+DbKsD0JUIolIuVxKZI9ZxeHbP0gaGQmHAgmjoNBLYcJ4njX/c0DMIFeS1d+LL1OMFV9BsX316fCP9tFlPYZmhZwV2a0vfadP0dBm/030mEkop9XMC0OWe8/sL8j5GesmllmXNPTuuKReTyG4J+BoPdANdsSyxxu8=
         * jobTitle : 主任
         * recordId : 2096
         * userName : nHbamZ15214565560
         */

        private String department;
        private int departmentId;
        private int doctorId;
        private String doctorName;
        private int evaluateStatus;
        private long inquiryTime;
        private String jiGuangPwd;
        private String jobTitle;
        private int recordId;
        private String userName;
        private String imagePic;

        public String getImagePic() {
            return imagePic;
        }

        public void setImagePic(String imagePic) {
            this.imagePic = imagePic;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public int getDepartmentId() {
            return departmentId;
        }

        public void setDepartmentId(int departmentId) {
            this.departmentId = departmentId;
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

        public int getEvaluateStatus() {
            return evaluateStatus;
        }

        public void setEvaluateStatus(int evaluateStatus) {
            this.evaluateStatus = evaluateStatus;
        }

        public long getInquiryTime() {
            return inquiryTime;
        }

        public void setInquiryTime(long inquiryTime) {
            this.inquiryTime = inquiryTime;
        }

        public String getJiGuangPwd() {
            return jiGuangPwd;
        }

        public void setJiGuangPwd(String jiGuangPwd) {
            this.jiGuangPwd = jiGuangPwd;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "department='" + department + '\'' +
                    ", departmentId=" + departmentId +
                    ", doctorId=" + doctorId +
                    ", doctorName='" + doctorName + '\'' +
                    ", evaluateStatus=" + evaluateStatus +
                    ", inquiryTime=" + inquiryTime +
                    ", jiGuangPwd='" + jiGuangPwd + '\'' +
                    ", jobTitle='" + jobTitle + '\'' +
                    ", recordId=" + recordId +
                    ", userName='" + userName + '\'' +
                    ", imagePic='" + imagePic + '\'' +
                    '}';
        }
    }
}
