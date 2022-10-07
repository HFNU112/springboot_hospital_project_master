package com.lb.common;

public class Global {
    public static String DEL_CODE_SUCCESS = "401";
    public static String DEL_MSG_SUCCESS = "删除成功";
    public static String DEL_CODE_ERROR = "402";
    public static String DEL_MSG_ERROR = "删除失败";

    public static String SAVE_CODE_SUCCESS="302";
    public static String SAVE_MSG_SUCCESS="保存成功";
    public static String SAVE_CODE_ERROR="301";
    public static String SAVE_MSG_ERROR="该身份证已被注册或使用！";
    public static String SAVE_MSG_DRUGS_ERROR="该药品已存在！";
    public static String SAVE_APPOINTMENT_SUCCESS="预约成功！";

    public static String APPOINTMENT_CODE_SUCCESS="501"; //挂号单生成成功code值
    public static String SEEK_CODE_SUCCESS="601"; //就诊单打印code
    public static String SEEK_MSG_SUCCESS="就诊单打印成功"; //就诊单打印code
    public static String SEEK_MSG_EXIST="就诊记录已经存在";


    //就诊状态码
    public static String SEEK_CODE_NONE = "1";
    public static String SEEK_CODE_PROCESSING = "2";
    public static String SEEK_CODE_DONE = "3";


}
