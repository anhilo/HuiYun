package com.kuanguang.huiyun.http;

/**
 * 接口url
 * Created by Roy on 2016/12/11.
 */

public class HttpUrls {
    /** DEBUG 模式（true打开/false为关闭） */
    public static final boolean DEBUG = true;

    public static final String BASE_URL = "http://api.jiurunjiaoyu.com/";//

    /** 发送短信验证码 */
    public static final String SENDLOGIN = BASE_URL + "api/SmsApi/SendLogin?";
    /** 用户注册登陆 */
    public static final String LOGIN = BASE_URL + "api/UserApi/Login";
    /** 用户资料修改 */
    public static final String UPDATEUSERINFO = BASE_URL + "api/UserApi/UpdateUserInfo";
    /** 修改用户图像 */
    public static final String UPDATEUSERHEAD = BASE_URL + "api/UserApi/UpdateUserHead";
    /** 查询咨询列表 */
    public static final String GETNEWSLIST = BASE_URL + "api/NewsApi/GetNewsList";
    /** 讲师列表 */
    public static final String GETLECTURERLIST = BASE_URL + "api/LecturerApi/GetLecturerList";
    /** 获取题目列表 */
    public static final String GETPROBLEMLIST = BASE_URL + "api/ProblemApi/GetProblemList";
    /** 添加收藏 */
    public static final String ADDPROBLEMCOLLECT = BASE_URL + "api/ProblemCollectApi/AddProblemCollect";
    /** 取消收藏 */
    public static final String DELPROBLEMCOLLECT = BASE_URL + "api/ProblemCollectApi/DelProblemCollect";
    /** 轮播图 */
    public static final String GETADVERTISEMENTLIST = BASE_URL + "api/AdvertisementApi/GetAdvertisementList";
    /** 获取章节列表 */
    public static final String GETCHAPTERLIST = BASE_URL + "api/ProblemApi/GetchapterList";
    /** 收藏列表 */
    public static final String GETMYPROBLEMCOLLECTLIST = BASE_URL + "api/ProblemCollectApi/GetMyProblemCollectList";
    /** 消息列表 */
    public static final String GETNOTIFICATIONLIST = BASE_URL + "api/NotificationApi/GetNotificationList";
    /** 问答列表 */
    public static final String GETQUESTIONLIST = BASE_URL + "api/QuestionApi/GetQuestionList";
    /** 检测版本更新 */
    public static final String GETVERSIONTABLELIST = BASE_URL + "api/VersionTableApi/GetVersionTableList";
    /** 新增提问 */
    public static final String ADDQUESTION = BASE_URL + "api/QuestionApi/AddQuestion";
    /** 新增回复 */
    public static final String ADDREPLY = BASE_URL + "api/QuestionApi/AddReply";
    /** 获取单个问题的详细 */
    public static final String GETQUESTION = BASE_URL + "api/QuestionApi/GetQuestion";
    /** 根据级别获取必刷的题目 */
    public static final String GETINTENSIVE = BASE_URL + "api/ProblemApi/GetIntensive";
    /** 把未读消息变成已读消息 */
    public static final String READNOTIFICATION = BASE_URL + "api/NotificationApi/ReadNotification";
    /** 根据UserInfoId得到用户信息 */
    public static final String GETUSERINFO = BASE_URL + "api/UserApi/GetUserInfo";
    /** 根据分类id获取科目列表 */
    public static final String GETSUBJECTINFOLIST = BASE_URL + "api/ProblemApi/GetSubjectInfoList";
    /** 添加答题记录 */
    public static final String ADDUSERINFOANSWERRECORD = BASE_URL + "api/ProblemApi/AddUserInfoAnswerRecord";
    /** 获取视频分类 */
    public static final String GETVIDEOCLASSLIST = BASE_URL + "api/VideoApi/GetVideoClassList";
    /** 根据分类获取视频 */
    public static final String GEVIDEOLIST = BASE_URL + "api/VideoApi/GeVideoList";
}
