package com.lb.entity;
import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2020-03-25
*/
@Data
@Table(name="lb_patient")
public class LbPatient {
	
	// alias
	public static final String ALIAS_id = "id";
	public static final String ALIAS_age = "age";
	public static final String ALIAS_appointment_id = "appointment_id";
	public static final String ALIAS_hospitalization_id = "hospitalization_id";
	public static final String ALIAS_isout = "isout";
	public static final String ALIAS_sex = "sex";
	public static final String ALIAS_user_id = "user_id";
	public static final String ALIAS_address = "address";
	public static final String ALIAS_cert_id = "cert_id";
	public static final String ALIAS_drugsids = "drugsids";
	public static final String ALIAS_name = "name";
	
	/*
	主键
	*/
	private Integer id ;
	/*
	年龄
	*/
	private Integer age ;
	/*
	预约信息
	*/
	private Integer appointmentId ;
	/*
	住院信息
	*/
	private Integer hospitalizationId ;
	/*
	是否出院
	*/
	private Integer isout ;
	/*
	性别
	*/
	private Integer sex ;
	/*
	登录
	*/
	private Integer userId ;
	/*
	家庭住址
	*/
	private String address ;
	/*
	身份证
	*/
	private String certId ;
	/*
	药品信息
	*/
	private String drugsids ;
	/*
	姓名
	*/
	private String name ;

	//映射医生的名称
	private String doctorName ;
}
