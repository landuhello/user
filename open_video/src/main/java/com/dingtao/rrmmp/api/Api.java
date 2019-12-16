package com.dingtao.rrmmp.api;

/**
 * data:${DATA}
 * author:刘(Administrator)
 * function
 */public class Api {

    public static final String BASEURL = "http://172.17.8.100/";
//     public static final String BASEURL = "http://mobile.bwstudent.com/";

    //首页
    public static final String HOME_BANNER = "health/share/v1/bannersShow";
    public static final String HOME_WZZX_LIST = "health/share/knowledgeBase/v1/findDepartment";
    //健康咨询标题
    public static final String HOME_Health_Consultation_Title = "health/share/information/v1/findInformationPlateList";
    ///健康咨询内容
    public static final String HOME_Health_Consultation_Content = "health/share/information/v1/findInformationList";
    public static final String Home_Consultation_details = "health/share/information/v1/findInformation";
    //科目医生列表
    public static final String home_doctor_list = "health/user/inquiry/v1/findDoctorList";
    //医生详细
    public static final String home_doctor_detail = "health/user/inquiry/v1/findDoctorInfo";
    //3. 咨询医生
    public static final String home_consultDoctor = "health/user/inquiry/verify/v1/consultDoctor";
    //关注医生
    public static final String home_followDoctor = "health/user/inquiry/verify/v1/followDoctor";
    //取消关注
    public static final String home_cancelFollow = "health/user/inquiry/verify/v1/cancelFollow";
    //结束问诊
    public static final String home_endInquiry = "health/user/inquiry/verify/v1/endInquiry";
    public static final String homewatchInfoRewards = "health/user/verify/v1/watchInfoRewards";


    /*
        用户接口
    */
    public static final String EMAIL = "health/user/v1/sendOutEmailCode";//发送邮箱
    public static final String REGISTER = "health/user/v1/register";//注册
    public static final String LOGIN = "health/user/v1/login";//登录
    public static final String USERINFOBYID = "health/user/verify/v1/getUserInfoById";//根据用户ID查询用户信息
    public static final String PERFECTUSERINFO = "health/user/verify/v1/perfectUserInfo";//完善用户信息
    public static final String Querywallet = "health/user/verify/v1/findUserWallet";//完善用户信息
    //档案类
    public static final String UPDATEARCHIVES = "health/user/verify/v1/updateUserArchives";//修改用户档案
    public static final String ARCHIVES = "health/user/verify/v1/addUserArchives";//添加用户档案
    public static final String ARCHIVESPICTURE = "health/user/verify/v1/uploadArchivesPicture";//上传用户档案图片
    public static final String USERARCHIVES = "health/user/verify/v1/findUserArchives";//查看用户档案
    public static final String DELARCHIVES = "health/user/verify/v1/deleteUserArchives";//删除档案
    //签到类
    public static final String SIGN = "health/user/verify/v1/addSign";//用户签到
    public static final String WHETHERSIGN = "health/user/verify/v1/whetherSignToday";//查询用户当天是否签到
    public static final String SIGNDAY = "health/user/verify/v1/findUserSign";//查询用户连续签到天数
    //修改
    public static final String HEADPIC = "health/user/verify/v1/modifyHeadPic";//上传头像
    public static final String SEX = "health/user/verify/v1/updateUserSex";//修改性别
    public static final String NICKNAME = "health/user/verify/v1/modifyNickName";//修改名称
    public static final String UPDATEPASSWODE = "health/user/verify/v1/updateUserPwd";//修改密码 ( 正常修改 )
    public static final String CHECKCODE = "health/user/v1/checkCode";//37.检验验证码
    public static final String RESETPASSWORDE = "health/user/v1/resetUserPwd";//38.重置用户密码（忘记密码用）
    //邀请码
    public static final String CODE = "health/user/verify/v1/makeInvitationCode";//生成邀请码
    public static final String FINDCODE = "health/user/verify/v1/findUserInvitationCode";//查询用户邀请码
    //推送
    public static final String JPUSHTOKEN = "health/user/verify/v1/addUserPushToken";//添加用户极光推送信息Token
    //任务
    public static final String TASKLIST = "health/user/verify/v1/findUserTaskList";//任务列表
    //咨询
    public static final String ADDCOLLECTION = "health/user/verify/v1/addInfoCollection";//收藏资讯
    public static final String COLLECTIONLIST = "health/user/verify/v1/findUserInfoCollectionList";//查询咨询列表
    public static final String DELCOLLECTION = "health/user/verify/v1/cancelInfoCollection";//取消收藏咨询
    //病友圈
    public static final String ADDSICK = "health/user/verify/v1/addUserSickCollection";//27.收藏病友圈
    public static final String Listofpatients = "health/user/sickCircle/v1/findSickCircleList";//28.病友圈列表
    public static final String DELSICK = "health/user/verify/v1/cancelSickCollection";//29.取消病友圈收藏
    public static final String COLLECTIONSICK="health/user/verify/v1/findUserSickCollectionList";
    //健康课堂视频
    public static final String DELVIDEO = "health/user/verify/v1/cancelVideoCollection";//31.用户取消视频收藏
    public static final String DELBUYVIDEO = "health/user/verify/v1/deleteVideoBuy";//34.删除购买健康课堂视频
    //医生
    public static final String DOCTORLIST = "health/user/verify/v1/findUserDoctorFollowList";//32. 查询用户关注医生列表
    public static final String QXDOCTOR="health/user/inquiry/verify/v1/cancelFollow";

    //钱包
    public static final String WALLET = "health/user/verify/v1/findUserWallet";//36.我的钱包

    //查询健康讲堂类目
    public static final String videotype = "health/user/video/v1/findVideoCategoryList";
    // 根据视频类目查询视频列表
    public static final String VideoList = "health/user/video/v1/findVideoVoList";
    //视频收藏
    public static final String Collectionvideo = "health/user/video/verify/v1/addUserVideoCollection";
    //弹幕
    public static final String Videobarrage = "health/user/video/verify/v1/addVideoComment";
    //视频取消收藏
    public static final String cancelVideoCollection = "health/user/verify/v1/cancelVideoCollection";
    //评论列表
    public static final String VideoCommentList = "health/user/video/v1/findVideoCommentList";
    public static final String Buy_video = "health/user/video/verify/v1/videoBuy";
    //查看当前问诊
    public static final String findCurrentInquiryRecord = "health/user/inquiry/verify/v1/findCurrentInquiryRecord";
    //结束问诊
    public static final String endInquiry = "health/user/inquiry/verify/v1/endInquiry";
    //查看历史问诊
    public static final String historyInquiry = "health/user/inquiry/verify/v1/findHistoryInquiryRecord";

    //  根据科室查询对应病症
    public static final String DiseaseList = "health/share/knowledgeBase/v1/findDiseaseCategory";
    public static final String Diseasedetail = "health/share/knowledgeBase/v1/findDiseaseKnowledge";
    //6.  药品科目分类列表查询
    public static final String DrugsCategoryType = "health/share/knowledgeBase/v1/findDrugsCategoryList";
    // 根据药品类目查询常见药品
    public static final String DrugsKnowledgeList = "health/share/knowledgeBase/v1/findDrugsKnowledgeList";
    public static final String DrugsKnowledgedetail = "health/share/knowledgeBase/v1/findDrugsKnowledge";

    //病友圈详细
    public static final String PatientCircleDetails = "health/user/sickCircle/v1/findSickCircleInfo";
    //搜索病友圈
    public static final String SearchCirclePatients = "health/user/sickCircle/v1/searchSickCircle";
   // 查询病友圈评论列表

    public static final String PatientCircleCommentList = "health/user/sickCircle/v1/findSickCircleCommentList";
   // 6. 采纳病友圈优秀的评论
    //
    public static final String adoptionProposal = "health/user/sickCircle/verify/v1/adoptionProposal";


    //7. 发表观点
    //接口地址：http://172.17.8.100/
    public static final String expressOpinion = "health/user/sickCircle/verify/v1/expressOpinion";
    //患者发表评论
    public static final String PatientPublishComment = "health/user/sickCircle/verify/v1/publishComment";

    //聊天记录
    public static final String findInquiryRecordList="health/user/inquiry/verify/v1/findInquiryRecordList";
    //评价
    public static final String evaluationInquiry="health/user/inquiry/verify/v1/evaluationInquiry";
    //送礼物
    public static final String handselGift="health/user/inquiry/verify/v1/handselGift";
    //礼物列表
    public static final String giftList="health/user/inquiry/v1/findGiftList";
    //评论详情
    public static final String doctorEvaluate="health/user/inquiry/verify/v1/findDoctorEvaluate";
    //查看消费计入
    public static final String RecordList="health/user/verify/v1/findUserConsumptionRecordList";
    //发布Sick Circle

    //找到病人生病圈列表
    public static final String findPatientSickCircleList = "health/user/sickCircle/v1/findPatientSickCircleList";
    //药品详细
    public static final String findDrugsKnowledge = "health/share/knowledgeBase/v1/findDrugsKnowledge";

    public static final String findDiseaseKnowledge = "health/share/knowledgeBase/v1/findDiseaseKnowledge";

    public static final String findVideo= "health/user/verify/v1/findUserVideoBuyList";


    public static final String publishSickCircle="health/user/sickCircle/verify/v1/publishSickCircle";
    public static final String uploadSickCirclePicture="health/user/sickCircle/verify/v1/uploadSickCirclePicture";
    //充值
    public static final String RECHARGE="health/user/verify/v1/recharge";
    //做任务
    public static final String DOTASK="health/user/verify/v1/doTask";
    public static final String RECEIVEREWARD="health/user/verify/v1/receiveReward";

    //视频收藏列表
    public static final String VideoCollectionList="health/user/verify/v1/findVideoCollectionList";
    //发送消息
    public static final String pushMessage="health/user/inquiry/verify/v1/pushMessage";
    //热门搜索
    public static final String popularSearch="health/share/v1/popularSearch";
    //首页搜索
    public static final String homePageSearch="health/share/v1/homePageSearch";


}
