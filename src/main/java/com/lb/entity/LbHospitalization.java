package com.lb.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/* 
* 
* gen by beetlsql 2020-03-25
*/
@Data
@Table(name="lb_hospitalization")
public class LbHospitalization   {
	
	// alias
	public static final String ALIAS_id = "id";
	public static final String ALIAS_patient_id = "patient_id";
	public static final String ALIAS_bed = "bed";
	public static final String ALIAS_door = "door";
	public static final String ALIAS_floor = "floor";
	public static final String ALIAS_medical_name = "medical_name";
	public static final String ALIAS_intime = "intime";
	public static final String ALIAS_outtime = "outtime";
	
	/*
	主键
	*/
	@Excel(name = "序号", orderNum = "0")
	private Integer id ;
	/*
	患者id
	*/
	private Integer patientId ;
	/*
	楼层
	*/
	@Excel(name = "楼层",orderNum = "1")
	private String floor ;
	/*
	房间号
	*/
	@Excel(name = "房间号",orderNum = "2")
	private String door ;
	/*
	床号
	*/
	@Excel(name = "床号",orderNum = "3")
	private String bed ;
	/*
	病名称
	*/
	@Excel(name = "病名称",orderNum = "4")
	private String medicalName ;
	/*
	住院时间
	*/
	@Excel(name = "住院时间",format = "yyyy-MM-dd",orderNum = "6")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date intime ;
	/*
	出院时间
	*/
	@Excel(name = "出院时间",format = "yyyy-MM-dd",orderNum = "7")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date outtime ;

	/*
	患者姓名
	 */
	@Excel(name = "患者姓名",orderNum = "5")
	private String patientName;
	
	public LbHospitalization() {
	}
}
