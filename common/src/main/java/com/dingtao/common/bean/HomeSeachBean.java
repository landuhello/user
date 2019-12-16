package com.dingtao.common.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/12/7<p>
 * <p>更改时间：2019/12/7<p>
 */
public class HomeSeachBean {

    //病症
    public List<DiseaseSearchVoListBean> diseaseSearchVoList;
    //医生
    public List<DoctorSearchVoListBean> doctorSearchVoList;
    //药品
    public List<DrugsSearchVoListBean> drugsSearchVoList;

    /*public static class DiseaseSearchVoListBean {
     *//**
     * diseaseId : 123
     * diseaseName : 新生儿黄疸
     *//*

            public int diseaseId;
            public String diseaseName;
        }

        public static class DoctorSearchVoListBean {
            *//**
     * doctorId : 1
     * doctorName : 张医生
     *//*

            public int doctorId;
            public String doctorName;
        }

        public static class DrugsSearchVoListBean {
            *//**
     * drugsId : 59
     * drugsName :  [小施尔康]小儿多维生素咀嚼片(10)
     *//*

            public int drugsId;
            public String drugsName;
        }*/

}
