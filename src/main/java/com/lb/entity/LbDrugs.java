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
@Table(name="lb_drugs")
public class LbDrugs   {
	
	// alias
	public static final String ALIAS_id = "id";
	public static final String ALIAS_number = "number";
	public static final String ALIAS_type = "type";
	public static final String ALIAS_name = "name";
	public static final String ALIAS_price = "price";
	public static final String ALIAS_text = "text";
	
	/*
	主键
	*/
	private Integer id ;
	/*
	数量
	*/
	private Integer number ;
	/*
	药品类型
	*/
	private Integer type ;
	/*
	药品名称
	*/
	private String name ;
	/*
	单价
	*/
	private BigDecimal price ;
	/*
	介绍
	*/
	private String text ;
}
