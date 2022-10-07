package com.lb.entity;
import lombok.Data;
import org.beetl.sql.core.annotatoin.Table;

import java.util.Date;


/* 
* 
* gen by beetlsql 2020-03-25
*/
@Data
@Table(name="lb_medicalhistory")
public class LbMedicalHistory {
	
	// alias
	public static final String ALIAS_id = "id";
	public static final String ALIAS_doctor_id = "doctor_id";
	public static final String ALIAS_hospitalization_id = "hospitalization_id";
	public static final String ALIAS_patient_id = "patient_id";
	public static final String ALIAS_name = "name";
	public static final String ALIAS_time = "time";
	
	/*
	主键
	*/
	private Integer id ;
	/*
	确诊人
	*/
	private Integer doctorId ;
	/*
	住院信息
	*/
	private Integer hospitalizationId ;
	/*
	患者id
	*/
	private Integer patientId ;
	/*
	病史名称
	*/
	private String name ;
	/*
	患病时间
	*/
	private Date time ;

	/*
	患者姓名
	 */
	private String patientName;

	/*
	医生姓名
	 */
	private String doctorName;

}
