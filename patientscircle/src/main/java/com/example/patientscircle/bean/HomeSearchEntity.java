package com.example.patientscircle.bean;

import java.util.List;

/**
 * Create by ysn
 * TIME: 2019/9/4
 * Doing:
 */
public class HomeSearchEntity {
    /**
     * result : {"diseaseSearchVoList":[{"diseaseId":116,"diseaseName":"小儿肾炎"},{"diseaseId":117,"diseaseName":"小儿遗尿症"},{"diseaseId":118,"diseaseName":"小儿夜哭"},{"diseaseId":130,"diseaseName":"小儿麻痹症"},{"diseaseId":192,"diseaseName":"肾小球肾炎"}],"doctorSearchVoList":[{"doctorId":12,"doctorName":"陈小"},{"doctorId":13,"doctorName":"马小瑞"},{"doctorId":26,"doctorName":"陈小胖"},{"doctorId":67,"doctorName":"李小龙"}],"drugsSearchVoList":[{"drugsId":17,"drugsName":" [小葵花]金银花露 "},{"drugsId":49,"drugsName":" [鲁南]小儿消积止咳口服液 "},{"drugsId":50,"drugsName":" [仁和]小儿七星茶颗粒 "},{"drugsId":58,"drugsName":" [仁和]小儿七星茶糖浆 "},{"drugsId":59,"drugsName":" [小施尔康]小儿多维生素咀嚼片(10) "}]}
     * message : 搜索成功
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
        private List<DiseaseSearchVoListBean> diseaseSearchVoList;
        private List<DoctorSearchVoListBean> doctorSearchVoList;
        private List<DrugsSearchVoListBean> drugsSearchVoList;

        public List<DiseaseSearchVoListBean> getDiseaseSearchVoList() {
            return diseaseSearchVoList;
        }

        public void setDiseaseSearchVoList(List<DiseaseSearchVoListBean> diseaseSearchVoList) {
            this.diseaseSearchVoList = diseaseSearchVoList;
        }

        public List<DoctorSearchVoListBean> getDoctorSearchVoList() {
            return doctorSearchVoList;
        }

        public void setDoctorSearchVoList(List<DoctorSearchVoListBean> doctorSearchVoList) {
            this.doctorSearchVoList = doctorSearchVoList;
        }

        public List<DrugsSearchVoListBean> getDrugsSearchVoList() {
            return drugsSearchVoList;
        }

        public void setDrugsSearchVoList(List<DrugsSearchVoListBean> drugsSearchVoList) {
            this.drugsSearchVoList = drugsSearchVoList;
        }

        public static class DiseaseSearchVoListBean {
            /**
             * diseaseId : 116
             * diseaseName : 小儿肾炎
             */

            private int diseaseId;
            private String diseaseName;

            public int getDiseaseId() {
                return diseaseId;
            }

            public void setDiseaseId(int diseaseId) {
                this.diseaseId = diseaseId;
            }

            public String getDiseaseName() {
                return diseaseName;
            }

            public void setDiseaseName(String diseaseName) {
                this.diseaseName = diseaseName;
            }
        }

        public static class DoctorSearchVoListBean {
            /**
             * doctorId : 12
             * doctorName : 陈小
             */

            private int doctorId;
            private String doctorName;

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
        }

        public static class DrugsSearchVoListBean {
            /**
             * drugsId : 17
             * drugsName :  [小葵花]金银花露
             */

            private int drugsId;
            private String drugsName;

            public int getDrugsId() {
                return drugsId;
            }

            public void setDrugsId(int drugsId) {
                this.drugsId = drugsId;
            }

            public String getDrugsName() {
                return drugsName;
            }

            public void setDrugsName(String drugsName) {
                this.drugsName = drugsName;
            }
        }
    }
}
