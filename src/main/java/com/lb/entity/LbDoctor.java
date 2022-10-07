package com.lb.entity;
import java.math.*;
import java.util.Date;
import java.sql.Timestamp;

import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;


/* 
* 
* gen by beetlsql 2020-03-25
*/
@Data
@Table(name="lb_doctor")
public class LbDoctor   {
	
	// alias
	public static final String ALIAS_id = "id";
	public static final String ALIAS_age = "age";
	public static final String ALIAS_expert = "expert";
	public static final String ALIAS_sex = "sex";
	public static final String ALIAS_user_id = "user_id";
	public static final String ALIAS_address = "address";
	public static final String ALIAS_cert_id = "cert_id";
	public static final String ALIAS_department = "department";
	public static final String ALIAS_name = "name";
	public static final String ALIAS_text = "text";
	
	/*
	主键
	*/
	private Integer id ;
	/*
	年龄
	*/
	private Integer age ;
	private Integer expert ;
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
	部门
	*/
	private String department ;
	/*
	姓名
	*/
	private String name ;
	/*
	介绍
	*/
	private String text ;
}
