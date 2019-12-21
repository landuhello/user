package com.dingtao.common.bean;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：张自磊<p>
 * <p>创建时间：2019/12/19<p>
 * <p>更改时间：2019/12/19<p>
 */
public class FindDoctorInfoBean {
        /**
         * badNum : 0
         * commentList : []
         * commentNum : 0
         * doctorId : 162
         * doctorName : 呆志3
         * doctorReceiveGiftList : []
         * followFlag : 2
         * goodField : 小儿科soeasy
         * imagePic : http://172.17.8.100/images/health/doctor/system_image_pic/system_image2.jpg
         * inauguralHospital : 清华大学附属医院
         * jobTitle : 主任
         * personalProfile : 在世华佗
         * praise : 0.00%
         * praiseNum : 0
         * serverNum : 0
         * servicePrice : 500
         */


        public int badNum;
        public int commentNum;
        public int doctorId;
        public String doctorName;
        public int followFlag;
        public String goodField;
        public String imagePic;
        public String inauguralHospital;
        public String jobTitle;
        public String personalProfile;
        public String praise;
        public int praiseNum;
        public int serverNum;
        public int servicePrice;
        public List<DoctorComment> commentList;
        public List<DoctorReceiveGift> doctorReceiveGiftList;
}
