package com.example.personaldetails.activity.bean;

/*
 *@Auther:王泽洋
 *@Date: 时间
 *@Description:功能
 * */
public class AddArchives {
    /**
     * diseaseMain : 头疼
     * diseaseNow : 无
     * diseaseBefore : 无
     * treatmentHospitalRecent : 人民医院
     * treatmentProcess : 每周一次
     * treatmentStartTime : 2019-4-1
     * treatmentEndTime : 2019-8-12
     */

    private String diseaseMain;
    private String diseaseNow;
    private String diseaseBefore;
    private String treatmentHospitalRecent;
    private String treatmentProcess;
    private String treatmentStartTime;
    private String treatmentEndTime;

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

    public String getDiseaseBefore() {
        return diseaseBefore;
    }

    public void setDiseaseBefore(String diseaseBefore) {
        this.diseaseBefore = diseaseBefore;
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

    public String getTreatmentStartTime() {
        return treatmentStartTime;
    }

    public void setTreatmentStartTime(String treatmentStartTime) {
        this.treatmentStartTime = treatmentStartTime;
    }

    public String getTreatmentEndTime() {
        return treatmentEndTime;
    }

    public void setTreatmentEndTime(String treatmentEndTime) {
        this.treatmentEndTime = treatmentEndTime;
    }
}
